.class public final Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager$profileListener$1;
.super Ljava/lang/Object;
.source "HeadsetManager.kt"

# interfaces
.implements Landroid/bluetooth/BluetoothProfile$ServiceListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;-><init>(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0008\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\t"
    }
    d2 = {
        "com/github/florent37/assets_audio_player/stopwhencall/HeadsetManager$profileListener$1",
        "Landroid/bluetooth/BluetoothProfile$ServiceListener;",
        "onServiceConnected",
        "",
        "profile",
        "",
        "proxy",
        "Landroid/bluetooth/BluetoothProfile;",
        "onServiceDisconnected",
        "assets_audio_player_release"
    }
    k = 0x1
    mv = {
        0x1,
        0x6,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field final synthetic this$0:Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;


# direct methods
.method constructor <init>(Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;)V
    .locals 0

    iput-object p1, p0, Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager$profileListener$1;->this$0:Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;

    .line 49
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onServiceConnected(ILandroid/bluetooth/BluetoothProfile;)V
    .locals 1

    const-string v0, "proxy"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 p2, 0x1

    if-ne p1, p2, :cond_1

    .line 53
    iget-object p1, p0, Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager$profileListener$1;->this$0:Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;

    invoke-virtual {p1}, Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;->getOnHeadsetPluggedListener()Lkotlin/jvm/functions/Function1;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p2

    invoke-interface {p1, p2}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    :goto_0
    return-void
.end method

.method public onServiceDisconnected(I)V
    .locals 1

    const/4 v0, 0x1

    if-ne p1, v0, :cond_1

    .line 59
    iget-object p1, p0, Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager$profileListener$1;->this$0:Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;

    invoke-virtual {p1}, Lcom/github/florent37/assets_audio_player/stopwhencall/HeadsetManager;->getOnHeadsetPluggedListener()Lkotlin/jvm/functions/Function1;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-interface {p1, v0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    :goto_0
    return-void
.end method
