package com.tencent.thumbplayer.core.downloadproxy.api;

import android.text.TextUtils;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class TPDLProxyInitParam implements Serializable {
    private String appVer;
    private String cacheDir;
    private String configStr;
    private String dataDir;
    private String guid;
    private int platform;

    public TPDLProxyInitParam(int i, String str, String str2) {
        this.platform = 0;
        this.appVer = "";
        this.guid = "";
        this.cacheDir = "";
        this.dataDir = "";
        this.configStr = "";
        this.platform = i;
        this.appVer = str;
        this.guid = str2;
    }

    public TPDLProxyInitParam(int i, String str, String str2, String str3) {
        this(i, str, str2);
        if (!TextUtils.isEmpty(str3)) {
            this.dataDir = str3;
        }
    }

    public TPDLProxyInitParam(int i, String str, String str2, String str3, String str4, String str5) {
        this(i, str, str2, str4);
        if (!TextUtils.isEmpty(str3)) {
            this.cacheDir = str3;
        }
        if (!TextUtils.isEmpty(str4)) {
            this.dataDir = str4;
        }
        if (!TextUtils.isEmpty(str5)) {
            this.configStr = str5;
        }
    }

    public int getPlatform() {
        return this.platform;
    }

    public String getAppVer() {
        return this.appVer;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getCacheDir() {
        return this.cacheDir;
    }

    public String getDataDir() {
        return this.dataDir;
    }

    public String getConfigStr() {
        return this.configStr;
    }
}
