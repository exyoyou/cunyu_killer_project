package io.flutter.app;

import android.app.Activity;
import android.app.Application;
import io.flutter.FlutterInjector;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class FlutterApplication extends Application {
    private Activity mCurrentActivity = null;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        FlutterInjector.instance().flutterLoader().startInitialization(this);
    }

    public Activity getCurrentActivity() {
        return this.mCurrentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        this.mCurrentActivity = activity;
    }
}
