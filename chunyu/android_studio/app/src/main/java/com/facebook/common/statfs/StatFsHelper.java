package com.facebook.common.statfs;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.Throwables;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class StatFsHelper {
    private static final long RESTAT_INTERVAL_MS = TimeUnit.MINUTES.toMillis(2);
    private static StatFsHelper sStatsFsHelper;
    private volatile File mExternalPath;
    private volatile File mInternalPath;
    private long mLastRestatTime;
    private volatile StatFs mInternalStatFs = null;
    private volatile StatFs mExternalStatFs = null;
    private volatile boolean mInitialized = false;
    private final Lock lock = new ReentrantLock();

    /* loaded from: classes4.dex */
    public enum StorageType {
        INTERNAL,
        EXTERNAL
    }

    public static synchronized StatFsHelper getInstance() {
        StatFsHelper statFsHelper;
        synchronized (StatFsHelper.class) {
            if (sStatsFsHelper == null) {
                sStatsFsHelper = new StatFsHelper();
            }
            statFsHelper = sStatsFsHelper;
        }
        return statFsHelper;
    }

    protected StatFsHelper() {
    }

    private void ensureInitialized() {
        if (!this.mInitialized) {
            this.lock.lock();
            try {
                if (!this.mInitialized) {
                    this.mInternalPath = Environment.getDataDirectory();
                    this.mExternalPath = Environment.getExternalStorageDirectory();
                    updateStats();
                    this.mInitialized = true;
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean testLowDiskSpace(StorageType storageType, long j) {
        ensureInitialized();
        long availableStorageSpace = getAvailableStorageSpace(storageType);
        return availableStorageSpace <= 0 || availableStorageSpace < j;
    }

    public long getAvailableStorageSpace(StorageType storageType) {
        long j;
        long j2;
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFs = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFs == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            j2 = statFs.getBlockSizeLong();
            j = statFs.getAvailableBlocksLong();
        } else {
            j2 = (long) statFs.getBlockSize();
            j = (long) statFs.getAvailableBlocks();
        }
        return j2 * j;
    }

    private void maybeUpdateStats() {
        if (this.lock.tryLock()) {
            try {
                if (SystemClock.uptimeMillis() - this.mLastRestatTime > RESTAT_INTERVAL_MS) {
                    updateStats();
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public void resetStats() {
        if (this.lock.tryLock()) {
            try {
                ensureInitialized();
                updateStats();
            } finally {
                this.lock.unlock();
            }
        }
    }

    private void updateStats() {
        this.mInternalStatFs = updateStatsHelper(this.mInternalStatFs, this.mInternalPath);
        this.mExternalStatFs = updateStatsHelper(this.mExternalStatFs, this.mExternalPath);
        this.mLastRestatTime = SystemClock.uptimeMillis();
    }

    private StatFs updateStatsHelper(@Nullable StatFs statFs, @Nullable File file) {
        StatFs statFs2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            if (statFs == null) {
                statFs = createStatFs(file.getAbsolutePath());
            } else {
                statFs.restat(file.getAbsolutePath());
            }
            statFs2 = statFs;
            return statFs2;
        } catch (IllegalArgumentException unused) {
            return statFs2;
        } catch (Throwable th) {
            throw Throwables.propagate(th);
        }
    }

    protected static StatFs createStatFs(String str) {
        return new StatFs(str);
    }
}
