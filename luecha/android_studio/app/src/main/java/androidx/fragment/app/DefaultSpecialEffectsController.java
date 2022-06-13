package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    void executeOperations(List<SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation operation3 : list) {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(operation3.getFragment().mView);
            int i = AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[operation3.getFinalState().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                if (from == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                    operation = operation3;
                }
            } else if (i == 4 && from != SpecialEffectsController.Operation.State.VISIBLE) {
                operation2 = operation3;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList<SpecialEffectsController.Operation> arrayList3 = new ArrayList(list);
        for (final SpecialEffectsController.Operation operation4 : list) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            operation4.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(operation4, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            operation4.markStartedSpecialEffect(cancellationSignal2);
            boolean z2 = false;
            if (z) {
                if (operation4 != operation) {
                    arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                    operation4.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(operation4)) {
                                arrayList3.remove(operation4);
                                DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                            }
                        }
                    });
                }
                z2 = true;
                arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                operation4.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList3.contains(operation4)) {
                            arrayList3.remove(operation4);
                            DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                        }
                    }
                });
            } else {
                if (operation4 != operation2) {
                    arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                    operation4.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(operation4)) {
                                arrayList3.remove(operation4);
                                DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                            }
                        }
                    });
                }
                z2 = true;
                arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                operation4.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList3.contains(operation4)) {
                            arrayList3.remove(operation4);
                            DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                        }
                    }
                });
            }
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, arrayList3, z, operation, operation2);
        startAnimations(arrayList, arrayList3, startTransitions.containsValue(true), startTransitions);
        for (SpecialEffectsController.Operation operation5 : arrayList3) {
            applyContainerChanges(operation5);
        }
        arrayList3.clear();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void startAnimations(List<AnimationInfo> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (final AnimationInfo animationInfo : list) {
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            final boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z3) {
                                list2.remove(operation);
                            }
                            final View view = fragment.mView;
                            container.startViewTransition(view);
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    container.endViewTransition(view);
                                    if (z3) {
                                        operation.getFinalState().applyState(view);
                                    }
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                }
                            });
                            z2 = true;
                        }
                    }
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            final AnimationInfo animationInfo2 = (AnimationInfo) it.next();
            SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo2.getAnimation(context))).animation);
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation2);
                    animationInfo2.completeSpecialEffect();
                } else {
                    container.startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view2);
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation3) {
                            container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    container.endViewTransition(view2);
                                    animationInfo2.completeSpecialEffect();
                                }
                            });
                        }
                    });
                    view2.startAnimation(endViewTransitionAnimation);
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view2.clearAnimation();
                        container.endViewTransition(view2);
                        animationInfo2.completeSpecialEffect();
                    }
                });
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r30v0, types: [androidx.fragment.app.DefaultSpecialEffectsController] */
    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> list, List<SpecialEffectsController.Operation> list2, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        ArrayList<View> arrayList;
        ArrayList<View> arrayList2;
        View view;
        View view2;
        HashMap hashMap;
        Object obj;
        Object obj2;
        SpecialEffectsController.Operation operation3;
        ArrayMap arrayMap;
        HashMap hashMap2;
        FragmentTransitionImpl fragmentTransitionImpl;
        SpecialEffectsController.Operation operation4;
        ArrayList<View> arrayList3;
        Rect rect;
        SpecialEffectsController.Operation operation5;
        View view3;
        SharedElementCallback sharedElementCallback;
        SharedElementCallback sharedElementCallback2;
        ArrayList<String> arrayList4;
        final Rect rect2;
        String findKeyForValue;
        ArrayList<String> arrayList5;
        boolean z2 = z;
        SpecialEffectsController.Operation operation6 = operation;
        SpecialEffectsController.Operation operation7 = operation2;
        HashMap hashMap3 = new HashMap();
        final FragmentTransitionImpl fragmentTransitionImpl2 = 0;
        for (TransitionInfo transitionInfo : list) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = handlingImpl;
                } else if (!(handlingImpl == null || fragmentTransitionImpl2 == handlingImpl)) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            for (TransitionInfo transitionInfo2 : list) {
                hashMap3.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return hashMap3;
        }
        View view4 = new View(getContainer().getContext());
        Rect rect3 = new Rect();
        ArrayList<View> arrayList6 = new ArrayList<>();
        ArrayList<View> arrayList7 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        Object obj3 = null;
        View view5 = null;
        boolean z3 = false;
        for (TransitionInfo transitionInfo3 : list) {
            if (!transitionInfo3.hasSharedElementTransition() || operation6 == null || operation7 == null) {
                arrayMap = arrayMap2;
                operation5 = operation7;
                view3 = view4;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                hashMap2 = hashMap3;
                operation4 = operation6;
                arrayList3 = arrayList7;
                rect = rect3;
                view5 = view5;
            } else {
                Object wrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                int i = 0;
                for (ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames(); i < sharedElementTargetNames.size(); sharedElementTargetNames = sharedElementTargetNames) {
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i));
                    }
                    i++;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                if (!z2) {
                    sharedElementCallback2 = operation.getFragment().getExitTransitionCallback();
                    sharedElementCallback = operation2.getFragment().getEnterTransitionCallback();
                } else {
                    sharedElementCallback2 = operation.getFragment().getEnterTransitionCallback();
                    sharedElementCallback = operation2.getFragment().getExitTransitionCallback();
                }
                int i2 = 0;
                for (int size = sharedElementSourceNames.size(); i2 < size; size = size) {
                    arrayMap2.put(sharedElementSourceNames.get(i2), sharedElementTargetNames2.get(i2));
                    i2++;
                }
                ArrayMap arrayMap3 = new ArrayMap();
                findNamedViews(arrayMap3, operation.getFragment().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (sharedElementCallback2 != null) {
                    sharedElementCallback2.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str = sharedElementSourceNames.get(size2);
                        View view6 = (View) arrayMap3.get(str);
                        if (view6 == null) {
                            arrayMap2.remove(str);
                            arrayList5 = sharedElementSourceNames;
                        } else {
                            arrayList5 = sharedElementSourceNames;
                            if (!str.equals(ViewCompat.getTransitionName(view6))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view6), (String) arrayMap2.remove(str));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList5;
                    }
                    arrayList4 = sharedElementSourceNames;
                } else {
                    arrayList4 = sharedElementSourceNames;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                final ArrayMap arrayMap4 = new ArrayMap();
                findNamedViews(arrayMap4, operation2.getFragment().mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (sharedElementCallback != null) {
                    sharedElementCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap4);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str2 = sharedElementTargetNames2.get(size3);
                        View view7 = (View) arrayMap4.get(str2);
                        if (view7 == null) {
                            String findKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap2, str2);
                            if (findKeyForValue2 != null) {
                                arrayMap2.remove(findKeyForValue2);
                            }
                        } else if (!str2.equals(ViewCompat.getTransitionName(view7)) && (findKeyForValue = FragmentTransition.findKeyForValue(arrayMap2, str2)) != null) {
                            arrayMap2.put(findKeyForValue, ViewCompat.getTransitionName(view7));
                        }
                    }
                } else {
                    FragmentTransition.retainValues(arrayMap2, arrayMap4);
                }
                retainMatchingViews(arrayMap3, arrayMap2.keySet());
                retainMatchingViews(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList6.clear();
                    arrayList7.clear();
                    arrayMap = arrayMap2;
                    arrayList3 = arrayList7;
                    rect = rect3;
                    view3 = view4;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view5 = view5;
                    obj3 = null;
                    operation5 = operation2;
                    hashMap2 = hashMap3;
                    operation4 = operation;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z2, arrayMap3, true);
                    arrayMap = arrayMap2;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, arrayMap4, false);
                        }
                    });
                    for (View view8 : arrayMap3.values()) {
                        captureTransitioningViews(arrayList6, view8);
                    }
                    if (!arrayList4.isEmpty()) {
                        View view9 = (View) arrayMap3.get(arrayList4.get(0));
                        fragmentTransitionImpl2.setEpicenter(wrapTransitionInSet, view9);
                        view5 = view9;
                    } else {
                        view5 = view5;
                    }
                    for (View view10 : arrayMap4.values()) {
                        captureTransitioningViews(arrayList7, view10);
                    }
                    if (!sharedElementTargetNames2.isEmpty()) {
                        final View view11 = (View) arrayMap4.get(sharedElementTargetNames2.get(0));
                        if (view11 != null) {
                            rect2 = rect3;
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                                @Override // java.lang.Runnable
                                public void run() {
                                    fragmentTransitionImpl2.getBoundsOnScreen(view11, rect2);
                                }
                            });
                            view3 = view4;
                            z3 = true;
                            fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view3, arrayList6);
                            rect = rect2;
                            arrayList6 = arrayList6;
                            arrayList3 = arrayList7;
                            fragmentTransitionImpl = fragmentTransitionImpl2;
                            fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList3);
                            operation4 = operation;
                            hashMap2 = hashMap3;
                            hashMap2.put(operation4, true);
                            operation5 = operation2;
                            hashMap2.put(operation5, true);
                            obj3 = wrapTransitionInSet;
                        } else {
                            rect2 = rect3;
                        }
                    } else {
                        rect2 = rect3;
                    }
                    view3 = view4;
                    fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view3, arrayList6);
                    rect = rect2;
                    arrayList6 = arrayList6;
                    arrayList3 = arrayList7;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList3);
                    operation4 = operation;
                    hashMap2 = hashMap3;
                    hashMap2.put(operation4, true);
                    operation5 = operation2;
                    hashMap2.put(operation5, true);
                    obj3 = wrapTransitionInSet;
                }
            }
            z2 = z;
            view4 = view3;
            operation7 = operation5;
            rect3 = rect;
            arrayList7 = arrayList3;
            operation6 = operation4;
            hashMap3 = hashMap2;
            arrayMap2 = arrayMap;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
        }
        View view12 = view5;
        View view13 = view4;
        boolean z4 = false;
        HashMap hashMap4 = hashMap3;
        ArrayList<View> arrayList8 = arrayList7;
        ArrayList arrayList9 = new ArrayList();
        Iterator<TransitionInfo> it = list.iterator();
        Object obj4 = null;
        Object obj5 = null;
        while (it.hasNext()) {
            TransitionInfo next = it.next();
            if (next.isVisibilityUnchanged()) {
                hashMap4.put(next.getOperation(), Boolean.valueOf(z4));
                next.completeSpecialEffect();
                it = it;
            } else {
                Object cloneTransition = fragmentTransitionImpl2.cloneTransition(next.getTransition());
                SpecialEffectsController.Operation operation8 = next.getOperation();
                boolean z5 = obj3 != null && (operation8 == operation6 || operation8 == operation7);
                if (cloneTransition == null) {
                    if (!z5) {
                        hashMap4.put(operation8, Boolean.valueOf(z4));
                        next.completeSpecialEffect();
                    }
                    view = view13;
                    arrayList2 = arrayList6;
                    arrayList = arrayList8;
                    obj2 = obj4;
                    obj = obj5;
                    hashMap = hashMap4;
                    view2 = view12;
                } else {
                    final ArrayList<View> arrayList10 = new ArrayList<>();
                    captureTransitioningViews(arrayList10, operation8.getFragment().mView);
                    if (z5) {
                        if (operation8 == operation6) {
                            arrayList10.removeAll(arrayList6);
                        } else {
                            arrayList10.removeAll(arrayList8);
                        }
                    }
                    if (arrayList10.isEmpty()) {
                        fragmentTransitionImpl2.addTarget(cloneTransition, view13);
                        view = view13;
                        arrayList2 = arrayList6;
                        arrayList = arrayList8;
                        operation3 = operation8;
                        obj = obj5;
                        hashMap = hashMap4;
                        obj2 = obj4;
                    } else {
                        fragmentTransitionImpl2.addTargets(cloneTransition, arrayList10);
                        view = view13;
                        obj2 = obj4;
                        arrayList2 = arrayList6;
                        obj = obj5;
                        arrayList = arrayList8;
                        hashMap = hashMap4;
                        fragmentTransitionImpl2.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList10, null, null, null, null);
                        if (operation8.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation3 = operation8;
                            list2.remove(operation3);
                            fragmentTransitionImpl2.scheduleHideFragmentView(cloneTransition, operation3.getFragment().mView, arrayList10);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList10, 4);
                                }
                            });
                        } else {
                            operation3 = operation8;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList9.addAll(arrayList10);
                        if (z3) {
                            fragmentTransitionImpl2.setEpicenter(cloneTransition, rect3);
                        }
                        view2 = view12;
                    } else {
                        view2 = view12;
                        fragmentTransitionImpl2.setEpicenter(cloneTransition, view2);
                    }
                    hashMap.put(operation3, true);
                    if (next.isOverlapAllowed()) {
                        obj = fragmentTransitionImpl2.mergeTransitionsTogether(obj, cloneTransition, null);
                    } else {
                        obj2 = fragmentTransitionImpl2.mergeTransitionsTogether(obj2, cloneTransition, null);
                    }
                }
                it = it;
                obj4 = obj2;
                obj5 = obj;
                hashMap4 = hashMap;
                view12 = view2;
                view13 = view;
                arrayList6 = arrayList2;
                arrayList8 = arrayList;
                z4 = false;
            }
        }
        Object mergeTransitionsInSequence = fragmentTransitionImpl2.mergeTransitionsInSequence(obj5, obj4, obj3);
        for (final TransitionInfo transitionInfo4 : list) {
            if (!transitionInfo4.isVisibilityUnchanged()) {
                Object transition = transitionInfo4.getTransition();
                SpecialEffectsController.Operation operation9 = transitionInfo4.getOperation();
                boolean z6 = obj3 != null && (operation9 == operation6 || operation9 == operation7);
                if (transition != null || z6) {
                    if (!ViewCompat.isLaidOut(getContainer())) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation9);
                        }
                        transitionInfo4.completeSpecialEffect();
                    } else {
                        fragmentTransitionImpl2.setListenerForTransitionEnd(transitionInfo4.getOperation().getFragment(), mergeTransitionsInSequence, transitionInfo4.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                            @Override // java.lang.Runnable
                            public void run() {
                                transitionInfo4.completeSpecialEffect();
                            }
                        });
                    }
                }
            }
        }
        if (!ViewCompat.isLaidOut(getContainer())) {
            return hashMap4;
        }
        FragmentTransition.setViewVisibility(arrayList9, 4);
        ArrayList<String> prepareSetNameOverridesReordered = fragmentTransitionImpl2.prepareSetNameOverridesReordered(arrayList8);
        fragmentTransitionImpl2.beginDelayedTransition(getContainer(), mergeTransitionsInSequence);
        fragmentTransitionImpl2.setNameOverridesReordered(getContainer(), arrayList6, arrayList8, prepareSetNameOverridesReordered, arrayMap2);
        FragmentTransition.setViewVisibility(arrayList9, 0);
        fragmentTransitionImpl2.swapSharedElementTargets(obj3, arrayList6, arrayList8);
        return hashMap4;
    }

    void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            if (!arrayList.contains(view) && ViewCompat.getTransitionName(view) != null) {
                arrayList.add(view);
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return from == finalState || !(from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim = false;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mIsPop = z;
        }

        FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object obj;
            Object obj2;
            boolean z3;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    obj2 = operation.getFragment().getReenterTransition();
                } else {
                    obj2 = operation.getFragment().getEnterTransition();
                }
                this.mTransition = obj2;
                if (z) {
                    z3 = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    z3 = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = z3;
            } else {
                if (z) {
                    obj = operation.getFragment().getReturnTransition();
                } else {
                    obj = operation.getFragment().getExitTransition();
                }
                this.mTransition = obj;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        Object getTransition() {
            return this.mTransition;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
