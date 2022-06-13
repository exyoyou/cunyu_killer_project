package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.CompositeMediaSource;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MaskingMediaPeriod;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.source.ads.AdsLoader;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.ui.AdViewProvider;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public final class AdsMediaSource extends CompositeMediaSource<MediaSource.MediaPeriodId> {
    private static final MediaSource.MediaPeriodId CHILD_SOURCE_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());
    private final MediaSourceFactory adMediaSourceFactory;
    private AdPlaybackState adPlaybackState;
    private final DataSpec adTagDataSpec;
    private final AdViewProvider adViewProvider;
    private final Object adsId;
    private final AdsLoader adsLoader;
    private ComponentListener componentListener;
    private final MediaSource contentMediaSource;
    private Timeline contentTimeline;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Timeline.Period period = new Timeline.Period();
    private AdMediaSourceHolder[][] adMediaSourceHolders = new AdMediaSourceHolder[0];

    /* loaded from: classes2.dex */
    public static final class AdLoadException extends IOException {
        public static final int TYPE_AD = 0;
        public static final int TYPE_AD_GROUP = 1;
        public static final int TYPE_ALL_ADS = 2;
        public static final int TYPE_UNEXPECTED = 3;
        public final int type;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface Type {
        }

        public static AdLoadException createForAd(Exception exc) {
            return new AdLoadException(0, exc);
        }

        public static AdLoadException createForAdGroup(Exception exc, int i) {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Failed to load ad group ");
            sb.append(i);
            return new AdLoadException(1, new IOException(sb.toString(), exc));
        }

        public static AdLoadException createForAllAds(Exception exc) {
            return new AdLoadException(2, exc);
        }

        public static AdLoadException createForUnexpected(RuntimeException runtimeException) {
            return new AdLoadException(3, runtimeException);
        }

        private AdLoadException(int i, Exception exc) {
            super(exc);
            this.type = i;
        }

        public RuntimeException getRuntimeExceptionForUnexpected() {
            Assertions.checkState(this.type == 3);
            return (RuntimeException) Assertions.checkNotNull(getCause());
        }
    }

    public AdsMediaSource(MediaSource mediaSource, DataSpec dataSpec, Object obj, MediaSourceFactory mediaSourceFactory, AdsLoader adsLoader, AdViewProvider adViewProvider) {
        this.contentMediaSource = mediaSource;
        this.adMediaSourceFactory = mediaSourceFactory;
        this.adsLoader = adsLoader;
        this.adViewProvider = adViewProvider;
        this.adTagDataSpec = dataSpec;
        this.adsId = obj;
        adsLoader.setSupportedContentTypes(mediaSourceFactory.getSupportedTypes());
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.contentMediaSource.getMediaItem();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        ComponentListener componentListener = new ComponentListener();
        this.componentListener = componentListener;
        prepareChildSource(CHILD_SOURCE_MEDIA_PERIOD_ID, this.contentMediaSource);
        this.mainHandler.post(new Runnable(componentListener) { // from class: com.google.android.exoplayer2.source.ads.-$$Lambda$AdsMediaSource$zcXBZahV9F-k_KJACPO-bl_WWX0
            private final /* synthetic */ AdsMediaSource.ComponentListener f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AdsMediaSource.this.lambda$prepareSourceInternal$0$AdsMediaSource(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$prepareSourceInternal$0$AdsMediaSource(ComponentListener componentListener) {
        this.adsLoader.start(this, this.adTagDataSpec, this.adsId, this.adViewProvider, componentListener);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        if (((AdPlaybackState) Assertions.checkNotNull(this.adPlaybackState)).adGroupCount <= 0 || !mediaPeriodId.isAd()) {
            MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j);
            maskingMediaPeriod.setMediaSource(this.contentMediaSource);
            maskingMediaPeriod.createPeriod(mediaPeriodId);
            return maskingMediaPeriod;
        }
        int i = mediaPeriodId.adGroupIndex;
        int i2 = mediaPeriodId.adIndexInAdGroup;
        AdMediaSourceHolder[][] adMediaSourceHolderArr = this.adMediaSourceHolders;
        if (adMediaSourceHolderArr[i].length <= i2) {
            adMediaSourceHolderArr[i] = (AdMediaSourceHolder[]) Arrays.copyOf(adMediaSourceHolderArr[i], i2 + 1);
        }
        AdMediaSourceHolder adMediaSourceHolder = this.adMediaSourceHolders[i][i2];
        if (adMediaSourceHolder == null) {
            adMediaSourceHolder = new AdMediaSourceHolder(mediaPeriodId);
            this.adMediaSourceHolders[i][i2] = adMediaSourceHolder;
            maybeUpdateAdMediaSources();
        }
        return adMediaSourceHolder.createMediaPeriod(mediaPeriodId, allocator, j);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MaskingMediaPeriod maskingMediaPeriod = (MaskingMediaPeriod) mediaPeriod;
        MediaSource.MediaPeriodId mediaPeriodId = maskingMediaPeriod.id;
        if (mediaPeriodId.isAd()) {
            AdMediaSourceHolder adMediaSourceHolder = (AdMediaSourceHolder) Assertions.checkNotNull(this.adMediaSourceHolders[mediaPeriodId.adGroupIndex][mediaPeriodId.adIndexInAdGroup]);
            adMediaSourceHolder.releaseMediaPeriod(maskingMediaPeriod);
            if (adMediaSourceHolder.isInactive()) {
                adMediaSourceHolder.release();
                this.adMediaSourceHolders[mediaPeriodId.adGroupIndex][mediaPeriodId.adIndexInAdGroup] = null;
                return;
            }
            return;
        }
        maskingMediaPeriod.releasePeriod();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        ComponentListener componentListener = (ComponentListener) Assertions.checkNotNull(this.componentListener);
        this.componentListener = null;
        componentListener.stop();
        this.contentTimeline = null;
        this.adPlaybackState = null;
        this.adMediaSourceHolders = new AdMediaSourceHolder[0];
        this.mainHandler.post(new Runnable(componentListener) { // from class: com.google.android.exoplayer2.source.ads.-$$Lambda$AdsMediaSource$4aMSO39zZ_2Ojc5az8RW_qLlkmQ
            private final /* synthetic */ AdsMediaSource.ComponentListener f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AdsMediaSource.this.lambda$releaseSourceInternal$1$AdsMediaSource(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$releaseSourceInternal$1$AdsMediaSource(ComponentListener componentListener) {
        this.adsLoader.stop(this, componentListener);
    }

    public void onChildSourceInfoRefreshed(MediaSource.MediaPeriodId mediaPeriodId, MediaSource mediaSource, Timeline timeline) {
        if (mediaPeriodId.isAd()) {
            int i = mediaPeriodId.adGroupIndex;
            ((AdMediaSourceHolder) Assertions.checkNotNull(this.adMediaSourceHolders[i][mediaPeriodId.adIndexInAdGroup])).handleSourceInfoRefresh(timeline);
        } else {
            boolean z = true;
            if (timeline.getPeriodCount() != 1) {
                z = false;
            }
            Assertions.checkArgument(z);
            this.contentTimeline = timeline;
        }
        maybeUpdateSourceInfo();
    }

    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId, MediaSource.MediaPeriodId mediaPeriodId2) {
        return mediaPeriodId.isAd() ? mediaPeriodId : mediaPeriodId2;
    }

    public void onAdPlaybackState(AdPlaybackState adPlaybackState) {
        boolean z = false;
        if (this.adPlaybackState == null) {
            this.adMediaSourceHolders = new AdMediaSourceHolder[adPlaybackState.adGroupCount];
            Arrays.fill(this.adMediaSourceHolders, new AdMediaSourceHolder[0]);
        } else {
            if (adPlaybackState.adGroupCount == this.adPlaybackState.adGroupCount) {
                z = true;
            }
            Assertions.checkState(z);
        }
        this.adPlaybackState = adPlaybackState;
        maybeUpdateAdMediaSources();
        maybeUpdateSourceInfo();
    }

    private void maybeUpdateAdMediaSources() {
        Uri uri;
        AdPlaybackState adPlaybackState = this.adPlaybackState;
        if (adPlaybackState != null) {
            for (int i = 0; i < this.adMediaSourceHolders.length; i++) {
                int i2 = 0;
                while (true) {
                    AdMediaSourceHolder[][] adMediaSourceHolderArr = this.adMediaSourceHolders;
                    if (i2 < adMediaSourceHolderArr[i].length) {
                        AdMediaSourceHolder adMediaSourceHolder = adMediaSourceHolderArr[i][i2];
                        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i);
                        if (adMediaSourceHolder != null && !adMediaSourceHolder.hasMediaSource() && i2 < adGroup.uris.length && (uri = adGroup.uris[i2]) != null) {
                            MediaItem.Builder uri2 = new MediaItem.Builder().setUri(uri);
                            MediaItem.PlaybackProperties playbackProperties = this.contentMediaSource.getMediaItem().playbackProperties;
                            if (!(playbackProperties == null || playbackProperties.drmConfiguration == null)) {
                                MediaItem.DrmConfiguration drmConfiguration = playbackProperties.drmConfiguration;
                                uri2.setDrmUuid(drmConfiguration.uuid);
                                uri2.setDrmKeySetId(drmConfiguration.getKeySetId());
                                uri2.setDrmLicenseUri(drmConfiguration.licenseUri);
                                uri2.setDrmForceDefaultLicenseUri(drmConfiguration.forceDefaultLicenseUri);
                                uri2.setDrmLicenseRequestHeaders(drmConfiguration.requestHeaders);
                                uri2.setDrmMultiSession(drmConfiguration.multiSession);
                                uri2.setDrmPlayClearContentWithoutKey(drmConfiguration.playClearContentWithoutKey);
                                uri2.setDrmSessionForClearTypes(drmConfiguration.sessionForClearTypes);
                            }
                            adMediaSourceHolder.initializeWithMediaSource(this.adMediaSourceFactory.createMediaSource(uri2.build()), uri);
                        }
                        i2++;
                    }
                }
            }
        }
    }

    private void maybeUpdateSourceInfo() {
        Timeline timeline = this.contentTimeline;
        AdPlaybackState adPlaybackState = this.adPlaybackState;
        if (adPlaybackState != null && timeline != null) {
            if (adPlaybackState.adGroupCount == 0) {
                refreshSourceInfo(timeline);
                return;
            }
            this.adPlaybackState = this.adPlaybackState.withAdDurationsUs(getAdDurationsUs());
            refreshSourceInfo(new SinglePeriodAdTimeline(timeline, this.adPlaybackState));
        }
    }

    private long[][] getAdDurationsUs() {
        long[][] jArr = new long[this.adMediaSourceHolders.length];
        int i = 0;
        while (true) {
            AdMediaSourceHolder[][] adMediaSourceHolderArr = this.adMediaSourceHolders;
            if (i >= adMediaSourceHolderArr.length) {
                return jArr;
            }
            jArr[i] = new long[adMediaSourceHolderArr[i].length];
            int i2 = 0;
            while (true) {
                AdMediaSourceHolder[][] adMediaSourceHolderArr2 = this.adMediaSourceHolders;
                if (i2 < adMediaSourceHolderArr2[i].length) {
                    AdMediaSourceHolder adMediaSourceHolder = adMediaSourceHolderArr2[i][i2];
                    jArr[i][i2] = adMediaSourceHolder == null ? C.TIME_UNSET : adMediaSourceHolder.getDurationUs();
                    i2++;
                }
            }
            i++;
        }
    }

    /* loaded from: classes2.dex */
    public final class ComponentListener implements AdsLoader.EventListener {
        private final Handler playerHandler = Util.createHandlerForCurrentLooper();
        private volatile boolean stopped;

        @Override // com.google.android.exoplayer2.source.ads.AdsLoader.EventListener
        public /* synthetic */ void onAdClicked() {
            AdsLoader.EventListener.CC.$default$onAdClicked(this);
        }

        @Override // com.google.android.exoplayer2.source.ads.AdsLoader.EventListener
        public /* synthetic */ void onAdTapped() {
            AdsLoader.EventListener.CC.$default$onAdTapped(this);
        }

        public ComponentListener() {
            AdsMediaSource.this = r1;
        }

        public void stop() {
            this.stopped = true;
            this.playerHandler.removeCallbacksAndMessages(null);
        }

        @Override // com.google.android.exoplayer2.source.ads.AdsLoader.EventListener
        public void onAdPlaybackState(AdPlaybackState adPlaybackState) {
            if (!this.stopped) {
                this.playerHandler.post(new Runnable(adPlaybackState) { // from class: com.google.android.exoplayer2.source.ads.-$$Lambda$AdsMediaSource$ComponentListener$EnOXLA4Xyh_hsc2De4jHB6dR5vU
                    private final /* synthetic */ AdPlaybackState f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        AdsMediaSource.ComponentListener.this.lambda$onAdPlaybackState$0$AdsMediaSource$ComponentListener(this.f$1);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onAdPlaybackState$0$AdsMediaSource$ComponentListener(AdPlaybackState adPlaybackState) {
            if (!this.stopped) {
                AdsMediaSource.this.onAdPlaybackState(adPlaybackState);
            }
        }

        @Override // com.google.android.exoplayer2.source.ads.AdsLoader.EventListener
        public void onAdLoadError(AdLoadException adLoadException, DataSpec dataSpec) {
            if (!this.stopped) {
                AdsMediaSource.this.createEventDispatcher(null).loadError(new LoadEventInfo(LoadEventInfo.getNewId(), dataSpec, SystemClock.elapsedRealtime()), 6, (IOException) adLoadException, true);
            }
        }
    }

    /* loaded from: classes2.dex */
    public final class AdPrepareListener implements MaskingMediaPeriod.PrepareListener {
        private final Uri adUri;

        public AdPrepareListener(Uri uri) {
            AdsMediaSource.this = r1;
            this.adUri = uri;
        }

        @Override // com.google.android.exoplayer2.source.MaskingMediaPeriod.PrepareListener
        public void onPrepareComplete(MediaSource.MediaPeriodId mediaPeriodId) {
            AdsMediaSource.this.mainHandler.post(new Runnable(mediaPeriodId) { // from class: com.google.android.exoplayer2.source.ads.-$$Lambda$AdsMediaSource$AdPrepareListener$fKnX3U0AN4mPVLC978gwlh8u4Wo
                private final /* synthetic */ MediaSource.MediaPeriodId f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    AdsMediaSource.AdPrepareListener.this.lambda$onPrepareComplete$0$AdsMediaSource$AdPrepareListener(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onPrepareComplete$0$AdsMediaSource$AdPrepareListener(MediaSource.MediaPeriodId mediaPeriodId) {
            AdsMediaSource.this.adsLoader.handlePrepareComplete(AdsMediaSource.this, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        }

        @Override // com.google.android.exoplayer2.source.MaskingMediaPeriod.PrepareListener
        public void onPrepareError(MediaSource.MediaPeriodId mediaPeriodId, IOException iOException) {
            AdsMediaSource.this.createEventDispatcher(mediaPeriodId).loadError(new LoadEventInfo(LoadEventInfo.getNewId(), new DataSpec(this.adUri), SystemClock.elapsedRealtime()), 6, (IOException) AdLoadException.createForAd(iOException), true);
            AdsMediaSource.this.mainHandler.post(new Runnable(mediaPeriodId, iOException) { // from class: com.google.android.exoplayer2.source.ads.-$$Lambda$AdsMediaSource$AdPrepareListener$ZJtMjO8RAu0BMqqkF1KvmN_9T84
                private final /* synthetic */ MediaSource.MediaPeriodId f$1;
                private final /* synthetic */ IOException f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    AdsMediaSource.AdPrepareListener.this.lambda$onPrepareError$1$AdsMediaSource$AdPrepareListener(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onPrepareError$1$AdsMediaSource$AdPrepareListener(MediaSource.MediaPeriodId mediaPeriodId, IOException iOException) {
            AdsMediaSource.this.adsLoader.handlePrepareError(AdsMediaSource.this, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, iOException);
        }
    }

    /* loaded from: classes2.dex */
    public final class AdMediaSourceHolder {
        private final List<MaskingMediaPeriod> activeMediaPeriods = new ArrayList();
        private MediaSource adMediaSource;
        private Uri adUri;
        private final MediaSource.MediaPeriodId id;
        private Timeline timeline;

        public AdMediaSourceHolder(MediaSource.MediaPeriodId mediaPeriodId) {
            AdsMediaSource.this = r1;
            this.id = mediaPeriodId;
        }

        public void initializeWithMediaSource(MediaSource mediaSource, Uri uri) {
            this.adMediaSource = mediaSource;
            this.adUri = uri;
            for (int i = 0; i < this.activeMediaPeriods.size(); i++) {
                MaskingMediaPeriod maskingMediaPeriod = this.activeMediaPeriods.get(i);
                maskingMediaPeriod.setMediaSource(mediaSource);
                maskingMediaPeriod.setPrepareListener(new AdPrepareListener(uri));
            }
            AdsMediaSource.this.prepareChildSource(this.id, mediaSource);
        }

        public MediaPeriod createMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
            MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j);
            this.activeMediaPeriods.add(maskingMediaPeriod);
            MediaSource mediaSource = this.adMediaSource;
            if (mediaSource != null) {
                maskingMediaPeriod.setMediaSource(mediaSource);
                maskingMediaPeriod.setPrepareListener(new AdPrepareListener((Uri) Assertions.checkNotNull(this.adUri)));
            }
            Timeline timeline = this.timeline;
            if (timeline != null) {
                maskingMediaPeriod.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(0), mediaPeriodId.windowSequenceNumber));
            }
            return maskingMediaPeriod;
        }

        public void handleSourceInfoRefresh(Timeline timeline) {
            boolean z = true;
            if (timeline.getPeriodCount() != 1) {
                z = false;
            }
            Assertions.checkArgument(z);
            if (this.timeline == null) {
                Object uidOfPeriod = timeline.getUidOfPeriod(0);
                for (int i = 0; i < this.activeMediaPeriods.size(); i++) {
                    MaskingMediaPeriod maskingMediaPeriod = this.activeMediaPeriods.get(i);
                    maskingMediaPeriod.createPeriod(new MediaSource.MediaPeriodId(uidOfPeriod, maskingMediaPeriod.id.windowSequenceNumber));
                }
            }
            this.timeline = timeline;
        }

        public long getDurationUs() {
            Timeline timeline = this.timeline;
            if (timeline == null) {
                return C.TIME_UNSET;
            }
            return timeline.getPeriod(0, AdsMediaSource.this.period).getDurationUs();
        }

        public void releaseMediaPeriod(MaskingMediaPeriod maskingMediaPeriod) {
            this.activeMediaPeriods.remove(maskingMediaPeriod);
            maskingMediaPeriod.releasePeriod();
        }

        public void release() {
            if (hasMediaSource()) {
                AdsMediaSource.this.releaseChildSource(this.id);
            }
        }

        public boolean hasMediaSource() {
            return this.adMediaSource != null;
        }

        public boolean isInactive() {
            return this.activeMediaPeriods.isEmpty();
        }
    }
}
