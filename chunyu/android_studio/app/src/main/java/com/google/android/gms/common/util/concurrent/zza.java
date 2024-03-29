package com.google.android.gms.common.util.concurrent;

import android.os.Process;

/* loaded from: classes4.dex */
final class zza implements Runnable {
    private final int priority;
    private final Runnable zzhs;

    public zza(Runnable runnable, int i) {
        this.zzhs = runnable;
        this.priority = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.priority);
        this.zzhs.run();
    }
}
