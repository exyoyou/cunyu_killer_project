.class public final synthetic Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$uX7qombvbhfllBfrArZ4fy72Fso;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/google/android/exoplayer2/util/ListenerSet$IterationFinishedEvent;


# instance fields
.field private final synthetic f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsCollector;

.field private final synthetic f$1:Lcom/google/android/exoplayer2/Player;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/exoplayer2/analytics/AnalyticsCollector;Lcom/google/android/exoplayer2/Player;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$uX7qombvbhfllBfrArZ4fy72Fso;->f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsCollector;

    iput-object p2, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$uX7qombvbhfllBfrArZ4fy72Fso;->f$1:Lcom/google/android/exoplayer2/Player;

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;Lcom/google/android/exoplayer2/util/FlagSet;)V
    .locals 2

    iget-object v0, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$uX7qombvbhfllBfrArZ4fy72Fso;->f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsCollector;

    iget-object v1, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$uX7qombvbhfllBfrArZ4fy72Fso;->f$1:Lcom/google/android/exoplayer2/Player;

    check-cast p1, Lcom/google/android/exoplayer2/analytics/AnalyticsListener;

    invoke-virtual {v0, v1, p1, p2}, Lcom/google/android/exoplayer2/analytics/AnalyticsCollector;->lambda$setPlayer$1$AnalyticsCollector(Lcom/google/android/exoplayer2/Player;Lcom/google/android/exoplayer2/analytics/AnalyticsListener;Lcom/google/android/exoplayer2/util/FlagSet;)V

    return-void
.end method
