package okhttp3.internal.http;

import com.google.android.gms.common.internal.ImagesContract;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.ByteString;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: HttpHeaders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\u001a\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\n\u0010\r\u001a\u00020\u0004*\u00020\u0006\u001a\u001a\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0002\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\f*\u00020\u0010H\u0002\u001a\u000e\u0010\u0014\u001a\u0004\u0018\u00010\f*\u00020\u0010H\u0002\u001a\u001a\u0010\u0015\u001a\u00020\u000f*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\n\u001a\f\u0010\u001a\u001a\u00020\u0004*\u00020\u0010H\u0002\u001a\u0014\u0010\u001b\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"QUOTED_STRING_DELIMITERS", "Lokio/ByteString;", "TOKEN_DELIMITERS", "hasBody", "", "response", "Lokhttp3/Response;", "parseChallenges", "", "Lokhttp3/Challenge;", "Lokhttp3/Headers;", "headerName", "", "promisesBody", "readChallengeHeader", "", "Lokio/Buffer;", "result", "", "readQuotedString", "readToken", "receiveHeaders", "Lokhttp3/CookieJar;", ImagesContract.URL, "Lokhttp3/HttpUrl;", "headers", "skipCommasAndWhitespace", "startsWith", "prefix", "", "okhttp"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.Companion.encodeUtf8("\"\\");
    private static final ByteString TOKEN_DELIMITERS = ByteString.Companion.encodeUtf8("\t ,=");

    public static final List<Challenge> parseChallenges(Headers headers, String str) {
        Intrinsics.checkNotNullParameter(headers, "$this$parseChallenges");
        Intrinsics.checkNotNullParameter(str, "headerName");
        ArrayList arrayList = new ArrayList();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            if (StringsKt.equals(str, headers.name(i), true)) {
                try {
                    readChallengeHeader(new Buffer().writeUtf8(headers.value(i)), arrayList);
                } catch (EOFException e) {
                    Platform.Companion.get().log("Unable to parse challenge", 5, e);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x008c, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x008c, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump */
    private static final void readChallengeHeader(okio.Buffer r8, java.util.List<okhttp3.Challenge> r9) throws java.io.EOFException {
        /*
            r0 = 0
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
        L_0x0004:
            r2 = r1
        L_0x0005:
            if (r2 != 0) goto L_0x0011
            skipCommasAndWhitespace(r8)
            java.lang.String r2 = readToken(r8)
            if (r2 != 0) goto L_0x0011
            return
        L_0x0011:
            boolean r3 = skipCommasAndWhitespace(r8)
            java.lang.String r4 = readToken(r8)
            if (r4 != 0) goto L_0x002f
            boolean r8 = r8.exhausted()
            if (r8 != 0) goto L_0x0022
            return
        L_0x0022:
            okhttp3.Challenge r8 = new okhttp3.Challenge
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            r8.<init>(r2, r0)
            r9.add(r8)
            return
        L_0x002f:
            r5 = 61
            byte r5 = (byte) r5
            int r6 = okhttp3.internal.Util.skipAll(r8, r5)
            boolean r7 = skipCommasAndWhitespace(r8)
            if (r3 != 0) goto L_0x006d
            if (r7 != 0) goto L_0x0044
            boolean r3 = r8.exhausted()
            if (r3 == 0) goto L_0x006d
        L_0x0044:
            okhttp3.Challenge r3 = new okhttp3.Challenge
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            java.lang.String r4 = "="
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r4 = kotlin.text.StringsKt.repeat(r4, r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.util.Map r4 = java.util.Collections.singletonMap(r0, r4)
            java.lang.String r5 = "Collections.singletonMap…ek + \"=\".repeat(eqCount))"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r3.<init>(r2, r4)
            r9.add(r3)
            goto L_0x0004
        L_0x006d:
            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
            r3.<init>()
            java.util.Map r3 = (java.util.Map) r3
            int r7 = okhttp3.internal.Util.skipAll(r8, r5)
            int r6 = r6 + r7
        L_0x0079:
            if (r4 != 0) goto L_0x008a
            java.lang.String r4 = readToken(r8)
            boolean r6 = skipCommasAndWhitespace(r8)
            if (r6 == 0) goto L_0x0086
            goto L_0x008c
        L_0x0086:
            int r6 = okhttp3.internal.Util.skipAll(r8, r5)
        L_0x008a:
            if (r6 != 0) goto L_0x0097
        L_0x008c:
            okhttp3.Challenge r5 = new okhttp3.Challenge
            r5.<init>(r2, r3)
            r9.add(r5)
            r2 = r4
            goto L_0x0005
        L_0x0097:
            r7 = 1
            if (r6 <= r7) goto L_0x009b
            return
        L_0x009b:
            boolean r7 = skipCommasAndWhitespace(r8)
            if (r7 == 0) goto L_0x00a2
            return
        L_0x00a2:
            r7 = 34
            byte r7 = (byte) r7
            boolean r7 = startsWith(r8, r7)
            if (r7 == 0) goto L_0x00b0
            java.lang.String r7 = readQuotedString(r8)
            goto L_0x00b4
        L_0x00b0:
            java.lang.String r7 = readToken(r8)
        L_0x00b4:
            if (r7 == 0) goto L_0x00ce
            java.lang.Object r4 = r3.put(r4, r7)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x00bf
            return
        L_0x00bf:
            boolean r4 = skipCommasAndWhitespace(r8)
            if (r4 != 0) goto L_0x00cc
            boolean r4 = r8.exhausted()
            if (r4 != 0) goto L_0x00cc
            return
        L_0x00cc:
            r4 = r1
            goto L_0x0079
        L_0x00ce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.HttpHeaders.readChallengeHeader(okio.Buffer, java.util.List):void");
    }

    private static final boolean skipCommasAndWhitespace(Buffer buffer) {
        boolean z = false;
        while (!buffer.exhausted()) {
            byte b = buffer.getByte(0);
            if (b == 9 || b == 32) {
                buffer.readByte();
            } else if (b != 44) {
                break;
            } else {
                buffer.readByte();
                z = true;
            }
        }
        return z;
    }

    private static final boolean startsWith(Buffer buffer, byte b) {
        return !buffer.exhausted() && buffer.getByte(0) == b;
    }

    private static final String readQuotedString(Buffer buffer) throws EOFException {
        byte b = (byte) 34;
        if (buffer.readByte() == b) {
            Buffer buffer2 = new Buffer();
            while (true) {
                long indexOfElement = buffer.indexOfElement(QUOTED_STRING_DELIMITERS);
                if (indexOfElement == -1) {
                    return null;
                }
                if (buffer.getByte(indexOfElement) == b) {
                    buffer2.write(buffer, indexOfElement);
                    buffer.readByte();
                    return buffer2.readUtf8();
                } else if (buffer.size() == indexOfElement + 1) {
                    return null;
                } else {
                    buffer2.write(buffer, indexOfElement);
                    buffer.readByte();
                    buffer2.write(buffer, 1L);
                }
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private static final String readToken(Buffer buffer) {
        long indexOfElement = buffer.indexOfElement(TOKEN_DELIMITERS);
        if (indexOfElement == -1) {
            indexOfElement = buffer.size();
        }
        if (indexOfElement != 0) {
            return buffer.readUtf8(indexOfElement);
        }
        return null;
    }

    public static final void receiveHeaders(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        Intrinsics.checkNotNullParameter(cookieJar, "$this$receiveHeaders");
        Intrinsics.checkNotNullParameter(httpUrl, ImagesContract.URL);
        Intrinsics.checkNotNullParameter(headers, "headers");
        if (cookieJar != CookieJar.NO_COOKIES) {
            List<Cookie> parseAll = Cookie.Companion.parseAll(httpUrl, headers);
            if (!parseAll.isEmpty()) {
                cookieJar.saveFromResponse(httpUrl, parseAll);
            }
        }
    }

    public static final boolean promisesBody(Response response) {
        Intrinsics.checkNotNullParameter(response, "$this$promisesBody");
        if (Intrinsics.areEqual(response.request().method(), "HEAD")) {
            return false;
        }
        int code = response.code();
        if (((code >= 100 && code < 200) || code == 204 || code == 304) && Util.headersContentLength(response) == -1 && !StringsKt.equals("chunked", Response.header$default(response, "Transfer-Encoding", null, 2, null), true)) {
            return false;
        }
        return true;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "No longer supported", replaceWith = @ReplaceWith(expression = "response.promisesBody()", imports = {}))
    public static final boolean hasBody(Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return promisesBody(response);
    }
}
