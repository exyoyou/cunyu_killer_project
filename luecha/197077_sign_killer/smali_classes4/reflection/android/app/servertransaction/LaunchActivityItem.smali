.class public Lreflection/android/app/servertransaction/LaunchActivityItem;
.super Ljava/lang/Object;
.source "LaunchActivityItem.java"


# static fields
.field public static final REF:Lreflection/MirrorReflection;

.field public static mIntent:Lreflection/MirrorReflection$FieldWrapper;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lreflection/MirrorReflection$FieldWrapper<",
            "Landroid/content/Intent;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-string v0, "android.app.servertransaction.LaunchActivityItem"

    .line 8
    invoke-static {v0}, Lreflection/MirrorReflection;->on(Ljava/lang/String;)Lreflection/MirrorReflection;

    move-result-object v0

    sput-object v0, Lreflection/android/app/servertransaction/LaunchActivityItem;->REF:Lreflection/MirrorReflection;

    const-string v1, "mIntent"

    .line 10
    invoke-virtual {v0, v1}, Lreflection/MirrorReflection;->field(Ljava/lang/String;)Lreflection/MirrorReflection$FieldWrapper;

    move-result-object v0

    sput-object v0, Lreflection/android/app/servertransaction/LaunchActivityItem;->mIntent:Lreflection/MirrorReflection$FieldWrapper;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
