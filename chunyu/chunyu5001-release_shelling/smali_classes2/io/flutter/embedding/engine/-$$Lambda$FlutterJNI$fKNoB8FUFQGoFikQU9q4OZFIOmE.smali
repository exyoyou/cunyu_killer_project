.class public final synthetic Lio/flutter/embedding/engine/-$$Lambda$FlutterJNI$fKNoB8FUFQGoFikQU9q4OZFIOmE;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Landroid/graphics/ImageDecoder$OnHeaderDecodedListener;


# instance fields
.field private final synthetic f$0:J


# direct methods
.method public synthetic constructor <init>(J)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-wide p1, p0, Lio/flutter/embedding/engine/-$$Lambda$FlutterJNI$fKNoB8FUFQGoFikQU9q4OZFIOmE;->f$0:J

    return-void
.end method


# virtual methods
.method public final onHeaderDecoded(Landroid/graphics/ImageDecoder;Landroid/graphics/ImageDecoder$ImageInfo;Landroid/graphics/ImageDecoder$Source;)V
    .locals 2

    iget-wide v0, p0, Lio/flutter/embedding/engine/-$$Lambda$FlutterJNI$fKNoB8FUFQGoFikQU9q4OZFIOmE;->f$0:J

    invoke-static {v0, v1, p1, p2, p3}, Lio/flutter/embedding/engine/FlutterJNI;->lambda$decodeImage$0(JLandroid/graphics/ImageDecoder;Landroid/graphics/ImageDecoder$ImageInfo;Landroid/graphics/ImageDecoder$Source;)V

    return-void
.end method
