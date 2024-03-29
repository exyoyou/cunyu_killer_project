package com.google.common.base;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes4.dex */
public interface Function<F, T> {
    @NullableDecl
    T apply(@NullableDecl F f);

    @Override // java.lang.Object
    boolean equals(@NullableDecl Object obj);
}
