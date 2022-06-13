package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public class ViewOverlayApi18 implements ViewOverlayImpl {
    private final ViewOverlay mViewOverlay;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewOverlayApi18(View view) {
        this.mViewOverlay = view.getOverlay();
    }

    @Override // androidx.transition.ViewOverlayImpl
    public void add(Drawable drawable) {
        this.mViewOverlay.add(drawable);
    }

    @Override // androidx.transition.ViewOverlayImpl
    public void remove(Drawable drawable) {
        this.mViewOverlay.remove(drawable);
    }
}
