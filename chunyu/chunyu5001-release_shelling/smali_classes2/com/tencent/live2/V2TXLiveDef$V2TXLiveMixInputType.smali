.class public final enum Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;
.super Ljava/lang/Enum;
.source "V2TXLiveDef.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/tencent/live2/V2TXLiveDef;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "V2TXLiveMixInputType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

.field public static final enum V2TXLiveMixInputTypeAudioVideo:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

.field public static final enum V2TXLiveMixInputTypePureAudio:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

.field public static final enum V2TXLiveMixInputTypePureVideo:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 405
    new-instance v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    const/4 v1, 0x0

    const-string v2, "V2TXLiveMixInputTypeAudioVideo"

    invoke-direct {v0, v2, v1}, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->V2TXLiveMixInputTypeAudioVideo:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    .line 408
    new-instance v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    const/4 v2, 0x1

    const-string v3, "V2TXLiveMixInputTypePureVideo"

    invoke-direct {v0, v3, v2}, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->V2TXLiveMixInputTypePureVideo:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    .line 411
    new-instance v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    const/4 v3, 0x2

    const-string v4, "V2TXLiveMixInputTypePureAudio"

    invoke-direct {v0, v4, v3}, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->V2TXLiveMixInputTypePureAudio:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    const/4 v0, 0x3

    new-array v0, v0, [Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    .line 402
    sget-object v4, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->V2TXLiveMixInputTypeAudioVideo:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    aput-object v4, v0, v1

    sget-object v1, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->V2TXLiveMixInputTypePureVideo:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->V2TXLiveMixInputTypePureAudio:Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    aput-object v1, v0, v3

    sput-object v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->$VALUES:[Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 402
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;
    .locals 1

    .line 402
    const-class v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    return-object p0
.end method

.method public static values()[Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;
    .locals 1

    .line 402
    sget-object v0, Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->$VALUES:[Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    invoke-virtual {v0}, [Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/tencent/live2/V2TXLiveDef$V2TXLiveMixInputType;

    return-object v0
.end method
