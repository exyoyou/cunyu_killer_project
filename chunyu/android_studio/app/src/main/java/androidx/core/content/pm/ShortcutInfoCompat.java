package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.net.UriCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public class ShortcutInfoCompat {
    private static final String EXTRA_LOCUS_ID = "extraLocusId";
    private static final String EXTRA_LONG_LIVED = "extraLongLived";
    private static final String EXTRA_PERSON_ = "extraPerson_";
    private static final String EXTRA_PERSON_COUNT = "extraPersonCount";
    private static final String EXTRA_SLICE_URI = "extraSliceUri";
    ComponentName mActivity;
    Set<String> mCategories;
    Context mContext;
    CharSequence mDisabledMessage;
    int mDisabledReason;
    PersistableBundle mExtras;
    boolean mHasKeyFieldsOnly;
    IconCompat mIcon;
    String mId;
    Intent[] mIntents;
    boolean mIsAlwaysBadged;
    boolean mIsCached;
    boolean mIsDeclaredInManifest;
    boolean mIsDynamic;
    boolean mIsEnabled = true;
    boolean mIsImmutable;
    boolean mIsLongLived;
    boolean mIsPinned;
    CharSequence mLabel;
    long mLastChangedTimestamp;
    LocusIdCompat mLocusId;
    CharSequence mLongLabel;
    String mPackageName;
    Person[] mPersons;
    int mRank;
    UserHandle mUser;

    ShortcutInfoCompat() {
    }

    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        IconCompat iconCompat = this.mIcon;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.toIcon(this.mContext));
        }
        if (!TextUtils.isEmpty(this.mLongLabel)) {
            intents.setLongLabel(this.mLongLabel);
        }
        if (!TextUtils.isEmpty(this.mDisabledMessage)) {
            intents.setDisabledMessage(this.mDisabledMessage);
        }
        ComponentName componentName = this.mActivity;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        Set<String> set = this.mCategories;
        if (set != null) {
            intents.setCategories(set);
        }
        intents.setRank(this.mRank);
        PersistableBundle persistableBundle = this.mExtras;
        if (persistableBundle != null) {
            intents.setExtras(persistableBundle);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Person[] personArr = this.mPersons;
            if (personArr != null && personArr.length > 0) {
                android.app.Person[] personArr2 = new android.app.Person[personArr.length];
                for (int i = 0; i < personArr2.length; i++) {
                    personArr2[i] = this.mPersons[i].toAndroidPerson();
                }
                intents.setPersons(personArr2);
            }
            LocusIdCompat locusIdCompat = this.mLocusId;
            if (locusIdCompat != null) {
                intents.setLocusId(locusIdCompat.toLocusId());
            }
            intents.setLongLived(this.mIsLongLived);
        } else {
            intents.setExtras(buildLegacyExtrasBundle());
        }
        return intents.build();
    }

    private PersistableBundle buildLegacyExtrasBundle() {
        if (this.mExtras == null) {
            this.mExtras = new PersistableBundle();
        }
        Person[] personArr = this.mPersons;
        if (personArr != null && personArr.length > 0) {
            this.mExtras.putInt("extraPersonCount", personArr.length);
            int i = 0;
            while (i < this.mPersons.length) {
                PersistableBundle persistableBundle = this.mExtras;
                StringBuilder sb = new StringBuilder();
                sb.append("extraPerson_");
                int i2 = i + 1;
                sb.append(i2);
                persistableBundle.putPersistableBundle(sb.toString(), this.mPersons[i].toPersistableBundle());
                i = i2;
            }
        }
        LocusIdCompat locusIdCompat = this.mLocusId;
        if (locusIdCompat != null) {
            this.mExtras.putString("extraLocusId", locusIdCompat.getId());
        }
        this.mExtras.putBoolean("extraLongLived", this.mIsLongLived);
        return this.mExtras;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent addToIntent(Intent intent) {
        Intent[] intentArr = this.mIntents;
        intent.putExtra("android.intent.extra.shortcut.INTENT", intentArr[intentArr.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if (this.mIcon != null) {
            Drawable drawable = null;
            if (this.mIsAlwaysBadged) {
                PackageManager packageManager = this.mContext.getPackageManager();
                ComponentName componentName = this.mActivity;
                if (componentName != null) {
                    try {
                        drawable = packageManager.getActivityIcon(componentName);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                if (drawable == null) {
                    drawable = this.mContext.getApplicationInfo().loadIcon(packageManager);
                }
            }
            this.mIcon.addToShortcutIntent(intent, drawable, this.mContext);
        }
        return intent;
    }

    public String getId() {
        return this.mId;
    }

    public String getPackage() {
        return this.mPackageName;
    }

    public ComponentName getActivity() {
        return this.mActivity;
    }

    public CharSequence getShortLabel() {
        return this.mLabel;
    }

    public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    public int getDisabledReason() {
        return this.mDisabledReason;
    }

    public Intent getIntent() {
        Intent[] intentArr = this.mIntents;
        return intentArr[intentArr.length - 1];
    }

    public Intent[] getIntents() {
        Intent[] intentArr = this.mIntents;
        return (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
    }

    public Set<String> getCategories() {
        return this.mCategories;
    }

    public LocusIdCompat getLocusId() {
        return this.mLocusId;
    }

    public int getRank() {
        return this.mRank;
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    static Person[] getPersonsFromExtra(PersistableBundle persistableBundle) {
        if (persistableBundle == null || !persistableBundle.containsKey("extraPersonCount")) {
            return null;
        }
        int i = persistableBundle.getInt("extraPersonCount");
        Person[] personArr = new Person[i];
        int i2 = 0;
        while (i2 < i) {
            StringBuilder sb = new StringBuilder();
            sb.append("extraPerson_");
            int i3 = i2 + 1;
            sb.append(i3);
            personArr[i2] = Person.fromPersistableBundle(persistableBundle.getPersistableBundle(sb.toString()));
            i2 = i3;
        }
        return personArr;
    }

    static boolean getLongLivedFromExtra(PersistableBundle persistableBundle) {
        if (persistableBundle == null || !persistableBundle.containsKey("extraLongLived")) {
            return false;
        }
        return persistableBundle.getBoolean("extraLongLived");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<ShortcutInfoCompat> fromShortcuts(Context context, List<ShortcutInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ShortcutInfo shortcutInfo : list) {
            arrayList.add(new Builder(context, shortcutInfo).build());
        }
        return arrayList;
    }

    public PersistableBundle getExtras() {
        return this.mExtras;
    }

    public UserHandle getUserHandle() {
        return this.mUser;
    }

    public long getLastChangedTimestamp() {
        return this.mLastChangedTimestamp;
    }

    public boolean isCached() {
        return this.mIsCached;
    }

    public boolean isDynamic() {
        return this.mIsDynamic;
    }

    public boolean isPinned() {
        return this.mIsPinned;
    }

    public boolean isDeclaredInManifest() {
        return this.mIsDeclaredInManifest;
    }

    public boolean isImmutable() {
        return this.mIsImmutable;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean hasKeyFieldsOnly() {
        return this.mHasKeyFieldsOnly;
    }

    static LocusIdCompat getLocusId(ShortcutInfo shortcutInfo) {
        if (Build.VERSION.SDK_INT < 29) {
            return getLocusIdFromExtra(shortcutInfo.getExtras());
        }
        if (shortcutInfo.getLocusId() == null) {
            return null;
        }
        return LocusIdCompat.toLocusIdCompat(shortcutInfo.getLocusId());
    }

    private static LocusIdCompat getLocusIdFromExtra(PersistableBundle persistableBundle) {
        String string;
        if (persistableBundle == null || (string = persistableBundle.getString("extraLocusId")) == null) {
            return null;
        }
        return new LocusIdCompat(string);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
    public static class Builder {
        private Map<String, Map<String, List<String>>> mCapabilityBindingParams;
        private Set<String> mCapabilityBindings;
        private final ShortcutInfoCompat mInfo = new ShortcutInfoCompat();
        private boolean mIsConversation;
        private Uri mSliceUri;

        public Builder(Context context, String str) {
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            shortcutInfoCompat.mContext = context;
            shortcutInfoCompat.mId = str;
        }

        public Builder(ShortcutInfoCompat shortcutInfoCompat) {
            this.mInfo.mContext = shortcutInfoCompat.mContext;
            this.mInfo.mId = shortcutInfoCompat.mId;
            this.mInfo.mPackageName = shortcutInfoCompat.mPackageName;
            this.mInfo.mIntents = (Intent[]) Arrays.copyOf(shortcutInfoCompat.mIntents, shortcutInfoCompat.mIntents.length);
            this.mInfo.mActivity = shortcutInfoCompat.mActivity;
            this.mInfo.mLabel = shortcutInfoCompat.mLabel;
            this.mInfo.mLongLabel = shortcutInfoCompat.mLongLabel;
            this.mInfo.mDisabledMessage = shortcutInfoCompat.mDisabledMessage;
            this.mInfo.mDisabledReason = shortcutInfoCompat.mDisabledReason;
            this.mInfo.mIcon = shortcutInfoCompat.mIcon;
            this.mInfo.mIsAlwaysBadged = shortcutInfoCompat.mIsAlwaysBadged;
            this.mInfo.mUser = shortcutInfoCompat.mUser;
            this.mInfo.mLastChangedTimestamp = shortcutInfoCompat.mLastChangedTimestamp;
            this.mInfo.mIsCached = shortcutInfoCompat.mIsCached;
            this.mInfo.mIsDynamic = shortcutInfoCompat.mIsDynamic;
            this.mInfo.mIsPinned = shortcutInfoCompat.mIsPinned;
            this.mInfo.mIsDeclaredInManifest = shortcutInfoCompat.mIsDeclaredInManifest;
            this.mInfo.mIsImmutable = shortcutInfoCompat.mIsImmutable;
            this.mInfo.mIsEnabled = shortcutInfoCompat.mIsEnabled;
            this.mInfo.mLocusId = shortcutInfoCompat.mLocusId;
            this.mInfo.mIsLongLived = shortcutInfoCompat.mIsLongLived;
            this.mInfo.mHasKeyFieldsOnly = shortcutInfoCompat.mHasKeyFieldsOnly;
            this.mInfo.mRank = shortcutInfoCompat.mRank;
            if (shortcutInfoCompat.mPersons != null) {
                this.mInfo.mPersons = (Person[]) Arrays.copyOf(shortcutInfoCompat.mPersons, shortcutInfoCompat.mPersons.length);
            }
            if (shortcutInfoCompat.mCategories != null) {
                this.mInfo.mCategories = new HashSet(shortcutInfoCompat.mCategories);
            }
            if (shortcutInfoCompat.mExtras != null) {
                this.mInfo.mExtras = shortcutInfoCompat.mExtras;
            }
        }

        public Builder(Context context, ShortcutInfo shortcutInfo) {
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            shortcutInfoCompat.mContext = context;
            shortcutInfoCompat.mId = shortcutInfo.getId();
            this.mInfo.mPackageName = shortcutInfo.getPackage();
            Intent[] intents = shortcutInfo.getIntents();
            this.mInfo.mIntents = (Intent[]) Arrays.copyOf(intents, intents.length);
            this.mInfo.mActivity = shortcutInfo.getActivity();
            this.mInfo.mLabel = shortcutInfo.getShortLabel();
            this.mInfo.mLongLabel = shortcutInfo.getLongLabel();
            this.mInfo.mDisabledMessage = shortcutInfo.getDisabledMessage();
            if (Build.VERSION.SDK_INT >= 28) {
                this.mInfo.mDisabledReason = shortcutInfo.getDisabledReason();
            } else {
                this.mInfo.mDisabledReason = shortcutInfo.isEnabled() ? 0 : 3;
            }
            this.mInfo.mCategories = shortcutInfo.getCategories();
            this.mInfo.mPersons = ShortcutInfoCompat.getPersonsFromExtra(shortcutInfo.getExtras());
            this.mInfo.mUser = shortcutInfo.getUserHandle();
            this.mInfo.mLastChangedTimestamp = shortcutInfo.getLastChangedTimestamp();
            if (Build.VERSION.SDK_INT >= 30) {
                this.mInfo.mIsCached = shortcutInfo.isCached();
            }
            this.mInfo.mIsDynamic = shortcutInfo.isDynamic();
            this.mInfo.mIsPinned = shortcutInfo.isPinned();
            this.mInfo.mIsDeclaredInManifest = shortcutInfo.isDeclaredInManifest();
            this.mInfo.mIsImmutable = shortcutInfo.isImmutable();
            this.mInfo.mIsEnabled = shortcutInfo.isEnabled();
            this.mInfo.mHasKeyFieldsOnly = shortcutInfo.hasKeyFieldsOnly();
            this.mInfo.mLocusId = ShortcutInfoCompat.getLocusId(shortcutInfo);
            this.mInfo.mRank = shortcutInfo.getRank();
            this.mInfo.mExtras = shortcutInfo.getExtras();
        }

        public Builder setShortLabel(CharSequence charSequence) {
            this.mInfo.mLabel = charSequence;
            return this;
        }

        public Builder setLongLabel(CharSequence charSequence) {
            this.mInfo.mLongLabel = charSequence;
            return this;
        }

        public Builder setDisabledMessage(CharSequence charSequence) {
            this.mInfo.mDisabledMessage = charSequence;
            return this;
        }

        public Builder setIntent(Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        public Builder setIntents(Intent[] intentArr) {
            this.mInfo.mIntents = intentArr;
            return this;
        }

        public Builder setIcon(IconCompat iconCompat) {
            this.mInfo.mIcon = iconCompat;
            return this;
        }

        public Builder setLocusId(LocusIdCompat locusIdCompat) {
            this.mInfo.mLocusId = locusIdCompat;
            return this;
        }

        public Builder setIsConversation() {
            this.mIsConversation = true;
            return this;
        }

        public Builder setActivity(ComponentName componentName) {
            this.mInfo.mActivity = componentName;
            return this;
        }

        public Builder setAlwaysBadged() {
            this.mInfo.mIsAlwaysBadged = true;
            return this;
        }

        public Builder setPerson(Person person) {
            return setPersons(new Person[]{person});
        }

        public Builder setPersons(Person[] personArr) {
            this.mInfo.mPersons = personArr;
            return this;
        }

        public Builder setCategories(Set<String> set) {
            this.mInfo.mCategories = set;
            return this;
        }

        @Deprecated
        public Builder setLongLived() {
            this.mInfo.mIsLongLived = true;
            return this;
        }

        public Builder setLongLived(boolean z) {
            this.mInfo.mIsLongLived = z;
            return this;
        }

        public Builder setRank(int i) {
            this.mInfo.mRank = i;
            return this;
        }

        public Builder setExtras(PersistableBundle persistableBundle) {
            this.mInfo.mExtras = persistableBundle;
            return this;
        }

        public Builder addCapabilityBinding(String str) {
            if (this.mCapabilityBindings == null) {
                this.mCapabilityBindings = new HashSet();
            }
            this.mCapabilityBindings.add(str);
            return this;
        }

        public Builder addCapabilityBinding(String str, String str2, List<String> list) {
            addCapabilityBinding(str);
            if (!list.isEmpty()) {
                if (this.mCapabilityBindingParams == null) {
                    this.mCapabilityBindingParams = new HashMap();
                }
                if (this.mCapabilityBindingParams.get(str) == null) {
                    this.mCapabilityBindingParams.put(str, new HashMap());
                }
                this.mCapabilityBindingParams.get(str).put(str2, list);
            }
            return this;
        }

        public Builder setSliceUri(Uri uri) {
            this.mSliceUri = uri;
            return this;
        }

        public ShortcutInfoCompat build() {
            String[] strArr;
            if (TextUtils.isEmpty(this.mInfo.mLabel)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            } else if (this.mInfo.mIntents == null || this.mInfo.mIntents.length == 0) {
                throw new IllegalArgumentException("Shortcut must have an intent");
            } else {
                if (this.mIsConversation) {
                    if (this.mInfo.mLocusId == null) {
                        ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
                        shortcutInfoCompat.mLocusId = new LocusIdCompat(shortcutInfoCompat.mId);
                    }
                    this.mInfo.mIsLongLived = true;
                }
                if (this.mCapabilityBindings != null) {
                    if (this.mInfo.mCategories == null) {
                        this.mInfo.mCategories = new HashSet();
                    }
                    this.mInfo.mCategories.addAll(this.mCapabilityBindings);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    if (this.mCapabilityBindingParams != null) {
                        if (this.mInfo.mExtras == null) {
                            this.mInfo.mExtras = new PersistableBundle();
                        }
                        for (String str : this.mCapabilityBindingParams.keySet()) {
                            Map<String, List<String>> map = this.mCapabilityBindingParams.get(str);
                            this.mInfo.mExtras.putStringArray(str, (String[]) map.keySet().toArray(new String[0]));
                            for (String str2 : map.keySet()) {
                                List<String> list = map.get(str2);
                                PersistableBundle persistableBundle = this.mInfo.mExtras;
                                String str3 = str + "/" + str2;
                                if (list == null) {
                                    strArr = new String[0];
                                } else {
                                    strArr = (String[]) list.toArray(new String[0]);
                                }
                                persistableBundle.putStringArray(str3, strArr);
                            }
                        }
                    }
                    if (this.mSliceUri != null) {
                        if (this.mInfo.mExtras == null) {
                            this.mInfo.mExtras = new PersistableBundle();
                        }
                        this.mInfo.mExtras.putString(ShortcutInfoCompat.EXTRA_SLICE_URI, UriCompat.toSafeString(this.mSliceUri));
                    }
                }
                return this.mInfo;
            }
        }
    }
}
