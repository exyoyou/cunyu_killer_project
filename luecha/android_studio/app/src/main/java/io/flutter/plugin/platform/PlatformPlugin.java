package io.flutter.plugin.platform;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.core.view.WindowInsetsControllerCompat;
import io.flutter.Log;
import io.flutter.embedding.engine.systemchannels.PlatformChannel;
import java.io.FileNotFoundException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class PlatformPlugin {
    public static final int DEFAULT_SYSTEM_UI = 1280;
    private static final String TAG = "PlatformPlugin";
    private final Activity activity;
    private PlatformChannel.SystemChromeStyle currentTheme;
    private int mEnabledOverlays;
    final PlatformChannel.PlatformMessageHandler mPlatformMessageHandler;
    private final PlatformChannel platformChannel;
    private final PlatformPluginDelegate platformPluginDelegate;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    public interface PlatformPluginDelegate {
        boolean popSystemNavigator();
    }

    public PlatformPlugin(Activity activity, PlatformChannel platformChannel) {
        this(activity, platformChannel, null);
    }

    public PlatformPlugin(Activity activity, PlatformChannel platformChannel, PlatformPluginDelegate platformPluginDelegate) {
        this.mPlatformMessageHandler = new PlatformChannel.PlatformMessageHandler() { // from class: io.flutter.plugin.platform.PlatformPlugin.1
            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void playSystemSound(PlatformChannel.SoundType soundType) {
                PlatformPlugin.this.playSystemSound(soundType);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void vibrateHapticFeedback(PlatformChannel.HapticFeedbackType hapticFeedbackType) {
                PlatformPlugin.this.vibrateHapticFeedback(hapticFeedbackType);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void setPreferredOrientations(int i) {
                PlatformPlugin.this.setSystemChromePreferredOrientations(i);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void setApplicationSwitcherDescription(PlatformChannel.AppSwitcherDescription appSwitcherDescription) {
                PlatformPlugin.this.setSystemChromeApplicationSwitcherDescription(appSwitcherDescription);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void showSystemOverlays(List<PlatformChannel.SystemUiOverlay> list) {
                PlatformPlugin.this.setSystemChromeEnabledSystemUIOverlays(list);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void showSystemUiMode(PlatformChannel.SystemUiMode systemUiMode) {
                PlatformPlugin.this.setSystemChromeEnabledSystemUIMode(systemUiMode);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void setSystemUiChangeListener() {
                PlatformPlugin.this.setSystemChromeChangeListener();
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void restoreSystemUiOverlays() {
                PlatformPlugin.this.restoreSystemChromeSystemUIOverlays();
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void setSystemUiOverlayStyle(PlatformChannel.SystemChromeStyle systemChromeStyle) {
                PlatformPlugin.this.setSystemChromeSystemUIOverlayStyle(systemChromeStyle);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void popSystemNavigator() {
                PlatformPlugin.this.popSystemNavigator();
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public CharSequence getClipboardData(PlatformChannel.ClipboardContentFormat clipboardContentFormat) {
                return PlatformPlugin.this.getClipboardData(clipboardContentFormat);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public void setClipboardData(String str) {
                PlatformPlugin.this.setClipboardData(str);
            }

            @Override // io.flutter.embedding.engine.systemchannels.PlatformChannel.PlatformMessageHandler
            public boolean clipboardHasStrings() {
                return PlatformPlugin.this.clipboardHasStrings();
            }
        };
        this.activity = activity;
        this.platformChannel = platformChannel;
        this.platformChannel.setPlatformMessageHandler(this.mPlatformMessageHandler);
        this.platformPluginDelegate = platformPluginDelegate;
        this.mEnabledOverlays = 1280;
    }

    public void destroy() {
        this.platformChannel.setPlatformMessageHandler(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playSystemSound(PlatformChannel.SoundType soundType) {
        if (soundType == PlatformChannel.SoundType.CLICK) {
            this.activity.getWindow().getDecorView().playSoundEffect(0);
        }
    }

    void vibrateHapticFeedback(PlatformChannel.HapticFeedbackType hapticFeedbackType) {
        View decorView = this.activity.getWindow().getDecorView();
        int i = AnonymousClass3.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType[hapticFeedbackType.ordinal()];
        if (i == 1) {
            decorView.performHapticFeedback(0);
        } else if (i == 2) {
            decorView.performHapticFeedback(1);
        } else if (i == 3) {
            decorView.performHapticFeedback(3);
        } else if (i != 4) {
            if (i == 5 && Build.VERSION.SDK_INT >= 21) {
                decorView.performHapticFeedback(4);
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            decorView.performHapticFeedback(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemChromePreferredOrientations(int i) {
        this.activity.setRequestedOrientation(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemChromeApplicationSwitcherDescription(PlatformChannel.AppSwitcherDescription appSwitcherDescription) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT < 28 && Build.VERSION.SDK_INT > 21) {
                this.activity.setTaskDescription(new ActivityManager.TaskDescription(appSwitcherDescription.label, (Bitmap) null, appSwitcherDescription.color));
            }
            if (Build.VERSION.SDK_INT >= 28) {
                this.activity.setTaskDescription(new ActivityManager.TaskDescription(appSwitcherDescription.label, 0, appSwitcherDescription.color));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemChromeChangeListener() {
        this.activity.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: io.flutter.plugin.platform.PlatformPlugin.2
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                if ((i & 4) == 0) {
                    PlatformPlugin.this.platformChannel.systemChromeChanged(false);
                } else {
                    PlatformPlugin.this.platformChannel.systemChromeChanged(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemChromeEnabledSystemUIMode(PlatformChannel.SystemUiMode systemUiMode) {
        int i;
        if (systemUiMode == PlatformChannel.SystemUiMode.LEAN_BACK && Build.VERSION.SDK_INT >= 16) {
            i = 1798;
        } else if (systemUiMode == PlatformChannel.SystemUiMode.IMMERSIVE && Build.VERSION.SDK_INT >= 19) {
            i = 3846;
        } else if (systemUiMode == PlatformChannel.SystemUiMode.IMMERSIVE_STICKY && Build.VERSION.SDK_INT >= 19) {
            i = 5894;
        } else if (systemUiMode == PlatformChannel.SystemUiMode.EDGE_TO_EDGE && Build.VERSION.SDK_INT >= 29) {
            i = 1792;
        } else {
            return;
        }
        this.mEnabledOverlays = i;
        updateSystemUiOverlays();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemChromeEnabledSystemUIOverlays(List<PlatformChannel.SystemUiOverlay> list) {
        int i = (list.size() != 0 || Build.VERSION.SDK_INT < 19) ? 1798 : 5894;
        for (int i2 = 0; i2 < list.size(); i2++) {
            int i3 = AnonymousClass3.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[list.get(i2).ordinal()];
            if (i3 == 1) {
                i &= -5;
            } else if (i3 == 2) {
                i = i & -513 & -3;
            }
        }
        this.mEnabledOverlays = i;
        updateSystemUiOverlays();
    }

    public void updateSystemUiOverlays() {
        this.activity.getWindow().getDecorView().setSystemUiVisibility(this.mEnabledOverlays);
        PlatformChannel.SystemChromeStyle systemChromeStyle = this.currentTheme;
        if (systemChromeStyle != null) {
            setSystemChromeSystemUIOverlayStyle(systemChromeStyle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreSystemChromeSystemUIOverlays() {
        updateSystemUiOverlays();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemChromeSystemUIOverlayStyle(PlatformChannel.SystemChromeStyle systemChromeStyle) {
        Window window = this.activity.getWindow();
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, window.getDecorView());
        if (Build.VERSION.SDK_INT >= 23) {
            if (systemChromeStyle.statusBarIconBrightness != null) {
                int i = AnonymousClass3.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$Brightness[systemChromeStyle.statusBarIconBrightness.ordinal()];
                if (i == 1) {
                    windowInsetsControllerCompat.setAppearanceLightStatusBars(true);
                } else if (i == 2) {
                    windowInsetsControllerCompat.setAppearanceLightStatusBars(false);
                }
            }
            if (systemChromeStyle.statusBarColor != null) {
                window.setStatusBarColor(systemChromeStyle.statusBarColor.intValue());
            }
        }
        if (systemChromeStyle.systemStatusBarContrastEnforced != null && Build.VERSION.SDK_INT >= 29) {
            window.setStatusBarContrastEnforced(systemChromeStyle.systemStatusBarContrastEnforced.booleanValue());
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (systemChromeStyle.systemNavigationBarIconBrightness != null) {
                int i2 = AnonymousClass3.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$Brightness[systemChromeStyle.systemNavigationBarIconBrightness.ordinal()];
                if (i2 == 1) {
                    windowInsetsControllerCompat.setAppearanceLightNavigationBars(true);
                } else if (i2 == 2) {
                    windowInsetsControllerCompat.setAppearanceLightNavigationBars(false);
                }
            }
            if (systemChromeStyle.systemNavigationBarColor != null) {
                window.setNavigationBarColor(systemChromeStyle.systemNavigationBarColor.intValue());
            }
        }
        if (systemChromeStyle.systemNavigationBarDividerColor != null && Build.VERSION.SDK_INT >= 28) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(134217728);
            window.setNavigationBarDividerColor(systemChromeStyle.systemNavigationBarDividerColor.intValue());
        }
        if (systemChromeStyle.systemNavigationBarContrastEnforced != null && Build.VERSION.SDK_INT >= 29) {
            window.setNavigationBarContrastEnforced(systemChromeStyle.systemNavigationBarContrastEnforced.booleanValue());
        }
        this.currentTheme = systemChromeStyle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* renamed from: io.flutter.plugin.platform.PlatformPlugin$3  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$Brightness = new int[PlatformChannel.Brightness.values().length];
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType;
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay;

        static {
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$Brightness[PlatformChannel.Brightness.DARK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$Brightness[PlatformChannel.Brightness.LIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay = new int[PlatformChannel.SystemUiOverlay.values().length];
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[PlatformChannel.SystemUiOverlay.TOP_OVERLAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[PlatformChannel.SystemUiOverlay.BOTTOM_OVERLAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType = new int[PlatformChannel.HapticFeedbackType.values().length];
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType[PlatformChannel.HapticFeedbackType.STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType[PlatformChannel.HapticFeedbackType.LIGHT_IMPACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType[PlatformChannel.HapticFeedbackType.MEDIUM_IMPACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType[PlatformChannel.HapticFeedbackType.HEAVY_IMPACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$HapticFeedbackType[PlatformChannel.HapticFeedbackType.SELECTION_CLICK.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popSystemNavigator() {
        PlatformPluginDelegate platformPluginDelegate = this.platformPluginDelegate;
        if (platformPluginDelegate == null || !platformPluginDelegate.popSystemNavigator()) {
            Activity activity = this.activity;
            if (activity instanceof OnBackPressedDispatcherOwner) {
                ((OnBackPressedDispatcherOwner) activity).getOnBackPressedDispatcher().onBackPressed();
            } else {
                activity.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CharSequence getClipboardData(PlatformChannel.ClipboardContentFormat clipboardContentFormat) {
        ClipboardManager clipboardManager = (ClipboardManager) this.activity.getSystemService("clipboard");
        if (!clipboardManager.hasPrimaryClip()) {
            return null;
        }
        try {
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            if (primaryClip == null) {
                return null;
            }
            if (!(clipboardContentFormat == null || clipboardContentFormat == PlatformChannel.ClipboardContentFormat.PLAIN_TEXT)) {
                return null;
            }
            ClipData.Item itemAt = primaryClip.getItemAt(0);
            if (itemAt.getUri() != null) {
                this.activity.getContentResolver().openTypedAssetFileDescriptor(itemAt.getUri(), "text/*", null);
            }
            return itemAt.coerceToText(this.activity);
        } catch (FileNotFoundException unused) {
            return null;
        } catch (SecurityException e) {
            Log.w("PlatformPlugin", "Attempted to get clipboard data that requires additional permission(s).\nSee the exception details for which permission(s) are required, and consider adding them to your Android Manifest as described in:\nhttps://developer.android.com/guide/topics/permissions/overview", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClipboardData(String str) {
        ((ClipboardManager) this.activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean clipboardHasStrings() {
        ClipDescription primaryClipDescription;
        ClipboardManager clipboardManager = (ClipboardManager) this.activity.getSystemService("clipboard");
        if (clipboardManager.hasPrimaryClip() && (primaryClipDescription = clipboardManager.getPrimaryClipDescription()) != null) {
            return primaryClipDescription.hasMimeType("text/*");
        }
        return false;
    }
}
