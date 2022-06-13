package org.hamcrest.core;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class StringEndsWith extends SubstringMatcher {
    @Override // org.hamcrest.core.SubstringMatcher
    protected String relationship() {
        return "ending with";
    }

    public StringEndsWith(String str) {
        super(str);
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected boolean evalSubstringOf(String str) {
        return str.endsWith(this.substring);
    }

    @Factory
    public static Matcher<String> endsWith(String str) {
        return new StringEndsWith(str);
    }
}
