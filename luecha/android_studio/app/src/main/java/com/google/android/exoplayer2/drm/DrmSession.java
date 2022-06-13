package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes2.dex */
public interface DrmSession {
    public static final int STATE_ERROR = 1;
    public static final int STATE_OPENED = 3;
    public static final int STATE_OPENED_WITH_KEYS = 4;
    public static final int STATE_OPENING = 2;
    public static final int STATE_RELEASED = 0;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface State {
    }

    void acquire(DrmSessionEventListener.EventDispatcher eventDispatcher);

    DrmSessionException getError();

    ExoMediaCrypto getMediaCrypto();

    byte[] getOfflineLicenseKeySetId();

    UUID getSchemeUuid();

    int getState();

    boolean playClearSamplesWithoutKeys();

    Map<String, String> queryKeyStatus();

    void release(DrmSessionEventListener.EventDispatcher eventDispatcher);

    /* renamed from: com.google.android.exoplayer2.drm.DrmSession$-CC */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static boolean $default$playClearSamplesWithoutKeys(DrmSession drmSession) {
            return false;
        }

        public static void replaceSession(DrmSession drmSession, DrmSession drmSession2) {
            if (drmSession != drmSession2) {
                if (drmSession2 != null) {
                    drmSession2.acquire(null);
                }
                if (drmSession != null) {
                    drmSession.release(null);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DrmSessionException extends IOException {
        public final int errorCode;

        public DrmSessionException(Throwable th, int i) {
            super(th);
            this.errorCode = i;
        }
    }
}
