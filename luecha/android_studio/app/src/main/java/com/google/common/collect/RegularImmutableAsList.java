package com.google.common.collect;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
class RegularImmutableAsList<E> extends ImmutableAsList<E> {
    private final ImmutableCollection<E> delegate;
    private final ImmutableList<? extends E> delegateList;

    RegularImmutableAsList(ImmutableCollection<E> immutableCollection, ImmutableList<? extends E> immutableList) {
        this.delegate = immutableCollection;
        this.delegateList = immutableList;
    }

    RegularImmutableAsList(ImmutableCollection<E> immutableCollection, Object[] objArr) {
        this(immutableCollection, ImmutableList.asImmutableList(objArr));
    }

    RegularImmutableAsList(ImmutableCollection<E> immutableCollection, Object[] objArr, int i) {
        this(immutableCollection, ImmutableList.asImmutableList(objArr, i));
    }

    @Override // com.google.common.collect.ImmutableAsList
    ImmutableCollection<E> delegateCollection() {
        return this.delegate;
    }

    ImmutableList<? extends E> delegateList() {
        return this.delegateList;
    }

    @Override // com.google.common.collect.ImmutableList, java.util.List
    public UnmodifiableListIterator<E> listIterator(int i) {
        return (UnmodifiableListIterator<? extends E>) this.delegateList.listIterator(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        return this.delegateList.copyIntoArray(objArr, i);
    }

    @Override // com.google.common.collect.ImmutableCollection
    Object[] internalArray() {
        return this.delegateList.internalArray();
    }

    @Override // com.google.common.collect.ImmutableCollection
    int internalArrayStart() {
        return this.delegateList.internalArrayStart();
    }

    @Override // com.google.common.collect.ImmutableCollection
    int internalArrayEnd() {
        return this.delegateList.internalArrayEnd();
    }

    @Override // java.util.List
    public E get(int i) {
        return (E) this.delegateList.get(i);
    }
}
