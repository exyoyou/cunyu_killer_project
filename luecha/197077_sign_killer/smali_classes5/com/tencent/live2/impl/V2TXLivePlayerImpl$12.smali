.class Lcom/tencent/live2/impl/V2TXLivePlayerImpl$12;
.super Ljava/lang/Object;
.source "V2TXLivePlayerImpl.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/tencent/live2/impl/V2TXLivePlayerImpl;->resumeAudio()I
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/tencent/live2/impl/V2TXLivePlayerImpl;


# direct methods
.method constructor <init>(Lcom/tencent/live2/impl/V2TXLivePlayerImpl;)V
    .locals 0

    .line 375
    iput-object p1, p0, Lcom/tencent/live2/impl/V2TXLivePlayerImpl$12;->this$0:Lcom/tencent/live2/impl/V2TXLivePlayerImpl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .line 378
    iget-object v0, p0, Lcom/tencent/live2/impl/V2TXLivePlayerImpl$12;->this$0:Lcom/tencent/live2/impl/V2TXLivePlayerImpl;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/tencent/live2/impl/V2TXLivePlayerImpl;->access$1302(Lcom/tencent/live2/impl/V2TXLivePlayerImpl;Z)Z

    .line 379
    iget-object v0, p0, Lcom/tencent/live2/impl/V2TXLivePlayerImpl$12;->this$0:Lcom/tencent/live2/impl/V2TXLivePlayerImpl;

    invoke-static {v0}, Lcom/tencent/live2/impl/V2TXLivePlayerImpl;->access$100(Lcom/tencent/live2/impl/V2TXLivePlayerImpl;)Lcom/tencent/live2/V2TXLivePlayer;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 380
    iget-object v0, p0, Lcom/tencent/live2/impl/V2TXLivePlayerImpl$12;->this$0:Lcom/tencent/live2/impl/V2TXLivePlayerImpl;

    invoke-static {v0}, Lcom/tencent/live2/impl/V2TXLivePlayerImpl;->access$100(Lcom/tencent/live2/impl/V2TXLivePlayerImpl;)Lcom/tencent/live2/V2TXLivePlayer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/tencent/live2/V2TXLivePlayer;->resumeAudio()I

    :cond_0
    return-void
.end method
