.class Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit$2;
.super Ljava/lang/Object;
.source "HwAudioKaraokeFeatureKit.java"

# interfaces
.implements Landroid/os/IBinder$DeathRecipient;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;


# direct methods
.method constructor <init>(Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;)V
    .locals 0

    .line 66
    iput-object p1, p0, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit$2;->this$0:Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public binderDied()V
    .locals 3

    const-string v0, "HwAudioKit.HwAudioKaraokeFeatureKit"

    const-string v1, "binderDied"

    .line 68
    invoke-static {v0, v1}, Lcom/tencent/liteav/basic/log/TXCLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    .line 69
    iget-object v0, p0, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit$2;->this$0:Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;

    invoke-static {v0}, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;->access$700(Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;)Landroid/os/IBinder;

    move-result-object v0

    iget-object v1, p0, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit$2;->this$0:Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;

    invoke-static {v1}, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;->access$600(Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;)Landroid/os/IBinder$DeathRecipient;

    move-result-object v1

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/os/IBinder;->unlinkToDeath(Landroid/os/IBinder$DeathRecipient;I)Z

    .line 70
    iget-object v0, p0, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit$2;->this$0:Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;

    invoke-static {v0}, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;->access$200(Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;)Lcom/huawei/multimedia/liteav/audiokit/interfaces/FeatureKitManager;

    move-result-object v0

    const/16 v1, 0x3eb

    invoke-virtual {v0, v1}, Lcom/huawei/multimedia/liteav/audiokit/interfaces/FeatureKitManager;->onCallBack(I)V

    .line 71
    iget-object v0, p0, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit$2;->this$0:Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;->access$702(Lcom/huawei/multimedia/liteav/audiokit/interfaces/HwAudioKaraokeFeatureKit;Landroid/os/IBinder;)Landroid/os/IBinder;

    return-void
.end method
