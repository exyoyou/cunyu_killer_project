.class final Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$register$1;
.super Lkotlin/jvm/internal/Lambda;
.source "AssetsAudioPlayerPlugin.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer;->register()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/functions/Function1<",
        "Lcom/github/florent37/assets_audio_player/notification/MediaButtonsReceiver$MediaButtonAction;",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n\u00a2\u0006\u0002\u0008\u0004"
    }
    d2 = {
        "<anonymous>",
        "",
        "it",
        "Lcom/github/florent37/assets_audio_player/notification/MediaButtonsReceiver$MediaButtonAction;",
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
.field final synthetic this$0:Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer;


# direct methods
.method constructor <init>(Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer;)V
    .locals 0

    iput-object p1, p0, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$register$1;->this$0:Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer;

    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 147
    check-cast p1, Lcom/github/florent37/assets_audio_player/notification/MediaButtonsReceiver$MediaButtonAction;

    invoke-virtual {p0, p1}, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$register$1;->invoke(Lcom/github/florent37/assets_audio_player/notification/MediaButtonsReceiver$MediaButtonAction;)V

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p1
.end method

.method public final invoke(Lcom/github/florent37/assets_audio_player/notification/MediaButtonsReceiver$MediaButtonAction;)V
    .locals 1

    const-string v0, "it"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 149
    iget-object v0, p0, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer$register$1;->this$0:Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer;

    invoke-virtual {v0, p1}, Lcom/github/florent37/assets_audio_player/AssetsAudioPlayer;->onMediaButton(Lcom/github/florent37/assets_audio_player/notification/MediaButtonsReceiver$MediaButtonAction;)V

    return-void
.end method
