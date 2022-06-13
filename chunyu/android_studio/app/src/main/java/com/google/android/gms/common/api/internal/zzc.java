package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes4.dex */
public final class zzc extends Fragment implements LifecycleFragment {
    private static WeakHashMap<FragmentActivity, WeakReference<zzc>> zzbd = new WeakHashMap<>();
    private Map<String, LifecycleCallback> zzbe = new ArrayMap();
    private int zzbf = 0;
    private Bundle zzbg;

    public static zzc zza(FragmentActivity fragmentActivity) {
        zzc zzc;
        WeakReference<zzc> weakReference = zzbd.get(fragmentActivity);
        if (weakReference != null && (zzc = weakReference.get()) != null) {
            return zzc;
        }
        try {
            zzc zzc2 = (zzc) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (zzc2 == null || zzc2.isRemoving()) {
                zzc2 = new zzc();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add(zzc2, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzbd.put(fragmentActivity, new WeakReference<>(zzc2));
            return zzc2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return cls.cast(this.zzbe.get(str));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String str, LifecycleCallback lifecycleCallback) {
        if (!this.zzbe.containsKey(str)) {
            this.zzbe.put(str, lifecycleCallback);
            if (this.zzbf > 0) {
                new zze(Looper.getMainLooper()).post(new zzd(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 59);
        sb.append("LifecycleCallback with tag ");
        sb.append(str);
        sb.append(" already added to this fragment.");
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isCreated() {
        return this.zzbf > 0;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isStarted() {
        return this.zzbf >= 2;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbf = 1;
        this.zzbg = bundle;
        for (Map.Entry<String, LifecycleCallback> entry : this.zzbe.entrySet()) {
            entry.getValue().onCreate(bundle != null ? bundle.getBundle(entry.getKey()) : null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzbf = 2;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzbf = 3;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onActivityResult(i, i2, intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Map.Entry<String, LifecycleCallback> entry : this.zzbe.entrySet()) {
                Bundle bundle2 = new Bundle();
                entry.getValue().onSaveInstanceState(bundle2);
                bundle.putBundle(entry.getKey(), bundle2);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzbf = 4;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzbf = 5;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onDestroy();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final /* synthetic */ Activity getLifecycleActivity() {
        return getActivity();
    }
}
