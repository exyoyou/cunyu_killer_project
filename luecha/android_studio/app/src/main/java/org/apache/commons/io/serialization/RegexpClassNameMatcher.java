package org.apache.commons.io.serialization;

import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
final class RegexpClassNameMatcher implements ClassNameMatcher {
    private final Pattern pattern;

    public RegexpClassNameMatcher(String str) {
        this(Pattern.compile(str));
    }

    public RegexpClassNameMatcher(Pattern pattern) {
        if (pattern != null) {
            this.pattern = pattern;
            return;
        }
        throw new IllegalArgumentException("Null pattern");
    }

    @Override // org.apache.commons.io.serialization.ClassNameMatcher
    public boolean matches(String str) {
        return this.pattern.matcher(str).matches();
    }
}
