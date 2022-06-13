package org.junit.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class ArrayComparisonFailure extends AssertionError {
    private static final long serialVersionUID = 1;
    private final List<Integer> fIndices = new ArrayList();
    private final String fMessage;

    public ArrayComparisonFailure(String str, AssertionError assertionError, int i) {
        this.fMessage = str;
        initCause(assertionError);
        addDimension(i);
    }

    public void addDimension(int i) {
        this.fIndices.add(0, Integer.valueOf(i));
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        String str = this.fMessage;
        if (str != null) {
            sb.append(str);
        }
        sb.append("arrays first differed at element ");
        for (Integer num : this.fIndices) {
            int intValue = num.intValue();
            sb.append("[");
            sb.append(intValue);
            sb.append("]");
        }
        sb.append("; ");
        sb.append(getCause().getMessage());
        return sb.toString();
    }

    @Override // java.lang.Throwable, java.lang.Object
    public String toString() {
        return getMessage();
    }
}
