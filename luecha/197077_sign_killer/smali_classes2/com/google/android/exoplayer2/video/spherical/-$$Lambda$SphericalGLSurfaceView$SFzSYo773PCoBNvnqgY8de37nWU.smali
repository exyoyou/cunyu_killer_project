.class public final synthetic Lcom/google/android/exoplayer2/video/spherical/-$$Lambda$SphericalGLSurfaceView$SFzSYo773PCoBNvnqgY8de37nWU;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic f$0:Lcom/google/android/exoplayer2/video/spherical/SphericalGLSurfaceView;

.field private final synthetic f$1:Landroid/graphics/SurfaceTexture;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/exoplayer2/video/spherical/SphericalGLSurfaceView;Landroid/graphics/SurfaceTexture;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/exoplayer2/video/spherical/-$$Lambda$SphericalGLSurfaceView$SFzSYo773PCoBNvnqgY8de37nWU;->f$0:Lcom/google/android/exoplayer2/video/spherical/SphericalGLSurfaceView;

    iput-object p2, p0, Lcom/google/android/exoplayer2/video/spherical/-$$Lambda$SphericalGLSurfaceView$SFzSYo773PCoBNvnqgY8de37nWU;->f$1:Landroid/graphics/SurfaceTexture;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    iget-object v0, p0, Lcom/google/android/exoplayer2/video/spherical/-$$Lambda$SphericalGLSurfaceView$SFzSYo773PCoBNvnqgY8de37nWU;->f$0:Lcom/google/android/exoplayer2/video/spherical/SphericalGLSurfaceView;

    iget-object v1, p0, Lcom/google/android/exoplayer2/video/spherical/-$$Lambda$SphericalGLSurfaceView$SFzSYo773PCoBNvnqgY8de37nWU;->f$1:Landroid/graphics/SurfaceTexture;

    invoke-virtual {v0, v1}, Lcom/google/android/exoplayer2/video/spherical/SphericalGLSurfaceView;->lambda$onSurfaceTextureAvailable$1$SphericalGLSurfaceView(Landroid/graphics/SurfaceTexture;)V

    return-void
.end method
