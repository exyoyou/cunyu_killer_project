package com.google.android.gms.location;

import java.util.Comparator;

/* loaded from: classes4.dex */
final class zzh implements Comparator<DetectedActivity> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
        DetectedActivity detectedActivity3 = detectedActivity;
        DetectedActivity detectedActivity4 = detectedActivity2;
        int compareTo = Integer.valueOf(detectedActivity4.getConfidence()).compareTo(Integer.valueOf(detectedActivity3.getConfidence()));
        return compareTo == 0 ? Integer.valueOf(detectedActivity3.getType()).compareTo(Integer.valueOf(detectedActivity4.getType())) : compareTo;
    }
}
