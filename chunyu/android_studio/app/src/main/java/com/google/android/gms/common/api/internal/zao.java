package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* loaded from: classes4.dex */
final class zao extends zabr {
    private final /* synthetic */ Dialog zadk;
    private final /* synthetic */ zan zadl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zao(zan zan, Dialog dialog) {
        this.zadl = zan;
        this.zadk = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zas() {
        this.zadl.zadj.zaq();
        if (this.zadk.isShowing()) {
            this.zadk.dismiss();
        }
    }
}
