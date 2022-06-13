package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes2.dex */
final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zaak zagi;

    private zaat(zaak zaak) {
        this.zagi = zaak;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zagi.zaga.zaa(new zaar(this.zagi));
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zagi.zaen.lock();
        try {
            if (this.zagi.zad(connectionResult)) {
                this.zagi.zaar();
                this.zagi.zaap();
            } else {
                this.zagi.zae(connectionResult);
            }
        } finally {
            this.zagi.zaen.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zaat(zaak zaak, zaal zaal) {
        this(zaak);
    }
}
