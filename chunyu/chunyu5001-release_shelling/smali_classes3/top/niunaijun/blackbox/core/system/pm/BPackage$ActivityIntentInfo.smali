.class public Ltop/niunaijun/blackbox/core/system/pm/BPackage$ActivityIntentInfo;
.super Ltop/niunaijun/blackbox/core/system/pm/BPackage$IntentInfo;
.source "BPackage.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Ltop/niunaijun/blackbox/core/system/pm/BPackage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ActivityIntentInfo"
.end annotation


# instance fields
.field public activity:Ltop/niunaijun/blackbox/core/system/pm/BPackage$Activity;


# direct methods
.method public constructor <init>(Landroid/content/pm/PackageParser$IntentInfo;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "intentInfo"
        }
    .end annotation

    .line 402
    invoke-direct {p0, p1}, Ltop/niunaijun/blackbox/core/system/pm/BPackage$IntentInfo;-><init>(Landroid/content/pm/PackageParser$IntentInfo;)V

    return-void
.end method

.method public constructor <init>(Ltop/niunaijun/blackbox/core/system/pm/BPackage$IntentInfo;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "intentInfo"
        }
    .end annotation

    .line 406
    invoke-direct {p0, p1}, Ltop/niunaijun/blackbox/core/system/pm/BPackage$IntentInfo;-><init>(Ltop/niunaijun/blackbox/core/system/pm/BPackage$IntentInfo;)V

    return-void
.end method
