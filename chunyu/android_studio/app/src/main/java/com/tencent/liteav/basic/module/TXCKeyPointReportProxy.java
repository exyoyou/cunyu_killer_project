package com.tencent.liteav.basic.module;

/* loaded from: classes2.dex */
public class TXCKeyPointReportProxy {
    private static native void nativeTagKeyPointVideo(String str, int i);

    private static native void nativesetRemoteQuality(String str, int i, long j, int i2);

    public static void a(String str, int i) {
        nativeTagKeyPointVideo(str, i);
    }

    public static void a(String str, int i, long j, int i2) {
        nativesetRemoteQuality(str, i, j, i2);
    }
}
