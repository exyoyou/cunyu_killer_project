.class public Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;
.super Ljava/lang/Object;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "LoadSubtitleParams"
.end annotation


# instance fields
.field public bufferingDurationInt:I

.field public cdnTypeInt:I

.field public cgiUrlIndex:I

.field public endTimeUnix:J

.field public errCodeString:Ljava/lang/String;

.field public starTimeUnix:J

.field public subtitleUrlString:Ljava/lang/String;

.field final synthetic this$0:Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams;


# direct methods
.method public constructor <init>(Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams;)V
    .locals 0

    iput-object p1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->this$0:Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public paramsToJson(Lorg/json/JSONObject;)V
    .locals 3

    :try_start_0
    const-string v0, "stime"

    iget-wide v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->starTimeUnix:J

    invoke-virtual {p1, v0, v1, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    const-string v0, "etime"

    iget-wide v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->endTimeUnix:J

    invoke-virtual {p1, v0, v1, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    const-string v0, "bufferduration"

    iget v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->bufferingDurationInt:I

    invoke-virtual {p1, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    const-string v0, "url"

    iget-object v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->subtitleUrlString:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    const-string v0, "vt"

    iget v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->cdnTypeInt:I

    invoke-virtual {p1, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    const-string v0, "urlindex"

    iget v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->cgiUrlIndex:I

    invoke-virtual {p1, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    const-string v0, "code"

    iget-object v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->errCodeString:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "TPReportParams"

    invoke-static {v0, p1}, Lcom/tencent/thumbplayer/utils/TPLogUtil;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_0
    return-void
.end method

.method public paramsToProperties(Lcom/tencent/thumbplayer/b/a/a;)V
    .locals 3

    iget-wide v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->starTimeUnix:J

    const-string v2, "stime"

    invoke-interface {p1, v2, v0, v1}, Lcom/tencent/thumbplayer/b/a/a;->a(Ljava/lang/String;J)V

    iget-wide v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->endTimeUnix:J

    const-string v2, "etime"

    invoke-interface {p1, v2, v0, v1}, Lcom/tencent/thumbplayer/b/a/a;->a(Ljava/lang/String;J)V

    iget v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->bufferingDurationInt:I

    const-string v1, "bufferduration"

    invoke-interface {p1, v1, v0}, Lcom/tencent/thumbplayer/b/a/a;->a(Ljava/lang/String;I)V

    iget-object v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->subtitleUrlString:Ljava/lang/String;

    const-string v1, "url"

    invoke-interface {p1, v1, v0}, Lcom/tencent/thumbplayer/b/a/a;->a(Ljava/lang/String;Ljava/lang/String;)V

    iget v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->cdnTypeInt:I

    const-string v1, "vt"

    invoke-interface {p1, v1, v0}, Lcom/tencent/thumbplayer/b/a/a;->a(Ljava/lang/String;I)V

    iget v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->cgiUrlIndex:I

    const-string v1, "urlindex"

    invoke-interface {p1, v1, v0}, Lcom/tencent/thumbplayer/b/a/a;->a(Ljava/lang/String;I)V

    iget-object v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->errCodeString:Ljava/lang/String;

    const-string v1, "code"

    invoke-interface {p1, v1, v0}, Lcom/tencent/thumbplayer/b/a/a;->a(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public reset()V
    .locals 2

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->starTimeUnix:J

    iput-wide v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->endTimeUnix:J

    const/4 v0, 0x0

    iput v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->bufferingDurationInt:I

    const-string v1, ""

    iput-object v1, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->subtitleUrlString:Ljava/lang/String;

    iput v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->cdnTypeInt:I

    iput v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->cgiUrlIndex:I

    const-string v0, "0"

    iput-object v0, p0, Lcom/tencent/thumbplayer/tplayer/plugins/report/TPReportParams$LoadSubtitleParams;->errCodeString:Ljava/lang/String;

    return-void
.end method
