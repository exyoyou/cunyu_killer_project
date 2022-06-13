package reflection.android.app;

import android.content.pm.ProviderInfo;
import android.os.IInterface;
import com.umeng.analytics.pro.c;
import reflection.MirrorReflection;

/* loaded from: classes4.dex */
public class IActivityManager {
    public static final MirrorReflection REF;
    public static MirrorReflection.MethodWrapper<Integer> startActivity;

    static {
        MirrorReflection on = MirrorReflection.on("android.app.IActivityManager");
        REF = on;
        startActivity = on.method("startActivity", new Class[0]);
    }

    /* loaded from: classes4.dex */
    public static class ContentProviderHolder {
        public static final MirrorReflection REF;
        public static MirrorReflection.FieldWrapper<ProviderInfo> info;
        public static MirrorReflection.FieldWrapper<IInterface> provider;

        static {
            MirrorReflection on = MirrorReflection.on("android.app.IActivityManager$ContentProviderHolder");
            REF = on;
            info = on.field("info");
            provider = on.field(c.M);
        }
    }
}
