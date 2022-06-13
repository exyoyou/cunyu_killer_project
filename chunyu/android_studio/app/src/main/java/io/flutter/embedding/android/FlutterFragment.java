package io.flutter.embedding.android;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivityAndFragmentDelegate;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterShellArgs;
import io.flutter.embedding.engine.renderer.FlutterUiDisplayListener;
import io.flutter.plugin.platform.PlatformPlugin;
import io.flutter.util.ViewUtils;

/* loaded from: classes2.dex */
public class FlutterFragment extends Fragment implements FlutterActivityAndFragmentDelegate.Host, ComponentCallbacks2 {
    protected static final String ARG_APP_BUNDLE_PATH = "app_bundle_path";
    protected static final String ARG_CACHED_ENGINE_ID = "cached_engine_id";
    protected static final String ARG_DART_ENTRYPOINT = "dart_entrypoint";
    protected static final String ARG_DESTROY_ENGINE_WITH_FRAGMENT = "destroy_engine_with_fragment";
    protected static final String ARG_ENABLE_STATE_RESTORATION = "enable_state_restoration";
    protected static final String ARG_FLUTTERVIEW_RENDER_MODE = "flutterview_render_mode";
    protected static final String ARG_FLUTTERVIEW_TRANSPARENCY_MODE = "flutterview_transparency_mode";
    protected static final String ARG_FLUTTER_INITIALIZATION_ARGS = "initialization_args";
    protected static final String ARG_HANDLE_DEEPLINKING = "handle_deeplinking";
    protected static final String ARG_INITIAL_ROUTE = "initial_route";
    protected static final String ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY = "should_attach_engine_to_activity";
    protected static final String ARG_SHOULD_AUTOMATICALLY_HANDLE_ON_BACK_PRESSED = "should_automatically_handle_on_back_pressed";
    protected static final String ARG_SHOULD_DELAY_FIRST_ANDROID_VIEW_DRAW = "should_delay_first_android_view_draw";
    public static final int FLUTTER_VIEW_ID = ViewUtils.generateViewId(61938);
    private static final String TAG = "FlutterFragment";
    FlutterActivityAndFragmentDelegate delegate;
    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) { // from class: io.flutter.embedding.android.FlutterFragment.1
        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            FlutterFragment.this.onBackPressed();
        }
    };

    /* loaded from: classes2.dex */
    @interface ActivityCallThrough {
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public void onFlutterSurfaceViewCreated(FlutterSurfaceView flutterSurfaceView) {
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public void onFlutterTextureViewCreated(FlutterTextureView flutterTextureView) {
    }

    @Override // androidx.fragment.app.Fragment, io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    public static FlutterFragment createDefault() {
        return new NewEngineFragmentBuilder().build();
    }

    public static NewEngineFragmentBuilder withNewEngine() {
        return new NewEngineFragmentBuilder();
    }

    /* loaded from: classes2.dex */
    public static class NewEngineFragmentBuilder {
        private String appBundlePath;
        private String dartEntrypoint;
        private final Class<? extends FlutterFragment> fragmentClass;
        private boolean handleDeeplinking;
        private String initialRoute;
        private RenderMode renderMode;
        private FlutterShellArgs shellArgs;
        private boolean shouldAttachEngineToActivity;
        private boolean shouldAutomaticallyHandleOnBackPressed;
        private boolean shouldDelayFirstAndroidViewDraw;
        private TransparencyMode transparencyMode;

        public NewEngineFragmentBuilder() {
            this.dartEntrypoint = "main";
            this.initialRoute = "/";
            this.handleDeeplinking = false;
            this.appBundlePath = null;
            this.shellArgs = null;
            this.renderMode = RenderMode.surface;
            this.transparencyMode = TransparencyMode.transparent;
            this.shouldAttachEngineToActivity = true;
            this.shouldAutomaticallyHandleOnBackPressed = false;
            this.shouldDelayFirstAndroidViewDraw = false;
            this.fragmentClass = FlutterFragment.class;
        }

        public NewEngineFragmentBuilder(Class<? extends FlutterFragment> cls) {
            this.dartEntrypoint = "main";
            this.initialRoute = "/";
            this.handleDeeplinking = false;
            this.appBundlePath = null;
            this.shellArgs = null;
            this.renderMode = RenderMode.surface;
            this.transparencyMode = TransparencyMode.transparent;
            this.shouldAttachEngineToActivity = true;
            this.shouldAutomaticallyHandleOnBackPressed = false;
            this.shouldDelayFirstAndroidViewDraw = false;
            this.fragmentClass = cls;
        }

        public NewEngineFragmentBuilder dartEntrypoint(String str) {
            this.dartEntrypoint = str;
            return this;
        }

        public NewEngineFragmentBuilder initialRoute(String str) {
            this.initialRoute = str;
            return this;
        }

        public NewEngineFragmentBuilder handleDeeplinking(Boolean bool) {
            this.handleDeeplinking = bool.booleanValue();
            return this;
        }

        public NewEngineFragmentBuilder appBundlePath(String str) {
            this.appBundlePath = str;
            return this;
        }

        public NewEngineFragmentBuilder flutterShellArgs(FlutterShellArgs flutterShellArgs) {
            this.shellArgs = flutterShellArgs;
            return this;
        }

        public NewEngineFragmentBuilder renderMode(RenderMode renderMode) {
            this.renderMode = renderMode;
            return this;
        }

        public NewEngineFragmentBuilder transparencyMode(TransparencyMode transparencyMode) {
            this.transparencyMode = transparencyMode;
            return this;
        }

        public NewEngineFragmentBuilder shouldAttachEngineToActivity(boolean z) {
            this.shouldAttachEngineToActivity = z;
            return this;
        }

        public NewEngineFragmentBuilder shouldAutomaticallyHandleOnBackPressed(boolean z) {
            this.shouldAutomaticallyHandleOnBackPressed = z;
            return this;
        }

        public NewEngineFragmentBuilder shouldDelayFirstAndroidViewDraw(boolean z) {
            this.shouldDelayFirstAndroidViewDraw = z;
            return this;
        }

        protected Bundle createArgs() {
            Bundle bundle = new Bundle();
            bundle.putString(FlutterFragment.ARG_INITIAL_ROUTE, this.initialRoute);
            bundle.putBoolean(FlutterFragment.ARG_HANDLE_DEEPLINKING, this.handleDeeplinking);
            bundle.putString(FlutterFragment.ARG_APP_BUNDLE_PATH, this.appBundlePath);
            bundle.putString(FlutterFragment.ARG_DART_ENTRYPOINT, this.dartEntrypoint);
            FlutterShellArgs flutterShellArgs = this.shellArgs;
            if (flutterShellArgs != null) {
                bundle.putStringArray(FlutterFragment.ARG_FLUTTER_INITIALIZATION_ARGS, flutterShellArgs.toArray());
            }
            RenderMode renderMode = this.renderMode;
            if (renderMode == null) {
                renderMode = RenderMode.surface;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_RENDER_MODE, renderMode.name());
            TransparencyMode transparencyMode = this.transparencyMode;
            if (transparencyMode == null) {
                transparencyMode = TransparencyMode.transparent;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_TRANSPARENCY_MODE, transparencyMode.name());
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY, this.shouldAttachEngineToActivity);
            bundle.putBoolean(FlutterFragment.ARG_DESTROY_ENGINE_WITH_FRAGMENT, true);
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_AUTOMATICALLY_HANDLE_ON_BACK_PRESSED, this.shouldAutomaticallyHandleOnBackPressed);
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_DELAY_FIRST_ANDROID_VIEW_DRAW, this.shouldDelayFirstAndroidViewDraw);
            return bundle;
        }

        public <T extends FlutterFragment> T build() {
            try {
                T t = (T) ((FlutterFragment) this.fragmentClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                if (t != null) {
                    t.setArguments(createArgs());
                    return t;
                }
                throw new RuntimeException("The FlutterFragment subclass sent in the constructor (" + this.fragmentClass.getCanonicalName() + ") does not match the expected return type.");
            } catch (Exception e) {
                throw new RuntimeException("Could not instantiate FlutterFragment subclass (" + this.fragmentClass.getName() + ")", e);
            }
        }
    }

    public static CachedEngineFragmentBuilder withCachedEngine(String str) {
        return new CachedEngineFragmentBuilder(str);
    }

    /* loaded from: classes2.dex */
    public static class CachedEngineFragmentBuilder {
        private boolean destroyEngineWithFragment;
        private final String engineId;
        private final Class<? extends FlutterFragment> fragmentClass;
        private boolean handleDeeplinking;
        private RenderMode renderMode;
        private boolean shouldAttachEngineToActivity;
        private boolean shouldAutomaticallyHandleOnBackPressed;
        private boolean shouldDelayFirstAndroidViewDraw;
        private TransparencyMode transparencyMode;

        private CachedEngineFragmentBuilder(String str) {
            this(FlutterFragment.class, str);
        }

        public CachedEngineFragmentBuilder(Class<? extends FlutterFragment> cls, String str) {
            this.destroyEngineWithFragment = false;
            this.handleDeeplinking = false;
            this.renderMode = RenderMode.surface;
            this.transparencyMode = TransparencyMode.transparent;
            this.shouldAttachEngineToActivity = true;
            this.shouldAutomaticallyHandleOnBackPressed = false;
            this.shouldDelayFirstAndroidViewDraw = false;
            this.fragmentClass = cls;
            this.engineId = str;
        }

        public CachedEngineFragmentBuilder destroyEngineWithFragment(boolean z) {
            this.destroyEngineWithFragment = z;
            return this;
        }

        public CachedEngineFragmentBuilder renderMode(RenderMode renderMode) {
            this.renderMode = renderMode;
            return this;
        }

        public CachedEngineFragmentBuilder transparencyMode(TransparencyMode transparencyMode) {
            this.transparencyMode = transparencyMode;
            return this;
        }

        public CachedEngineFragmentBuilder handleDeeplinking(Boolean bool) {
            this.handleDeeplinking = bool.booleanValue();
            return this;
        }

        public CachedEngineFragmentBuilder shouldAttachEngineToActivity(boolean z) {
            this.shouldAttachEngineToActivity = z;
            return this;
        }

        public CachedEngineFragmentBuilder shouldAutomaticallyHandleOnBackPressed(boolean z) {
            this.shouldAutomaticallyHandleOnBackPressed = z;
            return this;
        }

        public CachedEngineFragmentBuilder shouldDelayFirstAndroidViewDraw(boolean z) {
            this.shouldDelayFirstAndroidViewDraw = z;
            return this;
        }

        protected Bundle createArgs() {
            Bundle bundle = new Bundle();
            bundle.putString(FlutterFragment.ARG_CACHED_ENGINE_ID, this.engineId);
            bundle.putBoolean(FlutterFragment.ARG_DESTROY_ENGINE_WITH_FRAGMENT, this.destroyEngineWithFragment);
            bundle.putBoolean(FlutterFragment.ARG_HANDLE_DEEPLINKING, this.handleDeeplinking);
            RenderMode renderMode = this.renderMode;
            if (renderMode == null) {
                renderMode = RenderMode.surface;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_RENDER_MODE, renderMode.name());
            TransparencyMode transparencyMode = this.transparencyMode;
            if (transparencyMode == null) {
                transparencyMode = TransparencyMode.transparent;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_TRANSPARENCY_MODE, transparencyMode.name());
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY, this.shouldAttachEngineToActivity);
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_AUTOMATICALLY_HANDLE_ON_BACK_PRESSED, this.shouldAutomaticallyHandleOnBackPressed);
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_DELAY_FIRST_ANDROID_VIEW_DRAW, this.shouldDelayFirstAndroidViewDraw);
            return bundle;
        }

        public <T extends FlutterFragment> T build() {
            try {
                T t = (T) ((FlutterFragment) this.fragmentClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                if (t != null) {
                    t.setArguments(createArgs());
                    return t;
                }
                throw new RuntimeException("The FlutterFragment subclass sent in the constructor (" + this.fragmentClass.getCanonicalName() + ") does not match the expected return type.");
            } catch (Exception e) {
                throw new RuntimeException("Could not instantiate FlutterFragment subclass (" + this.fragmentClass.getName() + ")", e);
            }
        }
    }

    public FlutterFragment() {
        setArguments(new Bundle());
    }

    void setDelegate(FlutterActivityAndFragmentDelegate flutterActivityAndFragmentDelegate) {
        this.delegate = flutterActivityAndFragmentDelegate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.delegate = new FlutterActivityAndFragmentDelegate(this);
        this.delegate.onAttach(context);
        if (getArguments().getBoolean(ARG_SHOULD_AUTOMATICALLY_HANDLE_ON_BACK_PRESSED, false)) {
            requireActivity().getOnBackPressedDispatcher().addCallback(this, this.onBackPressedCallback);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.delegate.onRestoreInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.delegate.onCreateView(layoutInflater, viewGroup, bundle, FLUTTER_VIEW_ID, shouldDelayFirstAndroidViewDraw());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (stillAttachedForEvent("onStart")) {
            this.delegate.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (stillAttachedForEvent("onResume")) {
            this.delegate.onResume();
        }
    }

    public void onPostResume() {
        if (stillAttachedForEvent("onPostResume")) {
            this.delegate.onPostResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (stillAttachedForEvent("onPause")) {
            this.delegate.onPause();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (stillAttachedForEvent("onStop")) {
            this.delegate.onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (stillAttachedForEvent("onDestroyView")) {
            this.delegate.onDestroyView();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (stillAttachedForEvent("onSaveInstanceState")) {
            this.delegate.onSaveInstanceState(bundle);
        }
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public void detachFromFlutterEngine() {
        Log.w(TAG, "FlutterFragment " + this + " connection to the engine " + getFlutterEngine() + " evicted by another attaching activity");
        FlutterActivityAndFragmentDelegate flutterActivityAndFragmentDelegate = this.delegate;
        if (flutterActivityAndFragmentDelegate != null) {
            flutterActivityAndFragmentDelegate.onDestroyView();
            this.delegate.onDetach();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        FlutterActivityAndFragmentDelegate flutterActivityAndFragmentDelegate = this.delegate;
        if (flutterActivityAndFragmentDelegate != null) {
            flutterActivityAndFragmentDelegate.onDetach();
            this.delegate.release();
            this.delegate = null;
            return;
        }
        Log.v(TAG, "FlutterFragment " + this + " onDetach called after release.");
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (stillAttachedForEvent("onRequestPermissionsResult")) {
            this.delegate.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void onNewIntent(Intent intent) {
        if (stillAttachedForEvent("onNewIntent")) {
            this.delegate.onNewIntent(intent);
        }
    }

    public void onBackPressed() {
        if (stillAttachedForEvent("onBackPressed")) {
            this.delegate.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (stillAttachedForEvent("onActivityResult")) {
            this.delegate.onActivityResult(i, i2, intent);
        }
    }

    public void onUserLeaveHint() {
        if (stillAttachedForEvent("onUserLeaveHint")) {
            this.delegate.onUserLeaveHint();
        }
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (stillAttachedForEvent("onTrimMemory")) {
            this.delegate.onTrimMemory(i);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (stillAttachedForEvent("onLowMemory")) {
            this.delegate.onLowMemory();
        }
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public FlutterShellArgs getFlutterShellArgs() {
        String[] stringArray = getArguments().getStringArray(ARG_FLUTTER_INITIALIZATION_ARGS);
        if (stringArray == null) {
            stringArray = new String[0];
        }
        return new FlutterShellArgs(stringArray);
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public String getCachedEngineId() {
        return getArguments().getString(ARG_CACHED_ENGINE_ID, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFlutterEngineInjected() {
        return this.delegate.isFlutterEngineFromHost();
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public boolean shouldDestroyEngineWithHost() {
        boolean z = getArguments().getBoolean(ARG_DESTROY_ENGINE_WITH_FRAGMENT, false);
        return (getCachedEngineId() != null || this.delegate.isFlutterEngineFromHost()) ? z : getArguments().getBoolean(ARG_DESTROY_ENGINE_WITH_FRAGMENT, true);
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public String getDartEntrypointFunctionName() {
        return getArguments().getString(ARG_DART_ENTRYPOINT, "main");
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public String getAppBundlePath() {
        return getArguments().getString(ARG_APP_BUNDLE_PATH);
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public String getInitialRoute() {
        return getArguments().getString(ARG_INITIAL_ROUTE);
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public RenderMode getRenderMode() {
        return RenderMode.valueOf(getArguments().getString(ARG_FLUTTERVIEW_RENDER_MODE, RenderMode.surface.name()));
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public TransparencyMode getTransparencyMode() {
        return TransparencyMode.valueOf(getArguments().getString(ARG_FLUTTERVIEW_TRANSPARENCY_MODE, TransparencyMode.transparent.name()));
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host, io.flutter.embedding.android.SplashScreenProvider
    public SplashScreen provideSplashScreen() {
        FragmentActivity activity = getActivity();
        if (activity instanceof SplashScreenProvider) {
            return ((SplashScreenProvider) activity).provideSplashScreen();
        }
        return null;
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host, io.flutter.embedding.android.FlutterEngineProvider
    public FlutterEngine provideFlutterEngine(Context context) {
        FragmentActivity activity = getActivity();
        if (!(activity instanceof FlutterEngineProvider)) {
            return null;
        }
        Log.v(TAG, "Deferring to attached Activity to provide a FlutterEngine.");
        return ((FlutterEngineProvider) activity).provideFlutterEngine(getContext());
    }

    public FlutterEngine getFlutterEngine() {
        return this.delegate.getFlutterEngine();
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public PlatformPlugin providePlatformPlugin(Activity activity, FlutterEngine flutterEngine) {
        if (activity != null) {
            return new PlatformPlugin(getActivity(), flutterEngine.getPlatformChannel(), this);
        }
        return null;
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host, io.flutter.embedding.android.FlutterEngineConfigurator
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        FragmentActivity activity = getActivity();
        if (activity instanceof FlutterEngineConfigurator) {
            ((FlutterEngineConfigurator) activity).configureFlutterEngine(flutterEngine);
        }
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host, io.flutter.embedding.android.FlutterEngineConfigurator
    public void cleanUpFlutterEngine(FlutterEngine flutterEngine) {
        FragmentActivity activity = getActivity();
        if (activity instanceof FlutterEngineConfigurator) {
            ((FlutterEngineConfigurator) activity).cleanUpFlutterEngine(flutterEngine);
        }
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public boolean shouldAttachEngineToActivity() {
        return getArguments().getBoolean(ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY);
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public boolean shouldHandleDeeplinking() {
        return getArguments().getBoolean(ARG_HANDLE_DEEPLINKING);
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public void onFlutterUiDisplayed() {
        FragmentActivity activity = getActivity();
        if (activity instanceof FlutterUiDisplayListener) {
            ((FlutterUiDisplayListener) activity).onFlutterUiDisplayed();
        }
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public void onFlutterUiNoLongerDisplayed() {
        FragmentActivity activity = getActivity();
        if (activity instanceof FlutterUiDisplayListener) {
            ((FlutterUiDisplayListener) activity).onFlutterUiNoLongerDisplayed();
        }
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public boolean shouldRestoreAndSaveState() {
        if (getArguments().containsKey(ARG_ENABLE_STATE_RESTORATION)) {
            return getArguments().getBoolean(ARG_ENABLE_STATE_RESTORATION);
        }
        return getCachedEngineId() == null;
    }

    @Override // io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public void updateSystemUiOverlays() {
        FlutterActivityAndFragmentDelegate flutterActivityAndFragmentDelegate = this.delegate;
        if (flutterActivityAndFragmentDelegate != null) {
            flutterActivityAndFragmentDelegate.updateSystemUiOverlays();
        }
    }

    @Override // io.flutter.plugin.platform.PlatformPlugin.PlatformPluginDelegate
    public boolean popSystemNavigator() {
        FragmentActivity activity;
        if (!getArguments().getBoolean(ARG_SHOULD_AUTOMATICALLY_HANDLE_ON_BACK_PRESSED, false) || (activity = getActivity()) == null) {
            return false;
        }
        this.onBackPressedCallback.setEnabled(false);
        activity.getOnBackPressedDispatcher().onBackPressed();
        this.onBackPressedCallback.setEnabled(true);
        return true;
    }

    boolean shouldDelayFirstAndroidViewDraw() {
        return getArguments().getBoolean(ARG_SHOULD_DELAY_FIRST_ANDROID_VIEW_DRAW);
    }

    private boolean stillAttachedForEvent(String str) {
        FlutterActivityAndFragmentDelegate flutterActivityAndFragmentDelegate = this.delegate;
        if (flutterActivityAndFragmentDelegate == null) {
            Log.w(TAG, "FlutterFragment " + hashCode() + " " + str + " called after release.");
            return false;
        } else if (flutterActivityAndFragmentDelegate.isAttached()) {
            return true;
        } else {
            Log.w(TAG, "FlutterFragment " + hashCode() + " " + str + " called after detach.");
            return false;
        }
    }
}
