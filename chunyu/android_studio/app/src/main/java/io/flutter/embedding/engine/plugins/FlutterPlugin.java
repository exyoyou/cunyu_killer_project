package io.flutter.embedding.engine.plugins;

import android.content.Context;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.platform.PlatformViewRegistry;
import io.flutter.view.TextureRegistry;

/* loaded from: classes2.dex */
public interface FlutterPlugin {

    /* loaded from: classes2.dex */
    public interface FlutterAssets {
        String getAssetFilePathByName(String str);

        String getAssetFilePathByName(String str, String str2);

        String getAssetFilePathBySubpath(String str);

        String getAssetFilePathBySubpath(String str, String str2);
    }

    void onAttachedToEngine(FlutterPluginBinding flutterPluginBinding);

    void onDetachedFromEngine(FlutterPluginBinding flutterPluginBinding);

    /* loaded from: classes2.dex */
    public static class FlutterPluginBinding {
        private final Context applicationContext;
        private final BinaryMessenger binaryMessenger;
        private final FlutterAssets flutterAssets;
        private final FlutterEngine flutterEngine;
        private final PlatformViewRegistry platformViewRegistry;
        private final TextureRegistry textureRegistry;

        public FlutterPluginBinding(Context context, FlutterEngine flutterEngine, BinaryMessenger binaryMessenger, TextureRegistry textureRegistry, PlatformViewRegistry platformViewRegistry, FlutterAssets flutterAssets) {
            this.applicationContext = context;
            this.flutterEngine = flutterEngine;
            this.binaryMessenger = binaryMessenger;
            this.textureRegistry = textureRegistry;
            this.platformViewRegistry = platformViewRegistry;
            this.flutterAssets = flutterAssets;
        }

        public Context getApplicationContext() {
            return this.applicationContext;
        }

        @Deprecated
        public FlutterEngine getFlutterEngine() {
            return this.flutterEngine;
        }

        public BinaryMessenger getBinaryMessenger() {
            return this.binaryMessenger;
        }

        public TextureRegistry getTextureRegistry() {
            return this.textureRegistry;
        }

        public PlatformViewRegistry getPlatformViewRegistry() {
            return this.platformViewRegistry;
        }

        public FlutterAssets getFlutterAssets() {
            return this.flutterAssets;
        }
    }
}
