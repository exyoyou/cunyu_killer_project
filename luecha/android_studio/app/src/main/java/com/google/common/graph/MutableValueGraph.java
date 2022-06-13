package com.google.common.graph;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public interface MutableValueGraph<N, V> extends ValueGraph<N, V> {
    boolean addNode(N n);

    V putEdgeValue(EndpointPair<N> endpointPair, V v);

    V putEdgeValue(N n, N n2, V v);

    V removeEdge(EndpointPair<N> endpointPair);

    V removeEdge(N n, N n2);

    boolean removeNode(N n);
}
