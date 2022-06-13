package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public abstract class ActivityResultContract<I, O> {
    public abstract Intent createIntent(Context context, I i);

    public SynchronousResult<O> getSynchronousResult(Context context, I i) {
        return null;
    }

    public abstract O parseResult(int i, Intent intent);

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
    public static final class SynchronousResult<T> {
        private final T mValue;

        public SynchronousResult(T t) {
            this.mValue = t;
        }

        public T getValue() {
            return this.mValue;
        }
    }
}
