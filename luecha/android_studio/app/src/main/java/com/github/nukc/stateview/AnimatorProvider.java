package com.github.nukc.stateview;

import android.animation.Animator;
import android.view.View;
import kotlin.Metadata;

/* compiled from: AnimatorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/github/nukc/stateview/AnimatorProvider;", "", "hideAnimation", "Landroid/animation/Animator;", "view", "Landroid/view/View;", "showAnimation", "kotlin_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface AnimatorProvider {
    Animator hideAnimation(View view);

    Animator showAnimation(View view);
}
