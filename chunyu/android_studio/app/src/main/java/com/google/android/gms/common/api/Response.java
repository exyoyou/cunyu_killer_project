package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* loaded from: classes4.dex */
public class Response<T extends Result> {
    private T zzao;

    public Response() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response(T t) {
        this.zzao = t;
    }

    protected T getResult() {
        return this.zzao;
    }

    public void setResult(T t) {
        this.zzao = t;
    }
}
