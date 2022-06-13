package org.junit.internal.builders;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class IgnoredClassRunner extends Runner {
    private final Class<?> clazz;

    public IgnoredClassRunner(Class<?> cls) {
        this.clazz = cls;
    }

    @Override // org.junit.runner.Runner
    public void run(RunNotifier runNotifier) {
        runNotifier.fireTestIgnored(getDescription());
    }

    @Override // org.junit.runner.Runner, org.junit.runner.Describable
    public Description getDescription() {
        return Description.createSuiteDescription(this.clazz);
    }
}
