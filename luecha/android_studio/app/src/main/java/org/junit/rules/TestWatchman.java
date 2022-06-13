package org.junit.rules;

import org.junit.internal.AssumptionViolatedException;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
@Deprecated
/* loaded from: classes5.dex */
public class TestWatchman implements MethodRule {
    public void failed(Throwable th, FrameworkMethod frameworkMethod) {
    }

    public void finished(FrameworkMethod frameworkMethod) {
    }

    public void starting(FrameworkMethod frameworkMethod) {
    }

    public void succeeded(FrameworkMethod frameworkMethod) {
    }

    @Override // org.junit.rules.MethodRule
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, Object obj) {
        return new Statement() { // from class: org.junit.rules.TestWatchman.1
            @Override // org.junit.runners.model.Statement
            public void evaluate() throws Throwable {
                try {
                    TestWatchman.this.starting(frameworkMethod);
                    try {
                        statement.evaluate();
                        TestWatchman.this.succeeded(frameworkMethod);
                    } catch (AssumptionViolatedException e) {
                        throw e;
                    } catch (Throwable th) {
                        TestWatchman.this.failed(th, frameworkMethod);
                        throw th;
                    }
                } finally {
                    TestWatchman.this.finished(frameworkMethod);
                }
            }
        };
    }
}
