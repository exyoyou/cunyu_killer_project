package io.flutter.plugin.platform;

import android.view.View;
import io.flutter.view.AccessibilityBridge;

/* loaded from: classes2.dex */
public interface PlatformViewsAccessibilityDelegate {
    void attachAccessibilityBridge(AccessibilityBridge accessibilityBridge);

    void detachAccessibilityBridge();

    View getPlatformViewById(Integer num);

    boolean usesVirtualDisplay(Integer num);
}
