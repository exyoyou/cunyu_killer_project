.class public Lcom/tencent/rtmp/downloader/a/b;
.super Lcom/tencent/rtmp/downloader/TXVodDownloadManager;
.source "TXVodDownloadManagerEx.java"


# static fields
.field private static g:Lcom/tencent/rtmp/downloader/a/b;


# instance fields
.field a:Lcom/tencent/liteav/txcplayer/b/a$a;

.field private b:Lcom/tencent/liteav/txcplayer/b/a;

.field private c:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/tencent/rtmp/downloader/a/c;",
            ">;"
        }
    .end annotation
.end field

.field private d:Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;

.field private e:Landroid/content/SharedPreferences;

.field private f:Landroid/content/SharedPreferences$Editor;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method private constructor <init>()V
    .locals 3

    .line 63
    invoke-direct {p0}, Lcom/tencent/rtmp/downloader/TXVodDownloadManager;-><init>()V

    .line 599
    new-instance v0, Lcom/tencent/rtmp/downloader/a/b$4;

    invoke-direct {v0, p0}, Lcom/tencent/rtmp/downloader/a/b$4;-><init>(Lcom/tencent/rtmp/downloader/a/b;)V

    iput-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->a:Lcom/tencent/liteav/txcplayer/b/a$a;

    .line 64
    invoke-direct {p0}, Lcom/tencent/rtmp/downloader/a/b;->c()Landroid/content/Context;

    move-result-object v0

    .line 65
    invoke-static {v0}, Lcom/tencent/liteav/txcplayer/b/c;->a(Landroid/content/Context;)Lcom/tencent/liteav/txcplayer/b/a;

    move-result-object v1

    iput-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    .line 66
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    if-eqz v1, :cond_0

    .line 67
    iget-object v2, p0, Lcom/tencent/rtmp/downloader/a/b;->a:Lcom/tencent/liteav/txcplayer/b/a$a;

    invoke-virtual {v1, v2}, Lcom/tencent/liteav/txcplayer/b/a;->setListener(Lcom/tencent/liteav/txcplayer/b/a$a;)V

    .line 69
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    if-eqz v0, :cond_1

    const/4 v1, 0x0

    const-string v2, "vod_download"

    .line 71
    invoke-virtual {v0, v2, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    .line 72
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    iput-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    :cond_1
    return-void
.end method

.method public static a()Lcom/tencent/rtmp/downloader/a/b;
    .locals 1

    .line 107
    sget-object v0, Lcom/tencent/rtmp/downloader/a/b;->g:Lcom/tencent/rtmp/downloader/a/b;

    if-nez v0, :cond_0

    .line 108
    new-instance v0, Lcom/tencent/rtmp/downloader/a/b;

    invoke-direct {v0}, Lcom/tencent/rtmp/downloader/a/b;-><init>()V

    sput-object v0, Lcom/tencent/rtmp/downloader/a/b;->g:Lcom/tencent/rtmp/downloader/a/b;

    .line 110
    :cond_0
    sget-object v0, Lcom/tencent/rtmp/downloader/a/b;->g:Lcom/tencent/rtmp/downloader/a/b;

    return-object v0
.end method

.method static synthetic a(Lcom/tencent/rtmp/downloader/a/b;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 41
    invoke-direct {p0, p1, p2}, Lcom/tencent/rtmp/downloader/a/b;->b(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static synthetic a(Lcom/tencent/rtmp/downloader/a/b;)Ljava/util/ArrayList;
    .locals 0

    .line 41
    iget-object p0, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    return-object p0
.end method

.method static synthetic a(Lcom/tencent/rtmp/downloader/a/b;Lcom/tencent/rtmp/downloader/a/c;)V
    .locals 0

    .line 41
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->a(Lcom/tencent/rtmp/downloader/a/c;)V

    return-void
.end method

.method static synthetic a(Lcom/tencent/rtmp/downloader/a/b;Lcom/tencent/rtmp/downloader/a/c;Lcom/tencent/liteav/network/g;)V
    .locals 0

    .line 41
    invoke-direct {p0, p1, p2}, Lcom/tencent/rtmp/downloader/a/b;->a(Lcom/tencent/rtmp/downloader/a/c;Lcom/tencent/liteav/network/g;)V

    return-void
.end method

.method private a(Lcom/tencent/rtmp/downloader/a/c;)V
    .locals 5

    .line 467
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getUrl()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 470
    :cond_0
    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    .line 471
    invoke-virtual {v1}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v1

    const-string v2, ".m3u8"

    invoke-virtual {v1, v2}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    const/16 v2, -0x138c

    if-eqz v1, :cond_5

    .line 472
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    invoke-virtual {v1, v0}, Lcom/tencent/liteav/txcplayer/b/a;->makePlayPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Lcom/tencent/rtmp/downloader/a/c;->a(Ljava/lang/String;)V

    .line 473
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_2

    .line 474
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->d:Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;

    if-eqz v0, :cond_1

    const/16 v1, -0x138f

    const-string v2, "Failed to create local path"

    .line 475
    invoke-interface {v0, p1, v1, v2}, Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;->onDownloadError(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;ILjava/lang/String;)V

    :cond_1
    return-void

    .line 479
    :cond_2
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v1

    if-eqz v1, :cond_3

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v1

    invoke-virtual {v1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getToken()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_3

    const-string v1, "/"

    .line 480
    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    .line 481
    array-length v3, v1

    if-lez v3, :cond_3

    .line 482
    array-length v3, v1

    add-int/lit8 v3, v3, -0x1

    aget-object v1, v1, v3

    .line 483
    invoke-virtual {v0, v1}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v1

    .line 484
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v4, 0x0

    invoke-virtual {v0, v4, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "voddrm.token."

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v4

    invoke-virtual {v4}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getToken()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "."

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 487
    :cond_3
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "download hls "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " to "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v3, "TXVodDownloadManagerEx"

    invoke-static {v3, v1}, Lcom/tencent/rtmp/TXLog;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 488
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    monitor-enter v1

    .line 489
    :try_start_0
    iget-object v3, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    invoke-virtual {v3, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 490
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 491
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v0, v3}, Lcom/tencent/liteav/txcplayer/b/a;->downloadHls(Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/tencent/rtmp/downloader/a/c;->g(I)V

    .line 492
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getTaskId()I

    move-result v0

    if-gez v0, :cond_4

    const-string v0, "TXVodDownloadManagerEx"

    const-string v1, "start download failed"

    .line 493
    invoke-static {v0, v1}, Lcom/tencent/rtmp/TXLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    .line 494
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->d:Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;

    if-eqz v0, :cond_4

    const-string v1, "Internal error"

    .line 495
    invoke-interface {v0, p1, v2, v1}, Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;->onDownloadError(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;ILjava/lang/String;)V

    :cond_4
    return-void

    :catchall_0
    move-exception p1

    .line 490
    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1

    .line 500
    :cond_5
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "only support m3u8 file, format error: "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "TXVodDownloadManagerEx"

    invoke-static {v1, v0}, Lcom/tencent/rtmp/TXLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    .line 501
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->d:Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;

    if-eqz v0, :cond_6

    const-string v1, "No support format"

    .line 502
    invoke-interface {v0, p1, v2, v1}, Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;->onDownloadError(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;ILjava/lang/String;)V

    :cond_6
    return-void
.end method

.method private a(Lcom/tencent/rtmp/downloader/a/c;Lcom/tencent/liteav/network/g;)V
    .locals 3

    if-eqz p2, :cond_3

    .line 372
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 375
    :cond_0
    invoke-virtual {p2}, Lcom/tencent/liteav/network/g;->a()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_2

    const-string v1, ".m3u8"

    .line 376
    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    .line 380
    :cond_1
    invoke-virtual {p2}, Lcom/tencent/liteav/network/g;->f()I

    move-result v1

    mul-int/lit16 v1, v1, 0x3e8

    invoke-virtual {p1, v1}, Lcom/tencent/rtmp/downloader/a/c;->a(I)V

    .line 381
    invoke-virtual {p2}, Lcom/tencent/liteav/network/g;->g()I

    move-result v1

    invoke-virtual {p1, v1}, Lcom/tencent/rtmp/downloader/a/c;->c(I)V

    .line 382
    invoke-static {}, Lcom/tencent/liteav/network/c;->a()Lcom/tencent/liteav/network/c;

    move-result-object v1

    new-instance v2, Lcom/tencent/rtmp/downloader/a/b$3;

    invoke-direct {v2, p0, p1, p2, v0}, Lcom/tencent/rtmp/downloader/a/b$3;-><init>(Lcom/tencent/rtmp/downloader/a/b;Lcom/tencent/rtmp/downloader/a/c;Lcom/tencent/liteav/network/g;Ljava/lang/String;)V

    invoke-virtual {v1, v0, v2}, Lcom/tencent/liteav/network/c;->a(Ljava/lang/String;Lcom/tencent/liteav/network/c$b;)V

    return-void

    .line 377
    :cond_2
    :goto_0
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "not support format! masterPlaylistUrl : "

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "TXVodDownloadManagerEx"

    invoke-static {p2, p1}, Lcom/tencent/liteav/basic/log/TXCLog;->w(Ljava/lang/String;Ljava/lang/String;)V

    :cond_3
    :goto_1
    return-void
.end method

.method static synthetic a(Lcom/tencent/rtmp/downloader/a/b;Ljava/lang/String;)Z
    .locals 0

    .line 41
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->d(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method static synthetic b(Lcom/tencent/rtmp/downloader/a/b;)Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;
    .locals 0

    .line 41
    iget-object p0, p0, Lcom/tencent/rtmp/downloader/a/b;->d:Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;

    return-object p0
.end method

.method private b(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 6

    .line 507
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 510
    :cond_0
    new-instance v0, Ljava/lang/StringBuffer;

    invoke-direct {v0}, Ljava/lang/StringBuffer;-><init>()V

    const-string v0, "\n"

    .line 511
    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_3

    .line 513
    array-length v0, p1

    if-lez v0, :cond_3

    .line 514
    array-length v0, p1

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v2, v0, :cond_3

    aget-object v4, p1, v2

    .line 515
    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_2

    const-string v5, "#EXT-X-STREAM-INF:"

    .line 516
    invoke-virtual {v4, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_1

    .line 517
    invoke-virtual {v4, p2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_2

    const/4 v3, 0x1

    goto :goto_1

    :cond_1
    if-eqz v3, :cond_2

    const-string v5, ".m3u8"

    .line 522
    invoke-virtual {v4, v5}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_2

    return-object v4

    :cond_2
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_3
    return-object v1
.end method

.method static synthetic b(Lcom/tencent/rtmp/downloader/a/b;Lcom/tencent/rtmp/downloader/a/c;)V
    .locals 0

    .line 41
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->b(Lcom/tencent/rtmp/downloader/a/c;)V

    return-void
.end method

.method private b(Lcom/tencent/rtmp/downloader/a/c;)V
    .locals 3

    .line 868
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    if-eqz v0, :cond_0

    .line 869
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->c(Lcom/tencent/rtmp/downloader/a/c;)Ljava/lang/String;

    move-result-object v0

    .line 870
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 871
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->d(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Ljava/lang/String;

    move-result-object p1

    .line 872
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 873
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v1, p1, v0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 874
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 875
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "saveDownloadMediaInfo key: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "| mediaInfo: "

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v0, "TXVodDownloadManagerEx"

    invoke-static {v0, p1}, Lcom/tencent/rtmp/TXLog;->i(Ljava/lang/String;Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private c()Landroid/content/Context;
    .locals 6

    const/4 v0, 0x0

    :try_start_0
    const-string v1, "android.app.ActivityThread"

    .line 79
    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v1

    if-nez v1, :cond_0

    return-object v0

    :cond_0
    const-string v2, "currentActivityThread"

    const/4 v3, 0x0

    new-array v4, v3, [Ljava/lang/Class;

    .line 83
    invoke-virtual {v1, v2, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    if-nez v1, :cond_1

    return-object v0

    :cond_1
    const/4 v2, 0x1

    .line 87
    invoke-virtual {v1, v2}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    new-array v2, v3, [Ljava/lang/Object;

    .line 88
    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 89
    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    const-string v4, "getApplication"

    new-array v5, v3, [Ljava/lang/Class;

    invoke-virtual {v2, v4, v5}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    if-nez v2, :cond_2

    return-object v0

    :cond_2
    new-array v3, v3, [Ljava/lang/Object;

    .line 93
    invoke-virtual {v2, v1, v3}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 94
    check-cast v1, Landroid/app/Application;

    .line 95
    invoke-virtual {v1}, Landroid/app/Application;->getApplicationContext()Landroid/content/Context;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object v0

    :catchall_0
    move-exception v1

    .line 97
    invoke-virtual {v1}, Ljava/lang/Throwable;->printStackTrace()V

    return-object v0
.end method

.method private c(Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;)Lcom/tencent/rtmp/downloader/a/c;
    .locals 11

    .line 211
    new-instance v0, Lcom/tencent/rtmp/downloader/a/c;

    invoke-direct {v0}, Lcom/tencent/rtmp/downloader/a/c;-><init>()V

    .line 213
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getTemplateName()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 214
    new-instance v1, Lcom/tencent/rtmp/downloader/a/a;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getAuthBuilder()Lcom/tencent/rtmp/TXPlayerAuthBuilder;

    move-result-object v2

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getQuality()I

    move-result v3

    invoke-direct {v1, v2, v3}, Lcom/tencent/rtmp/downloader/a/a;-><init>(Lcom/tencent/rtmp/TXPlayerAuthBuilder;I)V

    goto :goto_0

    .line 216
    :cond_0
    new-instance v1, Lcom/tencent/rtmp/downloader/a/a;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getAuthBuilder()Lcom/tencent/rtmp/TXPlayerAuthBuilder;

    move-result-object v2

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getTemplateName()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Lcom/tencent/rtmp/downloader/a/a;-><init>(Lcom/tencent/rtmp/TXPlayerAuthBuilder;Ljava/lang/String;)V

    .line 218
    :goto_0
    invoke-virtual {v0, v1}, Lcom/tencent/rtmp/downloader/a/c;->a(Lcom/tencent/rtmp/downloader/a/a;)V

    .line 220
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->c(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object v2

    if-eqz v2, :cond_1

    return-object v2

    .line 224
    :cond_1
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getAuthBuilder()Lcom/tencent/rtmp/TXPlayerAuthBuilder;

    move-result-object v2

    if-eqz v2, :cond_3

    .line 225
    invoke-virtual {v1}, Lcom/tencent/rtmp/downloader/a/a;->getAuthBuilder()Lcom/tencent/rtmp/TXPlayerAuthBuilder;

    move-result-object v2

    .line 226
    new-instance v10, Lcom/tencent/liteav/network/i;

    invoke-direct {v10}, Lcom/tencent/liteav/network/i;-><init>()V

    .line 227
    invoke-virtual {v2}, Lcom/tencent/rtmp/TXPlayerAuthBuilder;->isHttps()Z

    move-result v3

    invoke-virtual {v10, v3}, Lcom/tencent/liteav/network/i;->a(Z)V

    .line 228
    new-instance v3, Lcom/tencent/rtmp/downloader/a/b$1;

    invoke-direct {v3, p0, v0, p1, v1}, Lcom/tencent/rtmp/downloader/a/b$1;-><init>(Lcom/tencent/rtmp/downloader/a/b;Lcom/tencent/rtmp/downloader/a/c;Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;Lcom/tencent/rtmp/downloader/a/a;)V

    invoke-virtual {v10, v3}, Lcom/tencent/liteav/network/i;->a(Lcom/tencent/liteav/network/j;)V

    .line 283
    invoke-virtual {v2}, Lcom/tencent/rtmp/TXPlayerAuthBuilder;->getAppId()I

    move-result v4

    .line 284
    invoke-virtual {v2}, Lcom/tencent/rtmp/TXPlayerAuthBuilder;->getFileId()Ljava/lang/String;

    move-result-object v5

    .line 285
    invoke-virtual {v2}, Lcom/tencent/rtmp/TXPlayerAuthBuilder;->getTimeout()Ljava/lang/String;

    move-result-object v6

    .line 286
    invoke-virtual {v2}, Lcom/tencent/rtmp/TXPlayerAuthBuilder;->getUs()Ljava/lang/String;

    move-result-object v7

    .line 287
    invoke-virtual {v2}, Lcom/tencent/rtmp/TXPlayerAuthBuilder;->getExper()I

    move-result v8

    .line 288
    invoke-virtual {v2}, Lcom/tencent/rtmp/TXPlayerAuthBuilder;->getSign()Ljava/lang/String;

    move-result-object v9

    move-object v3, v10

    .line 283
    invoke-virtual/range {v3 .. v9}, Lcom/tencent/liteav/network/i;->a(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)I

    move-result p1

    if-nez p1, :cond_2

    .line 289
    invoke-virtual {v0, v10}, Lcom/tencent/rtmp/downloader/a/c;->a(Lcom/tencent/liteav/network/i;)V

    .line 290
    iget-object p1, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    monitor-enter p1

    .line 291
    :try_start_0
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 292
    monitor-exit p1

    return-object v0

    :catchall_0
    move-exception v0

    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_2
    const-string p1, "TXVodDownloadManagerEx"

    const-string v0, "unable to getPlayInfo"

    .line 295
    invoke-static {p1, v0}, Lcom/tencent/rtmp/TXLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    :cond_3
    const/4 p1, 0x0

    return-object p1
.end method

.method private c(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Lcom/tencent/rtmp/downloader/a/c;
    .locals 4

    .line 180
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->d(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Ljava/lang/String;

    move-result-object p1

    .line 181
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_1

    .line 182
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    invoke-interface {v0, p1, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 183
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_1

    .line 184
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->e(Ljava/lang/String;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 186
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/tencent/rtmp/downloader/a/b;->d(Ljava/lang/String;)Z

    move-result v2

    const-string v3, "TXVodDownloadManagerEx"

    if-eqz v2, :cond_0

    const-string p1, "partly download, resume download"

    .line 188
    invoke-static {v3, p1}, Lcom/tencent/rtmp/TXLog;->i(Ljava/lang/String;Ljava/lang/String;)V

    .line 189
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->a(Lcom/tencent/rtmp/downloader/a/c;)V

    return-object v0

    :cond_0
    const-string v0, "file is deleted, remove cache and restart download"

    .line 192
    invoke-static {v3, v0}, Lcom/tencent/rtmp/TXLog;->w(Ljava/lang/String;Ljava/lang/String;)V

    .line 194
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0, p1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 195
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "_kv"

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {v0, p1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 196
    iget-object p1, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    :cond_1
    return-object v1
.end method

.method private c(Lcom/tencent/rtmp/downloader/a/c;)Ljava/lang/String;
    .locals 2

    if-nez p1, :cond_0

    const-string p1, ""

    return-object p1

    .line 906
    :cond_0
    invoke-static {}, Landroid/os/Parcel;->obtain()Landroid/os/Parcel;

    move-result-object v0

    const/4 v1, 0x0

    .line 907
    invoke-virtual {p1, v0, v1}, Lcom/tencent/rtmp/downloader/a/c;->writeToParcel(Landroid/os/Parcel;I)V

    .line 908
    invoke-virtual {v0, v1}, Landroid/os/Parcel;->setDataPosition(I)V

    .line 909
    invoke-virtual {v0}, Landroid/os/Parcel;->marshall()[B

    move-result-object p1

    const/4 v1, 0x2

    invoke-static {p1, v1}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object p1

    .line 910
    invoke-virtual {v0}, Landroid/os/Parcel;->recycle()V

    return-object p1
.end method

.method private d(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Ljava/lang/String;
    .locals 3

    .line 887
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v0

    const/4 v1, 0x0

    const-string v2, "_"

    if-nez v0, :cond_1

    .line 889
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getUrl()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 891
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getUserName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getUrl()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/tencent/liteav/basic/util/TXCCommonUtil;->getMD5(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_0
    return-object v1

    .line 895
    :cond_1
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getFileId()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_2

    .line 897
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getUserName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getAppId()I

    move-result v1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getFileId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getQuality()I

    move-result v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_2
    return-object v1
.end method

.method private d(Ljava/lang/String;)Z
    .locals 2

    const-string v0, ".hls"

    .line 857
    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x0

    .line 858
    invoke-virtual {p1, v0}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v0

    add-int/lit8 v0, v0, 0x4

    invoke-virtual {p1, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    .line 860
    :cond_0
    new-instance v0, Ljava/io/File;

    invoke-direct {v0, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result p1

    return p1
.end method

.method private e(Ljava/lang/String;)Lcom/tencent/rtmp/downloader/a/c;
    .locals 7

    .line 915
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    const/4 v0, 0x2

    .line 918
    invoke-static {p1, v0}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p1

    .line 919
    invoke-static {}, Landroid/os/Parcel;->obtain()Landroid/os/Parcel;

    move-result-object v0

    .line 920
    array-length v1, p1

    const/4 v2, 0x0

    invoke-virtual {v0, p1, v2, v1}, Landroid/os/Parcel;->unmarshall([BII)V

    .line 921
    invoke-virtual {v0, v2}, Landroid/os/Parcel;->setDataPosition(I)V

    .line 922
    sget-object p1, Lcom/tencent/rtmp/downloader/a/c;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-interface {p1, v0}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/tencent/rtmp/downloader/a/c;

    .line 923
    invoke-virtual {v0}, Landroid/os/Parcel;->recycle()V

    if-eqz p1, :cond_1

    .line 924
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 925
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/a/c;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v0

    check-cast v0, Lcom/tencent/rtmp/downloader/a/a;

    .line 926
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/a;->getOverlayKey()Ljava/lang/String;

    move-result-object v1

    .line 927
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/a;->getOverlayIv()Ljava/lang/String;

    move-result-object v2

    .line 928
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_1

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_1

    .line 929
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/a;->getAppId()I

    move-result v3

    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/a;->getUserName()Ljava/lang/String;

    move-result-object v4

    .line 930
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/a;->getFileId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/a;->getQuality()I

    move-result v6

    .line 929
    invoke-static {v3, v4, v5, v6}, Lcom/tencent/liteav/txcvodplayer/hlsencoder/TXCHLSEncoder;->a(ILjava/lang/String;Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v3

    .line 931
    invoke-static {v3, v1}, Lcom/tencent/liteav/txcvodplayer/hlsencoder/TXCHLSEncoder;->b(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/tencent/rtmp/downloader/a/a;->a(Ljava/lang/String;)V

    .line 932
    invoke-static {v3, v2}, Lcom/tencent/liteav/txcvodplayer/hlsencoder/TXCHLSEncoder;->b(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/tencent/rtmp/downloader/a/a;->b(Ljava/lang/String;)V

    :cond_1
    return-object p1
.end method


# virtual methods
.method a(Lcom/tencent/liteav/network/m;I)Lcom/tencent/liteav/network/n;
    .locals 1

    if-nez p2, :cond_0

    .line 587
    invoke-virtual {p1}, Lcom/tencent/liteav/network/m;->f()Lcom/tencent/liteav/network/n;

    move-result-object p1

    goto :goto_0

    .line 589
    :cond_0
    invoke-static {p2}, Lcom/tencent/rtmp/downloader/a/a;->a(I)Ljava/lang/String;

    move-result-object p2

    const-string v0, "hls"

    .line 590
    invoke-virtual {p1, p2, v0}, Lcom/tencent/liteav/network/m;->a(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/liteav/network/n;

    move-result-object p1

    :goto_0
    return-object p1
.end method

.method a(Lcom/tencent/liteav/network/m;Ljava/lang/String;)Lcom/tencent/liteav/network/n;
    .locals 1

    const-string v0, "hls"

    .line 596
    invoke-virtual {p1, p2, v0}, Lcom/tencent/liteav/network/m;->b(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/liteav/network/n;

    move-result-object p1

    return-object p1
.end method

.method public a(ILjava/lang/String;I)Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;
    .locals 9

    .line 777
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 781
    :cond_0
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    monitor-enter v0

    .line 783
    :try_start_0
    iget-object v2, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/tencent/rtmp/downloader/a/c;

    .line 784
    invoke-virtual {v3}, Lcom/tencent/rtmp/downloader/a/c;->getDataSource()Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;

    move-result-object v4

    if-eqz v4, :cond_1

    .line 785
    invoke-virtual {v4}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getAppId()I

    move-result v5

    if-ne v5, p1, :cond_1

    .line 786
    invoke-virtual {v4}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getFileId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-virtual {v4}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getQuality()I

    move-result v4

    if-ne v4, p3, :cond_1

    .line 787
    monitor-exit v0

    return-object v3

    .line 790
    :cond_2
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 793
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    if-eqz v0, :cond_4

    .line 794
    new-instance v0, Lcom/tencent/rtmp/downloader/a/c;

    invoke-direct {v0}, Lcom/tencent/rtmp/downloader/a/c;-><init>()V

    .line 795
    new-instance v8, Lcom/tencent/rtmp/downloader/a/a;

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v2, v8

    move v3, p1

    move-object v4, p2

    move v5, p3

    invoke-direct/range {v2 .. v7}, Lcom/tencent/rtmp/downloader/a/a;-><init>(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v8}, Lcom/tencent/rtmp/downloader/a/c;->a(Lcom/tencent/rtmp/downloader/a/a;)V

    .line 796
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->d(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Ljava/lang/String;

    move-result-object p1

    .line 797
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    invoke-interface {v0, p1, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 798
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->e(Ljava/lang/String;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 800
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/tencent/rtmp/downloader/a/b;->d(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_3

    .line 802
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0, p1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 803
    iget-object p1, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 804
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "fileId : "

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, " | qualityId: "

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, " download file not exist! remove download info!"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "TXVodDownloadManagerEx"

    invoke-static {p2, p1}, Lcom/tencent/rtmp/TXLog;->w(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    :cond_3
    move-object v1, v0

    :cond_4
    :goto_0
    return-object v1

    :catchall_0
    move-exception p1

    .line 790
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method a(Lcom/tencent/liteav/txcplayer/b/b;)Lcom/tencent/rtmp/downloader/a/c;
    .locals 5

    .line 698
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    monitor-enter v0

    .line 699
    :try_start_0
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_8

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/tencent/rtmp/downloader/a/c;

    .line 700
    invoke-virtual {v2}, Lcom/tencent/rtmp/downloader/a/c;->getTaskId()I

    move-result v3

    iget v4, p1, Lcom/tencent/liteav/txcplayer/b/b;->a:I

    if-eq v3, v4, :cond_1

    invoke-virtual {v2}, Lcom/tencent/rtmp/downloader/a/c;->getUrl()Ljava/lang/String;

    move-result-object v3

    iget-object v4, p1, Lcom/tencent/liteav/txcplayer/b/b;->b:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 701
    :cond_1
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->h:I

    if-lez v1, :cond_2

    .line 702
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->h:I

    invoke-virtual {v2, v1}, Lcom/tencent/rtmp/downloader/a/c;->b(I)V

    .line 704
    :cond_2
    invoke-virtual {v2}, Lcom/tencent/rtmp/downloader/a/c;->getDuration()I

    move-result v1

    if-gtz v1, :cond_3

    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->i:I

    if-lez v1, :cond_3

    .line 705
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->i:I

    invoke-virtual {v2, v1}, Lcom/tencent/rtmp/downloader/a/c;->a(I)V

    .line 707
    :cond_3
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->c:I

    if-lez v1, :cond_4

    .line 708
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->c:I

    invoke-virtual {v2, v1}, Lcom/tencent/rtmp/downloader/a/c;->d(I)V

    .line 710
    :cond_4
    invoke-virtual {v2}, Lcom/tencent/rtmp/downloader/a/c;->getSize()I

    move-result v1

    if-gtz v1, :cond_5

    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->d:I

    if-lez v1, :cond_5

    .line 711
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->d:I

    invoke-virtual {v2, v1}, Lcom/tencent/rtmp/downloader/a/c;->c(I)V

    .line 713
    :cond_5
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->f:I

    if-lez v1, :cond_6

    .line 714
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->f:I

    invoke-virtual {v2, v1}, Lcom/tencent/rtmp/downloader/a/c;->e(I)V

    .line 716
    :cond_6
    iget v1, p1, Lcom/tencent/liteav/txcplayer/b/b;->g:I

    if-lez v1, :cond_7

    .line 717
    iget p1, p1, Lcom/tencent/liteav/txcplayer/b/b;->g:I

    invoke-virtual {v2, p1}, Lcom/tencent/rtmp/downloader/a/c;->f(I)V

    .line 719
    :cond_7
    monitor-exit v0

    return-object v2

    .line 722
    :cond_8
    monitor-exit v0

    const/4 p1, 0x0

    return-object p1

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public a(Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;)Lcom/tencent/rtmp/downloader/a/c;
    .locals 2

    .line 162
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getAuthBuilder()Lcom/tencent/rtmp/TXPlayerAuthBuilder;

    move-result-object v0

    const-string v1, "TXVodDownloadManagerEx"

    if-eqz v0, :cond_0

    const-string v0, "startDownloadV2"

    .line 163
    invoke-static {v1, v0}, Lcom/tencent/rtmp/TXLog;->w(Ljava/lang/String;Ljava/lang/String;)V

    .line 165
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->c(Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object p1

    return-object p1

    :cond_0
    const-string v0, "startDownloadV4"

    .line 167
    invoke-static {v1, v0}, Lcom/tencent/rtmp/TXLog;->w(Ljava/lang/String;Ljava/lang/String;)V

    .line 169
    invoke-virtual {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->b(Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object p1

    return-object p1
.end method

.method public a(Ljava/lang/String;Ljava/lang/String;)Lcom/tencent/rtmp/downloader/a/c;
    .locals 1

    .line 144
    new-instance v0, Lcom/tencent/rtmp/downloader/a/c;

    invoke-direct {v0}, Lcom/tencent/rtmp/downloader/a/c;-><init>()V

    .line 145
    invoke-virtual {v0, p1}, Lcom/tencent/rtmp/downloader/a/c;->b(Ljava/lang/String;)V

    .line 146
    invoke-virtual {v0, p2}, Lcom/tencent/rtmp/downloader/a/c;->c(Ljava/lang/String;)V

    .line 148
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->c(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object p1

    if-eqz p1, :cond_0

    return-object p1

    .line 152
    :cond_0
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->a(Lcom/tencent/rtmp/downloader/a/c;)V

    return-object v0
.end method

.method public a(Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;)V
    .locals 0

    .line 134
    iput-object p1, p0, Lcom/tencent/rtmp/downloader/a/b;->d:Lcom/tencent/rtmp/downloader/ITXVodDownloadListener;

    return-void
.end method

.method public a(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)V
    .locals 3

    if-nez p1, :cond_0

    return-void

    .line 540
    :cond_0
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getTaskId()I

    move-result v0

    const-string v1, "TXVodDownloadManagerEx"

    if-gez v0, :cond_1

    const-string p1, "stop download not start task"

    .line 541
    invoke-static {v1, p1}, Lcom/tencent/liteav/basic/log/TXCLog;->w(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    .line 544
    :cond_1
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getTaskId()I

    move-result v2

    invoke-virtual {v0, v2}, Lcom/tencent/liteav/txcplayer/b/a;->stop(I)V

    .line 545
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "stop download "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getUrl()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Lcom/tencent/liteav/basic/log/TXCLog;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public a(Ljava/lang/String;)V
    .locals 1

    .line 118
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    invoke-virtual {v0, p1}, Lcom/tencent/liteav/txcplayer/b/a;->setDownloadPath(Ljava/lang/String;)V

    return-void
.end method

.method public a(Ljava/util/Map;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 126
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    invoke-virtual {v0, p1}, Lcom/tencent/liteav/txcplayer/b/a;->setHeaders(Ljava/util/Map;)V

    return-void
.end method

.method public b(Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;)Lcom/tencent/rtmp/downloader/a/c;
    .locals 7

    if-nez p1, :cond_0

    const/4 p1, 0x0

    return-object p1

    .line 310
    :cond_0
    new-instance v6, Lcom/tencent/rtmp/downloader/a/a;

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getAppId()I

    move-result v1

    .line 311
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getFileId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getQuality()I

    move-result v3

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getPSign()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadDataSource;->getUserName()Ljava/lang/String;

    move-result-object v5

    move-object v0, v6

    invoke-direct/range {v0 .. v5}, Lcom/tencent/rtmp/downloader/a/a;-><init>(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;)V

    .line 312
    new-instance p1, Lcom/tencent/rtmp/downloader/a/c;

    invoke-direct {p1}, Lcom/tencent/rtmp/downloader/a/c;-><init>()V

    .line 313
    invoke-virtual {p1, v6}, Lcom/tencent/rtmp/downloader/a/c;->a(Lcom/tencent/rtmp/downloader/a/a;)V

    .line 315
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->c(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object v0

    if-eqz v0, :cond_1

    return-object v0

    .line 319
    :cond_1
    new-instance v0, Lcom/tencent/rtmp/b;

    invoke-virtual {v6}, Lcom/tencent/rtmp/downloader/a/a;->getAppId()I

    move-result v1

    .line 320
    invoke-virtual {v6}, Lcom/tencent/rtmp/downloader/a/a;->getFileId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6}, Lcom/tencent/rtmp/downloader/a/a;->getPSign()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v0, v1, v2, v3}, Lcom/tencent/rtmp/b;-><init>(ILjava/lang/String;Ljava/lang/String;)V

    .line 322
    new-instance v1, Lcom/tencent/liteav/network/g;

    invoke-direct {v1, v0}, Lcom/tencent/liteav/network/g;-><init>(Lcom/tencent/rtmp/b;)V

    .line 323
    new-instance v0, Lcom/tencent/rtmp/downloader/a/b$2;

    invoke-direct {v0, p0, p1}, Lcom/tencent/rtmp/downloader/a/b$2;-><init>(Lcom/tencent/rtmp/downloader/a/b;Lcom/tencent/rtmp/downloader/a/c;)V

    invoke-virtual {v1, v0}, Lcom/tencent/liteav/network/g;->a(Lcom/tencent/liteav/network/g$a;)V

    return-object p1
.end method

.method public b()Ljava/util/List;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;",
            ">;"
        }
    .end annotation

    .line 732
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    if-eqz v0, :cond_5

    .line 734
    :try_start_0
    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v0

    .line 735
    new-instance v1, Ljava/util/ArrayList;

    invoke-interface {v0}, Ljava/util/Map;->size()I

    move-result v2

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 738
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 739
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-direct {p0, v3}, Lcom/tencent/rtmp/downloader/a/b;->e(Ljava/lang/String;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object v3

    if-eqz v3, :cond_0

    .line 741
    invoke-virtual {v3}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v4}, Lcom/tencent/rtmp/downloader/a/b;->d(Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_1

    .line 743
    iget-object v3, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-interface {v3, v2}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 744
    iget-object v2, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v2}, Landroid/content/SharedPreferences$Editor;->commit()Z

    goto :goto_0

    :cond_1
    const/4 v2, 0x0

    .line 747
    iget-object v4, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    monitor-enter v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 748
    :try_start_1
    iget-object v5, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :cond_2
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_3

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/tencent/rtmp/downloader/a/c;

    .line 749
    invoke-virtual {v6}, Lcom/tencent/rtmp/downloader/a/c;->getTaskId()I

    move-result v7

    invoke-virtual {v3}, Lcom/tencent/rtmp/downloader/a/c;->getTaskId()I

    move-result v8

    if-ne v7, v8, :cond_2

    .line 750
    invoke-interface {v1, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    const/4 v2, 0x1

    .line 755
    :cond_3
    monitor-exit v4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-nez v2, :cond_0

    .line 757
    :try_start_2
    invoke-interface {v1, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_0

    :catchall_0
    move-exception v0

    .line 755
    :try_start_3
    monitor-exit v4
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    :try_start_4
    throw v0
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    :cond_4
    return-object v1

    :catch_0
    move-exception v0

    .line 764
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    :cond_5
    const/4 v0, 0x0

    return-object v0
.end method

.method public b(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Z
    .locals 2

    .line 553
    invoke-virtual {p1}, Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;->getPlayPath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->b(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 554
    invoke-direct {p0, p1}, Lcom/tencent/rtmp/downloader/a/b;->d(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Ljava/lang/String;

    move-result-object p1

    .line 555
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    if-eqz v0, :cond_0

    .line 556
    invoke-interface {v0, p1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 557
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "_kv"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {v0, p1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 558
    iget-object p1, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    const-string p1, "TXVodDownloadManagerEx"

    const-string v0, "delete DownloadMediaInfo and file complete"

    .line 559
    invoke-static {p1, v0}, Lcom/tencent/rtmp/TXLog;->i(Ljava/lang/String;Ljava/lang/String;)V

    const/4 p1, 0x1

    return p1

    :cond_0
    const/4 p1, 0x0

    return p1
.end method

.method public b(Ljava/lang/String;)Z
    .locals 4

    .line 572
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "delete file "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "TXVodDownloadManagerEx"

    invoke-static {v1, v0}, Lcom/tencent/liteav/basic/log/TXCLog;->d(Ljava/lang/String;Ljava/lang/String;)V

    .line 573
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    monitor-enter v0

    .line 574
    :try_start_0
    iget-object v1, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/tencent/rtmp/downloader/a/c;

    .line 575
    invoke-virtual {v2}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_0

    invoke-virtual {v2}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    const-string p1, "TXVodDownloadManagerEx"

    const-string v1, "file is downloading, can not be delete"

    .line 576
    invoke-static {p1, v1}, Lcom/tencent/liteav/basic/log/TXCLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    const/4 p1, 0x0

    .line 577
    monitor-exit v0

    return p1

    .line 580
    :cond_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 581
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->b:Lcom/tencent/liteav/txcplayer/b/a;

    invoke-virtual {v0, p1}, Lcom/tencent/liteav/txcplayer/b/a;->deleteDownloadFile(Ljava/lang/String;)Z

    move-result p1

    return p1

    :catchall_0
    move-exception p1

    .line 580
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public c(Ljava/lang/String;)Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;
    .locals 5

    .line 819
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 823
    :cond_0
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    monitor-enter v0

    .line 824
    :try_start_0
    iget-object v2, p0, Lcom/tencent/rtmp/downloader/a/b;->c:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/tencent/rtmp/downloader/a/c;

    .line 825
    invoke-virtual {v3}, Lcom/tencent/rtmp/downloader/a/c;->getUrl()Ljava/lang/String;

    move-result-object v4

    if-eqz v4, :cond_1

    invoke-virtual {v3}, Lcom/tencent/rtmp/downloader/a/c;->getUrl()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 826
    monitor-exit v0

    return-object v3

    .line 829
    :cond_2
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 832
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    if-eqz v0, :cond_4

    .line 833
    new-instance v0, Lcom/tencent/rtmp/downloader/a/c;

    invoke-direct {v0}, Lcom/tencent/rtmp/downloader/a/c;-><init>()V

    .line 834
    invoke-virtual {v0, p1}, Lcom/tencent/rtmp/downloader/a/c;->b(Ljava/lang/String;)V

    .line 835
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->d(Lcom/tencent/rtmp/downloader/TXVodDownloadMediaInfo;)Ljava/lang/String;

    move-result-object p1

    .line 836
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->e:Landroid/content/SharedPreferences;

    invoke-interface {v0, p1, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 837
    invoke-direct {p0, v0}, Lcom/tencent/rtmp/downloader/a/b;->e(Ljava/lang/String;)Lcom/tencent/rtmp/downloader/a/c;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 839
    invoke-virtual {v0}, Lcom/tencent/rtmp/downloader/a/c;->getPlayPath()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/tencent/rtmp/downloader/a/b;->d(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_3

    .line 841
    iget-object v0, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0, p1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 842
    iget-object p1, p0, Lcom/tencent/rtmp/downloader/a/b;->f:Landroid/content/SharedPreferences$Editor;

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    goto :goto_0

    :cond_3
    move-object v1, v0

    :cond_4
    :goto_0
    return-object v1

    :catchall_0
    move-exception p1

    .line 829
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method
