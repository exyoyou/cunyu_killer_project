package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.internal.location.zzad;
import com.google.android.gms.internal.location.zzak;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class zzp extends zzak {
    private final /* synthetic */ TaskCompletionSource zzab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(FusedLocationProviderClient fusedLocationProviderClient, TaskCompletionSource taskCompletionSource) {
        this.zzab = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzaj
    public final void zza(zzad zzad) throws RemoteException {
        Status status = zzad.getStatus();
        if (status == null) {
            this.zzab.trySetException(new ApiException(new Status(8, "Got null status from location service")));
        } else if (status.getStatusCode() == 0) {
            this.zzab.setResult(true);
        } else {
            this.zzab.trySetException(ApiExceptionUtil.fromStatus(status));
        }
    }
}
