package com.google.common.base;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class Enums {
    private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> enumConstantCache = new WeakHashMap();

    private Enums() {
    }

    public static Field getField(Enum<?> r1) {
        try {
            return r1.getDeclaringClass().getDeclaredField(r1.name());
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> cls, String str) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(str);
        return Platform.getEnumIfPresent(cls, str);
    }

    private static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(Class<T> cls) {
        HashMap hashMap = new HashMap();
        Iterator it = EnumSet.allOf(cls).iterator();
        while (it.hasNext()) {
            Enum r2 = (Enum) it.next();
            hashMap.put(r2.name(), new WeakReference(r2));
        }
        enumConstantCache.put(cls, hashMap);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(Class<T> cls) {
        Map<String, WeakReference<? extends Enum<?>>> map;
        synchronized (enumConstantCache) {
            map = enumConstantCache.get(cls);
            if (map == null) {
                map = populateCache(cls);
            }
        }
        return map;
    }

    public static <T extends Enum<T>> Converter<String, T> stringConverter(Class<T> cls) {
        return new StringConverter(cls);
    }

    /* loaded from: classes2.dex */
    private static final class StringConverter<T extends Enum<T>> extends Converter<String, T> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Class<T> enumClass;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.base.Converter
        protected /* bridge */ /* synthetic */ String doBackward(Object obj) {
            return doBackward((StringConverter<T>) ((Enum) obj));
        }

        StringConverter(Class<T> cls) {
            this.enumClass = (Class) Preconditions.checkNotNull(cls);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public T doForward(String str) {
            return (T) Enum.valueOf(this.enumClass, str);
        }

        protected String doBackward(T t) {
            return t.name();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function, java.lang.Object
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof StringConverter) {
                return this.enumClass.equals(((StringConverter) obj).enumClass);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.enumClass.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
        }
    }
}
