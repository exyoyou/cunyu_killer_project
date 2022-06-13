package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzf implements Parcelable.Creator<ActivityTransitionRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ActivityTransitionRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        String str = null;
        ArrayList arrayList2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, ActivityTransition.CREATOR);
            } else if (fieldId == 2) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, ClientIdentity.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ActivityTransitionRequest(arrayList, str, arrayList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ActivityTransitionRequest[] newArray(int i) {
        return new ActivityTransitionRequest[i];
    }
}
