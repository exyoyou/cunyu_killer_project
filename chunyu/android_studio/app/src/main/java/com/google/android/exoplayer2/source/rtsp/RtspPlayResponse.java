package com.google.android.exoplayer2.source.rtsp;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class RtspPlayResponse {
    public final RtspSessionTiming sessionTiming;
    public final int status;
    public final ImmutableList<RtspTrackTiming> trackTimingList;

    public RtspPlayResponse(int i, RtspSessionTiming rtspSessionTiming, List<RtspTrackTiming> list) {
        this.status = i;
        this.sessionTiming = rtspSessionTiming;
        this.trackTimingList = ImmutableList.copyOf((Collection) list);
    }
}
