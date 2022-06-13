package com.google.android.exoplayer2.source.rtsp;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class RtspMediaTrack {
    private static final String AAC_CODECS_PREFIX = "mp4a.40.";
    private static final String GENERIC_CONTROL_ATTR = "*";
    private static final String H264_CODECS_PREFIX = "avc1.";
    private static final String PARAMETER_PROFILE_LEVEL_ID = "profile-level-id";
    private static final String PARAMETER_SPROP_PARAMS = "sprop-parameter-sets";
    public final RtpPayloadFormat payloadFormat;
    public final Uri uri;

    public RtspMediaTrack(MediaDescription mediaDescription, Uri uri) {
        Assertions.checkArgument(mediaDescription.attributes.containsKey(SessionDescription.ATTR_CONTROL));
        this.payloadFormat = generatePayloadFormat(mediaDescription);
        this.uri = extractTrackUri(uri, (String) Util.castNonNull(mediaDescription.attributes.get(SessionDescription.ATTR_CONTROL)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RtspMediaTrack rtspMediaTrack = (RtspMediaTrack) obj;
        return this.payloadFormat.equals(rtspMediaTrack.payloadFormat) && this.uri.equals(rtspMediaTrack.uri);
    }

    public int hashCode() {
        return ((217 + this.payloadFormat.hashCode()) * 31) + this.uri.hashCode();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0091, code lost:
        if (r5.equals(com.google.android.exoplayer2.util.MimeTypes.AUDIO_AC3) != false) goto L_0x009f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump */
    static com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat generatePayloadFormat(com.google.android.exoplayer2.source.rtsp.MediaDescription r11) {
        /*
            com.google.android.exoplayer2.Format$Builder r0 = new com.google.android.exoplayer2.Format$Builder
            r0.<init>()
            int r1 = r11.bitrate
            if (r1 <= 0) goto L_0x000e
            int r1 = r11.bitrate
            r0.setAverageBitrate(r1)
        L_0x000e:
            com.google.common.collect.ImmutableMap<java.lang.String, java.lang.String> r1 = r11.attributes
            java.lang.String r2 = "rtpmap"
            boolean r1 = r1.containsKey(r2)
            com.google.android.exoplayer2.util.Assertions.checkArgument(r1)
            com.google.common.collect.ImmutableMap<java.lang.String, java.lang.String> r1 = r11.attributes
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = com.google.android.exoplayer2.util.Util.castNonNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = " "
            java.lang.String[] r1 = com.google.android.exoplayer2.util.Util.split(r1, r2)
            int r1 = r1.length
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0035
            r1 = r4
            goto L_0x0036
        L_0x0035:
            r1 = r3
        L_0x0036:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r1)
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r1 = r11.rtpMapAttribute
            int r1 = r1.payloadType
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r5 = r11.rtpMapAttribute
            java.lang.String r5 = r5.mediaEncoding
            java.lang.String r5 = com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat.getMimeTypeFromRtpMediaType(r5)
            r0.setSampleMimeType(r5)
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r6 = r11.rtpMapAttribute
            int r6 = r6.clockRate
            java.lang.String r7 = r11.mediaType
            java.lang.String r8 = "audio"
            boolean r7 = r8.equals(r7)
            r8 = -1
            if (r7 == 0) goto L_0x0067
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r7 = r11.rtpMapAttribute
            int r7 = r7.encodingParameters
            int r7 = inferChannelCount(r7, r5)
            com.google.android.exoplayer2.Format$Builder r9 = r0.setSampleRate(r6)
            r9.setChannelCount(r7)
            goto L_0x0068
        L_0x0067:
            r7 = r8
        L_0x0068:
            com.google.common.collect.ImmutableMap r11 = r11.getFmtpParametersAsMap()
            int r9 = r5.hashCode()
            r10 = -53558318(0xfffffffffccec3d2, float:-8.588679E36)
            if (r9 == r10) goto L_0x0094
            r10 = 187078296(0xb269698, float:3.208373E-32)
            if (r9 == r10) goto L_0x008b
            r2 = 1331836730(0x4f62373a, float:3.79527014E9)
            if (r9 == r2) goto L_0x0080
            goto L_0x009e
        L_0x0080:
            java.lang.String r2 = "video/avc"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x009e
            r2 = r4
            goto L_0x009f
        L_0x008b:
            java.lang.String r9 = "audio/ac3"
            boolean r5 = r5.equals(r9)
            if (r5 == 0) goto L_0x009e
            goto L_0x009f
        L_0x0094:
            java.lang.String r2 = "audio/mp4a-latm"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x009e
            r2 = r3
            goto L_0x009f
        L_0x009e:
            r2 = r8
        L_0x009f:
            if (r2 == 0) goto L_0x00b0
            if (r2 == r4) goto L_0x00a4
            goto L_0x00c3
        L_0x00a4:
            boolean r2 = r11.isEmpty()
            r2 = r2 ^ r4
            com.google.android.exoplayer2.util.Assertions.checkArgument(r2)
            processH264FmtpAttribute(r0, r11)
            goto L_0x00c3
        L_0x00b0:
            if (r7 == r8) goto L_0x00b4
            r2 = r4
            goto L_0x00b5
        L_0x00b4:
            r2 = r3
        L_0x00b5:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r2)
            boolean r2 = r11.isEmpty()
            r2 = r2 ^ r4
            com.google.android.exoplayer2.util.Assertions.checkArgument(r2)
            processAacFmtpAttribute(r0, r11, r7, r6)
        L_0x00c3:
            if (r6 <= 0) goto L_0x00c7
            r2 = r4
            goto L_0x00c8
        L_0x00c7:
            r2 = r3
        L_0x00c8:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r2)
            r2 = 96
            if (r1 < r2) goto L_0x00d0
            r3 = r4
        L_0x00d0:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r3)
            com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat r2 = new com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat
            com.google.android.exoplayer2.Format r0 = r0.build()
            r2.<init>(r0, r1, r6, r11)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.rtsp.RtspMediaTrack.generatePayloadFormat(com.google.android.exoplayer2.source.rtsp.MediaDescription):com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat");
    }

    private static int inferChannelCount(int i, String str) {
        if (i != -1) {
            return i;
        }
        return str.equals(MimeTypes.AUDIO_AC3) ? 6 : 1;
    }

    private static void processAacFmtpAttribute(Format.Builder builder, ImmutableMap<String, String> immutableMap, int i, int i2) {
        Assertions.checkArgument(immutableMap.containsKey(PARAMETER_PROFILE_LEVEL_ID));
        String valueOf = String.valueOf((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_PROFILE_LEVEL_ID)));
        builder.setCodecs(valueOf.length() != 0 ? AAC_CODECS_PREFIX.concat(valueOf) : new String(AAC_CODECS_PREFIX));
        builder.setInitializationData(ImmutableList.of(AacUtil.buildAacLcAudioSpecificConfig(i2, i)));
    }

    private static void processH264FmtpAttribute(Format.Builder builder, ImmutableMap<String, String> immutableMap) {
        Assertions.checkArgument(immutableMap.containsKey(PARAMETER_SPROP_PARAMS));
        String[] split = Util.split((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_SPROP_PARAMS)), ",");
        Assertions.checkArgument(split.length == 2);
        ImmutableList of = ImmutableList.of(getH264InitializationDataFromParameterSet(split[0]), getH264InitializationDataFromParameterSet(split[1]));
        builder.setInitializationData(of);
        byte[] bArr = (byte[]) of.get(0);
        NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit(bArr, NalUnitUtil.NAL_START_CODE.length, bArr.length);
        builder.setPixelWidthHeightRatio(parseSpsNalUnit.pixelWidthAspectRatio);
        builder.setHeight(parseSpsNalUnit.height);
        builder.setWidth(parseSpsNalUnit.width);
        String str = immutableMap.get(PARAMETER_PROFILE_LEVEL_ID);
        if (str != null) {
            String valueOf = String.valueOf(str);
            builder.setCodecs(valueOf.length() != 0 ? H264_CODECS_PREFIX.concat(valueOf) : new String(H264_CODECS_PREFIX));
            return;
        }
        builder.setCodecs(CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc));
    }

    private static byte[] getH264InitializationDataFromParameterSet(String str) {
        byte[] decode = Base64.decode(str, 0);
        byte[] bArr = new byte[decode.length + NalUnitUtil.NAL_START_CODE.length];
        System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, 0, NalUnitUtil.NAL_START_CODE.length);
        System.arraycopy(decode, 0, bArr, NalUnitUtil.NAL_START_CODE.length, decode.length);
        return bArr;
    }

    private static Uri extractTrackUri(Uri uri, String str) {
        Uri parse = Uri.parse(str);
        if (parse.isAbsolute()) {
            return parse;
        }
        if (str.equals("*")) {
            return uri;
        }
        return uri.buildUpon().appendEncodedPath(str).build();
    }
}
