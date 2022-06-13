package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

/* loaded from: classes4.dex */
public class DataCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final List<Key> cacheKeys;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int sourceIdIndex;
    private Key sourceKey;

    public DataCacheGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this(decodeHelper.getCacheKeys(), decodeHelper, fetcherReadyCallback);
    }

    public DataCacheGenerator(List<Key> list, DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.sourceIdIndex = -1;
        this.cacheKeys = list;
        this.helper = decodeHelper;
        this.cb = fetcherReadyCallback;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (hasNextModelLoader() == false) goto L_0x0068;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        r0 = r7.modelLoaders;
        r3 = r7.modelLoaderIndex;
        r7.modelLoaderIndex = r3 + 1;
        r7.loadData = r0.get(r3).buildLoadData(r7.cacheFile, r7.helper.getWidth(), r7.helper.getHeight(), r7.helper.getOptions());
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0047, code lost:
        if (r7.loadData == null) goto L_0x0015;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0057, code lost:
        if (r7.helper.hasLoadPath(r7.loadData.fetcher.getDataClass()) == false) goto L_0x0015;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
        r7.loadData.fetcher.loadData(r7.helper.getPriority(), r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0066, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006b, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0012, code lost:
        r7.loadData = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r1 != false) goto L_0x0068;
     */
    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    /* Code decompiled incorrectly, please refer to instructions dump */
    public boolean startNext() {
        /*
            r7 = this;
            java.lang.String r0 = "DataCacheGenerator.startNext"
            com.bumptech.glide.util.pool.GlideTrace.beginSection(r0)
        L_0x0005:
            java.util.List<com.bumptech.glide.load.model.ModelLoader<java.io.File, ?>> r0 = r7.modelLoaders     // Catch: all -> 0x00b4
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x006c
            boolean r0 = r7.hasNextModelLoader()     // Catch: all -> 0x00b4
            if (r0 != 0) goto L_0x0012
            goto L_0x006c
        L_0x0012:
            r0 = 0
            r7.loadData = r0     // Catch: all -> 0x00b4
        L_0x0015:
            if (r1 != 0) goto L_0x0068
            boolean r0 = r7.hasNextModelLoader()     // Catch: all -> 0x00b4
            if (r0 == 0) goto L_0x0068
            java.util.List<com.bumptech.glide.load.model.ModelLoader<java.io.File, ?>> r0 = r7.modelLoaders     // Catch: all -> 0x00b4
            int r3 = r7.modelLoaderIndex     // Catch: all -> 0x00b4
            int r4 = r3 + 1
            r7.modelLoaderIndex = r4     // Catch: all -> 0x00b4
            java.lang.Object r0 = r0.get(r3)     // Catch: all -> 0x00b4
            com.bumptech.glide.load.model.ModelLoader r0 = (com.bumptech.glide.load.model.ModelLoader) r0     // Catch: all -> 0x00b4
            java.io.File r3 = r7.cacheFile     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DecodeHelper<?> r4 = r7.helper     // Catch: all -> 0x00b4
            int r4 = r4.getWidth()     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DecodeHelper<?> r5 = r7.helper     // Catch: all -> 0x00b4
            int r5 = r5.getHeight()     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DecodeHelper<?> r6 = r7.helper     // Catch: all -> 0x00b4
            com.bumptech.glide.load.Options r6 = r6.getOptions()     // Catch: all -> 0x00b4
            com.bumptech.glide.load.model.ModelLoader$LoadData r0 = r0.buildLoadData(r3, r4, r5, r6)     // Catch: all -> 0x00b4
            r7.loadData = r0     // Catch: all -> 0x00b4
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r7.loadData     // Catch: all -> 0x00b4
            if (r0 == 0) goto L_0x0015
            com.bumptech.glide.load.engine.DecodeHelper<?> r0 = r7.helper     // Catch: all -> 0x00b4
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r3 = r7.loadData     // Catch: all -> 0x00b4
            com.bumptech.glide.load.data.DataFetcher<Data> r3 = r3.fetcher     // Catch: all -> 0x00b4
            java.lang.Class r3 = r3.getDataClass()     // Catch: all -> 0x00b4
            boolean r0 = r0.hasLoadPath(r3)     // Catch: all -> 0x00b4
            if (r0 == 0) goto L_0x0015
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r7.loadData     // Catch: all -> 0x00b4
            com.bumptech.glide.load.data.DataFetcher<Data> r0 = r0.fetcher     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DecodeHelper<?> r1 = r7.helper     // Catch: all -> 0x00b4
            com.bumptech.glide.Priority r1 = r1.getPriority()     // Catch: all -> 0x00b4
            r0.loadData(r1, r7)     // Catch: all -> 0x00b4
            r1 = r2
            goto L_0x0015
        L_0x0068:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r1
        L_0x006c:
            int r0 = r7.sourceIdIndex     // Catch: all -> 0x00b4
            int r0 = r0 + r2
            r7.sourceIdIndex = r0     // Catch: all -> 0x00b4
            int r0 = r7.sourceIdIndex     // Catch: all -> 0x00b4
            java.util.List<com.bumptech.glide.load.Key> r2 = r7.cacheKeys     // Catch: all -> 0x00b4
            int r2 = r2.size()     // Catch: all -> 0x00b4
            if (r0 < r2) goto L_0x007f
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r1
        L_0x007f:
            java.util.List<com.bumptech.glide.load.Key> r0 = r7.cacheKeys     // Catch: all -> 0x00b4
            int r2 = r7.sourceIdIndex     // Catch: all -> 0x00b4
            java.lang.Object r0 = r0.get(r2)     // Catch: all -> 0x00b4
            com.bumptech.glide.load.Key r0 = (com.bumptech.glide.load.Key) r0     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DataCacheKey r2 = new com.bumptech.glide.load.engine.DataCacheKey     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DecodeHelper<?> r3 = r7.helper     // Catch: all -> 0x00b4
            com.bumptech.glide.load.Key r3 = r3.getSignature()     // Catch: all -> 0x00b4
            r2.<init>(r0, r3)     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DecodeHelper<?> r3 = r7.helper     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.cache.DiskCache r3 = r3.getDiskCache()     // Catch: all -> 0x00b4
            java.io.File r2 = r3.get(r2)     // Catch: all -> 0x00b4
            r7.cacheFile = r2     // Catch: all -> 0x00b4
            java.io.File r2 = r7.cacheFile     // Catch: all -> 0x00b4
            if (r2 == 0) goto L_0x0005
            r7.sourceKey = r0     // Catch: all -> 0x00b4
            com.bumptech.glide.load.engine.DecodeHelper<?> r0 = r7.helper     // Catch: all -> 0x00b4
            java.io.File r2 = r7.cacheFile     // Catch: all -> 0x00b4
            java.util.List r0 = r0.getModelLoaders(r2)     // Catch: all -> 0x00b4
            r7.modelLoaders = r0     // Catch: all -> 0x00b4
            r7.modelLoaderIndex = r1     // Catch: all -> 0x00b4
            goto L_0x0005
        L_0x00b4:
            r0 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DataCacheGenerator.startNext():boolean");
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.loadData;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object obj) {
        this.cb.onDataFetcherReady(this.sourceKey, obj, this.loadData.fetcher, DataSource.DATA_DISK_CACHE, this.sourceKey);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(Exception exc) {
        this.cb.onDataFetcherFailed(this.sourceKey, exc, this.loadData.fetcher, DataSource.DATA_DISK_CACHE);
    }
}
