package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public final class CombinedFuture<V> extends AggregateFuture<Object, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, AsyncCallable<V> asyncCallable) {
        init(new CombinedFutureRunningState(immutableCollection, z, new AsyncCallableInterruptibleTask(asyncCallable, executor)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, Callable<V> callable) {
        init(new CombinedFutureRunningState(immutableCollection, z, new CallableInterruptibleTask(callable, executor)));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    private final class CombinedFutureRunningState extends AggregateFuture<Object, V>.RunningState {
        private CombinedFutureInterruptibleTask task;

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        void collectOneValue(boolean z, int i, @NullableDecl Object obj) {
        }

        CombinedFutureRunningState(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, CombinedFutureInterruptibleTask combinedFutureInterruptibleTask) {
            super(immutableCollection, z, false);
            this.task = combinedFutureInterruptibleTask;
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        void handleAllCompleted() {
            CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.execute();
            } else {
                Preconditions.checkState(CombinedFuture.this.isDone());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.task = null;
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        void interruptTask() {
            CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.interruptTask();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    private abstract class CombinedFutureInterruptibleTask<T> extends InterruptibleTask<T> {
        private final Executor listenerExecutor;
        boolean thrownByExecute = true;

        abstract void setValue(T t);

        public CombinedFutureInterruptibleTask(Executor executor) {
            this.listenerExecutor = (Executor) Preconditions.checkNotNull(executor);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        final boolean isDone() {
            return CombinedFuture.this.isDone();
        }

        final void execute() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e) {
                if (this.thrownByExecute) {
                    CombinedFuture.this.setException(e);
                }
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        final void afterRanInterruptibly(T t, Throwable th) {
            if (th == null) {
                setValue(t);
            } else if (th instanceof ExecutionException) {
                CombinedFuture.this.setException(th.getCause());
            } else if (th instanceof CancellationException) {
                CombinedFuture.this.cancel(false);
            } else {
                CombinedFuture.this.setException(th);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    private final class AsyncCallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask {
        private final AsyncCallable<V> callable;

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        /* bridge */ /* synthetic */ void setValue(Object obj) {
            setValue((ListenableFuture) ((ListenableFuture) obj));
        }

        public AsyncCallableInterruptibleTask(AsyncCallable<V> asyncCallable, Executor executor) {
            super(executor);
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public ListenableFuture<V> runInterruptibly() throws Exception {
            this.thrownByExecute = false;
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        void setValue(ListenableFuture<V> listenableFuture) {
            CombinedFuture.this.setFuture(listenableFuture);
            CombinedFuture.this.releaseResources();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        String toPendingString() {
            return this.callable.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    private final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask {
        private final Callable<V> callable;

        public CallableInterruptibleTask(Callable<V> callable, Executor executor) {
            super(executor);
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        V runInterruptibly() throws Exception {
            this.thrownByExecute = false;
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        void setValue(V v) {
            CombinedFuture.this.set(v);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        String toPendingString() {
            return this.callable.toString();
        }
    }
}
