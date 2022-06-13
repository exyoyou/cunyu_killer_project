package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;

/* loaded from: classes4.dex */
public interface ResourceEncoder<T> extends Encoder<Resource<T>> {
    EncodeStrategy getEncodeStrategy(Options options);
}
