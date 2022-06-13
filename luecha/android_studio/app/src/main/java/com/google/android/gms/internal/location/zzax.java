package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.zzy;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzax extends zzy {
    private final ListenerHolder<LocationListener> zzda;

    public zzax(ListenerHolder<LocationListener> listenerHolder) {
        this.zzda = listenerHolder;
    }

    @Override // com.google.android.gms.location.zzx
    public final synchronized void onLocationChanged(Location location) {
        this.zzda.notifyListener(new zzay(this, location));
    }

    public final synchronized void release() {
        this.zzda.clear();
    }
}
