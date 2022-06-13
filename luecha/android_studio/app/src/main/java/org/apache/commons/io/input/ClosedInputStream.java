package org.apache.commons.io.input;

import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class ClosedInputStream extends InputStream {
    public static final ClosedInputStream CLOSED_INPUT_STREAM = new ClosedInputStream();

    @Override // java.io.InputStream
    public int read() {
        return -1;
    }
}
