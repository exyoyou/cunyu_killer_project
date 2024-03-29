.class abstract Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;
.super Ljava/lang/Object;
.source "DalvikPurgeableDecoder.java"

# interfaces
.implements Lcom/facebook/imagepipeline/platform/PlatformDecoder;


# static fields
.field protected static final EOI:[B


# instance fields
.field private final mUnpooledBitmapsCounter:Lcom/facebook/imagepipeline/memory/BitmapCounter;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x2

    new-array v0, v0, [B

    .line 31
    fill-array-data v0, :array_0

    sput-object v0, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->EOI:[B

    return-void

    nop

    :array_0
    .array-data 1
        -0x1t
        -0x27t
    .end array-data
.end method

.method constructor <init>()V
    .locals 1

    .line 36
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 37
    invoke-static {}, Lcom/facebook/imagepipeline/memory/BitmapCounterProvider;->get()Lcom/facebook/imagepipeline/memory/BitmapCounter;

    move-result-object v0

    iput-object v0, p0, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->mUnpooledBitmapsCounter:Lcom/facebook/imagepipeline/memory/BitmapCounter;

    return-void
.end method

.method protected static endsWithEOI(Lcom/facebook/common/references/CloseableReference;I)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/facebook/common/references/CloseableReference<",
            "Lcom/facebook/imagepipeline/memory/PooledByteBuffer;",
            ">;I)Z"
        }
    .end annotation

    .line 143
    invoke-virtual {p0}, Lcom/facebook/common/references/CloseableReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/facebook/imagepipeline/memory/PooledByteBuffer;

    const/4 v0, 0x1

    const/4 v1, 0x2

    if-lt p1, v1, :cond_0

    add-int/lit8 v1, p1, -0x2

    .line 145
    invoke-interface {p0, v1}, Lcom/facebook/imagepipeline/memory/PooledByteBuffer;->read(I)B

    move-result v1

    const/4 v2, -0x1

    if-ne v1, v2, :cond_0

    sub-int/2addr p1, v0

    .line 146
    invoke-interface {p0, p1}, Lcom/facebook/imagepipeline/memory/PooledByteBuffer;->read(I)B

    move-result p0

    const/16 p1, -0x27

    if-ne p0, p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private static getBitmapFactoryOptions(ILandroid/graphics/Bitmap$Config;)Landroid/graphics/BitmapFactory$Options;
    .locals 2

    .line 127
    new-instance v0, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v0}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    const/4 v1, 0x1

    .line 128
    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inDither:Z

    .line 129
    iput-object p1, v0, Landroid/graphics/BitmapFactory$Options;->inPreferredConfig:Landroid/graphics/Bitmap$Config;

    .line 131
    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inPurgeable:Z

    .line 133
    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inInputShareable:Z

    .line 135
    iput p0, v0, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    .line 136
    sget p0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 p1, 0xb

    if-lt p0, p1, :cond_0

    .line 137
    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inMutable:Z

    :cond_0
    return-object v0
.end method


# virtual methods
.method abstract decodeByteArrayAsPurgeable(Lcom/facebook/common/references/CloseableReference;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/facebook/common/references/CloseableReference<",
            "Lcom/facebook/imagepipeline/memory/PooledByteBuffer;",
            ">;",
            "Landroid/graphics/BitmapFactory$Options;",
            ")",
            "Landroid/graphics/Bitmap;"
        }
    .end annotation
.end method

.method public decodeFromEncodedImage(Lcom/facebook/imagepipeline/image/EncodedImage;Landroid/graphics/Bitmap$Config;)Lcom/facebook/common/references/CloseableReference;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/facebook/imagepipeline/image/EncodedImage;",
            "Landroid/graphics/Bitmap$Config;",
            ")",
            "Lcom/facebook/common/references/CloseableReference<",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation

    .line 55
    invoke-virtual {p1}, Lcom/facebook/imagepipeline/image/EncodedImage;->getSampleSize()I

    move-result v0

    .line 54
    invoke-static {v0, p2}, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->getBitmapFactoryOptions(ILandroid/graphics/Bitmap$Config;)Landroid/graphics/BitmapFactory$Options;

    move-result-object p2

    .line 57
    invoke-virtual {p1}, Lcom/facebook/imagepipeline/image/EncodedImage;->getByteBufferRef()Lcom/facebook/common/references/CloseableReference;

    move-result-object p1

    .line 58
    invoke-static {p1}, Lcom/facebook/common/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 60
    :try_start_0
    invoke-virtual {p0, p1, p2}, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->decodeByteArrayAsPurgeable(Lcom/facebook/common/references/CloseableReference;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p2

    .line 61
    invoke-virtual {p0, p2}, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->pinBitmap(Landroid/graphics/Bitmap;)Lcom/facebook/common/references/CloseableReference;

    move-result-object p2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 63
    invoke-static {p1}, Lcom/facebook/common/references/CloseableReference;->closeSafely(Lcom/facebook/common/references/CloseableReference;)V

    return-object p2

    :catchall_0
    move-exception p2

    invoke-static {p1}, Lcom/facebook/common/references/CloseableReference;->closeSafely(Lcom/facebook/common/references/CloseableReference;)V

    throw p2
.end method

.method abstract decodeJPEGByteArrayAsPurgeable(Lcom/facebook/common/references/CloseableReference;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/facebook/common/references/CloseableReference<",
            "Lcom/facebook/imagepipeline/memory/PooledByteBuffer;",
            ">;I",
            "Landroid/graphics/BitmapFactory$Options;",
            ")",
            "Landroid/graphics/Bitmap;"
        }
    .end annotation
.end method

.method public decodeJPEGFromEncodedImage(Lcom/facebook/imagepipeline/image/EncodedImage;Landroid/graphics/Bitmap$Config;I)Lcom/facebook/common/references/CloseableReference;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/facebook/imagepipeline/image/EncodedImage;",
            "Landroid/graphics/Bitmap$Config;",
            "I)",
            "Lcom/facebook/common/references/CloseableReference<",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation

    .line 84
    invoke-virtual {p1}, Lcom/facebook/imagepipeline/image/EncodedImage;->getSampleSize()I

    move-result v0

    .line 83
    invoke-static {v0, p2}, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->getBitmapFactoryOptions(ILandroid/graphics/Bitmap$Config;)Landroid/graphics/BitmapFactory$Options;

    move-result-object p2

    .line 86
    invoke-virtual {p1}, Lcom/facebook/imagepipeline/image/EncodedImage;->getByteBufferRef()Lcom/facebook/common/references/CloseableReference;

    move-result-object p1

    .line 87
    invoke-static {p1}, Lcom/facebook/common/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 89
    :try_start_0
    invoke-virtual {p0, p1, p3, p2}, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->decodeJPEGByteArrayAsPurgeable(Lcom/facebook/common/references/CloseableReference;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p2

    .line 90
    invoke-virtual {p0, p2}, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->pinBitmap(Landroid/graphics/Bitmap;)Lcom/facebook/common/references/CloseableReference;

    move-result-object p2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 92
    invoke-static {p1}, Lcom/facebook/common/references/CloseableReference;->closeSafely(Lcom/facebook/common/references/CloseableReference;)V

    return-object p2

    :catchall_0
    move-exception p2

    invoke-static {p1}, Lcom/facebook/common/references/CloseableReference;->closeSafely(Lcom/facebook/common/references/CloseableReference;)V

    throw p2
.end method

.method public pinBitmap(Landroid/graphics/Bitmap;)Lcom/facebook/common/references/CloseableReference;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/graphics/Bitmap;",
            ")",
            "Lcom/facebook/common/references/CloseableReference<",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation

    .line 155
    :try_start_0
    invoke-static {p1}, Lcom/facebook/imagepipeline/nativecode/Bitmaps;->pinBitmap(Landroid/graphics/Bitmap;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 160
    iget-object v0, p0, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->mUnpooledBitmapsCounter:Lcom/facebook/imagepipeline/memory/BitmapCounter;

    invoke-virtual {v0, p1}, Lcom/facebook/imagepipeline/memory/BitmapCounter;->increase(Landroid/graphics/Bitmap;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 164
    iget-object v0, p0, Lcom/facebook/imagepipeline/platform/DalvikPurgeableDecoder;->mUnpooledBitmapsCounter:Lcom/facebook/imagepipeline/memory/BitmapCounter;

    invoke-virtual {v0}, Lcom/facebook/imagepipeline/memory/BitmapCounter;->getReleaser()Lcom/facebook/common/references/ResourceReleaser;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/facebook/common/references/CloseableReference;->of(Ljava/lang/Object;Lcom/facebook/common/references/ResourceReleaser;)Lcom/facebook/common/references/CloseableReference;

    move-result-object p1

    return-object p1

    .line 161
    :cond_0
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->recycle()V

    .line 162
    new-instance p1, Lcom/facebook/imagepipeline/common/TooManyBitmapsException;

    invoke-direct {p1}, Lcom/facebook/imagepipeline/common/TooManyBitmapsException;-><init>()V

    throw p1

    :catch_0
    move-exception v0

    .line 157
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->recycle()V

    .line 158
    invoke-static {v0}, Lcom/facebook/common/internal/Throwables;->propagate(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;

    move-result-object p1

    throw p1
.end method
