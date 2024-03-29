package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: PlatformRandom.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0000\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0007\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0007¨\u0006\n"}, d2 = {"defaultPlatformRandom", "Lkotlin/random/Random;", "doubleFromParts", "", "hi26", "", "low27", "asJavaRandom", "Ljava/util/Random;", "asKotlinRandom", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlatformRandomKt {
    public static final double doubleFromParts(int i, int i2) {
        return ((double) ((((long) i) << 27) + ((long) i2))) / 9.007199254740992E15d;
    }

    public static final Random asJavaRandom(Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        AbstractPlatformRandom abstractPlatformRandom = random instanceof AbstractPlatformRandom ? (AbstractPlatformRandom) random : null;
        return abstractPlatformRandom == null ? new KotlinRandom(random) : abstractPlatformRandom.getImpl();
    }

    public static final Random asKotlinRandom(Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        KotlinRandom kotlinRandom = random instanceof KotlinRandom ? (KotlinRandom) random : null;
        return kotlinRandom == null ? new PlatformRandom(random) : kotlinRandom.getImpl();
    }

    private static final Random defaultPlatformRandom() {
        return PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }
}
