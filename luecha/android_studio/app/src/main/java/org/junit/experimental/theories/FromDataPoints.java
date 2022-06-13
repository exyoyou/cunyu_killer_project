package org.junit.experimental.theories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.experimental.theories.internal.SpecificDataPointsSupplier;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
@Target({ElementType.PARAMETER})
@ParametersSuppliedBy(SpecificDataPointsSupplier.class)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes5.dex */
public @interface FromDataPoints {
    String value();
}
