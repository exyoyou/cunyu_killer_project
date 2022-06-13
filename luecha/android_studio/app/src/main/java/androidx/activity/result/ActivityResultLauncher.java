package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public abstract class ActivityResultLauncher<I> {
    public abstract ActivityResultContract<I, ?> getContract();

    public abstract void launch(I i, ActivityOptionsCompat activityOptionsCompat);

    public abstract void unregister();

    public void launch(I i) {
        launch(i, null);
    }
}
