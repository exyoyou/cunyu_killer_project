.class Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient$13;
.super Ljava/lang/Object;
.source "InAppWebViewChromeClient.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient;->createBeforeUnloadDialog(Landroid/webkit/WebView;Ljava/lang/String;Landroid/webkit/JsResult;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient;

.field final synthetic val$result:Landroid/webkit/JsResult;


# direct methods
.method constructor <init>(Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient;Landroid/webkit/JsResult;)V
    .locals 0

    .line 559
    iput-object p1, p0, Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient$13;->this$0:Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient;

    iput-object p2, p0, Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient$13;->val$result:Landroid/webkit/JsResult;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 0

    .line 562
    iget-object p2, p0, Lcom/pichillilorenzo/flutter_inappwebview/InAppWebView/InAppWebViewChromeClient$13;->val$result:Landroid/webkit/JsResult;

    invoke-virtual {p2}, Landroid/webkit/JsResult;->confirm()V

    .line 563
    invoke-interface {p1}, Landroid/content/DialogInterface;->dismiss()V

    return-void
.end method
