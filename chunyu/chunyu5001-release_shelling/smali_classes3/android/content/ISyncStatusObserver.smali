.class public interface abstract Landroid/content/ISyncStatusObserver;
.super Ljava/lang/Object;
.source "ISyncStatusObserver.java"

# interfaces
.implements Landroid/os/IInterface;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroid/content/ISyncStatusObserver$Stub;,
        Landroid/content/ISyncStatusObserver$Default;
    }
.end annotation


# virtual methods
.method public abstract onStatusChanged(I)V
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "which"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation
.end method
