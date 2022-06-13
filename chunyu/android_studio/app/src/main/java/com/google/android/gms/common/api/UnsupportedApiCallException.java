package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

/* loaded from: classes4.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzar;

    public UnsupportedApiCallException(Feature feature) {
        this.zzar = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String valueOf = String.valueOf(this.zzar);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
        sb.append("Missing ");
        sb.append(valueOf);
        return sb.toString();
    }
}
