package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public class FontRequestWorker {
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
    static final Object LOCK = new Object();
    static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = new SimpleArrayMap<>();

    private FontRequestWorker() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void resetTypefaceCache() {
        sTypefaceCache.evictAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface requestFontSync(final Context context, final FontRequest fontRequest, CallbackWithHandler callbackWithHandler, final int i, int i2) {
        final String createCacheId = createCacheId(fontRequest, i);
        Typeface typeface = sTypefaceCache.get(createCacheId);
        if (typeface != null) {
            callbackWithHandler.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        } else if (i2 == -1) {
            TypefaceResult fontSync = getFontSync(createCacheId, context, fontRequest, i);
            callbackWithHandler.onTypefaceResult(fontSync);
            return fontSync.mTypeface;
        } else {
            try {
                TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public TypefaceResult call() {
                        return FontRequestWorker.getFontSync(createCacheId, context, fontRequest, i);
                    }
                }, i2);
                callbackWithHandler.onTypefaceResult(typefaceResult);
                return typefaceResult.mTypeface;
            } catch (InterruptedException unused) {
                callbackWithHandler.onTypefaceResult(new TypefaceResult(-3));
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface requestFontAsync(final Context context, final FontRequest fontRequest, final int i, Executor executor, final CallbackWithHandler callbackWithHandler) {
        final String createCacheId = createCacheId(fontRequest, i);
        Typeface typeface = sTypefaceCache.get(createCacheId);
        if (typeface != null) {
            callbackWithHandler.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        }
        AnonymousClass2 r1 = new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.2
            public void accept(TypefaceResult typefaceResult) {
                CallbackWithHandler.this.onTypefaceResult(typefaceResult);
            }
        };
        synchronized (LOCK) {
            ArrayList<Consumer<TypefaceResult>> arrayList = PENDING_REPLIES.get(createCacheId);
            if (arrayList != null) {
                arrayList.add(r1);
                return null;
            }
            ArrayList<Consumer<TypefaceResult>> arrayList2 = new ArrayList<>();
            arrayList2.add(r1);
            PENDING_REPLIES.put(createCacheId, arrayList2);
            AnonymousClass3 r8 = new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public TypefaceResult call() {
                    return FontRequestWorker.getFontSync(createCacheId, context, fontRequest, i);
                }
            };
            if (executor == null) {
                executor = DEFAULT_EXECUTOR_SERVICE;
            }
            RequestExecutor.execute(executor, r8, new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.4
                public void accept(TypefaceResult typefaceResult) {
                    synchronized (FontRequestWorker.LOCK) {
                        ArrayList<Consumer<TypefaceResult>> arrayList3 = FontRequestWorker.PENDING_REPLIES.get(createCacheId);
                        if (arrayList3 != null) {
                            FontRequestWorker.PENDING_REPLIES.remove(createCacheId);
                            for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                                arrayList3.get(i2).accept(typefaceResult);
                            }
                        }
                    }
                }
            });
            return null;
        }
    }

    private static String createCacheId(FontRequest fontRequest, int i) {
        return fontRequest.getId() + "-" + i;
    }

    static TypefaceResult getFontSync(String str, Context context, FontRequest fontRequest, int i) {
        Typeface typeface = sTypefaceCache.get(str);
        if (typeface != null) {
            return new TypefaceResult(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult fontFamilyResult = FontProvider.getFontFamilyResult(context, fontRequest, null);
            int fontFamilyResultStatus = getFontFamilyResultStatus(fontFamilyResult);
            if (fontFamilyResultStatus != 0) {
                return new TypefaceResult(fontFamilyResultStatus);
            }
            Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fontFamilyResult.getFonts(), i);
            if (createFromFontInfo == null) {
                return new TypefaceResult(-3);
            }
            sTypefaceCache.put(str, createFromFontInfo);
            return new TypefaceResult(createFromFontInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(-1);
        }
    }

    private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i = 1;
        if (fontFamilyResult.getStatusCode() == 0) {
            FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
            if (!(fonts == null || fonts.length == 0)) {
                i = 0;
                for (FontsContractCompat.FontInfo fontInfo : fonts) {
                    int resultCode = fontInfo.getResultCode();
                    if (resultCode != 0) {
                        if (resultCode < 0) {
                            return -3;
                        } else {
                            return resultCode;
                        }
                    }
                }
            }
            return i;
        } else if (fontFamilyResult.getStatusCode() != 1) {
            return -3;
        } else {
            return -2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
    public static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(int i) {
            this.mTypeface = null;
            this.mResult = i;
        }

        TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isSuccess() {
            return this.mResult == 0;
        }
    }
}
