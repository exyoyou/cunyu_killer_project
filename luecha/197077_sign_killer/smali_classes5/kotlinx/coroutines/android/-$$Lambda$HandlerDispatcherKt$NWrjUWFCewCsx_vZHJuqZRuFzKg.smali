.class public final synthetic Lkotlinx/coroutines/android/-$$Lambda$HandlerDispatcherKt$NWrjUWFCewCsx_vZHJuqZRuFzKg;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Landroid/view/Choreographer$FrameCallback;


# instance fields
.field private final synthetic f$0:Lkotlinx/coroutines/CancellableContinuation;


# direct methods
.method public synthetic constructor <init>(Lkotlinx/coroutines/CancellableContinuation;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lkotlinx/coroutines/android/-$$Lambda$HandlerDispatcherKt$NWrjUWFCewCsx_vZHJuqZRuFzKg;->f$0:Lkotlinx/coroutines/CancellableContinuation;

    return-void
.end method


# virtual methods
.method public final doFrame(J)V
    .locals 1

    iget-object v0, p0, Lkotlinx/coroutines/android/-$$Lambda$HandlerDispatcherKt$NWrjUWFCewCsx_vZHJuqZRuFzKg;->f$0:Lkotlinx/coroutines/CancellableContinuation;

    invoke-static {v0, p1, p2}, Lkotlinx/coroutines/android/HandlerDispatcherKt;->lambda$NWrjUWFCewCsx_vZHJuqZRuFzKg(Lkotlinx/coroutines/CancellableContinuation;J)V

    return-void
.end method
