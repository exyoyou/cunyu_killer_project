.class public final synthetic Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$-0g2wQmToQEeT0LOx3ycdVpB5ws;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/google/android/exoplayer2/util/ListenerSet$Event;


# instance fields
.field private final synthetic f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;

.field private final synthetic f$1:Ljava/lang/Object;

.field private final synthetic f$2:J


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;Ljava/lang/Object;J)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$-0g2wQmToQEeT0LOx3ycdVpB5ws;->f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;

    iput-object p2, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$-0g2wQmToQEeT0LOx3ycdVpB5ws;->f$1:Ljava/lang/Object;

    iput-wide p3, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$-0g2wQmToQEeT0LOx3ycdVpB5ws;->f$2:J

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;)V
    .locals 4

    iget-object v0, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$-0g2wQmToQEeT0LOx3ycdVpB5ws;->f$0:Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;

    iget-object v1, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$-0g2wQmToQEeT0LOx3ycdVpB5ws;->f$1:Ljava/lang/Object;

    iget-wide v2, p0, Lcom/google/android/exoplayer2/analytics/-$$Lambda$AnalyticsCollector$-0g2wQmToQEeT0LOx3ycdVpB5ws;->f$2:J

    check-cast p1, Lcom/google/android/exoplayer2/analytics/AnalyticsListener;

    invoke-static {v0, v1, v2, v3, p1}, Lcom/google/android/exoplayer2/analytics/AnalyticsCollector;->lambda$onRenderedFirstFrame$25(Lcom/google/android/exoplayer2/analytics/AnalyticsListener$EventTime;Ljava/lang/Object;JLcom/google/android/exoplayer2/analytics/AnalyticsListener;)V

    return-void
.end method
