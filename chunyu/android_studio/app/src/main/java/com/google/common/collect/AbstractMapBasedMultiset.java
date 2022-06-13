package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {
    private static final long serialVersionUID = 0;
    transient ObjectCountHashMap<E> backingMap;
    transient long size;

    abstract void init(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractMapBasedMultiset(int i) {
        init(i);
    }

    @Override // com.google.common.collect.Multiset
    public final int count(@NullableDecl Object obj) {
        return this.backingMap.get(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public final int add(@NullableDecl E e, int i) {
        if (i == 0) {
            return count(e);
        }
        boolean z = true;
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        int indexOf = this.backingMap.indexOf(e);
        if (indexOf == -1) {
            this.backingMap.put(e, i);
            this.size += (long) i;
            return 0;
        }
        int value = this.backingMap.getValue(indexOf);
        long j = (long) i;
        long j2 = ((long) value) + j;
        if (j2 > 2147483647L) {
            z = false;
        }
        Preconditions.checkArgument(z, "too many occurrences: %s", j2);
        this.backingMap.setValue(indexOf, (int) j2);
        this.size += j;
        return value;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public final int remove(@NullableDecl Object obj, int i) {
        if (i == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        int indexOf = this.backingMap.indexOf(obj);
        if (indexOf == -1) {
            return 0;
        }
        int value = this.backingMap.getValue(indexOf);
        if (value > i) {
            this.backingMap.setValue(indexOf, value - i);
        } else {
            this.backingMap.removeEntry(indexOf);
            i = value;
        }
        this.size -= (long) i;
        return value;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public final int setCount(@NullableDecl E e, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        ObjectCountHashMap<E> objectCountHashMap = this.backingMap;
        int remove = i == 0 ? objectCountHashMap.remove(e) : objectCountHashMap.put(e, i);
        this.size += (long) (i - remove);
        return remove;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public final boolean setCount(@NullableDecl E e, int i, int i2) {
        CollectPreconditions.checkNonnegative(i, "oldCount");
        CollectPreconditions.checkNonnegative(i2, "newCount");
        int indexOf = this.backingMap.indexOf(e);
        if (indexOf == -1) {
            if (i != 0) {
                return false;
            }
            if (i2 > 0) {
                this.backingMap.put(e, i2);
                this.size += (long) i2;
            }
            return true;
        } else if (this.backingMap.getValue(indexOf) != i) {
            return false;
        } else {
            if (i2 == 0) {
                this.backingMap.removeEntry(indexOf);
                this.size -= (long) i;
            } else {
                this.backingMap.setValue(indexOf, i2);
                this.size += (long) (i2 - i);
            }
            return true;
        }
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.backingMap.clear();
        this.size = 0;
    }

    /* loaded from: classes2.dex */
    abstract class Itr<T> implements Iterator<T> {
        int entryIndex;
        int expectedModCount;
        int toRemove = -1;

        abstract T result(int i);

        Itr() {
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.firstIndex();
            this.expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        }

        private void checkForConcurrentModification() {
            if (AbstractMapBasedMultiset.this.backingMap.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkForConcurrentModification();
            return this.entryIndex >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T result = result(this.entryIndex);
                this.toRemove = this.entryIndex;
                this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndex(this.entryIndex);
                return result;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.toRemove != -1);
            AbstractMapBasedMultiset.this.size -= (long) AbstractMapBasedMultiset.this.backingMap.removeEntry(this.toRemove);
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndexAfterRemove(this.entryIndex, this.toRemove);
            this.toRemove = -1;
            this.expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        }
    }

    @Override // com.google.common.collect.AbstractMultiset
    final Iterator<E> elementIterator() {
        return new AbstractMapBasedMultiset<E>.Itr() { // from class: com.google.common.collect.AbstractMapBasedMultiset.1
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            E result(int i) {
                return AbstractMapBasedMultiset.this.backingMap.getKey(i);
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset
    final Iterator<Multiset.Entry<E>> entryIterator() {
        return new AbstractMapBasedMultiset<E>.Itr() { // from class: com.google.common.collect.AbstractMapBasedMultiset.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            public Multiset.Entry<E> result(int i) {
                return AbstractMapBasedMultiset.this.backingMap.getEntry(i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTo(Multiset<? super E> multiset) {
        Preconditions.checkNotNull(multiset);
        int firstIndex = this.backingMap.firstIndex();
        while (firstIndex >= 0) {
            multiset.add((E) this.backingMap.getKey(firstIndex), this.backingMap.getValue(firstIndex));
            firstIndex = this.backingMap.nextIndex(firstIndex);
        }
    }

    @Override // com.google.common.collect.AbstractMultiset
    final int distinctElements() {
        return this.backingMap.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public final Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public final int size() {
        return Ints.saturatedCast(this.size);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMultiset(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readCount = Serialization.readCount(objectInputStream);
        init(3);
        Serialization.populateMultiset(this, objectInputStream, readCount);
    }
}
