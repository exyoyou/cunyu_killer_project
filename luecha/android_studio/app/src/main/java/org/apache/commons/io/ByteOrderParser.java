package org.apache.commons.io;

import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public final class ByteOrderParser {
    private ByteOrderParser() {
    }

    public static ByteOrder parseByteOrder(String str) {
        if (ByteOrder.BIG_ENDIAN.toString().equals(str)) {
            return ByteOrder.BIG_ENDIAN;
        }
        if (ByteOrder.LITTLE_ENDIAN.toString().equals(str)) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        throw new IllegalArgumentException("Unsupported byte order setting: " + str + ", expeced one of " + ByteOrder.LITTLE_ENDIAN + ", " + ByteOrder.BIG_ENDIAN);
    }
}
