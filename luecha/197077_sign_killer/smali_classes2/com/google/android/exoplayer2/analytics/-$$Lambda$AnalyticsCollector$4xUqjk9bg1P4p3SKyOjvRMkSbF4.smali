.class public final synthetic Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$4xUqjk9bg1P4p3SKyOjvRMkSbF4;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/google/android/exoplayer2/util/ListenerSet$Event;


# instance fields
.field private final synthetic f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;

.field private final synthetic f$1:I


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$4xUqjk9bg1P4p3SKyOjvRMkSbF4;->f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;

    iput p2, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$4xUqjk9bg1P4p3SKyOjvRMkSbF4;->f$1:I

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;)V
    .locals 2

    iget-object v0, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$4xUqjk9bg1P4p3SKyOjvRMkSbF4;->f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;

    iget v1, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$4xUqjk9bg1P4p3SKyOjvRMkSbF4;->f$1:I

    check-cast p1, Lcom/google/android/exoplayer2/analytics/AnalyticsListener;

    invoke-static {v0, v1, p1}, Lcom/google/android/exoplayer2/analytics/AnalyticsCollector;->lambda$onMaxSeekToPreviousPositionChanged$53(Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;ILcom/google/android/exoplayer2/analytics/AnalyticsListener;)V

    return-void
.end method
