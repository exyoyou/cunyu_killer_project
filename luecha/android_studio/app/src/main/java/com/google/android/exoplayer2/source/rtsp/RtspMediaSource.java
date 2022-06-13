package com.google.android.exoplayer2.source.rtsp;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.ForwardingTimeline;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.rtsp.RtpDataChannel;
import com.google.android.exoplayer2.source.rtsp.RtspMediaPeriod;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.List;

/* loaded from: classes2.dex */
public final class RtspMediaSource extends BaseMediaSource {
    public static final long DEFAULT_TIMEOUT_MS = 8000;
    private final MediaItem mediaItem;
    private final RtpDataChannel.Factory rtpDataChannelFactory;
    private boolean timelineIsLive;
    private boolean timelineIsSeekable;
    private final Uri uri;
    private final String userAgent;
    private long timelineDurationUs = C.TIME_UNSET;
    private boolean timelineIsPlaceholder = true;

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource
    protected void releaseSourceInternal() {
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.rtsp");
    }

    /* loaded from: classes2.dex */
    public static final class Factory implements MediaSourceFactory {
        private boolean forceUseRtpTcp;
        private long timeoutMs = 8000;
        private String userAgent = ExoPlayerLibraryInfo.VERSION_SLASHY;

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        public /* synthetic */ MediaSource createMediaSource(Uri uri) {
            return MediaSourceFactory.CC.$default$createMediaSource(this, uri);
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        public int[] getSupportedTypes() {
            return new int[]{3};
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        public Factory setDrmHttpDataSourceFactory(HttpDataSource.Factory factory) {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        public Factory setDrmSessionManager(DrmSessionManager drmSessionManager) {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        public Factory setDrmUserAgent(String str) {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        public /* synthetic */ MediaSourceFactory setStreamKeys(List<StreamKey> list) {
            return MediaSourceFactory.CC.$default$setStreamKeys(this, list);
        }

        public Factory setForceUseRtpTcp(boolean z) {
            this.forceUseRtpTcp = z;
            return this;
        }

        public Factory setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        public Factory setTimeoutMs(long j) {
            Assertions.checkArgument(j > 0);
            this.timeoutMs = j;
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        public RtspMediaSource createMediaSource(MediaItem mediaItem) {
            RtpDataChannel.Factory factory;
            Assertions.checkNotNull(mediaItem.playbackProperties);
            if (this.forceUseRtpTcp) {
                factory = new TransferRtpDataChannelFactory(this.timeoutMs);
            } else {
                factory = new UdpDataSourceRtpDataChannelFactory(this.timeoutMs);
            }
            return new RtspMediaSource(mediaItem, factory, this.userAgent);
        }
    }

    /* loaded from: classes2.dex */
    public static final class RtspPlaybackException extends IOException {
        public RtspPlaybackException(String str) {
            super(str);
        }

        public RtspPlaybackException(Throwable th) {
            super(th);
        }

        public RtspPlaybackException(String str, Throwable th) {
            super(str, th);
        }
    }

    RtspMediaSource(MediaItem mediaItem, RtpDataChannel.Factory factory, String str) {
        this.mediaItem = mediaItem;
        this.rtpDataChannelFactory = factory;
        this.userAgent = str;
        this.uri = ((MediaItem.PlaybackProperties) Assertions.checkNotNull(this.mediaItem.playbackProperties)).uri;
    }

    @Override // com.google.android.exoplayer2.source.BaseMediaSource
    protected void prepareSourceInternal(TransferListener transferListener) {
        notifySourceInfoRefreshed();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return new RtspMediaPeriod(allocator, this.rtpDataChannelFactory, this.uri, new RtspMediaPeriod.Listener() { // from class: com.google.android.exoplayer2.source.rtsp.-$$Lambda$RtspMediaSource$jD8GaPCIZBfF1v46R-a6Mb7sMCs
            @Override // com.google.android.exoplayer2.source.rtsp.RtspMediaPeriod.Listener
            public final void onSourceInfoRefreshed(RtspSessionTiming rtspSessionTiming) {
                RtspMediaSource.this.lambda$createPeriod$0$RtspMediaSource(rtspSessionTiming);
            }
        }, this.userAgent);
    }

    public /* synthetic */ void lambda$createPeriod$0$RtspMediaSource(RtspSessionTiming rtspSessionTiming) {
        this.timelineDurationUs = C.msToUs(rtspSessionTiming.getDurationMs());
        this.timelineIsSeekable = !rtspSessionTiming.isLive();
        this.timelineIsLive = rtspSessionTiming.isLive();
        this.timelineIsPlaceholder = false;
        notifySourceInfoRefreshed();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((RtspMediaPeriod) mediaPeriod).release();
    }

    private void notifySourceInfoRefreshed() {
        SinglePeriodTimeline singlePeriodTimeline = new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false, this.timelineIsLive, (Object) null, this.mediaItem);
        refreshSourceInfo(this.timelineIsPlaceholder ? new ForwardingTimeline(this, singlePeriodTimeline) { // from class: com.google.android.exoplayer2.source.rtsp.RtspMediaSource.1
            @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
            public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
                super.getWindow(i, window, j);
                window.isPlaceholder = true;
                return window;
            }

            @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
            public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
                super.getPeriod(i, period, z);
                period.isPlaceholder = true;
                return period;
            }
        } : singlePeriodTimeline);
    }
}
