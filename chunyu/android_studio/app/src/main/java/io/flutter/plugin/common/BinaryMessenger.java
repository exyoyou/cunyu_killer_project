package io.flutter.plugin.common;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public interface BinaryMessenger {

    /* loaded from: classes2.dex */
    public interface BinaryMessageHandler {
        void onMessage(ByteBuffer byteBuffer, BinaryReply binaryReply);
    }

    /* loaded from: classes2.dex */
    public interface BinaryReply {
        void reply(ByteBuffer byteBuffer);
    }

    /* loaded from: classes2.dex */
    public interface TaskQueue {
    }

    void disableBufferingIncomingMessages();

    void enableBufferingIncomingMessages();

    TaskQueue makeBackgroundTaskQueue();

    TaskQueue makeBackgroundTaskQueue(TaskQueueOptions taskQueueOptions);

    void send(String str, ByteBuffer byteBuffer);

    void send(String str, ByteBuffer byteBuffer, BinaryReply binaryReply);

    void setMessageHandler(String str, BinaryMessageHandler binaryMessageHandler);

    void setMessageHandler(String str, BinaryMessageHandler binaryMessageHandler, TaskQueue taskQueue);

    /* loaded from: classes2.dex */
    public static class TaskQueueOptions {
        private boolean isSerial = true;

        public boolean getIsSerial() {
            return this.isSerial;
        }

        public TaskQueueOptions setIsSerial(boolean z) {
            this.isSerial = z;
            return this;
        }
    }

    /* renamed from: io.flutter.plugin.common.BinaryMessenger$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static TaskQueue $default$makeBackgroundTaskQueue(BinaryMessenger _this) {
            return _this.makeBackgroundTaskQueue(new TaskQueueOptions());
        }

        public static TaskQueue $default$makeBackgroundTaskQueue(BinaryMessenger _this, TaskQueueOptions taskQueueOptions) {
            throw new UnsupportedOperationException("makeBackgroundTaskQueue not implemented.");
        }

        public static void $default$setMessageHandler(BinaryMessenger _this, String str, BinaryMessageHandler binaryMessageHandler, TaskQueue taskQueue) {
            if (taskQueue == null) {
                _this.setMessageHandler(str, binaryMessageHandler);
                return;
            }
            throw new UnsupportedOperationException("setMessageHandler called with nonnull taskQueue is not supported.");
        }

        public static void $default$enableBufferingIncomingMessages(BinaryMessenger _this) {
            throw new UnsupportedOperationException("enableBufferingIncomingMessages not implemented.");
        }

        public static void $default$disableBufferingIncomingMessages(BinaryMessenger _this) {
            throw new UnsupportedOperationException("disableBufferingIncomingMessages not implemented.");
        }
    }
}
