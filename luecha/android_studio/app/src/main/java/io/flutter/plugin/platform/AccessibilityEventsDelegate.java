package io.flutter.plugin.platform;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import io.flutter.view.AccessibilityBridge;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class AccessibilityEventsDelegate {
    private AccessibilityBridge accessibilityBridge;

    public boolean requestSendAccessibilityEvent(View view, View view2, AccessibilityEvent accessibilityEvent) {
        AccessibilityBridge accessibilityBridge = this.accessibilityBridge;
        if (accessibilityBridge == null) {
            return false;
        }
        return accessibilityBridge.externalViewRequestSendAccessibilityEvent(view, view2, accessibilityEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAccessibilityBridge(AccessibilityBridge accessibilityBridge) {
        this.accessibilityBridge = accessibilityBridge;
    }
}
