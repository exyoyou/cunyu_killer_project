package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* loaded from: classes4.dex */
public class DrawableImageViewTarget extends ImageViewTarget<Drawable> {
    public DrawableImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public DrawableImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setResource(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }
}
