package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: String.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\b¨\u0006\u0002"}, d2 = {"htmlEncode", "", "core-ktx_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class StringKt {
    public static final String htmlEncode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$htmlEncode");
        String htmlEncode = TextUtils.htmlEncode(str);
        Intrinsics.checkExpressionValueIsNotNull(htmlEncode, "TextUtils.htmlEncode(this)");
        return htmlEncode;
    }
}
