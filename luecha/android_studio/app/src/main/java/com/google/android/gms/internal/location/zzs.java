package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

/* loaded from: classes2.dex */
final class zzs extends zzab {
    private final /* synthetic */ LocationCallback zzcm;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzq zzq, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        this.zzcm = locationCallback;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zzaz zzaz) throws RemoteException {
        zzaz.zzb(ListenerHolders.createListenerKey(this.zzcm, LocationCallback.class.getSimpleName()), new zzac(this));
    }
}
