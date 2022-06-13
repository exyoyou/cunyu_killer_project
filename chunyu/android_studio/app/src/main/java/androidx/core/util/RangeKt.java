package androidx.core.util;

import android.util.Range;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: Range.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\f\u001a6\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0007\u001a7\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\n\u001a0\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u0002H\u00022\u0006\u0010\t\u001a\u0002H\u0002H\u0087\f¢\u0006\u0002\u0010\n\u001a(\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a(\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0007¨\u0006\u000e"}, d2 = {"and", "Landroid/util/Range;", ExifInterface.GPS_DIRECTION_TRUE, "", "other", "plus", "value", "(Landroid/util/Range;Ljava/lang/Comparable;)Landroid/util/Range;", "rangeTo", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;", "toClosedRange", "Lkotlin/ranges/ClosedRange;", "toRange", "core-ktx_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RangeKt {
    public static final <T extends Comparable<? super T>> Range<T> rangeTo(T t, T t2) {
        Intrinsics.checkParameterIsNotNull(t, "$this$rangeTo");
        Intrinsics.checkParameterIsNotNull(t2, "that");
        return new Range<>(t, t2);
    }

    public static final <T extends Comparable<? super T>> Range<T> plus(Range<T> range, T t) {
        Intrinsics.checkParameterIsNotNull(range, "$this$plus");
        Intrinsics.checkParameterIsNotNull(t, "value");
        Range<T> extend = range.extend((Range<T>) t);
        Intrinsics.checkExpressionValueIsNotNull(extend, "extend(value)");
        return extend;
    }

    public static final <T extends Comparable<? super T>> Range<T> plus(Range<T> range, Range<T> range2) {
        Intrinsics.checkParameterIsNotNull(range, "$this$plus");
        Intrinsics.checkParameterIsNotNull(range2, "other");
        Range<T> extend = range.extend(range2);
        Intrinsics.checkExpressionValueIsNotNull(extend, "extend(other)");
        return extend;
    }

    public static final <T extends Comparable<? super T>> Range<T> and(Range<T> range, Range<T> range2) {
        Intrinsics.checkParameterIsNotNull(range, "$this$and");
        Intrinsics.checkParameterIsNotNull(range2, "other");
        Range<T> intersect = range.intersect(range2);
        Intrinsics.checkExpressionValueIsNotNull(intersect, "intersect(other)");
        return intersect;
    }

    public static final <T extends Comparable<? super T>> ClosedRange<T> toClosedRange(Range<T> range) {
        Intrinsics.checkParameterIsNotNull(range, "$this$toClosedRange");
        return new ClosedRange<T>(range) { // from class: androidx.core.util.RangeKt$toClosedRange$1
            final /* synthetic */ Range $this_toClosedRange;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$this_toClosedRange = r1;
            }

            /* JADX WARN: Incorrect types in method signature: (TT;)Z */
            @Override // kotlin.ranges.ClosedRange
            public boolean contains(Comparable comparable) {
                Intrinsics.checkParameterIsNotNull(comparable, "value");
                return ClosedRange.DefaultImpls.contains(this, comparable);
            }

            @Override // kotlin.ranges.ClosedRange
            public boolean isEmpty() {
                return ClosedRange.DefaultImpls.isEmpty(this);
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.ranges.ClosedRange
            public Comparable getEndInclusive() {
                return this.$this_toClosedRange.getUpper();
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.ranges.ClosedRange
            public Comparable getStart() {
                return this.$this_toClosedRange.getLower();
            }
        };
    }

    public static final <T extends Comparable<? super T>> Range<T> toRange(ClosedRange<T> closedRange) {
        Intrinsics.checkParameterIsNotNull(closedRange, "$this$toRange");
        return new Range<>(closedRange.getStart(), closedRange.getEndInclusive());
    }
}
