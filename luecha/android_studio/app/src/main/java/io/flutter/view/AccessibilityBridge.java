package io.flutter.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.LocaleSpan;
import android.text.style.TtsSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.flutter.Log;
import io.flutter.embedding.engine.systemchannels.AccessibilityChannel;
import io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate;
import io.flutter.util.Predicate;
import io.flutter.util.ViewUtils;
import io.flutter.view.AccessibilityBridge;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class AccessibilityBridge extends AccessibilityNodeProvider {
    private static final int ACTION_SHOW_ON_SCREEN = 16908342;
    private static final int MIN_ENGINE_GENERATED_NODE_ID = 65536;
    private static final int ROOT_NODE_ID = 0;
    private static final float SCROLL_EXTENT_FOR_INFINITY = 100000.0f;
    private static final float SCROLL_POSITION_CAP_FOR_INFINITY = 70000.0f;
    private static final String TAG = "AccessibilityBridge";
    private final AccessibilityChannel accessibilityChannel;
    private int accessibilityFeatureFlags;
    private SemanticsNode accessibilityFocusedSemanticsNode;
    private final AccessibilityManager accessibilityManager;
    private final AccessibilityChannel.AccessibilityMessageHandler accessibilityMessageHandler;
    private final AccessibilityManager.AccessibilityStateChangeListener accessibilityStateChangeListener;
    private final AccessibilityViewEmbedder accessibilityViewEmbedder;
    private final ContentObserver animationScaleObserver;
    private final ContentResolver contentResolver;
    private final Map<Integer, CustomAccessibilityAction> customAccessibilityActions;
    private Integer embeddedAccessibilityFocusedNodeId;
    private Integer embeddedInputFocusedNodeId;
    private final List<Integer> flutterNavigationStack;
    private final Map<Integer, SemanticsNode> flutterSemanticsTree;
    private SemanticsNode hoveredObject;
    private SemanticsNode inputFocusedSemanticsNode;
    private boolean isReleased;
    private SemanticsNode lastInputFocusedSemanticsNode;
    private Integer lastLeftFrameInset;
    private OnAccessibilityChangeListener onAccessibilityChangeListener;
    private final PlatformViewsAccessibilityDelegate platformViewsAccessibilityDelegate;
    private int previousRouteId;
    private final View rootAccessibilityView;
    private final AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener;
    private static final int SCROLLABLE_ACTIONS = ((Action.SCROLL_RIGHT.value | Action.SCROLL_LEFT.value) | Action.SCROLL_UP.value) | Action.SCROLL_DOWN.value;
    private static final int FOCUSABLE_FLAGS = ((((((((((Flag.HAS_CHECKED_STATE.value | Flag.IS_CHECKED.value) | Flag.IS_SELECTED.value) | Flag.IS_TEXT_FIELD.value) | Flag.IS_FOCUSED.value) | Flag.HAS_ENABLED_STATE.value) | Flag.IS_ENABLED.value) | Flag.IS_IN_MUTUALLY_EXCLUSIVE_GROUP.value) | Flag.HAS_TOGGLED_STATE.value) | Flag.IS_TOGGLED.value) | Flag.IS_FOCUSABLE.value) | Flag.IS_SLIDER.value;
    private static int FIRST_RESOURCE_ID = 267386881;

    /* loaded from: classes5.dex */
    public interface OnAccessibilityChangeListener {
        void onAccessibilityChanged(boolean z, boolean z2);
    }

    /* loaded from: classes5.dex */
    public enum StringAttributeType {
        SPELLOUT,
        LOCALE
    }

    public static /* synthetic */ boolean lambda$shouldSetCollectionInfo$0(SemanticsNode semanticsNode, SemanticsNode semanticsNode2) {
        return semanticsNode2 == semanticsNode;
    }

    static /* synthetic */ int access$1172(AccessibilityBridge accessibilityBridge, int i) {
        int i2 = i & accessibilityBridge.accessibilityFeatureFlags;
        accessibilityBridge.accessibilityFeatureFlags = i2;
        return i2;
    }

    static /* synthetic */ int access$1176(AccessibilityBridge accessibilityBridge, int i) {
        int i2 = i | accessibilityBridge.accessibilityFeatureFlags;
        accessibilityBridge.accessibilityFeatureFlags = i2;
        return i2;
    }

    public int getHoveredObjectId() {
        return this.hoveredObject.id;
    }

    public AccessibilityBridge(View view, AccessibilityChannel accessibilityChannel, AccessibilityManager accessibilityManager, ContentResolver contentResolver, PlatformViewsAccessibilityDelegate platformViewsAccessibilityDelegate) {
        this(view, accessibilityChannel, accessibilityManager, contentResolver, new AccessibilityViewEmbedder(view, 65536), platformViewsAccessibilityDelegate);
    }

    public AccessibilityBridge(View view, AccessibilityChannel accessibilityChannel, final AccessibilityManager accessibilityManager, ContentResolver contentResolver, AccessibilityViewEmbedder accessibilityViewEmbedder, PlatformViewsAccessibilityDelegate platformViewsAccessibilityDelegate) {
        this.flutterSemanticsTree = new HashMap();
        this.customAccessibilityActions = new HashMap();
        this.accessibilityFeatureFlags = 0;
        this.flutterNavigationStack = new ArrayList();
        this.previousRouteId = 0;
        this.lastLeftFrameInset = 0;
        this.isReleased = false;
        this.accessibilityMessageHandler = new AccessibilityChannel.AccessibilityMessageHandler() { // from class: io.flutter.view.AccessibilityBridge.1
            @Override // io.flutter.embedding.engine.systemchannels.AccessibilityChannel.AccessibilityMessageHandler
            public void announce(String str) {
                AccessibilityBridge.this.rootAccessibilityView.announceForAccessibility(str);
            }

            @Override // io.flutter.embedding.engine.systemchannels.AccessibilityChannel.AccessibilityMessageHandler
            public void onTap(int i) {
                AccessibilityBridge.this.sendAccessibilityEvent(i, 1);
            }

            @Override // io.flutter.embedding.engine.systemchannels.AccessibilityChannel.AccessibilityMessageHandler
            public void onLongPress(int i) {
                AccessibilityBridge.this.sendAccessibilityEvent(i, 2);
            }

            @Override // io.flutter.embedding.engine.systemchannels.AccessibilityChannel.AccessibilityMessageHandler
            public void onTooltip(String str) {
                if (Build.VERSION.SDK_INT < 28) {
                    AccessibilityEvent obtainAccessibilityEvent = AccessibilityBridge.this.obtainAccessibilityEvent(0, 32);
                    obtainAccessibilityEvent.getText().add(str);
                    AccessibilityBridge.this.sendAccessibilityEvent(obtainAccessibilityEvent);
                }
            }

            @Override // io.flutter.embedding.engine.FlutterJNI.AccessibilityDelegate
            public void updateCustomAccessibilityActions(ByteBuffer byteBuffer, String[] strArr) {
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                AccessibilityBridge.this.updateCustomAccessibilityActions(byteBuffer, strArr);
            }

            @Override // io.flutter.embedding.engine.FlutterJNI.AccessibilityDelegate
            public void updateSemantics(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                for (ByteBuffer byteBuffer2 : byteBufferArr) {
                    byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
                }
                AccessibilityBridge.this.updateSemantics(byteBuffer, strArr, byteBufferArr);
            }
        };
        this.accessibilityStateChangeListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: io.flutter.view.AccessibilityBridge.2
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public void onAccessibilityStateChanged(boolean z) {
                if (!AccessibilityBridge.this.isReleased) {
                    if (z) {
                        AccessibilityBridge.this.accessibilityChannel.setAccessibilityMessageHandler(AccessibilityBridge.this.accessibilityMessageHandler);
                        AccessibilityBridge.this.accessibilityChannel.onAndroidAccessibilityEnabled();
                    } else {
                        AccessibilityBridge.this.accessibilityChannel.setAccessibilityMessageHandler(null);
                        AccessibilityBridge.this.accessibilityChannel.onAndroidAccessibilityDisabled();
                    }
                    if (AccessibilityBridge.this.onAccessibilityChangeListener != null) {
                        AccessibilityBridge.this.onAccessibilityChangeListener.onAccessibilityChanged(z, AccessibilityBridge.this.accessibilityManager.isTouchExplorationEnabled());
                    }
                }
            }
        };
        this.animationScaleObserver = new ContentObserver(new Handler()) { // from class: io.flutter.view.AccessibilityBridge.3
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                onChange(z, null);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                String str;
                if (!AccessibilityBridge.this.isReleased) {
                    if (Build.VERSION.SDK_INT < 17) {
                        str = null;
                    } else {
                        str = Settings.Global.getString(AccessibilityBridge.this.contentResolver, "transition_animation_scale");
                    }
                    if (str != null && str.equals("0")) {
                        AccessibilityBridge.access$1176(AccessibilityBridge.this, AccessibilityFeature.DISABLE_ANIMATIONS.value);
                    } else {
                        AccessibilityBridge.access$1172(AccessibilityBridge.this, ~AccessibilityFeature.DISABLE_ANIMATIONS.value);
                    }
                    AccessibilityBridge.this.sendLatestAccessibilityFlagsToFlutter();
                }
            }
        };
        this.rootAccessibilityView = view;
        this.accessibilityChannel = accessibilityChannel;
        this.accessibilityManager = accessibilityManager;
        this.contentResolver = contentResolver;
        this.accessibilityViewEmbedder = accessibilityViewEmbedder;
        this.platformViewsAccessibilityDelegate = platformViewsAccessibilityDelegate;
        this.accessibilityStateChangeListener.onAccessibilityStateChanged(accessibilityManager.isEnabled());
        this.accessibilityManager.addAccessibilityStateChangeListener(this.accessibilityStateChangeListener);
        if (Build.VERSION.SDK_INT >= 19) {
            this.touchExplorationStateChangeListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: io.flutter.view.AccessibilityBridge.4
                @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
                public void onTouchExplorationStateChanged(boolean z) {
                    if (!AccessibilityBridge.this.isReleased) {
                        if (z) {
                            AccessibilityBridge.access$1176(AccessibilityBridge.this, AccessibilityFeature.ACCESSIBLE_NAVIGATION.value);
                        } else {
                            AccessibilityBridge.this.onTouchExplorationExit();
                            AccessibilityBridge.access$1172(AccessibilityBridge.this, ~AccessibilityFeature.ACCESSIBLE_NAVIGATION.value);
                        }
                        AccessibilityBridge.this.sendLatestAccessibilityFlagsToFlutter();
                        if (AccessibilityBridge.this.onAccessibilityChangeListener != null) {
                            AccessibilityBridge.this.onAccessibilityChangeListener.onAccessibilityChanged(accessibilityManager.isEnabled(), z);
                        }
                    }
                }
            };
            this.touchExplorationStateChangeListener.onTouchExplorationStateChanged(accessibilityManager.isTouchExplorationEnabled());
            this.accessibilityManager.addTouchExplorationStateChangeListener(this.touchExplorationStateChangeListener);
        } else {
            this.touchExplorationStateChangeListener = null;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.animationScaleObserver.onChange(false);
            this.contentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, this.animationScaleObserver);
        }
        platformViewsAccessibilityDelegate.attachAccessibilityBridge(this);
    }

    public void release() {
        this.isReleased = true;
        this.platformViewsAccessibilityDelegate.detachAccessibilityBridge();
        setOnAccessibilityChangeListener(null);
        this.accessibilityManager.removeAccessibilityStateChangeListener(this.accessibilityStateChangeListener);
        if (Build.VERSION.SDK_INT >= 19) {
            this.accessibilityManager.removeTouchExplorationStateChangeListener(this.touchExplorationStateChangeListener);
        }
        this.contentResolver.unregisterContentObserver(this.animationScaleObserver);
        this.accessibilityChannel.setAccessibilityMessageHandler(null);
    }

    public boolean isAccessibilityEnabled() {
        return this.accessibilityManager.isEnabled();
    }

    public boolean isTouchExplorationEnabled() {
        return this.accessibilityManager.isTouchExplorationEnabled();
    }

    public void setOnAccessibilityChangeListener(OnAccessibilityChangeListener onAccessibilityChangeListener) {
        this.onAccessibilityChangeListener = onAccessibilityChangeListener;
    }

    public void sendLatestAccessibilityFlagsToFlutter() {
        this.accessibilityChannel.setAccessibilityFeatures(this.accessibilityFeatureFlags);
    }

    private boolean shouldSetCollectionInfo(SemanticsNode semanticsNode) {
        return semanticsNode.scrollChildren > 0 && (SemanticsNode.nullableHasAncestor(this.accessibilityFocusedSemanticsNode, new Predicate() { // from class: io.flutter.view.-$$Lambda$AccessibilityBridge$JBDpWWlF54eN0gel8Jn1hY7yOmE
            @Override // io.flutter.util.Predicate
            public final boolean test(Object obj) {
                return AccessibilityBridge.lambda$shouldSetCollectionInfo$0(AccessibilityBridge.SemanticsNode.this, (AccessibilityBridge.SemanticsNode) obj);
            }
        }) || !SemanticsNode.nullableHasAncestor(this.accessibilityFocusedSemanticsNode, $$Lambda$AccessibilityBridge$_xgAbWhyKyeHrMGUVUYzbSFEeBY.INSTANCE));
    }

    public AccessibilityNodeInfo obtainAccessibilityNodeInfo(View view, int i) {
        return AccessibilityNodeInfo.obtain(view, i);
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        int i2;
        SemanticsNode semanticsNode;
        if (i >= 65536) {
            return this.accessibilityViewEmbedder.createAccessibilityNodeInfo(i);
        }
        boolean z = false;
        if (i == -1) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(this.rootAccessibilityView);
            this.rootAccessibilityView.onInitializeAccessibilityNodeInfo(obtain);
            if (this.flutterSemanticsTree.containsKey(0)) {
                obtain.addChild(this.rootAccessibilityView, 0);
            }
            return obtain;
        }
        SemanticsNode semanticsNode2 = this.flutterSemanticsTree.get(Integer.valueOf(i));
        if (semanticsNode2 == null) {
            return null;
        }
        if (semanticsNode2.platformViewId != -1) {
            View platformViewById = this.platformViewsAccessibilityDelegate.getPlatformViewById(Integer.valueOf(semanticsNode2.platformViewId));
            if (this.platformViewsAccessibilityDelegate.usesVirtualDisplay(Integer.valueOf(semanticsNode2.platformViewId))) {
                return this.accessibilityViewEmbedder.getRootNode(platformViewById, semanticsNode2.id, semanticsNode2.getGlobalRect());
            }
        }
        AccessibilityNodeInfo obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(this.rootAccessibilityView, i);
        if (Build.VERSION.SDK_INT >= 18) {
            obtainAccessibilityNodeInfo.setViewIdResourceName("");
        }
        obtainAccessibilityNodeInfo.setPackageName(this.rootAccessibilityView.getContext().getPackageName());
        obtainAccessibilityNodeInfo.setClassName("android.view.View");
        obtainAccessibilityNodeInfo.setSource(this.rootAccessibilityView, i);
        obtainAccessibilityNodeInfo.setFocusable(semanticsNode2.isFocusable());
        SemanticsNode semanticsNode3 = this.inputFocusedSemanticsNode;
        if (semanticsNode3 != null) {
            obtainAccessibilityNodeInfo.setFocused(semanticsNode3.id == i);
        }
        SemanticsNode semanticsNode4 = this.accessibilityFocusedSemanticsNode;
        if (semanticsNode4 != null) {
            obtainAccessibilityNodeInfo.setAccessibilityFocused(semanticsNode4.id == i);
        }
        if (semanticsNode2.hasFlag(Flag.IS_TEXT_FIELD)) {
            obtainAccessibilityNodeInfo.setPassword(semanticsNode2.hasFlag(Flag.IS_OBSCURED));
            if (!semanticsNode2.hasFlag(Flag.IS_READ_ONLY)) {
                obtainAccessibilityNodeInfo.setClassName("android.widget.EditText");
            }
            if (Build.VERSION.SDK_INT >= 18) {
                obtainAccessibilityNodeInfo.setEditable(!semanticsNode2.hasFlag(Flag.IS_READ_ONLY));
                if (!(semanticsNode2.textSelectionBase == -1 || semanticsNode2.textSelectionExtent == -1)) {
                    obtainAccessibilityNodeInfo.setTextSelection(semanticsNode2.textSelectionBase, semanticsNode2.textSelectionExtent);
                }
                if (Build.VERSION.SDK_INT > 18 && (semanticsNode = this.accessibilityFocusedSemanticsNode) != null && semanticsNode.id == i) {
                    obtainAccessibilityNodeInfo.setLiveRegion(1);
                }
            }
            if (semanticsNode2.hasAction(Action.MOVE_CURSOR_FORWARD_BY_CHARACTER)) {
                obtainAccessibilityNodeInfo.addAction(256);
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (semanticsNode2.hasAction(Action.MOVE_CURSOR_BACKWARD_BY_CHARACTER)) {
                obtainAccessibilityNodeInfo.addAction(512);
                i2 |= 1;
            }
            if (semanticsNode2.hasAction(Action.MOVE_CURSOR_FORWARD_BY_WORD)) {
                obtainAccessibilityNodeInfo.addAction(256);
                i2 |= 2;
            }
            if (semanticsNode2.hasAction(Action.MOVE_CURSOR_BACKWARD_BY_WORD)) {
                obtainAccessibilityNodeInfo.addAction(512);
                i2 |= 2;
            }
            obtainAccessibilityNodeInfo.setMovementGranularities(i2);
            if (Build.VERSION.SDK_INT >= 21 && semanticsNode2.maxValueLength >= 0) {
                int length = semanticsNode2.value == null ? 0 : semanticsNode2.value.length();
                int unused = semanticsNode2.currentValueLength;
                int unused2 = semanticsNode2.maxValueLength;
                obtainAccessibilityNodeInfo.setMaxTextLength((length - semanticsNode2.currentValueLength) + semanticsNode2.maxValueLength);
            }
        }
        if (Build.VERSION.SDK_INT > 18) {
            if (semanticsNode2.hasAction(Action.SET_SELECTION)) {
                obtainAccessibilityNodeInfo.addAction(131072);
            }
            if (semanticsNode2.hasAction(Action.COPY)) {
                obtainAccessibilityNodeInfo.addAction(16384);
            }
            if (semanticsNode2.hasAction(Action.CUT)) {
                obtainAccessibilityNodeInfo.addAction(65536);
            }
            if (semanticsNode2.hasAction(Action.PASTE)) {
                obtainAccessibilityNodeInfo.addAction(32768);
            }
        }
        if (Build.VERSION.SDK_INT >= 21 && semanticsNode2.hasAction(Action.SET_TEXT)) {
            obtainAccessibilityNodeInfo.addAction(2097152);
        }
        if (semanticsNode2.hasFlag(Flag.IS_BUTTON) || semanticsNode2.hasFlag(Flag.IS_LINK)) {
            obtainAccessibilityNodeInfo.setClassName("android.widget.Button");
        }
        if (semanticsNode2.hasFlag(Flag.IS_IMAGE)) {
            obtainAccessibilityNodeInfo.setClassName("android.widget.ImageView");
        }
        if (Build.VERSION.SDK_INT > 18 && semanticsNode2.hasAction(Action.DISMISS)) {
            obtainAccessibilityNodeInfo.setDismissable(true);
            obtainAccessibilityNodeInfo.addAction(1048576);
        }
        if (semanticsNode2.parent != null) {
            obtainAccessibilityNodeInfo.setParent(this.rootAccessibilityView, semanticsNode2.parent.id);
        } else {
            obtainAccessibilityNodeInfo.setParent(this.rootAccessibilityView);
        }
        if (semanticsNode2.previousNodeId != -1 && Build.VERSION.SDK_INT >= 22) {
            obtainAccessibilityNodeInfo.setTraversalAfter(this.rootAccessibilityView, semanticsNode2.previousNodeId);
        }
        Rect globalRect = semanticsNode2.getGlobalRect();
        if (semanticsNode2.parent != null) {
            Rect globalRect2 = semanticsNode2.parent.getGlobalRect();
            Rect rect = new Rect(globalRect);
            rect.offset(-globalRect2.left, -globalRect2.top);
            obtainAccessibilityNodeInfo.setBoundsInParent(rect);
        } else {
            obtainAccessibilityNodeInfo.setBoundsInParent(globalRect);
        }
        obtainAccessibilityNodeInfo.setBoundsInScreen(getBoundsInScreen(globalRect));
        obtainAccessibilityNodeInfo.setVisibleToUser(true);
        obtainAccessibilityNodeInfo.setEnabled(!semanticsNode2.hasFlag(Flag.HAS_ENABLED_STATE) || semanticsNode2.hasFlag(Flag.IS_ENABLED));
        if (semanticsNode2.hasAction(Action.TAP)) {
            if (Build.VERSION.SDK_INT < 21 || semanticsNode2.onTapOverride == null) {
                obtainAccessibilityNodeInfo.addAction(16);
                obtainAccessibilityNodeInfo.setClickable(true);
            } else {
                obtainAccessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, semanticsNode2.onTapOverride.hint));
                obtainAccessibilityNodeInfo.setClickable(true);
            }
        }
        if (semanticsNode2.hasAction(Action.LONG_PRESS)) {
            if (Build.VERSION.SDK_INT < 21 || semanticsNode2.onLongPressOverride == null) {
                obtainAccessibilityNodeInfo.addAction(32);
                obtainAccessibilityNodeInfo.setLongClickable(true);
            } else {
                obtainAccessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, semanticsNode2.onLongPressOverride.hint));
                obtainAccessibilityNodeInfo.setLongClickable(true);
            }
        }
        if (semanticsNode2.hasAction(Action.SCROLL_LEFT) || semanticsNode2.hasAction(Action.SCROLL_UP) || semanticsNode2.hasAction(Action.SCROLL_RIGHT) || semanticsNode2.hasAction(Action.SCROLL_DOWN)) {
            obtainAccessibilityNodeInfo.setScrollable(true);
            if (semanticsNode2.hasFlag(Flag.HAS_IMPLICIT_SCROLLING)) {
                if (semanticsNode2.hasAction(Action.SCROLL_LEFT) || semanticsNode2.hasAction(Action.SCROLL_RIGHT)) {
                    if (Build.VERSION.SDK_INT <= 19 || !shouldSetCollectionInfo(semanticsNode2)) {
                        obtainAccessibilityNodeInfo.setClassName("android.widget.HorizontalScrollView");
                    } else {
                        obtainAccessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(0, semanticsNode2.scrollChildren, false));
                    }
                } else if (Build.VERSION.SDK_INT <= 18 || !shouldSetCollectionInfo(semanticsNode2)) {
                    obtainAccessibilityNodeInfo.setClassName("android.widget.ScrollView");
                } else {
                    obtainAccessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(semanticsNode2.scrollChildren, 0, false));
                }
            }
            if (semanticsNode2.hasAction(Action.SCROLL_LEFT) || semanticsNode2.hasAction(Action.SCROLL_UP)) {
                obtainAccessibilityNodeInfo.addAction(4096);
            }
            if (semanticsNode2.hasAction(Action.SCROLL_RIGHT) || semanticsNode2.hasAction(Action.SCROLL_DOWN)) {
                obtainAccessibilityNodeInfo.addAction(8192);
            }
        }
        if (semanticsNode2.hasAction(Action.INCREASE) || semanticsNode2.hasAction(Action.DECREASE)) {
            obtainAccessibilityNodeInfo.setClassName("android.widget.SeekBar");
            if (semanticsNode2.hasAction(Action.INCREASE)) {
                obtainAccessibilityNodeInfo.addAction(4096);
            }
            if (semanticsNode2.hasAction(Action.DECREASE)) {
                obtainAccessibilityNodeInfo.addAction(8192);
            }
        }
        if (semanticsNode2.hasFlag(Flag.IS_LIVE_REGION) && Build.VERSION.SDK_INT > 18) {
            obtainAccessibilityNodeInfo.setLiveRegion(1);
        }
        if (semanticsNode2.hasFlag(Flag.IS_TEXT_FIELD)) {
            obtainAccessibilityNodeInfo.setText(semanticsNode2.getValueLabelHint());
        } else if (!semanticsNode2.hasFlag(Flag.SCOPES_ROUTE)) {
            CharSequence valueLabelHint = semanticsNode2.getValueLabelHint();
            if (Build.VERSION.SDK_INT < 28 && semanticsNode2.tooltip != null) {
                if (valueLabelHint == null) {
                    valueLabelHint = "";
                }
                valueLabelHint = ((Object) valueLabelHint) + "\n" + semanticsNode2.tooltip;
            }
            if (valueLabelHint != null) {
                obtainAccessibilityNodeInfo.setContentDescription(valueLabelHint);
            }
        }
        if (Build.VERSION.SDK_INT >= 28 && semanticsNode2.tooltip != null) {
            obtainAccessibilityNodeInfo.setTooltipText(semanticsNode2.tooltip);
        }
        boolean hasFlag = semanticsNode2.hasFlag(Flag.HAS_CHECKED_STATE);
        boolean hasFlag2 = semanticsNode2.hasFlag(Flag.HAS_TOGGLED_STATE);
        if (hasFlag || hasFlag2) {
            z = true;
        }
        obtainAccessibilityNodeInfo.setCheckable(z);
        if (hasFlag) {
            obtainAccessibilityNodeInfo.setChecked(semanticsNode2.hasFlag(Flag.IS_CHECKED));
            if (semanticsNode2.hasFlag(Flag.IS_IN_MUTUALLY_EXCLUSIVE_GROUP)) {
                obtainAccessibilityNodeInfo.setClassName("android.widget.RadioButton");
            } else {
                obtainAccessibilityNodeInfo.setClassName("android.widget.CheckBox");
            }
        } else if (hasFlag2) {
            obtainAccessibilityNodeInfo.setChecked(semanticsNode2.hasFlag(Flag.IS_TOGGLED));
            obtainAccessibilityNodeInfo.setClassName("android.widget.Switch");
        }
        obtainAccessibilityNodeInfo.setSelected(semanticsNode2.hasFlag(Flag.IS_SELECTED));
        if (Build.VERSION.SDK_INT >= 28) {
            obtainAccessibilityNodeInfo.setHeading(semanticsNode2.hasFlag(Flag.IS_HEADER));
        }
        SemanticsNode semanticsNode5 = this.accessibilityFocusedSemanticsNode;
        if (semanticsNode5 == null || semanticsNode5.id != i) {
            obtainAccessibilityNodeInfo.addAction(64);
        } else {
            obtainAccessibilityNodeInfo.addAction(128);
        }
        if (Build.VERSION.SDK_INT >= 21 && semanticsNode2.customAccessibilityActions != null) {
            for (CustomAccessibilityAction customAccessibilityAction : semanticsNode2.customAccessibilityActions) {
                obtainAccessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(customAccessibilityAction.resourceId, customAccessibilityAction.label));
            }
        }
        for (SemanticsNode semanticsNode6 : semanticsNode2.childrenInTraversalOrder) {
            if (!semanticsNode6.hasFlag(Flag.IS_HIDDEN)) {
                if (semanticsNode6.platformViewId != -1) {
                    View platformViewById2 = this.platformViewsAccessibilityDelegate.getPlatformViewById(Integer.valueOf(semanticsNode6.platformViewId));
                    if (!this.platformViewsAccessibilityDelegate.usesVirtualDisplay(Integer.valueOf(semanticsNode6.platformViewId))) {
                        obtainAccessibilityNodeInfo.addChild(platformViewById2);
                    }
                }
                obtainAccessibilityNodeInfo.addChild(this.rootAccessibilityView, semanticsNode6.id);
            }
        }
        return obtainAccessibilityNodeInfo;
    }

    private Rect getBoundsInScreen(Rect rect) {
        Rect rect2 = new Rect(rect);
        int[] iArr = new int[2];
        this.rootAccessibilityView.getLocationOnScreen(iArr);
        rect2.offset(iArr[0], iArr[1]);
        return rect2;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public boolean performAction(int i, int i2, Bundle bundle) {
        if (i >= 65536) {
            boolean performAction = this.accessibilityViewEmbedder.performAction(i, i2, bundle);
            if (performAction && i2 == 128) {
                this.embeddedAccessibilityFocusedNodeId = null;
            }
            return performAction;
        }
        SemanticsNode semanticsNode = this.flutterSemanticsTree.get(Integer.valueOf(i));
        boolean z = false;
        if (semanticsNode == null) {
            return false;
        }
        switch (i2) {
            case 16:
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.TAP);
                return true;
            case 32:
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.LONG_PRESS);
                return true;
            case 64:
                if (this.accessibilityFocusedSemanticsNode == null) {
                    this.rootAccessibilityView.invalidate();
                }
                this.accessibilityFocusedSemanticsNode = semanticsNode;
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.DID_GAIN_ACCESSIBILITY_FOCUS);
                sendAccessibilityEvent(i, 32768);
                if (semanticsNode.hasAction(Action.INCREASE) || semanticsNode.hasAction(Action.DECREASE)) {
                    sendAccessibilityEvent(i, 4);
                }
                return true;
            case 128:
                SemanticsNode semanticsNode2 = this.accessibilityFocusedSemanticsNode;
                if (semanticsNode2 != null && semanticsNode2.id == i) {
                    this.accessibilityFocusedSemanticsNode = null;
                }
                Integer num = this.embeddedAccessibilityFocusedNodeId;
                if (num != null && num.intValue() == i) {
                    this.embeddedAccessibilityFocusedNodeId = null;
                }
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.DID_LOSE_ACCESSIBILITY_FOCUS);
                sendAccessibilityEvent(i, 65536);
                return true;
            case 256:
                if (Build.VERSION.SDK_INT < 18) {
                    return false;
                }
                return performCursorMoveAction(semanticsNode, i, bundle, true);
            case 512:
                if (Build.VERSION.SDK_INT < 18) {
                    return false;
                }
                return performCursorMoveAction(semanticsNode, i, bundle, false);
            case 4096:
                if (semanticsNode.hasAction(Action.SCROLL_UP)) {
                    this.accessibilityChannel.dispatchSemanticsAction(i, Action.SCROLL_UP);
                } else if (semanticsNode.hasAction(Action.SCROLL_LEFT)) {
                    this.accessibilityChannel.dispatchSemanticsAction(i, Action.SCROLL_LEFT);
                } else if (!semanticsNode.hasAction(Action.INCREASE)) {
                    return false;
                } else {
                    semanticsNode.value = semanticsNode.increasedValue;
                    semanticsNode.valueAttributes = semanticsNode.increasedValueAttributes;
                    sendAccessibilityEvent(i, 4);
                    this.accessibilityChannel.dispatchSemanticsAction(i, Action.INCREASE);
                }
                return true;
            case 8192:
                if (semanticsNode.hasAction(Action.SCROLL_DOWN)) {
                    this.accessibilityChannel.dispatchSemanticsAction(i, Action.SCROLL_DOWN);
                } else if (semanticsNode.hasAction(Action.SCROLL_RIGHT)) {
                    this.accessibilityChannel.dispatchSemanticsAction(i, Action.SCROLL_RIGHT);
                } else if (!semanticsNode.hasAction(Action.DECREASE)) {
                    return false;
                } else {
                    semanticsNode.value = semanticsNode.decreasedValue;
                    semanticsNode.valueAttributes = semanticsNode.decreasedValueAttributes;
                    sendAccessibilityEvent(i, 4);
                    this.accessibilityChannel.dispatchSemanticsAction(i, Action.DECREASE);
                }
                return true;
            case 16384:
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.COPY);
                return true;
            case 32768:
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.PASTE);
                return true;
            case 65536:
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.CUT);
                return true;
            case 131072:
                if (Build.VERSION.SDK_INT < 18) {
                    return false;
                }
                HashMap hashMap = new HashMap();
                if (bundle != null && bundle.containsKey("ACTION_ARGUMENT_SELECTION_START_INT") && bundle.containsKey("ACTION_ARGUMENT_SELECTION_END_INT")) {
                    z = true;
                }
                if (z) {
                    hashMap.put(TtmlNode.RUBY_BASE, Integer.valueOf(bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT")));
                    hashMap.put(TtmlNode.ATTR_TTS_EXTENT, Integer.valueOf(bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT")));
                } else {
                    hashMap.put(TtmlNode.RUBY_BASE, Integer.valueOf(semanticsNode.textSelectionExtent));
                    hashMap.put(TtmlNode.ATTR_TTS_EXTENT, Integer.valueOf(semanticsNode.textSelectionExtent));
                }
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.SET_SELECTION, hashMap);
                SemanticsNode semanticsNode3 = this.flutterSemanticsTree.get(Integer.valueOf(i));
                semanticsNode3.textSelectionBase = ((Integer) hashMap.get(TtmlNode.RUBY_BASE)).intValue();
                semanticsNode3.textSelectionExtent = ((Integer) hashMap.get(TtmlNode.ATTR_TTS_EXTENT)).intValue();
                return true;
            case 1048576:
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.DISMISS);
                return true;
            case 2097152:
                if (Build.VERSION.SDK_INT < 21) {
                    return false;
                }
                return performSetText(semanticsNode, i, bundle);
            case 16908342:
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.SHOW_ON_SCREEN);
                return true;
            default:
                CustomAccessibilityAction customAccessibilityAction = this.customAccessibilityActions.get(Integer.valueOf(i2 - FIRST_RESOURCE_ID));
                if (customAccessibilityAction == null) {
                    return false;
                }
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.CUSTOM_ACTION, Integer.valueOf(customAccessibilityAction.id));
                return true;
        }
    }

    private boolean performCursorMoveAction(SemanticsNode semanticsNode, int i, Bundle bundle, boolean z) {
        String str;
        int i2 = bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
        boolean z2 = bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
        int i3 = semanticsNode.textSelectionBase;
        int i4 = semanticsNode.textSelectionExtent;
        predictCursorMovement(semanticsNode, i2, z, z2);
        if (!(i3 == semanticsNode.textSelectionBase && i4 == semanticsNode.textSelectionExtent)) {
            if (semanticsNode.value != null) {
                str = semanticsNode.value;
            } else {
                str = "";
            }
            AccessibilityEvent obtainAccessibilityEvent = obtainAccessibilityEvent(semanticsNode.id, 8192);
            obtainAccessibilityEvent.getText().add(str);
            obtainAccessibilityEvent.setFromIndex(semanticsNode.textSelectionBase);
            obtainAccessibilityEvent.setToIndex(semanticsNode.textSelectionExtent);
            obtainAccessibilityEvent.setItemCount(str.length());
            sendAccessibilityEvent(obtainAccessibilityEvent);
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 4 || i2 == 8 || i2 == 16) {
                    return true;
                }
                return false;
            } else if (z && semanticsNode.hasAction(Action.MOVE_CURSOR_FORWARD_BY_WORD)) {
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.MOVE_CURSOR_FORWARD_BY_WORD, Boolean.valueOf(z2));
                return true;
            } else if (z || !semanticsNode.hasAction(Action.MOVE_CURSOR_BACKWARD_BY_WORD)) {
                return false;
            } else {
                this.accessibilityChannel.dispatchSemanticsAction(i, Action.MOVE_CURSOR_BACKWARD_BY_WORD, Boolean.valueOf(z2));
                return true;
            }
        } else if (z && semanticsNode.hasAction(Action.MOVE_CURSOR_FORWARD_BY_CHARACTER)) {
            this.accessibilityChannel.dispatchSemanticsAction(i, Action.MOVE_CURSOR_FORWARD_BY_CHARACTER, Boolean.valueOf(z2));
            return true;
        } else if (z || !semanticsNode.hasAction(Action.MOVE_CURSOR_BACKWARD_BY_CHARACTER)) {
            return false;
        } else {
            this.accessibilityChannel.dispatchSemanticsAction(i, Action.MOVE_CURSOR_BACKWARD_BY_CHARACTER, Boolean.valueOf(z2));
            return true;
        }
    }

    private void predictCursorMovement(SemanticsNode semanticsNode, int i, boolean z, boolean z2) {
        if (semanticsNode.textSelectionExtent >= 0 && semanticsNode.textSelectionBase >= 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 4) {
                        if (i == 8 || i == 16) {
                            if (z) {
                                semanticsNode.textSelectionExtent = semanticsNode.value.length();
                            } else {
                                semanticsNode.textSelectionExtent = 0;
                            }
                        }
                    } else if (z && semanticsNode.textSelectionExtent < semanticsNode.value.length()) {
                        Matcher matcher = Pattern.compile("(?!^)(\\n)").matcher(semanticsNode.value.substring(semanticsNode.textSelectionExtent));
                        if (matcher.find()) {
                            SemanticsNode.access$2112(semanticsNode, matcher.start(1));
                        } else {
                            semanticsNode.textSelectionExtent = semanticsNode.value.length();
                        }
                    } else if (!z && semanticsNode.textSelectionExtent > 0) {
                        Matcher matcher2 = Pattern.compile("(?s:.*)(\\n)").matcher(semanticsNode.value.substring(0, semanticsNode.textSelectionExtent));
                        if (matcher2.find()) {
                            semanticsNode.textSelectionExtent = matcher2.start(1);
                        } else {
                            semanticsNode.textSelectionExtent = 0;
                        }
                    }
                } else if (z && semanticsNode.textSelectionExtent < semanticsNode.value.length()) {
                    Matcher matcher3 = Pattern.compile("\\p{L}(\\b)").matcher(semanticsNode.value.substring(semanticsNode.textSelectionExtent));
                    matcher3.find();
                    if (matcher3.find()) {
                        SemanticsNode.access$2112(semanticsNode, matcher3.start(1));
                    } else {
                        semanticsNode.textSelectionExtent = semanticsNode.value.length();
                    }
                } else if (!z && semanticsNode.textSelectionExtent > 0) {
                    Matcher matcher4 = Pattern.compile("(?s:.*)(\\b)\\p{L}").matcher(semanticsNode.value.substring(0, semanticsNode.textSelectionExtent));
                    if (matcher4.find()) {
                        semanticsNode.textSelectionExtent = matcher4.start(1);
                    }
                }
            } else if (z && semanticsNode.textSelectionExtent < semanticsNode.value.length()) {
                SemanticsNode.access$2112(semanticsNode, 1);
            } else if (!z && semanticsNode.textSelectionExtent > 0) {
                SemanticsNode.access$2120(semanticsNode, 1);
            }
            if (!z2) {
                semanticsNode.textSelectionBase = semanticsNode.textSelectionExtent;
            }
        }
    }

    private boolean performSetText(SemanticsNode semanticsNode, int i, Bundle bundle) {
        String string = (bundle == null || !bundle.containsKey("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE")) ? "" : bundle.getString("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE");
        this.accessibilityChannel.dispatchSemanticsAction(i, Action.SET_TEXT, string);
        semanticsNode.value = string;
        return true;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public AccessibilityNodeInfo findFocus(int i) {
        if (i == 1) {
            SemanticsNode semanticsNode = this.inputFocusedSemanticsNode;
            if (semanticsNode != null) {
                return createAccessibilityNodeInfo(semanticsNode.id);
            }
            Integer num = this.embeddedInputFocusedNodeId;
            if (num != null) {
                return createAccessibilityNodeInfo(num.intValue());
            }
        } else if (i != 2) {
            return null;
        }
        SemanticsNode semanticsNode2 = this.accessibilityFocusedSemanticsNode;
        if (semanticsNode2 != null) {
            return createAccessibilityNodeInfo(semanticsNode2.id);
        }
        Integer num2 = this.embeddedAccessibilityFocusedNodeId;
        if (num2 != null) {
            return createAccessibilityNodeInfo(num2.intValue());
        }
        return null;
    }

    private SemanticsNode getRootSemanticsNode() {
        return this.flutterSemanticsTree.get(0);
    }

    public SemanticsNode getOrCreateSemanticsNode(int i) {
        SemanticsNode semanticsNode = this.flutterSemanticsTree.get(Integer.valueOf(i));
        if (semanticsNode != null) {
            return semanticsNode;
        }
        SemanticsNode semanticsNode2 = new SemanticsNode(this);
        semanticsNode2.id = i;
        this.flutterSemanticsTree.put(Integer.valueOf(i), semanticsNode2);
        return semanticsNode2;
    }

    public CustomAccessibilityAction getOrCreateAccessibilityAction(int i) {
        CustomAccessibilityAction customAccessibilityAction = this.customAccessibilityActions.get(Integer.valueOf(i));
        if (customAccessibilityAction != null) {
            return customAccessibilityAction;
        }
        CustomAccessibilityAction customAccessibilityAction2 = new CustomAccessibilityAction();
        customAccessibilityAction2.id = i;
        customAccessibilityAction2.resourceId = FIRST_RESOURCE_ID + i;
        this.customAccessibilityActions.put(Integer.valueOf(i), customAccessibilityAction2);
        return customAccessibilityAction2;
    }

    public boolean onAccessibilityHoverEvent(MotionEvent motionEvent) {
        if (!this.accessibilityManager.isTouchExplorationEnabled() || this.flutterSemanticsTree.isEmpty()) {
            return false;
        }
        SemanticsNode hitTest = getRootSemanticsNode().hitTest(new float[]{motionEvent.getX(), motionEvent.getY(), 0.0f, 1.0f});
        if (hitTest != null && hitTest.platformViewId != -1) {
            return this.accessibilityViewEmbedder.onAccessibilityHoverEvent(hitTest.id, motionEvent);
        }
        if (motionEvent.getAction() == 9 || motionEvent.getAction() == 7) {
            handleTouchExploration(motionEvent.getX(), motionEvent.getY());
        } else if (motionEvent.getAction() == 10) {
            onTouchExplorationExit();
        } else {
            Log.d("flutter", "unexpected accessibility hover event: " + motionEvent);
            return false;
        }
        return true;
    }

    public void onTouchExplorationExit() {
        SemanticsNode semanticsNode = this.hoveredObject;
        if (semanticsNode != null) {
            sendAccessibilityEvent(semanticsNode.id, 256);
            this.hoveredObject = null;
        }
    }

    private void handleTouchExploration(float f, float f2) {
        SemanticsNode hitTest;
        if (!this.flutterSemanticsTree.isEmpty() && (hitTest = getRootSemanticsNode().hitTest(new float[]{f, f2, 0.0f, 1.0f})) != this.hoveredObject) {
            if (hitTest != null) {
                sendAccessibilityEvent(hitTest.id, 128);
            }
            SemanticsNode semanticsNode = this.hoveredObject;
            if (semanticsNode != null) {
                sendAccessibilityEvent(semanticsNode.id, 256);
            }
            this.hoveredObject = hitTest;
        }
    }

    void updateCustomAccessibilityActions(ByteBuffer byteBuffer, String[] strArr) {
        String str;
        while (byteBuffer.hasRemaining()) {
            CustomAccessibilityAction orCreateAccessibilityAction = getOrCreateAccessibilityAction(byteBuffer.getInt());
            orCreateAccessibilityAction.overrideId = byteBuffer.getInt();
            int i = byteBuffer.getInt();
            String str2 = null;
            if (i == -1) {
                str = null;
            } else {
                str = strArr[i];
            }
            orCreateAccessibilityAction.label = str;
            int i2 = byteBuffer.getInt();
            if (i2 != -1) {
                str2 = strArr[i2];
            }
            orCreateAccessibilityAction.hint = str2;
        }
    }

    void updateSemantics(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
        SemanticsNode semanticsNode;
        String str;
        SemanticsNode semanticsNode2;
        float f;
        float f2;
        WindowInsets rootWindowInsets;
        View platformViewById;
        ArrayList arrayList = new ArrayList();
        while (byteBuffer.hasRemaining()) {
            SemanticsNode orCreateSemanticsNode = getOrCreateSemanticsNode(byteBuffer.getInt());
            orCreateSemanticsNode.updateWith(byteBuffer, strArr, byteBufferArr);
            if (!orCreateSemanticsNode.hasFlag(Flag.IS_HIDDEN)) {
                if (orCreateSemanticsNode.hasFlag(Flag.IS_FOCUSED)) {
                    this.inputFocusedSemanticsNode = orCreateSemanticsNode;
                }
                if (orCreateSemanticsNode.hadPreviousConfig) {
                    arrayList.add(orCreateSemanticsNode);
                }
                if (!(orCreateSemanticsNode.platformViewId == -1 || this.platformViewsAccessibilityDelegate.usesVirtualDisplay(Integer.valueOf(orCreateSemanticsNode.platformViewId)) || (platformViewById = this.platformViewsAccessibilityDelegate.getPlatformViewById(Integer.valueOf(orCreateSemanticsNode.platformViewId))) == null)) {
                    platformViewById.setImportantForAccessibility(0);
                }
            }
        }
        HashSet hashSet = new HashSet();
        SemanticsNode rootSemanticsNode = getRootSemanticsNode();
        ArrayList<SemanticsNode> arrayList2 = new ArrayList();
        if (rootSemanticsNode != null) {
            float[] fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                if ((Build.VERSION.SDK_INT >= 28 ? doesLayoutInDisplayCutoutModeRequireLeftInset() : true) && (rootWindowInsets = this.rootAccessibilityView.getRootWindowInsets()) != null) {
                    if (!this.lastLeftFrameInset.equals(Integer.valueOf(rootWindowInsets.getSystemWindowInsetLeft()))) {
                        rootSemanticsNode.globalGeometryDirty = true;
                        rootSemanticsNode.inverseTransformDirty = true;
                    }
                    this.lastLeftFrameInset = Integer.valueOf(rootWindowInsets.getSystemWindowInsetLeft());
                    Matrix.translateM(fArr, 0, (float) this.lastLeftFrameInset.intValue(), 0.0f, 0.0f);
                }
            }
            rootSemanticsNode.updateRecursively(fArr, hashSet, false);
            rootSemanticsNode.collectRoutes(arrayList2);
        }
        SemanticsNode semanticsNode3 = null;
        for (SemanticsNode semanticsNode4 : arrayList2) {
            if (!this.flutterNavigationStack.contains(Integer.valueOf(semanticsNode4.id))) {
                semanticsNode3 = semanticsNode4;
            }
        }
        if (semanticsNode3 == null && arrayList2.size() > 0) {
            semanticsNode3 = (SemanticsNode) arrayList2.get(arrayList2.size() - 1);
        }
        if (!(semanticsNode3 == null || (semanticsNode3.id == this.previousRouteId && arrayList2.size() == this.flutterNavigationStack.size()))) {
            this.previousRouteId = semanticsNode3.id;
            onWindowNameChange(semanticsNode3);
        }
        this.flutterNavigationStack.clear();
        for (SemanticsNode semanticsNode5 : arrayList2) {
            this.flutterNavigationStack.add(Integer.valueOf(semanticsNode5.id));
        }
        Iterator<Map.Entry<Integer, SemanticsNode>> it = this.flutterSemanticsTree.entrySet().iterator();
        while (it.hasNext()) {
            SemanticsNode value = it.next().getValue();
            if (!hashSet.contains(value)) {
                willRemoveSemanticsNode(value);
                it.remove();
            }
        }
        sendWindowContentChangeEvent(0);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            SemanticsNode semanticsNode6 = (SemanticsNode) it2.next();
            if (semanticsNode6.didScroll()) {
                AccessibilityEvent obtainAccessibilityEvent = obtainAccessibilityEvent(semanticsNode6.id, 4096);
                float f3 = semanticsNode6.scrollPosition;
                float f4 = semanticsNode6.scrollExtentMax;
                if (Float.isInfinite(semanticsNode6.scrollExtentMax)) {
                    if (f3 > 70000.0f) {
                        f3 = 70000.0f;
                    }
                    f4 = 100000.0f;
                }
                if (Float.isInfinite(semanticsNode6.scrollExtentMin)) {
                    f = f4 + 100000.0f;
                    if (f3 < -70000.0f) {
                        f3 = -70000.0f;
                    }
                    f2 = f3 + 100000.0f;
                } else {
                    f = f4 - semanticsNode6.scrollExtentMin;
                    f2 = f3 - semanticsNode6.scrollExtentMin;
                }
                if (semanticsNode6.hadAction(Action.SCROLL_UP) || semanticsNode6.hadAction(Action.SCROLL_DOWN)) {
                    obtainAccessibilityEvent.setScrollY((int) f2);
                    obtainAccessibilityEvent.setMaxScrollY((int) f);
                } else if (semanticsNode6.hadAction(Action.SCROLL_LEFT) || semanticsNode6.hadAction(Action.SCROLL_RIGHT)) {
                    obtainAccessibilityEvent.setScrollX((int) f2);
                    obtainAccessibilityEvent.setMaxScrollX((int) f);
                }
                if (semanticsNode6.scrollChildren > 0) {
                    obtainAccessibilityEvent.setItemCount(semanticsNode6.scrollChildren);
                    obtainAccessibilityEvent.setFromIndex(semanticsNode6.scrollIndex);
                    int i = 0;
                    for (SemanticsNode semanticsNode7 : semanticsNode6.childrenInHitTestOrder) {
                        if (!semanticsNode7.hasFlag(Flag.IS_HIDDEN)) {
                            i++;
                        }
                    }
                    obtainAccessibilityEvent.setToIndex((semanticsNode6.scrollIndex + i) - 1);
                }
                sendAccessibilityEvent(obtainAccessibilityEvent);
            }
            if (semanticsNode6.hasFlag(Flag.IS_LIVE_REGION) && semanticsNode6.didChangeLabel()) {
                sendWindowContentChangeEvent(semanticsNode6.id);
            }
            SemanticsNode semanticsNode8 = this.accessibilityFocusedSemanticsNode;
            if (semanticsNode8 != null && semanticsNode8.id == semanticsNode6.id && !semanticsNode6.hadFlag(Flag.IS_SELECTED) && semanticsNode6.hasFlag(Flag.IS_SELECTED)) {
                AccessibilityEvent obtainAccessibilityEvent2 = obtainAccessibilityEvent(semanticsNode6.id, 4);
                obtainAccessibilityEvent2.getText().add(semanticsNode6.label);
                sendAccessibilityEvent(obtainAccessibilityEvent2);
            }
            SemanticsNode semanticsNode9 = this.inputFocusedSemanticsNode;
            if (semanticsNode9 != null && semanticsNode9.id == semanticsNode6.id && ((semanticsNode2 = this.lastInputFocusedSemanticsNode) == null || semanticsNode2.id != this.inputFocusedSemanticsNode.id)) {
                this.lastInputFocusedSemanticsNode = this.inputFocusedSemanticsNode;
                sendAccessibilityEvent(obtainAccessibilityEvent(semanticsNode6.id, 8));
            } else if (this.inputFocusedSemanticsNode == null) {
                this.lastInputFocusedSemanticsNode = null;
            }
            SemanticsNode semanticsNode10 = this.inputFocusedSemanticsNode;
            if (semanticsNode10 != null && semanticsNode10.id == semanticsNode6.id && semanticsNode6.hadFlag(Flag.IS_TEXT_FIELD) && semanticsNode6.hasFlag(Flag.IS_TEXT_FIELD) && ((semanticsNode = this.accessibilityFocusedSemanticsNode) == null || semanticsNode.id == this.inputFocusedSemanticsNode.id)) {
                String str2 = "";
                if (semanticsNode6.previousValue != null) {
                    str = semanticsNode6.previousValue;
                } else {
                    str = str2;
                }
                if (semanticsNode6.value != null) {
                    str2 = semanticsNode6.value;
                }
                AccessibilityEvent createTextChangedEvent = createTextChangedEvent(semanticsNode6.id, str, str2);
                if (createTextChangedEvent != null) {
                    sendAccessibilityEvent(createTextChangedEvent);
                }
                if (semanticsNode6.previousTextSelectionBase != semanticsNode6.textSelectionBase || semanticsNode6.previousTextSelectionExtent != semanticsNode6.textSelectionExtent) {
                    AccessibilityEvent obtainAccessibilityEvent3 = obtainAccessibilityEvent(semanticsNode6.id, 8192);
                    obtainAccessibilityEvent3.getText().add(str2);
                    obtainAccessibilityEvent3.setFromIndex(semanticsNode6.textSelectionBase);
                    obtainAccessibilityEvent3.setToIndex(semanticsNode6.textSelectionExtent);
                    obtainAccessibilityEvent3.setItemCount(str2.length());
                    sendAccessibilityEvent(obtainAccessibilityEvent3);
                }
            }
        }
    }

    private AccessibilityEvent createTextChangedEvent(int i, String str, String str2) {
        AccessibilityEvent obtainAccessibilityEvent = obtainAccessibilityEvent(i, 16);
        obtainAccessibilityEvent.setBeforeText(str);
        obtainAccessibilityEvent.getText().add(str2);
        int i2 = 0;
        while (i2 < str.length() && i2 < str2.length() && str.charAt(i2) == str2.charAt(i2)) {
            i2++;
        }
        if (i2 >= str.length() && i2 >= str2.length()) {
            return null;
        }
        obtainAccessibilityEvent.setFromIndex(i2);
        int length = str.length() - 1;
        int length2 = str2.length() - 1;
        while (length >= i2 && length2 >= i2 && str.charAt(length) == str2.charAt(length2)) {
            length--;
            length2--;
        }
        obtainAccessibilityEvent.setRemovedCount((length - i2) + 1);
        obtainAccessibilityEvent.setAddedCount((length2 - i2) + 1);
        return obtainAccessibilityEvent;
    }

    public void sendAccessibilityEvent(int i, int i2) {
        if (this.accessibilityManager.isEnabled()) {
            sendAccessibilityEvent(obtainAccessibilityEvent(i, i2));
        }
    }

    public void sendAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (this.accessibilityManager.isEnabled()) {
            this.rootAccessibilityView.getParent().requestSendAccessibilityEvent(this.rootAccessibilityView, accessibilityEvent);
        }
    }

    private void onWindowNameChange(SemanticsNode semanticsNode) {
        String routeName = semanticsNode.getRouteName();
        if (routeName == null) {
            routeName = " ";
        }
        if (Build.VERSION.SDK_INT >= 28) {
            setAccessibilityPaneTitle(routeName);
            return;
        }
        AccessibilityEvent obtainAccessibilityEvent = obtainAccessibilityEvent(semanticsNode.id, 32);
        obtainAccessibilityEvent.getText().add(routeName);
        sendAccessibilityEvent(obtainAccessibilityEvent);
    }

    private void setAccessibilityPaneTitle(String str) {
        this.rootAccessibilityView.setAccessibilityPaneTitle(str);
    }

    private void sendWindowContentChangeEvent(int i) {
        AccessibilityEvent obtainAccessibilityEvent = obtainAccessibilityEvent(i, 2048);
        if (Build.VERSION.SDK_INT >= 19) {
            obtainAccessibilityEvent.setContentChangeTypes(1);
        }
        sendAccessibilityEvent(obtainAccessibilityEvent);
    }

    public AccessibilityEvent obtainAccessibilityEvent(int i, int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        obtain.setPackageName(this.rootAccessibilityView.getContext().getPackageName());
        obtain.setSource(this.rootAccessibilityView, i);
        return obtain;
    }

    private boolean doesLayoutInDisplayCutoutModeRequireLeftInset() {
        Activity activity = ViewUtils.getActivity(this.rootAccessibilityView.getContext());
        if (activity == null || activity.getWindow() == null) {
            return false;
        }
        int i = activity.getWindow().getAttributes().layoutInDisplayCutoutMode;
        if (i == 2 || i == 0) {
            return true;
        }
        return false;
    }

    private void willRemoveSemanticsNode(SemanticsNode semanticsNode) {
        View platformViewById;
        Integer num;
        semanticsNode.parent = null;
        if (!(semanticsNode.platformViewId == -1 || (num = this.embeddedAccessibilityFocusedNodeId) == null || this.accessibilityViewEmbedder.platformViewOfNode(num.intValue()) != this.platformViewsAccessibilityDelegate.getPlatformViewById(Integer.valueOf(semanticsNode.platformViewId)))) {
            sendAccessibilityEvent(this.embeddedAccessibilityFocusedNodeId.intValue(), 65536);
            this.embeddedAccessibilityFocusedNodeId = null;
        }
        if (!(semanticsNode.platformViewId == -1 || this.platformViewsAccessibilityDelegate.usesVirtualDisplay(Integer.valueOf(semanticsNode.platformViewId)) || (platformViewById = this.platformViewsAccessibilityDelegate.getPlatformViewById(Integer.valueOf(semanticsNode.platformViewId))) == null)) {
            platformViewById.setImportantForAccessibility(4);
        }
        SemanticsNode semanticsNode2 = this.accessibilityFocusedSemanticsNode;
        if (semanticsNode2 == semanticsNode) {
            sendAccessibilityEvent(semanticsNode2.id, 65536);
            this.accessibilityFocusedSemanticsNode = null;
        }
        if (this.inputFocusedSemanticsNode == semanticsNode) {
            this.inputFocusedSemanticsNode = null;
        }
        if (this.hoveredObject == semanticsNode) {
            this.hoveredObject = null;
        }
    }

    public void reset() {
        this.flutterSemanticsTree.clear();
        SemanticsNode semanticsNode = this.accessibilityFocusedSemanticsNode;
        if (semanticsNode != null) {
            sendAccessibilityEvent(semanticsNode.id, 65536);
        }
        this.accessibilityFocusedSemanticsNode = null;
        this.hoveredObject = null;
        sendWindowContentChangeEvent(0);
    }

    /* loaded from: classes5.dex */
    public enum Action {
        TAP(1),
        LONG_PRESS(2),
        SCROLL_LEFT(4),
        SCROLL_RIGHT(8),
        SCROLL_UP(16),
        SCROLL_DOWN(32),
        INCREASE(64),
        DECREASE(128),
        SHOW_ON_SCREEN(256),
        MOVE_CURSOR_FORWARD_BY_CHARACTER(512),
        MOVE_CURSOR_BACKWARD_BY_CHARACTER(1024),
        SET_SELECTION(2048),
        COPY(4096),
        CUT(8192),
        PASTE(16384),
        DID_GAIN_ACCESSIBILITY_FOCUS(32768),
        DID_LOSE_ACCESSIBILITY_FOCUS(65536),
        CUSTOM_ACTION(131072),
        DISMISS(262144),
        MOVE_CURSOR_FORWARD_BY_WORD(524288),
        MOVE_CURSOR_BACKWARD_BY_WORD(1048576),
        SET_TEXT(2097152);
        
        public final int value;

        Action(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes5.dex */
    public enum Flag {
        HAS_CHECKED_STATE(1),
        IS_CHECKED(2),
        IS_SELECTED(4),
        IS_BUTTON(8),
        IS_TEXT_FIELD(16),
        IS_FOCUSED(32),
        HAS_ENABLED_STATE(64),
        IS_ENABLED(128),
        IS_IN_MUTUALLY_EXCLUSIVE_GROUP(256),
        IS_HEADER(512),
        IS_OBSCURED(1024),
        SCOPES_ROUTE(2048),
        NAMES_ROUTE(4096),
        IS_HIDDEN(8192),
        IS_IMAGE(16384),
        IS_LIVE_REGION(32768),
        HAS_TOGGLED_STATE(65536),
        IS_TOGGLED(131072),
        HAS_IMPLICIT_SCROLLING(262144),
        IS_READ_ONLY(1048576),
        IS_FOCUSABLE(2097152),
        IS_LINK(4194304),
        IS_SLIDER(8388608),
        IS_KEYBOARD_KEY(16777216);
        
        final int value;

        Flag(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes5.dex */
    public enum AccessibilityFeature {
        ACCESSIBLE_NAVIGATION(1),
        INVERT_COLORS(2),
        DISABLE_ANIMATIONS(4);
        
        final int value;

        AccessibilityFeature(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes5.dex */
    public enum TextDirection {
        UNKNOWN,
        LTR,
        RTL;

        public static TextDirection fromInt(int i) {
            if (i == 1) {
                return RTL;
            }
            if (i != 2) {
                return UNKNOWN;
            }
            return LTR;
        }
    }

    /* loaded from: classes5.dex */
    public static class CustomAccessibilityAction {
        private String hint;
        private String label;
        private int resourceId = -1;
        private int id = -1;
        private int overrideId = -1;

        CustomAccessibilityAction() {
        }
    }

    /* loaded from: classes5.dex */
    public static class StringAttribute {
        int end;
        int start;
        StringAttributeType type;

        private StringAttribute() {
        }
    }

    /* loaded from: classes5.dex */
    public static class SpellOutStringAttribute extends StringAttribute {
        private SpellOutStringAttribute() {
            super();
        }
    }

    /* loaded from: classes5.dex */
    public static class LocaleStringAttribute extends StringAttribute {
        String locale;

        private LocaleStringAttribute() {
            super();
        }
    }

    /* loaded from: classes5.dex */
    public static class SemanticsNode {
        final AccessibilityBridge accessibilityBridge;
        private int actions;
        private float bottom;
        private int currentValueLength;
        private List<CustomAccessibilityAction> customAccessibilityActions;
        private String decreasedValue;
        private List<StringAttribute> decreasedValueAttributes;
        private int flags;
        private Rect globalRect;
        private float[] globalTransform;
        private String hint;
        private List<StringAttribute> hintAttributes;
        private String increasedValue;
        private List<StringAttribute> increasedValueAttributes;
        private float[] inverseTransform;
        private String label;
        private List<StringAttribute> labelAttributes;
        private float left;
        private int maxValueLength;
        private CustomAccessibilityAction onLongPressOverride;
        private CustomAccessibilityAction onTapOverride;
        private SemanticsNode parent;
        private int platformViewId;
        private int previousActions;
        private int previousFlags;
        private String previousLabel;
        private float previousScrollExtentMax;
        private float previousScrollExtentMin;
        private float previousScrollPosition;
        private int previousTextSelectionBase;
        private int previousTextSelectionExtent;
        private String previousValue;
        private float right;
        private int scrollChildren;
        private float scrollExtentMax;
        private float scrollExtentMin;
        private int scrollIndex;
        private float scrollPosition;
        private TextDirection textDirection;
        private int textSelectionBase;
        private int textSelectionExtent;
        private String tooltip;

        /* renamed from: top */
        private float f36top;
        private float[] transform;
        private String value;
        private List<StringAttribute> valueAttributes;
        private int id = -1;
        private int previousNodeId = -1;
        private boolean hadPreviousConfig = false;
        private List<SemanticsNode> childrenInTraversalOrder = new ArrayList();
        private List<SemanticsNode> childrenInHitTestOrder = new ArrayList();
        private boolean inverseTransformDirty = true;
        private boolean globalGeometryDirty = true;

        private void log(String str, boolean z) {
        }

        static /* synthetic */ int access$2112(SemanticsNode semanticsNode, int i) {
            int i2 = semanticsNode.textSelectionExtent + i;
            semanticsNode.textSelectionExtent = i2;
            return i2;
        }

        static /* synthetic */ int access$2120(SemanticsNode semanticsNode, int i) {
            int i2 = semanticsNode.textSelectionExtent - i;
            semanticsNode.textSelectionExtent = i2;
            return i2;
        }

        public static boolean nullableHasAncestor(SemanticsNode semanticsNode, Predicate<SemanticsNode> predicate) {
            return (semanticsNode == null || semanticsNode.getAncestor(predicate) == null) ? false : true;
        }

        SemanticsNode(AccessibilityBridge accessibilityBridge) {
            this.accessibilityBridge = accessibilityBridge;
        }

        private SemanticsNode getAncestor(Predicate<SemanticsNode> predicate) {
            for (SemanticsNode semanticsNode = this.parent; semanticsNode != null; semanticsNode = semanticsNode.parent) {
                if (predicate.test(semanticsNode)) {
                    return semanticsNode;
                }
            }
            return null;
        }

        public boolean hasAction(Action action) {
            return (action.value & this.actions) != 0;
        }

        public boolean hadAction(Action action) {
            return (action.value & this.previousActions) != 0;
        }

        public boolean hasFlag(Flag flag) {
            return (flag.value & this.flags) != 0;
        }

        public boolean hadFlag(Flag flag) {
            return (flag.value & this.previousFlags) != 0;
        }

        public boolean didScroll() {
            return !Float.isNaN(this.scrollPosition) && !Float.isNaN(this.previousScrollPosition) && this.previousScrollPosition != this.scrollPosition;
        }

        public boolean didChangeLabel() {
            String str;
            if (this.label == null && this.previousLabel == null) {
                return false;
            }
            String str2 = this.label;
            if (str2 == null || (str = this.previousLabel) == null || !str2.equals(str)) {
                return true;
            }
            return false;
        }

        public void updateWith(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            this.hadPreviousConfig = true;
            this.previousValue = this.value;
            this.previousLabel = this.label;
            this.previousFlags = this.flags;
            this.previousActions = this.actions;
            this.previousTextSelectionBase = this.textSelectionBase;
            this.previousTextSelectionExtent = this.textSelectionExtent;
            this.previousScrollPosition = this.scrollPosition;
            this.previousScrollExtentMax = this.scrollExtentMax;
            this.previousScrollExtentMin = this.scrollExtentMin;
            this.flags = byteBuffer.getInt();
            this.actions = byteBuffer.getInt();
            this.maxValueLength = byteBuffer.getInt();
            this.currentValueLength = byteBuffer.getInt();
            this.textSelectionBase = byteBuffer.getInt();
            this.textSelectionExtent = byteBuffer.getInt();
            this.platformViewId = byteBuffer.getInt();
            this.scrollChildren = byteBuffer.getInt();
            this.scrollIndex = byteBuffer.getInt();
            this.scrollPosition = byteBuffer.getFloat();
            this.scrollExtentMax = byteBuffer.getFloat();
            this.scrollExtentMin = byteBuffer.getFloat();
            int i = byteBuffer.getInt();
            if (i == -1) {
                str = null;
            } else {
                str = strArr[i];
            }
            this.label = str;
            this.labelAttributes = getStringAttributesFromBuffer(byteBuffer, byteBufferArr);
            int i2 = byteBuffer.getInt();
            if (i2 == -1) {
                str2 = null;
            } else {
                str2 = strArr[i2];
            }
            this.value = str2;
            this.valueAttributes = getStringAttributesFromBuffer(byteBuffer, byteBufferArr);
            int i3 = byteBuffer.getInt();
            if (i3 == -1) {
                str3 = null;
            } else {
                str3 = strArr[i3];
            }
            this.increasedValue = str3;
            this.increasedValueAttributes = getStringAttributesFromBuffer(byteBuffer, byteBufferArr);
            int i4 = byteBuffer.getInt();
            if (i4 == -1) {
                str4 = null;
            } else {
                str4 = strArr[i4];
            }
            this.decreasedValue = str4;
            this.decreasedValueAttributes = getStringAttributesFromBuffer(byteBuffer, byteBufferArr);
            int i5 = byteBuffer.getInt();
            if (i5 == -1) {
                str5 = null;
            } else {
                str5 = strArr[i5];
            }
            this.hint = str5;
            this.hintAttributes = getStringAttributesFromBuffer(byteBuffer, byteBufferArr);
            int i6 = byteBuffer.getInt();
            if (i6 == -1) {
                str6 = null;
            } else {
                str6 = strArr[i6];
            }
            this.tooltip = str6;
            this.textDirection = TextDirection.fromInt(byteBuffer.getInt());
            this.left = byteBuffer.getFloat();
            this.f36top = byteBuffer.getFloat();
            this.right = byteBuffer.getFloat();
            this.bottom = byteBuffer.getFloat();
            if (this.transform == null) {
                this.transform = new float[16];
            }
            for (int i7 = 0; i7 < 16; i7++) {
                this.transform[i7] = byteBuffer.getFloat();
            }
            this.inverseTransformDirty = true;
            this.globalGeometryDirty = true;
            int i8 = byteBuffer.getInt();
            this.childrenInTraversalOrder.clear();
            this.childrenInHitTestOrder.clear();
            for (int i9 = 0; i9 < i8; i9++) {
                SemanticsNode orCreateSemanticsNode = this.accessibilityBridge.getOrCreateSemanticsNode(byteBuffer.getInt());
                orCreateSemanticsNode.parent = this;
                this.childrenInTraversalOrder.add(orCreateSemanticsNode);
            }
            for (int i10 = 0; i10 < i8; i10++) {
                SemanticsNode orCreateSemanticsNode2 = this.accessibilityBridge.getOrCreateSemanticsNode(byteBuffer.getInt());
                orCreateSemanticsNode2.parent = this;
                this.childrenInHitTestOrder.add(orCreateSemanticsNode2);
            }
            int i11 = byteBuffer.getInt();
            if (i11 == 0) {
                this.customAccessibilityActions = null;
                return;
            }
            List<CustomAccessibilityAction> list = this.customAccessibilityActions;
            if (list == null) {
                this.customAccessibilityActions = new ArrayList(i11);
            } else {
                list.clear();
            }
            for (int i12 = 0; i12 < i11; i12++) {
                CustomAccessibilityAction orCreateAccessibilityAction = this.accessibilityBridge.getOrCreateAccessibilityAction(byteBuffer.getInt());
                if (orCreateAccessibilityAction.overrideId == Action.TAP.value) {
                    this.onTapOverride = orCreateAccessibilityAction;
                } else if (orCreateAccessibilityAction.overrideId == Action.LONG_PRESS.value) {
                    this.onLongPressOverride = orCreateAccessibilityAction;
                } else {
                    this.customAccessibilityActions.add(orCreateAccessibilityAction);
                }
                this.customAccessibilityActions.add(orCreateAccessibilityAction);
            }
        }

        private List<StringAttribute> getStringAttributesFromBuffer(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) {
            int i = byteBuffer.getInt();
            if (i == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = byteBuffer.getInt();
                int i4 = byteBuffer.getInt();
                StringAttributeType stringAttributeType = StringAttributeType.values()[byteBuffer.getInt()];
                int i5 = AnonymousClass5.$SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType[stringAttributeType.ordinal()];
                if (i5 == 1) {
                    byteBuffer.getInt();
                    SpellOutStringAttribute spellOutStringAttribute = new SpellOutStringAttribute();
                    spellOutStringAttribute.start = i3;
                    spellOutStringAttribute.end = i4;
                    spellOutStringAttribute.type = stringAttributeType;
                    arrayList.add(spellOutStringAttribute);
                } else if (i5 == 2) {
                    ByteBuffer byteBuffer2 = byteBufferArr[byteBuffer.getInt()];
                    LocaleStringAttribute localeStringAttribute = new LocaleStringAttribute();
                    localeStringAttribute.start = i3;
                    localeStringAttribute.end = i4;
                    localeStringAttribute.type = stringAttributeType;
                    localeStringAttribute.locale = Charset.forName("UTF-8").decode(byteBuffer2).toString();
                    arrayList.add(localeStringAttribute);
                }
            }
            return arrayList;
        }

        private void ensureInverseTransform() {
            if (this.inverseTransformDirty) {
                this.inverseTransformDirty = false;
                if (this.inverseTransform == null) {
                    this.inverseTransform = new float[16];
                }
                if (!Matrix.invertM(this.inverseTransform, 0, this.transform, 0)) {
                    Arrays.fill(this.inverseTransform, 0.0f);
                }
            }
        }

        public Rect getGlobalRect() {
            return this.globalRect;
        }

        public SemanticsNode hitTest(float[] fArr) {
            float f = fArr[3];
            float f2 = fArr[0] / f;
            float f3 = fArr[1] / f;
            if (f2 < this.left || f2 >= this.right || f3 < this.f36top || f3 >= this.bottom) {
                return null;
            }
            float[] fArr2 = new float[4];
            for (SemanticsNode semanticsNode : this.childrenInHitTestOrder) {
                if (!semanticsNode.hasFlag(Flag.IS_HIDDEN)) {
                    semanticsNode.ensureInverseTransform();
                    Matrix.multiplyMV(fArr2, 0, semanticsNode.inverseTransform, 0, fArr, 0);
                    SemanticsNode hitTest = semanticsNode.hitTest(fArr2);
                    if (hitTest != null) {
                        return hitTest;
                    }
                }
            }
            if (isFocusable()) {
                return this;
            }
            return null;
        }

        public boolean isFocusable() {
            String str;
            String str2;
            String str3;
            if (hasFlag(Flag.SCOPES_ROUTE)) {
                return false;
            }
            if (hasFlag(Flag.IS_FOCUSABLE)) {
                return true;
            }
            if ((this.actions & (~AccessibilityBridge.SCROLLABLE_ACTIONS)) == 0 && (this.flags & AccessibilityBridge.FOCUSABLE_FLAGS) == 0 && (((str = this.label) == null || str.isEmpty()) && (((str2 = this.value) == null || str2.isEmpty()) && ((str3 = this.hint) == null || str3.isEmpty())))) {
                return false;
            }
            return true;
        }

        public void collectRoutes(List<SemanticsNode> list) {
            if (hasFlag(Flag.SCOPES_ROUTE)) {
                list.add(this);
            }
            for (SemanticsNode semanticsNode : this.childrenInTraversalOrder) {
                semanticsNode.collectRoutes(list);
            }
        }

        public String getRouteName() {
            String str;
            if (!(!hasFlag(Flag.NAMES_ROUTE) || (str = this.label) == null || str.isEmpty())) {
                return this.label;
            }
            for (SemanticsNode semanticsNode : this.childrenInTraversalOrder) {
                String routeName = semanticsNode.getRouteName();
                if (!(routeName == null || routeName.isEmpty())) {
                    return routeName;
                }
            }
            return null;
        }

        public void updateRecursively(float[] fArr, Set<SemanticsNode> set, boolean z) {
            set.add(this);
            if (this.globalGeometryDirty) {
                z = true;
            }
            if (z) {
                if (this.globalTransform == null) {
                    this.globalTransform = new float[16];
                }
                Matrix.multiplyMM(this.globalTransform, 0, fArr, 0, this.transform, 0);
                float[] fArr2 = new float[4];
                fArr2[2] = 0.0f;
                fArr2[3] = 1.0f;
                float[] fArr3 = new float[4];
                float[] fArr4 = new float[4];
                float[] fArr5 = new float[4];
                float[] fArr6 = new float[4];
                fArr2[0] = this.left;
                fArr2[1] = this.f36top;
                transformPoint(fArr3, this.globalTransform, fArr2);
                fArr2[0] = this.right;
                fArr2[1] = this.f36top;
                transformPoint(fArr4, this.globalTransform, fArr2);
                fArr2[0] = this.right;
                fArr2[1] = this.bottom;
                transformPoint(fArr5, this.globalTransform, fArr2);
                fArr2[0] = this.left;
                fArr2[1] = this.bottom;
                transformPoint(fArr6, this.globalTransform, fArr2);
                if (this.globalRect == null) {
                    this.globalRect = new Rect();
                }
                this.globalRect.set(Math.round(min(fArr3[0], fArr4[0], fArr5[0], fArr6[0])), Math.round(min(fArr3[1], fArr4[1], fArr5[1], fArr6[1])), Math.round(max(fArr3[0], fArr4[0], fArr5[0], fArr6[0])), Math.round(max(fArr3[1], fArr4[1], fArr5[1], fArr6[1])));
                this.globalGeometryDirty = false;
            }
            int i = -1;
            for (SemanticsNode semanticsNode : this.childrenInTraversalOrder) {
                semanticsNode.previousNodeId = i;
                i = semanticsNode.id;
                semanticsNode.updateRecursively(this.globalTransform, set, z);
            }
        }

        private void transformPoint(float[] fArr, float[] fArr2, float[] fArr3) {
            Matrix.multiplyMV(fArr, 0, fArr2, 0, fArr3, 0);
            float f = fArr[3];
            fArr[0] = fArr[0] / f;
            fArr[1] = fArr[1] / f;
            fArr[2] = fArr[2] / f;
            fArr[3] = 0.0f;
        }

        private float min(float f, float f2, float f3, float f4) {
            return Math.min(f, Math.min(f2, Math.min(f3, f4)));
        }

        private float max(float f, float f2, float f3, float f4) {
            return Math.max(f, Math.max(f2, Math.max(f3, f4)));
        }

        public CharSequence getValueLabelHint() {
            CharSequence[] charSequenceArr = Build.VERSION.SDK_INT < 21 ? new CharSequence[]{this.value, this.label, this.hint} : new CharSequence[]{createSpannableString(this.value, this.valueAttributes), createSpannableString(this.label, this.labelAttributes), createSpannableString(this.hint, this.hintAttributes)};
            CharSequence charSequence = null;
            for (CharSequence charSequence2 : charSequenceArr) {
                if (charSequence2 != null && charSequence2.length() > 0) {
                    charSequence = (charSequence == null || charSequence.length() == 0) ? charSequence2 : TextUtils.concat(charSequence, ", ", charSequence2);
                }
            }
            return charSequence;
        }

        private SpannableString createSpannableString(String str, List<StringAttribute> list) {
            if (str == null) {
                return null;
            }
            SpannableString spannableString = new SpannableString(str);
            if (list != null) {
                for (StringAttribute stringAttribute : list) {
                    int i = AnonymousClass5.$SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType[stringAttribute.type.ordinal()];
                    if (i == 1) {
                        spannableString.setSpan(new TtsSpan.Builder("android.type.verbatim").build(), stringAttribute.start, stringAttribute.end, 0);
                    } else if (i == 2) {
                        spannableString.setSpan(new LocaleSpan(Locale.forLanguageTag(((LocaleStringAttribute) stringAttribute).locale)), stringAttribute.start, stringAttribute.end, 0);
                    }
                }
            }
            return spannableString;
        }
    }

    /* renamed from: io.flutter.view.AccessibilityBridge$5 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType = new int[StringAttributeType.values().length];

        static {
            try {
                $SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType[StringAttributeType.SPELLOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType[StringAttributeType.LOCALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public boolean externalViewRequestSendAccessibilityEvent(View view, View view2, AccessibilityEvent accessibilityEvent) {
        Integer recordFlutterId;
        if (!this.accessibilityViewEmbedder.requestSendAccessibilityEvent(view, view2, accessibilityEvent) || (recordFlutterId = this.accessibilityViewEmbedder.getRecordFlutterId(view, accessibilityEvent)) == null) {
            return false;
        }
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 8) {
            this.embeddedInputFocusedNodeId = recordFlutterId;
            this.inputFocusedSemanticsNode = null;
            return true;
        } else if (eventType == 128) {
            this.hoveredObject = null;
            return true;
        } else if (eventType == 32768) {
            this.embeddedAccessibilityFocusedNodeId = recordFlutterId;
            this.accessibilityFocusedSemanticsNode = null;
            return true;
        } else if (eventType != 65536) {
            return true;
        } else {
            this.embeddedInputFocusedNodeId = null;
            this.embeddedAccessibilityFocusedNodeId = null;
            return true;
        }
    }
}
