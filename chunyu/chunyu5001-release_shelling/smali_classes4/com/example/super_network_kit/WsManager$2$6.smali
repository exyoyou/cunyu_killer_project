.class Lcom/example/super_network_kit/WsManager$2$6;
.super Ljava/lang/Object;
.source "WsManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/super_network_kit/WsManager$2;->onFailure(Lokhttp3/WebSocket;Ljava/lang/Throwable;Lokhttp3/Response;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/example/super_network_kit/WsManager$2;

.field final synthetic val$response:Lokhttp3/Response;

.field final synthetic val$t:Ljava/lang/Throwable;


# direct methods
.method constructor <init>(Lcom/example/super_network_kit/WsManager$2;Ljava/lang/Throwable;Lokhttp3/Response;)V
    .locals 0

    .line 167
    iput-object p1, p0, Lcom/example/super_network_kit/WsManager$2$6;->this$1:Lcom/example/super_network_kit/WsManager$2;

    iput-object p2, p0, Lcom/example/super_network_kit/WsManager$2$6;->val$t:Ljava/lang/Throwable;

    iput-object p3, p0, Lcom/example/super_network_kit/WsManager$2$6;->val$response:Lokhttp3/Response;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .line 170
    iget-object v0, p0, Lcom/example/super_network_kit/WsManager$2$6;->this$1:Lcom/example/super_network_kit/WsManager$2;

    iget-object v0, v0, Lcom/example/super_network_kit/WsManager$2;->this$0:Lcom/example/super_network_kit/WsManager;

    invoke-static {v0}, Lcom/example/super_network_kit/WsManager;->access$000(Lcom/example/super_network_kit/WsManager;)Lcom/example/super_network_kit/listener/WsStatusListener;

    move-result-object v0

    iget-object v1, p0, Lcom/example/super_network_kit/WsManager$2$6;->val$t:Ljava/lang/Throwable;

    iget-object v2, p0, Lcom/example/super_network_kit/WsManager$2$6;->val$response:Lokhttp3/Response;

    invoke-virtual {v0, v1, v2}, Lcom/example/super_network_kit/listener/WsStatusListener;->onFailure(Ljava/lang/Throwable;Lokhttp3/Response;)V

    return-void
.end method
