.class public final synthetic Lio/flutter/plugins/pathprovider/-$$Lambda$PathProviderPlugin$PathProviderPlatformThread$4MPiPxTFI9pHzSREjzIJDihoSpM;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/util/concurrent/Callable;


# instance fields
.field private final synthetic f$0:Lio/flutter/plugins/pathprovider/PathProviderPlugin$PathProviderPlatformThread;


# direct methods
.method public synthetic constructor <init>(Lio/flutter/plugins/pathprovider/PathProviderPlugin$PathProviderPlatformThread;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lio/flutter/plugins/pathprovider/-$$Lambda$PathProviderPlugin$PathProviderPlatformThread$4MPiPxTFI9pHzSREjzIJDihoSpM;->f$0:Lio/flutter/plugins/pathprovider/PathProviderPlugin$PathProviderPlatformThread;

    return-void
.end method


# virtual methods
.method public final call()Ljava/lang/Object;
    .locals 1

    iget-object v0, p0, Lio/flutter/plugins/pathprovider/-$$Lambda$PathProviderPlugin$PathProviderPlatformThread$4MPiPxTFI9pHzSREjzIJDihoSpM;->f$0:Lio/flutter/plugins/pathprovider/PathProviderPlugin$PathProviderPlatformThread;

    invoke-virtual {v0}, Lio/flutter/plugins/pathprovider/PathProviderPlugin$PathProviderPlatformThread;->lambda$getExternalCacheDirectories$3$PathProviderPlugin$PathProviderPlatformThread()Ljava/util/List;

    move-result-object v0

    return-object v0
.end method
