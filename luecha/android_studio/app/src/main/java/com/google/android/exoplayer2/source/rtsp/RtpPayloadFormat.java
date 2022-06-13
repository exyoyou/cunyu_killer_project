package com.google.android.exoplayer2.source.rtsp;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class RtpPayloadFormat {
    private static final String RTP_MEDIA_AC3 = "AC3";
    private static final String RTP_MEDIA_H264 = "H264";
    private static final String RTP_MEDIA_MPEG4_GENERIC = "MPEG4-GENERIC";
    public final int clockRate;
    public final ImmutableMap<String, String> fmtpParameters;
    public final Format format;
    public final int rtpPayloadType;

    public static boolean isFormatSupported(MediaDescription mediaDescription) {
        char c;
        String upperCase = Ascii.toUpperCase(mediaDescription.rtpMapAttribute.mediaEncoding);
        int hashCode = upperCase.hashCode();
        if (hashCode == -1922091719) {
            if (upperCase.equals(RTP_MEDIA_MPEG4_GENERIC)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 64593) {
            if (hashCode == 2194728 && upperCase.equals(RTP_MEDIA_H264)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (upperCase.equals(RTP_MEDIA_AC3)) {
                c = 0;
            }
            c = 65535;
        }
        return c == 0 || c == 1 || c == 2;
    }

    public static String getMimeTypeFromRtpMediaType(String str) {
        char c;
        String upperCase = Ascii.toUpperCase(str);
        int hashCode = upperCase.hashCode();
        if (hashCode == -1922091719) {
            if (upperCase.equals(RTP_MEDIA_MPEG4_GENERIC)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 64593) {
            if (hashCode == 2194728 && upperCase.equals(RTP_MEDIA_H264)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (upperCase.equals(RTP_MEDIA_AC3)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            return MimeTypes.AUDIO_AC3;
        }
        if (c == 1) {
            return "video/avc";
        }
        if (c == 2) {
            return MimeTypes.AUDIO_AAC;
        }
        throw new IllegalArgumentException(str);
    }

    public RtpPayloadFormat(Format format, int i, int i2, Map<String, String> map) {
        this.rtpPayloadType = i;
        this.clockRate = i2;
        this.format = format;
        this.fmtpParameters = ImmutableMap.copyOf(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RtpPayloadFormat rtpPayloadFormat = (RtpPayloadFormat) obj;
        return this.rtpPayloadType == rtpPayloadFormat.rtpPayloadType && this.clockRate == rtpPayloadFormat.clockRate && this.format.equals(rtpPayloadFormat.format) && this.fmtpParameters.equals(rtpPayloadFormat.fmtpParameters);
    }

    public int hashCode() {
        return ((((((217 + this.rtpPayloadType) * 31) + this.clockRate) * 31) + this.format.hashCode()) * 31) + this.fmtpParameters.hashCode();
    }
}
