package io.flutter.plugins.sharedpreferences;

import android.content.Context;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/* loaded from: classes2.dex */
public class SharedPreferencesPlugin implements FlutterPlugin {
    private static final String CHANNEL_NAME = "plugins.flutter.io/shared_preferences_android";
    private MethodChannel channel;
    private MethodCallHandlerImpl handler;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new SharedPreferencesPlugin().setupChannel(registrar.messenger(), registrar.context());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        setupChannel(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        teardownChannel();
    }

    private void setupChannel(BinaryMessenger binaryMessenger, Context context) {
        this.channel = new MethodChannel(binaryMessenger, CHANNEL_NAME);
        this.handler = new MethodCallHandlerImpl(context);
        this.channel.setMethodCallHandler(this.handler);
    }

    private void teardownChannel() {
        this.handler.teardown();
        this.handler = null;
        this.channel.setMethodCallHandler(null);
        this.channel = null;
    }
}
