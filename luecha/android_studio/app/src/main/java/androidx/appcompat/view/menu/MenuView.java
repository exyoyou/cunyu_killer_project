package androidx.appcompat.view.menu;

import android.graphics.drawable.Drawable;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface MenuView {

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl menuItemImpl, int i);

        boolean prefersCondensedTitle();

        void setCheckable(boolean z);

        void setChecked(boolean z);

        @Override // androidx.appcompat.view.menu.MenuView.ItemView
        void setEnabled(boolean z);

        void setIcon(Drawable drawable);

        void setShortcut(boolean z, char c);

        void setTitle(CharSequence charSequence);

        boolean showsIcon();
    }

    int getWindowAnimations();

    void initialize(MenuBuilder menuBuilder);
}
