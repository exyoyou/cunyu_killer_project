.class public Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;
.super Ljava/lang/Object;
.source "AccessibilityChannel.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$AccessibilityMessageHandler;
    }
.end annotation


# static fields
.field private static final TAG:Ljava/lang/String; = "AccessibilityChannel"


# instance fields
.field public final channel:Lio/flutter/plugin/common/BasicMessageChannel;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/flutter/plugin/common/BasicMessageChannel<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field public final flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

.field private handler:Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$AccessibilityMessageHandler;

.field private final parsingMessageHandler:Lio/flutter/plugin/common/BasicMessageChannel$MessageHandler;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/flutter/plugin/common/BasicMessageChannel$MessageHandler<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lio/flutter/embedding/engine/dart/DartExecutor;Lio/flutter/embedding/engine/FlutterJNI;)V
    .locals 3

    .line 88
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 26
    new-instance v0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$1;

    invoke-direct {v0, p0}, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$1;-><init>(Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;)V

    iput-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->parsingMessageHandler:Lio/flutter/plugin/common/BasicMessageChannel$MessageHandler;

    .line 89
    new-instance v0, Lio/flutter/plugin/common/BasicMessageChannel;

    sget-object v1, Lio/flutter/plugin/common/StandardMessageCodec;->INSTANCE:Lio/flutter/plugin/common/StandardMessageCodec;

    const-string v2, "flutter/accessibility"

    invoke-direct {v0, p1, v2, v1}, Lio/flutter/plugin/common/BasicMessageChannel;-><init>(Lio/flutter/plugin/common/BinaryMessenger;Ljava/lang/String;Lio/flutter/plugin/common/MessageCodec;)V

    iput-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->channel:Lio/flutter/plugin/common/BasicMessageChannel;

    .line 92
    iget-object p1, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->channel:Lio/flutter/plugin/common/BasicMessageChannel;

    iget-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->parsingMessageHandler:Lio/flutter/plugin/common/BasicMessageChannel$MessageHandler;

    invoke-virtual {p1, v0}, Lio/flutter/plugin/common/BasicMessageChannel;->setMessageHandler(Lio/flutter/plugin/common/BasicMessageChannel$MessageHandler;)V

    .line 93
    iput-object p2, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

    return-void
.end method

.method static synthetic access$000(Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;)Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$AccessibilityMessageHandler;
    .locals 0

    .line 19
    iget-object p0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->handler:Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$AccessibilityMessageHandler;

    return-object p0
.end method


# virtual methods
.method public dispatchSemanticsAction(ILio/flutter/view/AccessibilityBridge$Action;)V
    .locals 1

    .line 138
    iget-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

    invoke-virtual {v0, p1, p2}, Lio/flutter/embedding/engine/FlutterJNI;->dispatchSemanticsAction(ILio/flutter/view/AccessibilityBridge$Action;)V

    return-void
.end method

.method public dispatchSemanticsAction(ILio/flutter/view/AccessibilityBridge$Action;Ljava/lang/Object;)V
    .locals 1

    .line 147
    iget-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

    invoke-virtual {v0, p1, p2, p3}, Lio/flutter/embedding/engine/FlutterJNI;->dispatchSemanticsAction(ILio/flutter/view/AccessibilityBridge$Action;Ljava/lang/Object;)V

    return-void
.end method

.method public onAndroidAccessibilityDisabled()V
    .locals 2

    .line 113
    iget-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lio/flutter/embedding/engine/FlutterJNI;->setSemanticsEnabled(Z)V

    return-void
.end method

.method public onAndroidAccessibilityEnabled()V
    .locals 2

    .line 103
    iget-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lio/flutter/embedding/engine/FlutterJNI;->setSemanticsEnabled(Z)V

    return-void
.end method

.method public setAccessibilityFeatures(I)V
    .locals 1

    .line 121
    iget-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

    invoke-virtual {v0, p1}, Lio/flutter/embedding/engine/FlutterJNI;->setAccessibilityFeatures(I)V

    return-void
.end method

.method public setAccessibilityMessageHandler(Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$AccessibilityMessageHandler;)V
    .locals 1

    .line 155
    iput-object p1, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->handler:Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel$AccessibilityMessageHandler;

    .line 156
    iget-object v0, p0, Lio/flutter/embedding/engine/systemchannels/AccessibilityChannel;->flutterJNI:Lio/flutter/embedding/engine/FlutterJNI;

    invoke-virtual {v0, p1}, Lio/flutter/embedding/engine/FlutterJNI;->setAccessibilityDelegate(Lio/flutter/embedding/engine/FlutterJNI$AccessibilityDelegate;)V

    return-void
.end method
