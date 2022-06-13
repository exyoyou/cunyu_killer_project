package org.junit.internal.runners.statements;

import org.junit.runners.model.Statement;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class Fail extends Statement {
    private final Throwable error;

    public Fail(Throwable th) {
        this.error = th;
    }

    @Override // org.junit.runners.model.Statement
    public void evaluate() throws Throwable {
        throw this.error;
    }
}
