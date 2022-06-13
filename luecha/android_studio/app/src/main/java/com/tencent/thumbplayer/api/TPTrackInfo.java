package com.tencent.thumbplayer.api;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class TPTrackInfo {
    public static final int TP_MEDIA_TRACK_TYPE_AUDIO = 2;
    public static final int TP_MEDIA_TRACK_TYPE_SUBTITLE = 3;
    public static final int TP_MEDIA_TRACK_TYPE_UNKNOW = 0;
    public static final int TP_MEDIA_TRACK_TYPE_VIDEO = 1;
    public String name;
    public int trackType = 0;
    public boolean isSelected = false;
    public boolean isExclusive = true;
    public boolean isInternal = true;
    public TPHlsTag hlsTag = new TPHlsTag();

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof TPTrackInfo)) {
            TPTrackInfo tPTrackInfo = (TPTrackInfo) obj;
            if (this.name.equals(tPTrackInfo.name) && this.trackType == tPTrackInfo.trackType) {
                return true;
            }
        }
        return false;
    }

    public TPHlsTag getHlsTag() {
        return this.hlsTag;
    }

    public String getName() {
        return this.name;
    }

    public int getTrackType() {
        return this.trackType;
    }
}
