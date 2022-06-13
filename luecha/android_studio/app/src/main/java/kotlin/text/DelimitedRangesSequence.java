package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Strings.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012:\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0096\u0002RB\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlin/text/DelimitedRangesSequence;", "Lkotlin/sequences/Sequence;", "Lkotlin/ranges/IntRange;", "input", "", "startIndex", "", "limit", "getNextMatch", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "currentIndex", "Lkotlin/Pair;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/CharSequence;IILkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public final class DelimitedRangesSequence implements Sequence<IntRange> {
    private final Function2<CharSequence, Integer, Pair<Integer, Integer>> getNextMatch;
    private final CharSequence input;
    private final int limit;
    private final int startIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public DelimitedRangesSequence(CharSequence charSequence, int i, int i2, Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>> function2) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        Intrinsics.checkNotNullParameter(function2, "getNextMatch");
        this.input = charSequence;
        this.startIndex = i;
        this.limit = i2;
        this.getNextMatch = function2;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<IntRange> iterator() {
        return new Object() { // from class: kotlin.text.DelimitedRangesSequence$iterator$1
            private int counter;
            private int currentStartIndex;
            private IntRange nextItem;
            private int nextSearchIndex;
            private int nextState = -1;

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Incorrect args count in method signature: ()V */
            {
                int coerceIn = RangesKt.coerceIn(DelimitedRangesSequence.this.startIndex, 0, DelimitedRangesSequence.this.input.length());
                this.currentStartIndex = coerceIn;
                this.nextSearchIndex = coerceIn;
            }

            public final int getNextState() {
                return this.nextState;
            }

            public final void setNextState(int i) {
                this.nextState = i;
            }

            public final int getCurrentStartIndex() {
                return this.currentStartIndex;
            }

            public final void setCurrentStartIndex(int i) {
                this.currentStartIndex = i;
            }

            public final int getNextSearchIndex() {
                return this.nextSearchIndex;
            }

            public final void setNextSearchIndex(int i) {
                this.nextSearchIndex = i;
            }

            public final IntRange getNextItem() {
                return this.nextItem;
            }

            public final void setNextItem(IntRange intRange) {
                this.nextItem = intRange;
            }

            public final int getCounter() {
                return this.counter;
            }

            public final void setCounter(int i) {
                this.counter = i;
            }

            /* Code decompiled incorrectly, please refer to instructions dump */
            private final void calcNext() {
                /*
                    r6 = this;
                    int r0 = r6.nextSearchIndex
                    r1 = 0
                    if (r0 >= 0) goto L_0x000e
                    r6.nextState = r1
                    r0 = 0
                    kotlin.ranges.IntRange r0 = (kotlin.ranges.IntRange) r0
                    r6.nextItem = r0
                    goto L_0x00a0
                L_0x000e:
                    kotlin.text.DelimitedRangesSequence r0 = kotlin.text.DelimitedRangesSequence.this
                    int r0 = kotlin.text.DelimitedRangesSequence.access$getLimit$p(r0)
                    r2 = -1
                    r3 = 1
                    if (r0 <= 0) goto L_0x0025
                    int r0 = r6.counter
                    int r0 = r0 + r3
                    r6.counter = r0
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    int r4 = kotlin.text.DelimitedRangesSequence.access$getLimit$p(r4)
                    if (r0 >= r4) goto L_0x0033
                L_0x0025:
                    int r0 = r6.nextSearchIndex
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r4 = r4.length()
                    if (r0 <= r4) goto L_0x0049
                L_0x0033:
                    int r0 = r6.currentStartIndex
                    kotlin.ranges.IntRange r1 = new kotlin.ranges.IntRange
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r4 = kotlin.text.StringsKt.getLastIndex(r4)
                    r1.<init>(r0, r4)
                    r6.nextItem = r1
                    r6.nextSearchIndex = r2
                    goto L_0x009e
                L_0x0049:
                    kotlin.text.DelimitedRangesSequence r0 = kotlin.text.DelimitedRangesSequence.this
                    kotlin.jvm.functions.Function2 r0 = kotlin.text.DelimitedRangesSequence.access$getGetNextMatch$p(r0)
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r5 = r6.nextSearchIndex
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                    java.lang.Object r0 = r0.invoke(r4, r5)
                    kotlin.Pair r0 = (kotlin.Pair) r0
                    if (r0 != 0) goto L_0x0079
                    int r0 = r6.currentStartIndex
                    kotlin.ranges.IntRange r1 = new kotlin.ranges.IntRange
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r4 = kotlin.text.StringsKt.getLastIndex(r4)
                    r1.<init>(r0, r4)
                    r6.nextItem = r1
                    r6.nextSearchIndex = r2
                    goto L_0x009e
                L_0x0079:
                    java.lang.Object r2 = r0.component1()
                    java.lang.Number r2 = (java.lang.Number) r2
                    int r2 = r2.intValue()
                    java.lang.Object r0 = r0.component2()
                    java.lang.Number r0 = (java.lang.Number) r0
                    int r0 = r0.intValue()
                    int r4 = r6.currentStartIndex
                    kotlin.ranges.IntRange r4 = kotlin.ranges.RangesKt.until(r4, r2)
                    r6.nextItem = r4
                    int r2 = r2 + r0
                    r6.currentStartIndex = r2
                    if (r0 != 0) goto L_0x009b
                    r1 = 1
                L_0x009b:
                    int r2 = r2 + r1
                    r6.nextSearchIndex = r2
                L_0x009e:
                    r6.nextState = r3
                L_0x00a0:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.calcNext():void");
            }

            @Override // java.util.Iterator
            public IntRange next() {
                if (this.nextState == -1) {
                    calcNext();
                }
                if (this.nextState != 0) {
                    IntRange intRange = this.nextItem;
                    Objects.requireNonNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
                    this.nextItem = null;
                    this.nextState = -1;
                    return intRange;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextState == -1) {
                    calcNext();
                }
                return this.nextState == 1;
            }
        };
    }
}
