package com.google.errorprone.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
/* loaded from: classes5.dex */
public @interface RestrictedApi {
    String allowedOnPath() default "";

    String checkerName() default "RestrictedApi";

    String explanation();

    String link();

    Class<? extends Annotation>[] whitelistAnnotations() default {};

    Class<? extends Annotation>[] whitelistWithWarningAnnotations() default {};
}
