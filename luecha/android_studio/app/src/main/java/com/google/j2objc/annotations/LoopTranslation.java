package com.google.j2objc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
@Target({ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes5.dex */
public @interface LoopTranslation {

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    public enum LoopStyle {
        JAVA_ITERATOR,
        FAST_ENUMERATION
    }

    LoopStyle value();
}
