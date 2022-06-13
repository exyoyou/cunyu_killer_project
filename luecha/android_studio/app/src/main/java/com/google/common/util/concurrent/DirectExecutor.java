package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
enum DirectExecutor implements Executor {
    INSTANCE;

    @Override // java.lang.Enum, java.lang.Object
    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
