.class public final enum Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;
.super Ljava/lang/Enum;
.source "CaptchaConfiguration.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/netease/nis/captcha/CaptchaConfiguration;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "ModeType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

.field public static final enum MODE_CAPTCHA:Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

.field public static final enum MODE_INTELLIGENT_NO_SENSE:Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 1
    new-instance v0, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    const/4 v1, 0x0

    const-string v2, "MODE_CAPTCHA"

    invoke-direct {v0, v2, v1}, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;->MODE_CAPTCHA:Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    .line 5
    new-instance v2, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    const/4 v3, 0x1

    const-string v4, "MODE_INTELLIGENT_NO_SENSE"

    invoke-direct {v2, v4, v3}, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;-><init>(Ljava/lang/String;I)V

    sput-object v2, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;->MODE_INTELLIGENT_NO_SENSE:Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    const/4 v4, 0x2

    new-array v4, v4, [Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    aput-object v0, v4, v1

    aput-object v2, v4, v3

    .line 6
    sput-object v4, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;->$VALUES:[Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;
    .locals 1

    .line 1
    const-class v0, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    return-object p0
.end method

.method public static values()[Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;
    .locals 1

    .line 1
    sget-object v0, Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;->$VALUES:[Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    invoke-virtual {v0}, [Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/netease/nis/captcha/CaptchaConfiguration$ModeType;

    return-object v0
.end method
