package com.tencent.liteav.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class TXCFocusIndicatorView extends View {
    private static final int FOCUS_AREA_STROKE = 1;
    private Paint mPaint;
    private ScaleAnimation mScaleAnimation;
    private int mSize = 0;
    private int mFocusAreaStroke = 2;
    private Runnable mHideRunnable = new Runnable() { // from class: com.tencent.liteav.renderer.TXCFocusIndicatorView.1
        @Override // java.lang.Runnable
        public void run() {
            TXCFocusIndicatorView.this.setVisibility(8);
        }
    };

    public TXCFocusIndicatorView(Context context) {
        super(context);
        init(null, 0);
    }

    public TXCFocusIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public TXCFocusIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        this.mPaint = new Paint();
        this.mFocusAreaStroke = (int) ((getContext().getResources().getDisplayMetrics().density * 1.0f) + 0.5f);
        this.mScaleAnimation = new ScaleAnimation(1.3f, 1.0f, 1.3f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.mScaleAnimation.setDuration(200);
    }

    public void show(int i, int i2, int i3) {
        removeCallbacks(this.mHideRunnable);
        this.mScaleAnimation.cancel();
        this.mSize = i3;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(i, i2, 0, 0);
        int i4 = this.mSize;
        layoutParams.width = i4;
        layoutParams.height = i4;
        setVisibility(0);
        requestLayout();
        this.mScaleAnimation.reset();
        startAnimation(this.mScaleAnimation);
        postDelayed(this.mHideRunnable, 1000);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.save();
        int i = this.mFocusAreaStroke / 2;
        int i2 = this.mSize;
        Rect rect = new Rect(i, i, i2 - i, i2 - i);
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float) (i * 2));
        canvas.drawRect(rect, this.mPaint);
        canvas.restore();
        super.onDraw(canvas);
    }
}
