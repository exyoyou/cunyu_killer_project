.class synthetic Lio/flutter/view/AccessibilityBridge$5;
.super Ljava/lang/Object;
.source "AccessibilityBridge.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lio/flutter/view/AccessibilityBridge;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1008
    name = null
.end annotation


# static fields
.field static final synthetic $SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType:[I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 2510
    invoke-static {}, Lio/flutter/view/AccessibilityBridge$StringAttributeType;->values()[Lio/flutter/view/AccessibilityBridge$StringAttributeType;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [I

    sput-object v0, Lio/flutter/view/AccessibilityBridge$5;->$SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType:[I

    :try_start_0
    sget-object v0, Lio/flutter/view/AccessibilityBridge$5;->$SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType:[I

    sget-object v1, Lio/flutter/view/AccessibilityBridge$StringAttributeType;->SPELLOUT:Lio/flutter/view/AccessibilityBridge$StringAttributeType;

    invoke-virtual {v1}, Lio/flutter/view/AccessibilityBridge$StringAttributeType;->ordinal()I

    move-result v1

    const/4 v2, 0x1

    aput v2, v0, v1
    :try_end_0
    .catch Ljava/lang/NoSuchFieldError; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :try_start_1
    sget-object v0, Lio/flutter/view/AccessibilityBridge$5;->$SwitchMap$io$flutter$view$AccessibilityBridge$StringAttributeType:[I

    sget-object v1, Lio/flutter/view/AccessibilityBridge$StringAttributeType;->LOCALE:Lio/flutter/view/AccessibilityBridge$StringAttributeType;

    invoke-virtual {v1}, Lio/flutter/view/AccessibilityBridge$StringAttributeType;->ordinal()I

    move-result v1

    const/4 v2, 0x2

    aput v2, v0, v1
    :try_end_1
    .catch Ljava/lang/NoSuchFieldError; {:try_start_1 .. :try_end_1} :catch_1

    :catch_1
    return-void
.end method
