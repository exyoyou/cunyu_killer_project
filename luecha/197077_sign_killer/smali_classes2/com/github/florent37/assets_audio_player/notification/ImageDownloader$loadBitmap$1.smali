.class final Lcom/github/florent37/assets_audio_player/notification/ImageDownloader$loadBitmap$1;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "ImageDownloader.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/github/florent37/assets_audio_player/notification/ImageDownloader;->loadBitmap(Landroid/content/Context;Lcom/github/florent37/assets_audio_player/notification/ImageMetas;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    k = 0x3
    mv = {
        0x1,
        0x6,
        0x0
    }
    xi = 0x30
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "com.github.florent37.assets_audio_player.notification.ImageDownloader"
    f = "ImageDownloader.kt"
    i = {}
    l = {
        0x1a
    }
    m = "loadBitmap"
    n = {}
    s = {}
.end annotation


# instance fields
.field label:I

.field synthetic result:Ljava/lang/Object;

.field final synthetic this$0:Lcom/github/florent37/assets_audio_player/notification/ImageDownloader;


# direct methods
.method constructor <init>(Lcom/github/florent37/assets_audio_player/notification/ImageDownloader;Lkotlin/coroutines/Continuation;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/github/florent37/assets_audio_player/notification/ImageDownloader;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/github/florent37/assets_audio_player/notification/ImageDownloader$loadBitmap$1;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/github/florent37/assets_audio_player/notification/ImageDownloader$loadBitmap$1;->this$0:Lcom/github/florent37/assets_audio_player/notification/ImageDownloader;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    iput-object p1, p0, Lcom/github/florent37/assets_audio_player/notification/ImageDownloader$loadBitmap$1;->result:Ljava/lang/Object;

    iget p1, p0, Lcom/github/florent37/assets_audio_player/notification/ImageDownloader$loadBitmap$1;->label:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/github/florent37/assets_audio_player/notification/ImageDownloader$loadBitmap$1;->label:I

    iget-object p1, p0, Lcom/github/florent37/assets_audio_player/notification/ImageDownloader$loadBitmap$1;->this$0:Lcom/github/florent37/assets_audio_player/notification/ImageDownloader;

    move-object v0, p0

    check-cast v0, Lkotlin/coroutines/Continuation;

    const/4 v1, 0x0

    invoke-virtual {p1, v1, v1, v0}, Lcom/github/florent37/assets_audio_player/notification/ImageDownloader;->loadBitmap(Landroid/content/Context;Lcom/github/florent37/assets_audio_player/notification/ImageMetas;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method
