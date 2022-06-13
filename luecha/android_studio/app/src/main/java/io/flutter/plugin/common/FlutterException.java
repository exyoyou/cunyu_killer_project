package io.flutter.plugin.common;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class FlutterException extends RuntimeException {
    private static final String TAG = "FlutterException#";
    public final String code;
    public final Object details;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlutterException(String str, String str2, Object obj) {
        super(str2);
        this.code = str;
        this.details = obj;
    }
}
