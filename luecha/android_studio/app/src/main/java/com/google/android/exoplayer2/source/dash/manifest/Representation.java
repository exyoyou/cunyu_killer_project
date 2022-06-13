package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.DashSegmentIndex;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.google.android.exoplayer2.util.Assertions;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class Representation {
    public static final long REVISION_ID_DEFAULT = -1;
    public final ImmutableList<BaseUrl> baseUrls;
    public final Format format;
    public final List<Descriptor> inbandEventStreams;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;

    public abstract String getCacheKey();

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public static Representation newInstance(long j, Format format, List<BaseUrl> list, SegmentBase segmentBase) {
        return newInstance(j, format, list, segmentBase, null);
    }

    public static Representation newInstance(long j, Format format, List<BaseUrl> list, SegmentBase segmentBase, List<Descriptor> list2) {
        return newInstance(j, format, list, segmentBase, list2, null);
    }

    public static Representation newInstance(long j, Format format, List<BaseUrl> list, SegmentBase segmentBase, List<Descriptor> list2, String str) {
        if (segmentBase instanceof SegmentBase.SingleSegmentBase) {
            return new SingleSegmentRepresentation(j, format, list, (SegmentBase.SingleSegmentBase) segmentBase, list2, str, -1);
        }
        if (segmentBase instanceof SegmentBase.MultiSegmentBase) {
            return new MultiSegmentRepresentation(j, format, list, (SegmentBase.MultiSegmentBase) segmentBase, list2);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }

    private Representation(long j, Format format, List<BaseUrl> list, SegmentBase segmentBase, List<Descriptor> list2) {
        List<Descriptor> list3;
        Assertions.checkArgument(!list.isEmpty());
        this.revisionId = j;
        this.format = format;
        this.baseUrls = ImmutableList.copyOf((Collection) list);
        if (list2 == null) {
            list3 = Collections.emptyList();
        } else {
            list3 = Collections.unmodifiableList(list2);
        }
        this.inbandEventStreams = list3;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
    }

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    /* loaded from: classes2.dex */
    public static class SingleSegmentRepresentation extends Representation {
        private final String cacheKey;
        public final long contentLength;
        private final RangedUri indexUri;
        private final SingleSegmentIndex segmentIndex;
        public final Uri uri;

        public static SingleSegmentRepresentation newInstance(long j, Format format, String str, long j2, long j3, long j4, long j5, List<Descriptor> list, String str2, long j6) {
            return new SingleSegmentRepresentation(j, format, ImmutableList.of(new BaseUrl(str)), new SegmentBase.SingleSegmentBase(new RangedUri(null, j2, (j3 - j2) + 1), 1, 0, j4, (j5 - j4) + 1), list, str2, j6);
        }

        public SingleSegmentRepresentation(long j, Format format, List<BaseUrl> list, SegmentBase.SingleSegmentBase singleSegmentBase, List<Descriptor> list2, String str, long j2) {
            super(j, format, list, singleSegmentBase, list2);
            this.uri = Uri.parse(list.get(0).url);
            this.indexUri = singleSegmentBase.getIndex();
            this.cacheKey = str;
            this.contentLength = j2;
            this.segmentIndex = this.indexUri != null ? null : new SingleSegmentIndex(new RangedUri(null, 0, j2));
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return this.indexUri;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        public String getCacheKey() {
            return this.cacheKey;
        }
    }

    /* loaded from: classes2.dex */
    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        final SegmentBase.MultiSegmentBase segmentBase;

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        public String getCacheKey() {
            return null;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return null;
        }

        public MultiSegmentRepresentation(long j, Format format, List<BaseUrl> list, SegmentBase.MultiSegmentBase multiSegmentBase, List<Descriptor> list2) {
            super(j, format, list, multiSegmentBase, list2);
            this.segmentBase = multiSegmentBase;
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public RangedUri getSegmentUrl(long j) {
            return this.segmentBase.getSegmentUrl(this, j);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getSegmentNum(long j, long j2) {
            return this.segmentBase.getSegmentNum(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getTimeUs(long j) {
            return this.segmentBase.getSegmentTimeUs(j);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getDurationUs(long j, long j2) {
            return this.segmentBase.getSegmentDurationUs(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getFirstAvailableSegmentNum(long j, long j2) {
            return this.segmentBase.getFirstAvailableSegmentNum(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getSegmentCount(long j) {
            return this.segmentBase.getSegmentCount(j);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getAvailableSegmentCount(long j, long j2) {
            return this.segmentBase.getAvailableSegmentCount(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getNextSegmentAvailableTimeUs(long j, long j2) {
            return this.segmentBase.getNextSegmentAvailableTimeUs(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }
}
