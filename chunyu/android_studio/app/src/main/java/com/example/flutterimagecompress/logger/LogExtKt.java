package com.example.flutterimagecompress.logger;

import android.util.Log;
import com.example.flutterimagecompress.FlutterImageCompressPlugin;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogExt.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¨\u0006\u0004"}, d2 = {"log", "", "", "any", "flutter_image_compress_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class LogExtKt {
    public static final void log(Object obj, Object obj2) {
        String obj3;
        Intrinsics.checkNotNullParameter(obj, "<this>");
        if (FlutterImageCompressPlugin.Companion.getShowLog()) {
            String str = "null";
            if (!(obj2 == null || (obj3 = obj2.toString()) == null)) {
                str = obj3;
            }
            Log.i("flutter_image_compress", str);
        }
    }
}
