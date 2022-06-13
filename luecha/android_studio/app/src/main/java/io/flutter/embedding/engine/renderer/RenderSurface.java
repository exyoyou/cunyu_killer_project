package io.flutter.embedding.engine.renderer;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public interface RenderSurface {
    void attachToRenderer(FlutterRenderer flutterRenderer);

    void detachFromRenderer();

    FlutterRenderer getAttachedRenderer();

    void pause();
}
