package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
abstract class TypeCapture<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public final Type capture() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Preconditions.checkArgument(genericSuperclass instanceof ParameterizedType, "%s isn't parameterized", genericSuperclass);
        return ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }
}
