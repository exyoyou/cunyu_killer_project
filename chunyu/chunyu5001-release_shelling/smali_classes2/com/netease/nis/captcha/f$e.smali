.class Lcom/netease/nis/captcha/f$e;
.super Ljava/lang/Object;
.source "JsCallback.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/netease/nis/captcha/f;->onValidate(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic a:Ljava/lang/String;

.field final synthetic b:Ljava/lang/String;

.field final synthetic c:Ljava/lang/String;

.field final synthetic d:Lcom/netease/nis/captcha/f;


# direct methods
.method constructor <init>(Lcom/netease/nis/captcha/f;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 1
    iput-object p1, p0, Lcom/netease/nis/captcha/f$e;->d:Lcom/netease/nis/captcha/f;

    iput-object p2, p0, Lcom/netease/nis/captcha/f$e;->a:Ljava/lang/String;

    iput-object p3, p0, Lcom/netease/nis/captcha/f$e;->b:Ljava/lang/String;

    iput-object p4, p0, Lcom/netease/nis/captcha/f$e;->c:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/netease/nis/captcha/f$e;->d:Lcom/netease/nis/captcha/f;

    invoke-static {v0}, Lcom/netease/nis/captcha/f;->b(Lcom/netease/nis/captcha/f;)Lcom/netease/nis/captcha/CaptchaListener;

    move-result-object v0

    iget-object v1, p0, Lcom/netease/nis/captcha/f$e;->a:Ljava/lang/String;

    iget-object v2, p0, Lcom/netease/nis/captcha/f$e;->b:Ljava/lang/String;

    iget-object v3, p0, Lcom/netease/nis/captcha/f$e;->c:Ljava/lang/String;

    invoke-interface {v0, v1, v2, v3}, Lcom/netease/nis/captcha/CaptchaListener;->onValidate(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
