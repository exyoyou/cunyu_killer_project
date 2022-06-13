package com.opensource.svgaplayer;

import com.opensource.svgaplayer.utils.log.LogUtils;
import kotlin.Metadata;

/* compiled from: SVGACache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* loaded from: classes2.dex */
final class SVGACache$clearCache$1 implements Runnable {
    public static final SVGACache$clearCache$1 INSTANCE = new SVGACache$clearCache$1();

    SVGACache$clearCache$1() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        SVGACache.INSTANCE.clearDir$com_opensource_svgaplayer(SVGACache.INSTANCE.getCacheDir());
        LogUtils.INSTANCE.info("SVGACache", "Clear svga cache done!");
    }
}
