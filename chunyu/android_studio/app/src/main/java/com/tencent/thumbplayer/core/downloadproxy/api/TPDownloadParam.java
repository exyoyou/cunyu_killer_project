package com.tencent.thumbplayer.core.downloadproxy.api;

import com.tencent.thumbplayer.core.downloadproxy.utils.TPDLProxyLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TPDownloadParam {
    private static final String FILE_NAME = "TPDownloadParam";
    private int dlType;
    private Map<String, Object> extInfoMap;
    private ArrayList<String> urlList;

    public TPDownloadParam(ArrayList<String> arrayList, int i, Map<String, Object> map) {
        this.urlList = arrayList;
        this.dlType = i;
        this.extInfoMap = map;
    }

    public ArrayList<String> getUrlList() {
        return this.urlList;
    }

    public void setUrlList(ArrayList<String> arrayList) {
        this.urlList = arrayList;
    }

    public void setDlType(int i) {
        this.dlType = i;
    }

    public Map<String, Object> getExtInfoMap() {
        return this.extInfoMap;
    }

    public void setExtInfoMap(Map<String, Object> map) {
        this.extInfoMap = map;
    }

    public String getCdnUrls() {
        if (this.urlList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Map<String, Object> map = this.extInfoMap;
        boolean booleanValue = (map == null || !map.containsKey(TPDownloadProxyEnum.DLPARAM_ENABLE_EXPAND_DOWNLOAD_URL)) ? false : ((Boolean) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_ENABLE_EXPAND_DOWNLOAD_URL)).booleanValue();
        for (int i = 0; i < this.urlList.size(); i++) {
            stringBuffer.append(this.urlList.get(i));
            if (booleanValue) {
                if (this.urlList.get(i).indexOf("?") > 0) {
                    stringBuffer.append("&cost=low");
                } else {
                    stringBuffer.append("?cost=low");
                }
            }
            stringBuffer.append(";");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public int getDlType() {
        return this.dlType;
    }

    public String getVid() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_VID)) {
            return "";
        }
        return (String) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_VID);
    }

    public boolean isOffline() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_IS_OFFLINE)) {
            return false;
        }
        return ((Boolean) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_IS_OFFLINE)).booleanValue();
    }

    public boolean isAdaptive() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_ADAPTIVE_TYPE) || ((Integer) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_ADAPTIVE_TYPE)).intValue() <= 0) {
            return false;
        }
        return true;
    }

    public Map<String, String> getOfflinePlayExtraInfo() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_OFFLINE_PLAY_EXTRA_INFO)) {
            return null;
        }
        return (Map) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_OFFLINE_PLAY_EXTRA_INFO);
    }

    public String getPlayDefinition() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_PLAY_DEFINITION)) {
            return "";
        }
        return (String) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_PLAY_DEFINITION);
    }

    public String getFormat() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_CURRENT_FORMAT)) {
            return "";
        }
        return (String) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_CURRENT_FORMAT);
    }

    public String getKeyid() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_PLAY_KEYID)) {
            return "";
        }
        return (String) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_PLAY_KEYID);
    }

    public int getClipCount() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_PLAY_CLIP_COUNT)) {
            return 1;
        }
        return ((Integer) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_PLAY_CLIP_COUNT)).intValue();
    }

    public int getClipNo() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_PLAY_CLIP_NO)) {
            return 1;
        }
        return ((Integer) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_PLAY_CLIP_NO)).intValue();
    }

    public long getTotalDurationMS() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_FILE_DURATION)) {
            return 0;
        }
        return ((Long) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_FILE_DURATION)).longValue();
    }

    public String getSavaPath() {
        Map<String, Object> map = this.extInfoMap;
        if (map == null || map.isEmpty() || !this.extInfoMap.containsKey(TPDownloadProxyEnum.DLPARAM_SAVE_PATH)) {
            return "";
        }
        return (String) this.extInfoMap.get(TPDownloadProxyEnum.DLPARAM_SAVE_PATH);
    }

    public Object getExtInfo(String str) {
        Map<String, Object> map = this.extInfoMap;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public String getExtraJsonInfo() {
        Map<String, Object> map = this.extInfoMap;
        if (map != null && !map.isEmpty()) {
            try {
                HashMap hashMap = new HashMap();
                for (Map.Entry<String, Object> entry : this.extInfoMap.entrySet()) {
                    if (!entry.getKey().equalsIgnoreCase(TPDownloadProxyEnum.DLPARAM_IS_OFFLINE) && !entry.getKey().equalsIgnoreCase(TPDownloadProxyEnum.DLPARAM_OFFLINE_PLAY_EXTRA_INFO) && !entry.getKey().equalsIgnoreCase(TPDownloadProxyEnum.DLPARAM_PLAY_DEFINITION) && !entry.getKey().equalsIgnoreCase(TPDownloadProxyEnum.DLPARAM_ENABLE_EXPAND_DOWNLOAD_URL)) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                return new JSONObject(hashMap).toString();
            } catch (Throwable th) {
                TPDLProxyLog.e(FILE_NAME, 0, ITPDLProxyLogListener.COMMON_TAG, "getExtraJsonInfo failed, error:" + th.toString());
            }
        }
        return "";
    }

    private String getExtraFormatNodesJsonInfo(ArrayList<Map<String, Object>> arrayList) {
        if (arrayList == null) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            for (int i = 0; i < arrayList.size(); i++) {
                Map<String, Object> map = arrayList.get(i);
                if (i == arrayList.size() - 1) {
                    stringBuffer.append(String.format("{\"dl_param_name\":\"%s\", \"dl_param_bitrate\":%s}", map.get("dl_param_name"), map.get("dl_param_bitrate")));
                } else {
                    stringBuffer.append(String.format("{\"dl_param_name\":\"%s\", \"dl_param_bitrate\":%s}, ", map.get("dl_param_name"), map.get("dl_param_bitrate")));
                }
            }
            stringBuffer.append("]");
            return stringBuffer.toString();
        } catch (Throwable th) {
            TPDLProxyLog.e(FILE_NAME, 0, ITPDLProxyLogListener.COMMON_TAG, "getExtraJsonInfo failed, error:" + th.toString());
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }

    private String getUrlHostNodesJsonInfo(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            for (int i = 0; i < arrayList.size(); i++) {
                String str = arrayList.get(i);
                if (i == arrayList.size() - 1) {
                    stringBuffer.append(String.format("\"%s\"", str));
                } else {
                    stringBuffer.append(String.format("\"%s\", ", str));
                }
            }
            stringBuffer.append("]");
            return stringBuffer.toString();
        } catch (Throwable th) {
            TPDLProxyLog.e(FILE_NAME, 0, ITPDLProxyLogListener.COMMON_TAG, "getArrayListStr failed, error:" + th.toString());
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }
}
