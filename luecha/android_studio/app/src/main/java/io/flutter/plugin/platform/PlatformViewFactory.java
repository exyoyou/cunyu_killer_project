package io.flutter.plugin.platform;

import android.content.Context;
import io.flutter.plugin.common.MessageCodec;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public abstract class PlatformViewFactory {
    private final MessageCodec<Object> createArgsCodec;

    public abstract PlatformView create(Context context, int i, Object obj);

    public PlatformViewFactory(MessageCodec<Object> messageCodec) {
        this.createArgsCodec = messageCodec;
    }

    public final MessageCodec<Object> getCreateArgsCodec() {
        return this.createArgsCodec;
    }
}
