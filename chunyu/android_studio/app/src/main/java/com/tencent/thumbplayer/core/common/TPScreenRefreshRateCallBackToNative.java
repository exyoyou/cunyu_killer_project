package com.tencent.thumbplayer.core.common;

import com.tencent.thumbplayer.core.common.TPScreenRefreshRateDetector;

/* loaded from: classes2.dex */
public class TPScreenRefreshRateCallBackToNative implements TPScreenRefreshRateDetector.ScreenRefreshRateChangedListener {
    private static final String TAG = "TPScreenRefreshRateCallBack";
    private long mNativeContext;

    private TPScreenRefreshRateCallBackToNative(long j) {
        this.mNativeContext = j;
    }

    private native void _onScreenRefreshRateChanged(float f);

    private long getNativeContext() {
        return this.mNativeContext;
    }

    private void registerCallback() {
        TPScreenRefreshRateDetector.addListener(this);
    }

    private void unregisterCallback() {
        TPScreenRefreshRateDetector.removeListener(this);
    }

    @Override // com.tencent.thumbplayer.core.common.TPScreenRefreshRateDetector.ScreenRefreshRateChangedListener
    public void onScreenRefreshRateChanged(float f) {
        TPNativeLog.printLog(2, TAG, "onScreenRefreshRateChanged refreshRate:" + f);
        _onScreenRefreshRateChanged(f);
    }
}
