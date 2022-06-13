package com.google.common.graph;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public interface MutableNetwork<N, E> extends Network<N, E> {
    boolean addEdge(EndpointPair<N> endpointPair, E e);

    boolean addEdge(N n, N n2, E e);

    boolean addNode(N n);

    boolean removeEdge(E e);

    boolean removeNode(N n);
}
