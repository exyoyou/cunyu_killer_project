package org.junit.rules;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public abstract class Verifier implements TestRule {
    protected void verify() throws Throwable {
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement statement, Description description) {
        return new Statement() { // from class: org.junit.rules.Verifier.1
            @Override // org.junit.runners.model.Statement
            public void evaluate() throws Throwable {
                statement.evaluate();
                Verifier.this.verify();
            }
        };
    }
}
