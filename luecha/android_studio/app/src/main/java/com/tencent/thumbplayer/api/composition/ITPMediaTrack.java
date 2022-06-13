package com.tencent.thumbplayer.api.composition;

import java.util.List;

/* loaded from: classes5.dex */
public interface ITPMediaTrack extends ITPMediaAsset {
    int addTrackClip(ITPMediaTrackClip iTPMediaTrackClip);

    List<ITPMediaTrackClip> getAllTrackClips();

    long getTimelineDurationMs();

    ITPMediaTrackClip getTrackClip(int i);

    int getTrackId();

    int insertTrackClip(ITPMediaTrackClip iTPMediaTrackClip, int i);

    void removeAllTrackClips();

    boolean removeTrackClip(ITPMediaTrackClip iTPMediaTrackClip);

    boolean swapTrackClip(int i, int i2);
}
