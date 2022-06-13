package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.debug.UMRTLog;

/* compiled from: CacheDBHelper.java */
/* loaded from: classes3.dex */
public class ak extends SQLiteOpenHelper {
    private static final Object b = new Object();
    private static ak c = null;
    private static final String d = "CREATE TABLE IF NOT EXISTS stf(_id INTEGER PRIMARY KEY AUTOINCREMENT, _tp TEXT, _hd TEXT, _bd TEXT, _ts TEXT, _uuid TEXT, _re1 TEXT, _re2 TEXT)";
    private static final String e = "DROP TABLE IF EXISTS stf";
    private static final String f = "DELETE FROM stf WHERE _id IN( SELECT _id FROM stf ORDER BY _id LIMIT 1)";
    private final Context a;

    public static final int a() {
        return 1;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static ak a(Context context) {
        ak akVar;
        synchronized (b) {
            if (c == null) {
                c = new ak(context, am.b, null, 1);
            }
            akVar = c;
        }
        return akVar;
    }

    private ak(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.a = context;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(e);
            sQLiteDatabase.execSQL(d);
        } catch (SQLException unused) {
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(d);
        } catch (SQLiteDatabaseCorruptException unused) {
            a(sQLiteDatabase);
        } catch (Throwable th) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [有状态]创建二级缓存数据库失败: " + th.getMessage());
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
    }

    public void b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                writableDatabase.close();
            }
        } catch (Throwable unused) {
        }
    }

    public void a(String str, ContentValues contentValues) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.beginTransaction();
                    writableDatabase.insert(str, null, contentValues);
                    writableDatabase.setTransactionSuccessful();
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [有状态]插入二级缓存数据记录 成功。");
                    if (writableDatabase == null) {
                        return;
                    }
                } catch (Throwable unused) {
                    if (writableDatabase == null) {
                        return;
                    }
                }
                writableDatabase.endTransaction();
                writableDatabase.close();
            }
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, String str2, String[] strArr) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.beginTransaction();
                    writableDatabase.delete(str, str2, strArr);
                    writableDatabase.setTransactionSuccessful();
                    if (writableDatabase == null) {
                        return;
                    }
                } catch (Throwable unused) {
                    if (writableDatabase == null) {
                        return;
                    }
                }
                writableDatabase.endTransaction();
                writableDatabase.close();
            }
        } catch (Throwable unused2) {
        }
    }

    private void d() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.execSQL(f);
                    if (writableDatabase == null) {
                        return;
                    }
                } catch (Throwable unused) {
                    if (writableDatabase == null) {
                        return;
                    }
                }
                writableDatabase.close();
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0077, code lost:
        if (r1 == null) goto L_0x007a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007a, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public com.umeng.analytics.pro.al a(java.lang.String r11) {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r1 = "_uuid"
            java.lang.String r2 = "_tp"
            java.lang.String r3 = "_hd"
            java.lang.String r4 = "_bd"
            java.lang.String r5 = "_re1"
            java.lang.String r6 = "_re2"
            java.lang.String[] r3 = new java.lang.String[]{r1, r2, r3, r4, r5, r6}     // Catch: all -> 0x0073
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "1"
            r1 = r10
            r2 = r11
            android.database.Cursor r1 = r1.a(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: all -> 0x0073
            if (r1 == 0) goto L_0x006d
            boolean r2 = r1.moveToFirst()     // Catch: all -> 0x0074
            if (r2 == 0) goto L_0x006d
            com.umeng.analytics.pro.al r2 = new com.umeng.analytics.pro.al     // Catch: all -> 0x0074
            r2.<init>()     // Catch: all -> 0x0074
            r0 = 0
            java.lang.String r0 = r1.getString(r0)     // Catch: all -> 0x006b
            r2.a = r0     // Catch: all -> 0x006b
            r0 = 1
            java.lang.String r0 = r1.getString(r0)     // Catch: all -> 0x006b
            r2.b = r0     // Catch: all -> 0x006b
            r0 = 2
            java.lang.String r0 = r1.getString(r0)     // Catch: all -> 0x006b
            r3 = 3
            java.lang.String r3 = r1.getString(r3)     // Catch: all -> 0x006b
            android.content.Context r4 = r10.a     // Catch: all -> 0x006b
            com.umeng.analytics.pro.h r4 = com.umeng.analytics.pro.h.a(r4)     // Catch: all -> 0x006b
            java.lang.String r0 = r4.d(r0)     // Catch: all -> 0x006b
            r2.c = r0     // Catch: all -> 0x006b
            android.content.Context r0 = r10.a     // Catch: all -> 0x006b
            com.umeng.analytics.pro.h r0 = com.umeng.analytics.pro.h.a(r0)     // Catch: all -> 0x006b
            java.lang.String r0 = r0.d(r3)     // Catch: all -> 0x006b
            r2.d = r0     // Catch: all -> 0x006b
            r0 = 4
            java.lang.String r0 = r1.getString(r0)     // Catch: all -> 0x006b
            r2.e = r0     // Catch: all -> 0x006b
            r0 = 5
            java.lang.String r0 = r1.getString(r0)     // Catch: all -> 0x006b
            r2.f = r0     // Catch: all -> 0x006b
            r0 = r2
            goto L_0x006d
        L_0x006b:
            r0 = r2
            goto L_0x0074
        L_0x006d:
            if (r1 == 0) goto L_0x007a
        L_0x006f:
            r1.close()
            goto L_0x007a
        L_0x0073:
            r1 = r0
        L_0x0074:
            r10.d()     // Catch: all -> 0x007b
            if (r1 == 0) goto L_0x007a
            goto L_0x006f
        L_0x007a:
            return r0
        L_0x007b:
            r0 = move-exception
            r2 = r0
            if (r1 == 0) goto L_0x0082
            r1.close()
        L_0x0082:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.ak.a(java.lang.String):com.umeng.analytics.pro.al");
    }

    public void a(String str, String str2) {
        a(str, "_uuid=?", new String[]{str2});
    }

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return null;
            }
            return writableDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean b(String str) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.isOpen()) {
                        cursor = sQLiteDatabase.query(str, null, null, null, null, null, null, null);
                    }
                } catch (Throwable unused) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase == null) {
                        return false;
                    }
                    sQLiteDatabase.close();
                    return false;
                }
            }
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return true;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase == null) {
                return false;
            }
        } catch (Throwable unused2) {
            sQLiteDatabase = null;
        }
        sQLiteDatabase.close();
        return false;
    }

    public boolean c() {
        return !b(am.c);
    }
}
