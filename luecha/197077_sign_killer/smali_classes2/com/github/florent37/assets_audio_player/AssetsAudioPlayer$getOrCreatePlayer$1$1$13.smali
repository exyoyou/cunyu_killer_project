.class final Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$getOrCreatePlayer$1$1$13;
.super Lkotlin/jvm/internal/Lambda;
.source "AssetsAudioPlayerPlugin.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer;->getOrCreatePlayer(Ljava/lang/String;)Lcom/github/florent37/assets_audio_player/Player;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function0<",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0008\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n\u00a2\u0006\u0002\u0008\u0002"
    }
    d2 = {
        "<anonymous>",
        "",
        "invoke"
    }
    k = 0x3
    mv = {
        0x1,
        0x6,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field final synthetic $channel:Lio/flutter/plugin/common/MethodChannel;


# direct methods
.method constructor <init>(Lio/flutter/plugin/common/MethodChannel;)V
    .locals 0

    iput-object p1, p0, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$getOrCreatePlayer$1$1$13;->$channel:Lio/flutter/plugin/common/MethodChannel;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 1

    .line 228
    invoke-virtual {p0}, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$getOrCreatePlayer$1$1$13;->invoke()V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method public final invoke()V
    .locals 3

    .line 229
    iget-object v0, p0, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$getOrCreatePlayer$1$1$13;->$channel:Lio/flutter/plugin/common/MethodChannel;

    invoke-static {}, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayerPluginKt;->getMETHOD_CURRENT()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lio/flutter/plugin/common/MethodChannel;->invokeMethod(Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method
