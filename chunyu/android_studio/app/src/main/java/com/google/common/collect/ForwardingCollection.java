package com.google.common.collect;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public abstract class ForwardingCollection<E> extends ForwardingObject implements Collection<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public abstract Collection<E> delegate();

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return delegate().iterator();
    }

    @Override // java.util.Collection
    public int size() {
        return delegate().size();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        return delegate().removeAll(collection);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return delegate().contains(obj);
    }

    @Override // java.util.Collection, java.util.Queue
    public boolean add(E e) {
        return delegate().add(e);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return delegate().remove(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        return delegate().containsAll(collection);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return delegate().addAll(collection);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        return delegate().retainAll(collection);
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        delegate().clear();
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        return delegate().toArray();
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) delegate().toArray(tArr);
    }

    protected boolean standardContains(@NullableDecl Object obj) {
        return Iterators.contains(iterator(), obj);
    }

    protected boolean standardContainsAll(Collection<?> collection) {
        return Collections2.containsAllImpl(this, collection);
    }

    protected boolean standardAddAll(Collection<? extends E> collection) {
        return Iterators.addAll(this, collection.iterator());
    }

    protected boolean standardRemove(@NullableDecl Object obj) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (Objects.equal(it.next(), obj)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    protected boolean standardRemoveAll(Collection<?> collection) {
        return Iterators.removeAll(iterator(), collection);
    }

    protected boolean standardRetainAll(Collection<?> collection) {
        return Iterators.retainAll(iterator(), collection);
    }

    protected void standardClear() {
        Iterators.clear(iterator());
    }

    protected boolean standardIsEmpty() {
        return !iterator().hasNext();
    }

    protected String standardToString() {
        return Collections2.toStringImpl(this);
    }

    protected Object[] standardToArray() {
        return toArray(new Object[size()]);
    }

    protected <T> T[] standardToArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }
}
