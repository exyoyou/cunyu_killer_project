package ren.yale.android.cachewebviewlib;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ren.yale.android.cachewebviewlib.config.CacheExtensionConfig;
import ren.yale.android.cachewebviewlib.utils.FileUtil;
import ren.yale.android.cachewebviewlib.utils.MimeTypeMapUtils;
import ren.yale.android.cachewebviewlib.utils.NetUtils;
import ren.yale.android.cachewebviewlib.utils.OKHttpFile;

/* loaded from: classes5.dex */
public class WebViewCacheInterceptor implements WebViewRequestInterceptor {
    public static final String KEY_CACHE = "WebResourceInterceptor-Key-Cache";
    private String mAssetsDir;
    private CacheExtensionConfig mCacheExtensionConfig;
    private File mCacheFile;
    private long mCacheSize;
    private CacheType mCacheType;
    private long mConnectTimeout;
    private Context mContext;
    private boolean mDebug;
    private Dns mDns;
    private boolean mIsSuffixMod;
    private long mReadTimeout;
    private ResourceInterceptor mResourceInterceptor;
    private SSLSocketFactory mSSLSocketFactory;
    private boolean mTrustAllHostname;
    private X509TrustManager mX509TrustManager;
    private OkHttpClient mHttpClient = null;
    private String mOrigin = "";
    private String mReferer = "";
    private String mUserAgent = "";

    public WebViewCacheInterceptor(Builder builder) {
        this.mAssetsDir = null;
        this.mTrustAllHostname = false;
        this.mSSLSocketFactory = null;
        this.mX509TrustManager = null;
        this.mDns = null;
        this.mIsSuffixMod = false;
        this.mCacheExtensionConfig = builder.mCacheExtensionConfig;
        this.mCacheFile = builder.mCacheFile;
        this.mCacheSize = builder.mCacheSize;
        this.mCacheType = builder.mCacheType;
        this.mConnectTimeout = builder.mConnectTimeout;
        this.mReadTimeout = builder.mReadTimeout;
        this.mContext = builder.mContext;
        this.mDebug = builder.mDebug;
        this.mAssetsDir = builder.mAssetsDir;
        this.mX509TrustManager = builder.mX509TrustManager;
        this.mSSLSocketFactory = builder.mSSLSocketFactory;
        this.mTrustAllHostname = builder.mTrustAllHostname;
        this.mResourceInterceptor = builder.mResourceInterceptor;
        this.mIsSuffixMod = builder.mIsSuffixMod;
        this.mDns = builder.mDns;
        initHttpClient();
        if (isEnableAssets()) {
            initAssetsLoader();
        }
    }

    private boolean isEnableAssets() {
        return this.mAssetsDir != null;
    }

    private void initAssetsLoader() {
        AssetsLoader.getInstance().init(this.mContext).setDir(this.mAssetsDir).isAssetsSuffixMod(this.mIsSuffixMod);
    }

    private void initHttpClient() {
        X509TrustManager x509TrustManager;
        OkHttpClient.Builder addNetworkInterceptor = new OkHttpClient.Builder().cache(new Cache(this.mCacheFile, this.mCacheSize)).connectTimeout(this.mConnectTimeout, TimeUnit.SECONDS).readTimeout(this.mReadTimeout, TimeUnit.SECONDS).addNetworkInterceptor(new HttpCacheInterceptor());
        if (this.mTrustAllHostname) {
            addNetworkInterceptor.hostnameVerifier(new HostnameVerifier() { // from class: ren.yale.android.cachewebviewlib.WebViewCacheInterceptor.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
        }
        SSLSocketFactory sSLSocketFactory = this.mSSLSocketFactory;
        if (!(sSLSocketFactory == null || (x509TrustManager = this.mX509TrustManager) == null)) {
            addNetworkInterceptor.sslSocketFactory(sSLSocketFactory, x509TrustManager);
        }
        Dns dns = this.mDns;
        if (dns != null) {
            addNetworkInterceptor.dns(dns);
        }
        this.mHttpClient = addNetworkInterceptor.build();
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public WebResourceResponse interceptRequest(WebResourceRequest webResourceRequest) {
        return interceptRequest(webResourceRequest.getUrl().toString(), webResourceRequest.getRequestHeaders());
    }

    private Map<String, String> buildHeaders() {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(this.mOrigin)) {
            hashMap.put("Origin", this.mOrigin);
        }
        if (!TextUtils.isEmpty(this.mReferer)) {
            hashMap.put("Referer", this.mReferer);
        }
        if (!TextUtils.isEmpty(this.mUserAgent)) {
            hashMap.put("User-Agent", this.mUserAgent);
        }
        return hashMap;
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public WebResourceResponse interceptRequest(String str) {
        return interceptRequest(str, buildHeaders());
    }

    private boolean checkUrl(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("http")) {
            return false;
        }
        ResourceInterceptor resourceInterceptor = this.mResourceInterceptor;
        if (resourceInterceptor != null && !resourceInterceptor.interceptor(str)) {
            return false;
        }
        String fileExtensionFromUrl = MimeTypeMapUtils.getFileExtensionFromUrl(str);
        if (!TextUtils.isEmpty(fileExtensionFromUrl) && !this.mCacheExtensionConfig.isMedia(fileExtensionFromUrl) && this.mCacheExtensionConfig.canCache(fileExtensionFromUrl)) {
            return true;
        }
        return false;
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public void loadUrl(WebView webView, String str) {
        if (isValidUrl(str)) {
            webView.loadUrl(str);
            this.mReferer = webView.getUrl();
            this.mOrigin = NetUtils.getOriginUrl(this.mReferer);
            this.mUserAgent = webView.getSettings().getUserAgentString();
        }
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public void loadUrl(String str, String str2) {
        if (isValidUrl(str)) {
            this.mReferer = str;
            this.mOrigin = NetUtils.getOriginUrl(this.mReferer);
            this.mUserAgent = str2;
        }
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public void loadUrl(String str, Map<String, String> map, String str2) {
        if (isValidUrl(str)) {
            this.mReferer = str;
            this.mOrigin = NetUtils.getOriginUrl(this.mReferer);
            this.mUserAgent = str2;
        }
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public void loadUrl(WebView webView, String str, Map<String, String> map) {
        if (isValidUrl(str)) {
            webView.loadUrl(str, map);
            this.mReferer = webView.getUrl();
            this.mOrigin = NetUtils.getOriginUrl(this.mReferer);
            this.mUserAgent = webView.getSettings().getUserAgentString();
        }
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public void clearCache() {
        FileUtil.deleteDirs(this.mCacheFile.getAbsolutePath(), false);
        AssetsLoader.getInstance().clear();
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public void enableForce(boolean z) {
        if (z) {
            this.mCacheType = CacheType.FORCE;
        } else {
            this.mCacheType = CacheType.NORMAL;
        }
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public InputStream getCacheFile(String str) {
        return OKHttpFile.getCacheFile(this.mCacheFile, str);
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public void initAssetsData() {
        AssetsLoader.getInstance().initData();
    }

    @Override // ren.yale.android.cachewebviewlib.WebViewRequestInterceptor
    public File getCachePath() {
        return this.mCacheFile;
    }

    public void addHeader(Request.Builder builder, Map<String, String> map) {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    private WebResourceResponse interceptRequest(String str, Map<String, String> map) {
        InputStream resByUrl;
        if (this.mCacheType == CacheType.NORMAL || !checkUrl(str)) {
            return null;
        }
        if (!isEnableAssets() || (resByUrl = AssetsLoader.getInstance().getResByUrl(str)) == null) {
            try {
                Request.Builder url = new Request.Builder().url(str);
                if (this.mCacheExtensionConfig.isHtml(MimeTypeMapUtils.getFileExtensionFromUrl(str))) {
                    map.put("WebResourceInterceptor-Key-Cache", this.mCacheType.ordinal() + "");
                }
                addHeader(url, map);
                if (!NetUtils.isConnected(this.mContext)) {
                    url.cacheControl(CacheControl.FORCE_CACHE);
                }
                Response execute = this.mHttpClient.newCall(url.build()).execute();
                if (execute.cacheResponse() != null) {
                    CacheWebViewLog.d(String.format("from cache: %s", str), this.mDebug);
                } else {
                    CacheWebViewLog.d(String.format("from server: %s", str), this.mDebug);
                }
                WebResourceResponse webResourceResponse = new WebResourceResponse(MimeTypeMapUtils.getMimeTypeFromUrl(str), "", execute.body().byteStream());
                if (execute.code() == 504 && !NetUtils.isConnected(this.mContext)) {
                    return null;
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    String message = execute.message();
                    if (TextUtils.isEmpty(message)) {
                        message = "OK";
                    }
                    try {
                        webResourceResponse.setStatusCodeAndReasonPhrase(execute.code(), message);
                        webResourceResponse.setResponseHeaders(NetUtils.multimapToSingle(execute.headers().toMultimap()));
                    } catch (Exception unused) {
                        return null;
                    }
                }
                return webResourceResponse;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            CacheWebViewLog.d(String.format("from assets: %s", str), this.mDebug);
            return new WebResourceResponse(MimeTypeMapUtils.getMimeTypeFromUrl(str), "", resByUrl);
        }
    }

    /* loaded from: classes5.dex */
    public static class Builder {
        private File mCacheFile;
        private Context mContext;
        private ResourceInterceptor mResourceInterceptor;
        private long mCacheSize = 104857600;
        private long mConnectTimeout = 20;
        private long mReadTimeout = 20;
        private boolean mDebug = true;
        private CacheType mCacheType = CacheType.FORCE;
        private boolean mTrustAllHostname = false;
        private SSLSocketFactory mSSLSocketFactory = null;
        private X509TrustManager mX509TrustManager = null;
        private String mAssetsDir = null;
        private boolean mIsSuffixMod = false;
        private Dns mDns = null;
        private CacheExtensionConfig mCacheExtensionConfig = new CacheExtensionConfig();

        public Builder(Context context) {
            this.mContext = context;
            this.mCacheFile = new File(context.getCacheDir().toString(), "CacheWebViewCache");
        }

        public void setResourceInterceptor(ResourceInterceptor resourceInterceptor) {
            this.mResourceInterceptor = resourceInterceptor;
        }

        public Builder setTrustAllHostname() {
            this.mTrustAllHostname = true;
            return this;
        }

        public Builder setSSLSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (!(sSLSocketFactory == null || x509TrustManager == null)) {
                this.mSSLSocketFactory = sSLSocketFactory;
                this.mX509TrustManager = x509TrustManager;
            }
            return this;
        }

        public Builder setCachePath(File file) {
            if (file != null) {
                this.mCacheFile = file;
            }
            return this;
        }

        public Builder setCacheSize(long j) {
            if (j > 1024) {
                this.mCacheSize = j;
            }
            return this;
        }

        public Builder setReadTimeoutSecond(long j) {
            if (j >= 0) {
                this.mReadTimeout = j;
            }
            return this;
        }

        public Builder setConnectTimeoutSecond(long j) {
            if (j >= 0) {
                this.mConnectTimeout = j;
            }
            return this;
        }

        public Builder setCacheExtensionConfig(CacheExtensionConfig cacheExtensionConfig) {
            if (cacheExtensionConfig != null) {
                this.mCacheExtensionConfig = cacheExtensionConfig;
            }
            return this;
        }

        public Builder setDebug(boolean z) {
            this.mDebug = z;
            return this;
        }

        public Builder setCacheType(CacheType cacheType) {
            this.mCacheType = cacheType;
            return this;
        }

        public Builder isAssetsSuffixMod(boolean z) {
            this.mIsSuffixMod = z;
            return this;
        }

        public Builder setAssetsDir(String str) {
            if (str != null) {
                this.mAssetsDir = str;
            }
            return this;
        }

        public void setDns(Dns dns) {
            this.mDns = dns;
        }

        public WebViewRequestInterceptor build() {
            return new WebViewCacheInterceptor(this);
        }
    }

    boolean isValidUrl(String str) {
        return URLUtil.isValidUrl(str);
    }
}
