package okhttp3;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.umeng.analytics.pro.ai;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;

/* compiled from: Cookie.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 &2\u00020\u0001:\u0002%&BO\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n¢\u0006\u0002\u0010\u000eJ\r\u0010\u0007\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0012J\u0013\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0017J\r\u0010\r\u001a\u00020\nH\u0007¢\u0006\u0002\b\u0018J\r\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0002\b\u0019J\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001dJ\r\u0010\b\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u001eJ\r\u0010\f\u001a\u00020\nH\u0007¢\u0006\u0002\b\u001fJ\r\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\b J\b\u0010!\u001a\u00020\u0003H\u0016J\u0015\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\nH\u0000¢\u0006\u0002\b#J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b$R\u0013\u0010\u0007\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0013\u0010\u0005\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0013\u0010\r\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0011R\u0013\u0010\u000b\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0011R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000fR\u0013\u0010\b\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u0013\u0010\f\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0011R\u0013\u0010\t\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000f¨\u0006'"}, d2 = {"Lokhttp3/Cookie;", "", "name", "", "value", "expiresAt", "", "domain", "path", "secure", "", "httpOnly", "persistent", "hostOnly", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZ)V", "()Ljava/lang/String;", "()J", "()Z", "-deprecated_domain", "equals", "other", "-deprecated_expiresAt", "hashCode", "", "-deprecated_hostOnly", "-deprecated_httpOnly", "matches", ImagesContract.URL, "Lokhttp3/HttpUrl;", "-deprecated_name", "-deprecated_path", "-deprecated_persistent", "-deprecated_secure", "toString", "forObsoleteRfc2965", "toString$okhttp", "-deprecated_value", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes2.dex */
public final class Cookie {
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;
    public static final Companion Companion = new Companion(null);
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    @JvmStatic
    public static final Cookie parse(HttpUrl httpUrl, String str) {
        return Companion.parse(httpUrl, str);
    }

    @JvmStatic
    public static final List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
        return Companion.parseAll(httpUrl, headers);
    }

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.name = str;
        this.value = str2;
        this.expiresAt = j;
        this.domain = str3;
        this.path = str4;
        this.secure = z;
        this.httpOnly = z2;
        this.persistent = z3;
        this.hostOnly = z4;
    }

    public /* synthetic */ Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, j, str3, str4, z, z2, z3, z4);
    }

    public final String name() {
        return this.name;
    }

    public final String value() {
        return this.value;
    }

    public final long expiresAt() {
        return this.expiresAt;
    }

    public final String domain() {
        return this.domain;
    }

    public final String path() {
        return this.path;
    }

    public final boolean secure() {
        return this.secure;
    }

    public final boolean httpOnly() {
        return this.httpOnly;
    }

    public final boolean persistent() {
        return this.persistent;
    }

    public final boolean hostOnly() {
        return this.hostOnly;
    }

    public final boolean matches(HttpUrl httpUrl) {
        boolean z;
        Intrinsics.checkNotNullParameter(httpUrl, ImagesContract.URL);
        if (this.hostOnly) {
            z = Intrinsics.areEqual(httpUrl.host(), this.domain);
        } else {
            z = Companion.domainMatch(httpUrl.host(), this.domain);
        }
        if (!z || !Companion.pathMatch(httpUrl, this.path)) {
            return false;
        }
        if (!this.secure || httpUrl.isHttps()) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Cookie) {
            Cookie cookie = (Cookie) obj;
            if (Intrinsics.areEqual(cookie.name, this.name) && Intrinsics.areEqual(cookie.value, this.value) && cookie.expiresAt == this.expiresAt && Intrinsics.areEqual(cookie.domain, this.domain) && Intrinsics.areEqual(cookie.path, this.path) && cookie.secure == this.secure && cookie.httpOnly == this.httpOnly && cookie.persistent == this.persistent && cookie.hostOnly == this.hostOnly) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((((527 + this.name.hashCode()) * 31) + this.value.hashCode()) * 31) + Long.valueOf(this.expiresAt).hashCode()) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31) + Boolean.valueOf(this.secure).hashCode()) * 31) + Boolean.valueOf(this.httpOnly).hashCode()) * 31) + Boolean.valueOf(this.persistent).hashCode()) * 31) + Boolean.valueOf(this.hostOnly).hashCode();
    }

    public String toString() {
        return toString$okhttp(false);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "name", imports = {}))
    /* renamed from: -deprecated_name  reason: not valid java name */
    public final String m1567deprecated_name() {
        return this.name;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "value", imports = {}))
    /* renamed from: -deprecated_value  reason: not valid java name */
    public final String m1571deprecated_value() {
        return this.value;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "persistent", imports = {}))
    /* renamed from: -deprecated_persistent  reason: not valid java name */
    public final boolean m1569deprecated_persistent() {
        return this.persistent;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "expiresAt", imports = {}))
    /* renamed from: -deprecated_expiresAt  reason: not valid java name */
    public final long m1564deprecated_expiresAt() {
        return this.expiresAt;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hostOnly", imports = {}))
    /* renamed from: -deprecated_hostOnly  reason: not valid java name */
    public final boolean m1565deprecated_hostOnly() {
        return this.hostOnly;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "domain", imports = {}))
    /* renamed from: -deprecated_domain  reason: not valid java name */
    public final String m1563deprecated_domain() {
        return this.domain;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "path", imports = {}))
    /* renamed from: -deprecated_path  reason: not valid java name */
    public final String m1568deprecated_path() {
        return this.path;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "httpOnly", imports = {}))
    /* renamed from: -deprecated_httpOnly  reason: not valid java name */
    public final boolean m1566deprecated_httpOnly() {
        return this.httpOnly;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "secure", imports = {}))
    /* renamed from: -deprecated_secure  reason: not valid java name */
    public final boolean m1570deprecated_secure() {
        return this.secure;
    }

    public final String toString$okhttp(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('=');
        sb.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(DatesKt.toHttpDateString(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.domain);
        }
        sb.append("; path=");
        sb.append(this.path);
        if (this.secure) {
            sb.append("; secure");
        }
        if (this.httpOnly) {
            sb.append("; httponly");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString()");
        return sb2;
    }

    /* compiled from: Cookie.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0018\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0000J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0000J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/Cookie$Builder;", "", "()V", "domain", "", "expiresAt", "", "hostOnly", "", "httpOnly", "name", "path", "persistent", "secure", "value", "build", "Lokhttp3/Cookie;", "hostOnlyDomain", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes2.dex */
    public static final class Builder {
        private String domain;
        private boolean hostOnly;
        private boolean httpOnly;
        private String name;
        private boolean persistent;
        private boolean secure;
        private String value;
        private long expiresAt = DatesKt.MAX_DATE;
        private String path = "/";

        public final Builder name(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            Builder builder = this;
            if (Intrinsics.areEqual(StringsKt.trim((CharSequence) str).toString(), str)) {
                builder.name = str;
                return builder;
            }
            throw new IllegalArgumentException("name is not trimmed".toString());
        }

        public final Builder value(String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            Builder builder = this;
            if (Intrinsics.areEqual(StringsKt.trim((CharSequence) str).toString(), str)) {
                builder.value = str;
                return builder;
            }
            throw new IllegalArgumentException("value is not trimmed".toString());
        }

        public final Builder expiresAt(long j) {
            Builder builder = this;
            if (j <= 0) {
                j = Long.MIN_VALUE;
            }
            if (j > DatesKt.MAX_DATE) {
                j = 253402300799999L;
            }
            builder.expiresAt = j;
            builder.persistent = true;
            return builder;
        }

        public final Builder domain(String str) {
            Intrinsics.checkNotNullParameter(str, "domain");
            return domain(str, false);
        }

        public final Builder hostOnlyDomain(String str) {
            Intrinsics.checkNotNullParameter(str, "domain");
            return domain(str, true);
        }

        private final Builder domain(String str, boolean z) {
            Builder builder = this;
            String canonicalHost = HostnamesKt.toCanonicalHost(str);
            if (canonicalHost != null) {
                builder.domain = canonicalHost;
                builder.hostOnly = z;
                return builder;
            }
            throw new IllegalArgumentException("unexpected domain: " + str);
        }

        public final Builder path(String str) {
            Intrinsics.checkNotNullParameter(str, "path");
            Builder builder = this;
            if (StringsKt.startsWith$default(str, "/", false, 2, (Object) null)) {
                builder.path = str;
                return builder;
            }
            throw new IllegalArgumentException("path must start with '/'".toString());
        }

        public final Builder secure() {
            Builder builder = this;
            builder.secure = true;
            return builder;
        }

        public final Builder httpOnly() {
            Builder builder = this;
            builder.httpOnly = true;
            return builder;
        }

        public final Cookie build() {
            String str = this.name;
            if (str != null) {
                String str2 = this.value;
                if (str2 != null) {
                    long j = this.expiresAt;
                    String str3 = this.domain;
                    if (str3 != null) {
                        return new Cookie(str, str2, j, str3, this.path, this.secure, this.httpOnly, this.persistent, this.hostOnly, null);
                    }
                    throw new NullPointerException("builder.domain == null");
                }
                throw new NullPointerException("builder.value == null");
            }
            throw new NullPointerException("builder.name == null");
        }
    }

    /* compiled from: Cookie.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0002J'\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0000¢\u0006\u0002\b\u001bJ\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0007J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0002J \u0010\"\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\fH\u0002J\u0018\u0010$\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lokhttp3/Cookie$Companion;", "", "()V", "DAY_OF_MONTH_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MONTH_PATTERN", "TIME_PATTERN", "YEAR_PATTERN", "dateCharacterOffset", "", "input", "", "pos", "limit", "invert", "", "domainMatch", "urlHost", "domain", "parse", "Lokhttp3/Cookie;", "currentTimeMillis", "", ImagesContract.URL, "Lokhttp3/HttpUrl;", "setCookie", "parse$okhttp", "parseAll", "", "headers", "Lokhttp3/Headers;", "parseDomain", ai.az, "parseExpires", "parseMaxAge", "pathMatch", "path", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean domainMatch(String str, String str2) {
            if (Intrinsics.areEqual(str, str2)) {
                return true;
            }
            return StringsKt.endsWith$default(str, str2, false, 2, (Object) null) && str.charAt((str.length() - str2.length()) - 1) == '.' && !Util.canParseAsIpAddress(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean pathMatch(HttpUrl httpUrl, String str) {
            String encodedPath = httpUrl.encodedPath();
            if (Intrinsics.areEqual(encodedPath, str)) {
                return true;
            }
            return StringsKt.startsWith$default(encodedPath, str, false, 2, (Object) null) && (StringsKt.endsWith$default(str, "/", false, 2, (Object) null) || encodedPath.charAt(str.length()) == '/');
        }

        @JvmStatic
        public final Cookie parse(HttpUrl httpUrl, String str) {
            Intrinsics.checkNotNullParameter(httpUrl, ImagesContract.URL);
            Intrinsics.checkNotNullParameter(str, "setCookie");
            return parse$okhttp(System.currentTimeMillis(), httpUrl, str);
        }

        /* JADX WARN: Removed duplicated region for block: B:59:0x0124  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x016d  */
        /* Code decompiled incorrectly, please refer to instructions dump */
        public final okhttp3.Cookie parse$okhttp(long r25, okhttp3.HttpUrl r27, java.lang.String r28) {
            /*
            // Method dump skipped, instructions count: 406
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cookie.Companion.parse$okhttp(long, okhttp3.HttpUrl, java.lang.String):okhttp3.Cookie");
        }

        private final long parseExpires(String str, int i, int i2) {
            Companion companion = this;
            int dateCharacterOffset = companion.dateCharacterOffset(str, i, i2, false);
            Matcher matcher = Cookie.TIME_PATTERN.matcher(str);
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            int i6 = -1;
            int i7 = -1;
            int i8 = -1;
            while (dateCharacterOffset < i2) {
                int dateCharacterOffset2 = companion.dateCharacterOffset(str, dateCharacterOffset + 1, i2, true);
                matcher.region(dateCharacterOffset, dateCharacterOffset2);
                if (i4 == -1 && matcher.usePattern(Cookie.TIME_PATTERN).matches()) {
                    String group = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(group, "matcher.group(1)");
                    int parseInt = Integer.parseInt(group);
                    String group2 = matcher.group(2);
                    Intrinsics.checkNotNullExpressionValue(group2, "matcher.group(2)");
                    int parseInt2 = Integer.parseInt(group2);
                    String group3 = matcher.group(3);
                    Intrinsics.checkNotNullExpressionValue(group3, "matcher.group(3)");
                    i8 = Integer.parseInt(group3);
                    i7 = parseInt2;
                    i4 = parseInt;
                } else if (i5 == -1 && matcher.usePattern(Cookie.DAY_OF_MONTH_PATTERN).matches()) {
                    String group4 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(group4, "matcher.group(1)");
                    i5 = Integer.parseInt(group4);
                } else if (i6 == -1 && matcher.usePattern(Cookie.MONTH_PATTERN).matches()) {
                    String group5 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(group5, "matcher.group(1)");
                    Locale locale = Locale.US;
                    Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
                    if (group5 != null) {
                        String lowerCase = group5.toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                        String pattern = Cookie.MONTH_PATTERN.pattern();
                        Intrinsics.checkNotNullExpressionValue(pattern, "MONTH_PATTERN.pattern()");
                        i6 = StringsKt.indexOf$default((CharSequence) pattern, lowerCase, 0, false, 6, (Object) null) / 4;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                } else if (i3 == -1 && matcher.usePattern(Cookie.YEAR_PATTERN).matches()) {
                    String group6 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(group6, "matcher.group(1)");
                    i3 = Integer.parseInt(group6);
                }
                dateCharacterOffset = companion.dateCharacterOffset(str, dateCharacterOffset2 + 1, i2, false);
            }
            if (70 <= i3 && 99 >= i3) {
                i3 += 1900;
            }
            if (i3 >= 0 && 69 >= i3) {
                i3 += 2000;
            }
            if (i3 >= 1601) {
                if (i6 != -1) {
                    if (1 <= i5 && 31 >= i5) {
                        if (i4 >= 0 && 23 >= i4) {
                            if (i7 >= 0 && 59 >= i7) {
                                if (i8 >= 0 && 59 >= i8) {
                                    GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
                                    gregorianCalendar.setLenient(false);
                                    gregorianCalendar.set(1, i3);
                                    gregorianCalendar.set(2, i6 - 1);
                                    gregorianCalendar.set(5, i5);
                                    gregorianCalendar.set(11, i4);
                                    gregorianCalendar.set(12, i7);
                                    gregorianCalendar.set(13, i8);
                                    gregorianCalendar.set(14, 0);
                                    return gregorianCalendar.getTimeInMillis();
                                }
                                throw new IllegalArgumentException("Failed requirement.".toString());
                            }
                            throw new IllegalArgumentException("Failed requirement.".toString());
                        }
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        private final int dateCharacterOffset(String str, int i, int i2, boolean z) {
            while (i < i2) {
                char charAt = str.charAt(i);
                boolean z2 = true;
                if ((charAt >= ' ' || charAt == '\t') && charAt < 127 && (('0' > charAt || '9' < charAt) && (('a' > charAt || 'z' < charAt) && (('A' > charAt || 'Z' < charAt) && charAt != ':')))) {
                    z2 = false;
                }
                if (z2 == (!z)) {
                    return i;
                }
                i++;
            }
            return i2;
        }

        private final long parseMaxAge(String str) {
            try {
                long parseLong = Long.parseLong(str);
                if (parseLong <= 0) {
                    return Long.MIN_VALUE;
                }
                return parseLong;
            } catch (NumberFormatException e) {
                if (!new Regex("-?\\d+").matches(str)) {
                    throw e;
                } else if (StringsKt.startsWith$default(str, "-", false, 2, (Object) null)) {
                    return Long.MIN_VALUE;
                } else {
                    return Long.MAX_VALUE;
                }
            }
        }

        private final String parseDomain(String str) {
            if (!StringsKt.endsWith$default(str, ".", false, 2, (Object) null)) {
                String canonicalHost = HostnamesKt.toCanonicalHost(StringsKt.removePrefix(str, (CharSequence) "."));
                if (canonicalHost != null) {
                    return canonicalHost;
                }
                throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        @JvmStatic
        public final List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
            Intrinsics.checkNotNullParameter(httpUrl, ImagesContract.URL);
            Intrinsics.checkNotNullParameter(headers, "headers");
            List<String> values = headers.values(HttpHeaders.SET_COOKIE);
            ArrayList arrayList = null;
            int size = values.size();
            for (int i = 0; i < size; i++) {
                Cookie parse = parse(httpUrl, values.get(i));
                if (parse != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(parse);
                }
            }
            if (arrayList == null) {
                return CollectionsKt.emptyList();
            }
            List<Cookie> unmodifiableList = Collections.unmodifiableList(arrayList);
            Intrinsics.checkNotNullExpressionValue(unmodifiableList, "Collections.unmodifiableList(cookies)");
            return unmodifiableList;
        }
    }
}
