.class public Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
.super Ljava/lang/Object;
.source "ImageRequestBuilder.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;
    }
.end annotation


# instance fields
.field private mAutoRotateEnabled:Z

.field private mCacheChoice:Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;

.field private mDiskCacheEnabled:Z

.field private mImageDecodeOptions:Lcom/facebook/imagepipeline/common/ImageDecodeOptions;

.field private mLocalThumbnailPreviewsEnabled:Z

.field private mLowestPermittedRequestLevel:Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;

.field private mPostprocessor:Lcom/facebook/imagepipeline/request/Postprocessor;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private mProgressiveRenderingEnabled:Z

.field private mRequestPriority:Lcom/facebook/imagepipeline/common/Priority;

.field private mResizeOptions:Lcom/facebook/imagepipeline/common/ResizeOptions;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private mSourceUri:Landroid/net/Uri;


# direct methods
.method private constructor <init>()V
    .locals 3

    .line 94
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    .line 30
    iput-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    .line 31
    sget-object v1, Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;->FULL_FETCH:Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;

    iput-object v1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mLowestPermittedRequestLevel:Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;

    const/4 v1, 0x0

    .line 32
    iput-boolean v1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mAutoRotateEnabled:Z

    .line 33
    iput-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mResizeOptions:Lcom/facebook/imagepipeline/common/ResizeOptions;

    .line 34
    invoke-static {}, Lcom/facebook/imagepipeline/common/ImageDecodeOptions;->defaults()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;

    move-result-object v2

    iput-object v2, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mImageDecodeOptions:Lcom/facebook/imagepipeline/common/ImageDecodeOptions;

    .line 35
    sget-object v2, Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;->DEFAULT:Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;

    iput-object v2, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mCacheChoice:Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;

    .line 36
    iput-boolean v1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mProgressiveRenderingEnabled:Z

    .line 37
    iput-boolean v1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mLocalThumbnailPreviewsEnabled:Z

    .line 38
    sget-object v1, Lcom/facebook/imagepipeline/common/Priority;->HIGH:Lcom/facebook/imagepipeline/common/Priority;

    iput-object v1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mRequestPriority:Lcom/facebook/imagepipeline/common/Priority;

    .line 39
    iput-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mPostprocessor:Lcom/facebook/imagepipeline/request/Postprocessor;

    const/4 v0, 0x1

    .line 40
    iput-boolean v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mDiskCacheEnabled:Z

    return-void
.end method

.method public static fromRequest(Lcom/facebook/imagepipeline/request/ImageRequest;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 2

    .line 82
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getSourceUri()Landroid/net/Uri;

    move-result-object v0

    invoke-static {v0}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->newBuilderWithSource(Landroid/net/Uri;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 83
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getAutoRotateEnabled()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setAutoRotateEnabled(Z)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 84
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getImageDecodeOptions()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setImageDecodeOptions(Lcom/facebook/imagepipeline/common/ImageDecodeOptions;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 85
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getCacheChoice()Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setCacheChoice(Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 86
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getLocalThumbnailPreviewsEnabled()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setLocalThumbnailPreviewsEnabled(Z)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 87
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getLowestPermittedRequestLevel()Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setLowestPermittedRequestLevel(Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 88
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getPostprocessor()Lcom/facebook/imagepipeline/request/Postprocessor;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setPostprocessor(Lcom/facebook/imagepipeline/request/Postprocessor;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 89
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getProgressiveRenderingEnabled()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setProgressiveRenderingEnabled(Z)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 90
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getPriority()Lcom/facebook/imagepipeline/common/Priority;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setRequestPriority(Lcom/facebook/imagepipeline/common/Priority;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object v0

    .line 91
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequest;->getResizeOptions()Lcom/facebook/imagepipeline/common/ResizeOptions;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setResizeOptions(Lcom/facebook/imagepipeline/common/ResizeOptions;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object p0

    return-object p0
.end method

.method public static newBuilderWithResourceId(I)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 2

    .line 68
    new-instance v0, Landroid/net/Uri$Builder;

    invoke-direct {v0}, Landroid/net/Uri$Builder;-><init>()V

    const-string v1, "res"

    .line 69
    invoke-virtual {v0, v1}, Landroid/net/Uri$Builder;->scheme(Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    .line 70
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/net/Uri$Builder;->path(Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object p0

    .line 71
    invoke-virtual {p0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object p0

    .line 73
    invoke-static {p0}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->newBuilderWithSource(Landroid/net/Uri;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object p0

    return-object p0
.end method

.method public static newBuilderWithSource(Landroid/net/Uri;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 1

    .line 48
    new-instance v0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    invoke-direct {v0}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;-><init>()V

    invoke-virtual {v0, p0}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->setSource(Landroid/net/Uri;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public build()Lcom/facebook/imagepipeline/request/ImageRequest;
    .locals 1

    .line 260
    invoke-virtual {p0}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->validate()V

    .line 261
    new-instance v0, Lcom/facebook/imagepipeline/request/ImageRequest;

    invoke-direct {v0, p0}, Lcom/facebook/imagepipeline/request/ImageRequest;-><init>(Lcom/facebook/imagepipeline/request/ImageRequestBuilder;)V

    return-object v0
.end method

.method public disableDiskCache()Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 1

    const/4 v0, 0x0

    .line 217
    iput-boolean v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mDiskCacheEnabled:Z

    return-object p0
.end method

.method public getCacheChoice()Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;
    .locals 1

    .line 182
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mCacheChoice:Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;

    return-object v0
.end method

.method public getImageDecodeOptions()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;
    .locals 1

    .line 166
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mImageDecodeOptions:Lcom/facebook/imagepipeline/common/ImageDecodeOptions;

    return-object v0
.end method

.method public getLowestPermittedRequestLevel()Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;
    .locals 1

    .line 127
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mLowestPermittedRequestLevel:Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;

    return-object v0
.end method

.method public getPostprocessor()Lcom/facebook/imagepipeline/request/Postprocessor;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 252
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mPostprocessor:Lcom/facebook/imagepipeline/request/Postprocessor;

    return-object v0
.end method

.method public getRequestPriority()Lcom/facebook/imagepipeline/common/Priority;
    .locals 1

    .line 238
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mRequestPriority:Lcom/facebook/imagepipeline/common/Priority;

    return-object v0
.end method

.method public getResizeOptions()Lcom/facebook/imagepipeline/common/ResizeOptions;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 157
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mResizeOptions:Lcom/facebook/imagepipeline/common/ResizeOptions;

    return-object v0
.end method

.method public getSourceUri()Landroid/net/Uri;
    .locals 1

    .line 112
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    return-object v0
.end method

.method public isAutoRotateEnabled()Z
    .locals 1

    .line 142
    iget-boolean v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mAutoRotateEnabled:Z

    return v0
.end method

.method public isDiskCacheEnabled()Z
    .locals 1

    .line 223
    iget-boolean v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mDiskCacheEnabled:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    invoke-static {v0}, Lcom/facebook/common/util/UriUtil;->isNetworkUri(Landroid/net/Uri;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isLocalThumbnailPreviewsEnabled()Z
    .locals 1

    .line 212
    iget-boolean v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mLocalThumbnailPreviewsEnabled:Z

    return v0
.end method

.method public isProgressiveRenderingEnabled()Z
    .locals 1

    .line 197
    iget-boolean v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mProgressiveRenderingEnabled:Z

    return v0
.end method

.method public setAutoRotateEnabled(Z)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 136
    iput-boolean p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mAutoRotateEnabled:Z

    return-object p0
.end method

.method public setCacheChoice(Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 176
    iput-object p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mCacheChoice:Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;

    return-object p0
.end method

.method public setImageDecodeOptions(Lcom/facebook/imagepipeline/common/ImageDecodeOptions;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 161
    iput-object p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mImageDecodeOptions:Lcom/facebook/imagepipeline/common/ImageDecodeOptions;

    return-object p0
.end method

.method public setLocalThumbnailPreviewsEnabled(Z)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 206
    iput-boolean p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mLocalThumbnailPreviewsEnabled:Z

    return-object p0
.end method

.method public setLowestPermittedRequestLevel(Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 121
    iput-object p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mLowestPermittedRequestLevel:Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;

    return-object p0
.end method

.method public setPostprocessor(Lcom/facebook/imagepipeline/request/Postprocessor;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 246
    iput-object p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mPostprocessor:Lcom/facebook/imagepipeline/request/Postprocessor;

    return-object p0
.end method

.method public setProgressiveRenderingEnabled(Z)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 191
    iput-boolean p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mProgressiveRenderingEnabled:Z

    return-object p0
.end method

.method public setRequestPriority(Lcom/facebook/imagepipeline/common/Priority;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 232
    iput-object p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mRequestPriority:Lcom/facebook/imagepipeline/common/Priority;

    return-object p0
.end method

.method public setResizeOptions(Lcom/facebook/imagepipeline/common/ResizeOptions;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 151
    iput-object p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mResizeOptions:Lcom/facebook/imagepipeline/common/ResizeOptions;

    return-object p0
.end method

.method public setSource(Landroid/net/Uri;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;
    .locals 0

    .line 104
    invoke-static {p1}, Lcom/facebook/common/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 106
    iput-object p1, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    return-object p0
.end method

.method protected validate()V
    .locals 2

    .line 274
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    if-eqz v0, :cond_5

    .line 279
    invoke-static {v0}, Lcom/facebook/common/util/UriUtil;->isLocalResourceUri(Landroid/net/Uri;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 280
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->isAbsolute()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 283
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 287
    :try_start_0
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 289
    :catch_0
    new-instance v0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;

    const-string v1, "Resource URI path must be a resource id."

    invoke-direct {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 284
    :cond_0
    new-instance v0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;

    const-string v1, "Resource URI must not be empty"

    invoke-direct {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 281
    :cond_1
    new-instance v0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;

    const-string v1, "Resource URI path must be absolute."

    invoke-direct {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 295
    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    invoke-static {v0}, Lcom/facebook/common/util/UriUtil;->isLocalAssetUri(Landroid/net/Uri;)Z

    move-result v0

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder;->mSourceUri:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->isAbsolute()Z

    move-result v0

    if-eqz v0, :cond_3

    goto :goto_1

    .line 296
    :cond_3
    new-instance v0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;

    const-string v1, "Asset URI path must be absolute."

    invoke-direct {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_4
    :goto_1
    return-void

    .line 275
    :cond_5
    new-instance v0, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;

    const-string v1, "Source must be set!"

    invoke-direct {v0, v1}, Lcom/facebook/imagepipeline/request/ImageRequestBuilder$BuilderException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
