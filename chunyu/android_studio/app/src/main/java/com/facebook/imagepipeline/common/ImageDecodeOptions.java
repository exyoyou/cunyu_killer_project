package com.facebook.imagepipeline.common;

/* loaded from: classes4.dex */
public class ImageDecodeOptions {
    private static final ImageDecodeOptions DEFAULTS = newBuilder().build();
    public final int backgroundColor;
    public final boolean decodeAllFrames;
    public final boolean decodePreviewFrame;
    public final boolean forceOldAnimationCode;
    public final boolean forceStaticImage;
    public final int minDecodeIntervalMs;
    public final boolean useLastFrameForPreview;

    public ImageDecodeOptions(ImageDecodeOptionsBuilder imageDecodeOptionsBuilder) {
        this.minDecodeIntervalMs = imageDecodeOptionsBuilder.getMinDecodeIntervalMs();
        this.backgroundColor = imageDecodeOptionsBuilder.getBackgroundColor();
        this.forceOldAnimationCode = imageDecodeOptionsBuilder.getForceOldAnimationCode();
        this.decodePreviewFrame = imageDecodeOptionsBuilder.getDecodePreviewFrame();
        this.useLastFrameForPreview = imageDecodeOptionsBuilder.getUseLastFrameForPreview();
        this.decodeAllFrames = imageDecodeOptionsBuilder.getDecodeAllFrames();
        this.forceStaticImage = imageDecodeOptionsBuilder.getForceStaticImage();
    }

    public static ImageDecodeOptions defaults() {
        return DEFAULTS;
    }

    public static ImageDecodeOptionsBuilder newBuilder() {
        return new ImageDecodeOptionsBuilder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageDecodeOptions imageDecodeOptions = (ImageDecodeOptions) obj;
        return this.backgroundColor == imageDecodeOptions.backgroundColor && this.forceOldAnimationCode == imageDecodeOptions.forceOldAnimationCode && this.decodePreviewFrame == imageDecodeOptions.decodePreviewFrame && this.useLastFrameForPreview == imageDecodeOptions.useLastFrameForPreview && this.decodeAllFrames == imageDecodeOptions.decodeAllFrames && this.forceStaticImage == imageDecodeOptions.forceStaticImage;
    }

    public int hashCode() {
        return (this.backgroundColor * 31) + (this.forceOldAnimationCode ? 1 : 0);
    }

    public String toString() {
        return String.format(null, "%d-%d-%b-%b-%b-%b-%b", Integer.valueOf(this.minDecodeIntervalMs), Integer.valueOf(this.backgroundColor), Boolean.valueOf(this.forceOldAnimationCode), Boolean.valueOf(this.decodePreviewFrame), Boolean.valueOf(this.useLastFrameForPreview), Boolean.valueOf(this.decodeAllFrames), Boolean.valueOf(this.forceStaticImage));
    }
}
