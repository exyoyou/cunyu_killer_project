package io.flutter.embedding.engine.systemchannels;

import io.flutter.Log;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes2.dex */
public class NavigationChannel {
    private static final String TAG = "NavigationChannel";
    public final MethodChannel channel;
    private final MethodChannel.MethodCallHandler defaultHandler = new MethodChannel.MethodCallHandler() { // from class: io.flutter.embedding.engine.systemchannels.NavigationChannel.1
        @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
        public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
            result.success(null);
        }
    };

    public NavigationChannel(DartExecutor dartExecutor) {
        this.channel = new MethodChannel(dartExecutor, "flutter/navigation", JSONMethodCodec.INSTANCE);
        this.channel.setMethodCallHandler(this.defaultHandler);
    }

    public void setInitialRoute(String str) {
        Log.v(TAG, "Sending message to set initial route to '" + str + "'");
        this.channel.invokeMethod("setInitialRoute", str);
    }

    public void pushRoute(String str) {
        Log.v(TAG, "Sending message to push route '" + str + "'");
        this.channel.invokeMethod("pushRoute", str);
    }

    public void popRoute() {
        Log.v(TAG, "Sending message to pop route.");
        this.channel.invokeMethod("popRoute", null);
    }

    public void setMethodCallHandler(MethodChannel.MethodCallHandler methodCallHandler) {
        this.channel.setMethodCallHandler(methodCallHandler);
    }
}
