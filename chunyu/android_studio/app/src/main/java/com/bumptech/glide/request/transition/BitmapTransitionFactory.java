package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/* loaded from: classes4.dex */
public class BitmapTransitionFactory extends BitmapContainerTransitionFactory<Bitmap> {
    /* JADX INFO: Access modifiers changed from: protected */
    public Bitmap getBitmap(Bitmap bitmap) {
        return bitmap;
    }

    public BitmapTransitionFactory(TransitionFactory<Drawable> transitionFactory) {
        super(transitionFactory);
    }
}
