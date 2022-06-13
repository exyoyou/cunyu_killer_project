package io.flutter.plugin.editing;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStructure;
import android.view.WindowInsets;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import io.flutter.Log;
import io.flutter.embedding.android.KeyboardManager;
import io.flutter.embedding.engine.systemchannels.TextInputChannel;
import io.flutter.plugin.editing.ListenableEditingState;
import io.flutter.plugin.platform.PlatformViewsController;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class TextInputPlugin implements ListenableEditingState.EditingStateWatcher {
    private static final String TAG = "TextInputPlugin";
    private final AutofillManager afm;
    private TextInputChannel.Configuration configuration;
    private ImeSyncDeferringInsetsCallback imeSyncCallback;
    private InputTarget inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
    private boolean isInputConnectionLocked;
    private Rect lastClientRect;
    private InputConnection lastInputConnection;
    private SparseArray<TextInputChannel.Configuration> mAutofillConfigurations;
    private ListenableEditingState mEditable;
    private final InputMethodManager mImm;
    private TextInputChannel.TextEditState mLastKnownFrameworkTextEditingState;
    private boolean mRestartInputPending;
    private final View mView;
    private PlatformViewsController platformViewsController;
    private final TextInputChannel textInputChannel;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    public interface MinMax {
        void inspect(double d, double d2);
    }

    public TextInputPlugin(View view, TextInputChannel textInputChannel, PlatformViewsController platformViewsController) {
        int i = 0;
        this.mView = view;
        this.mEditable = new ListenableEditingState(null, this.mView);
        this.mImm = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (Build.VERSION.SDK_INT >= 26) {
            this.afm = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        } else {
            this.afm = null;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            i = (this.mView.getWindowSystemUiVisibility() & 2) == 0 ? 0 | WindowInsets.Type.navigationBars() : i;
            this.imeSyncCallback = new ImeSyncDeferringInsetsCallback(view, (this.mView.getWindowSystemUiVisibility() & 4) == 0 ? i | WindowInsets.Type.statusBars() : i, WindowInsets.Type.ime());
            this.imeSyncCallback.install();
        }
        this.textInputChannel = textInputChannel;
        textInputChannel.setTextInputMethodHandler(new TextInputChannel.TextInputMethodHandler() { // from class: io.flutter.plugin.editing.TextInputPlugin.1
            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void show() {
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.showTextInput(textInputPlugin.mView);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void hide() {
                if (TextInputPlugin.this.inputTarget.type == InputTarget.Type.HC_PLATFORM_VIEW) {
                    TextInputPlugin.this.notifyViewExited();
                    return;
                }
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.hideTextInput(textInputPlugin.mView);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void requestAutofill() {
                TextInputPlugin.this.notifyViewEntered();
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void finishAutofillContext(boolean z) {
                if (Build.VERSION.SDK_INT >= 26 && TextInputPlugin.this.afm != null) {
                    if (z) {
                        TextInputPlugin.this.afm.commit();
                    } else {
                        TextInputPlugin.this.afm.cancel();
                    }
                }
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setClient(int i2, TextInputChannel.Configuration configuration) {
                TextInputPlugin.this.setTextInputClient(i2, configuration);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setPlatformViewClient(int i2, boolean z) {
                TextInputPlugin.this.setPlatformViewTextInputClient(i2, z);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setEditingState(TextInputChannel.TextEditState textEditState) {
                TextInputPlugin textInputPlugin = TextInputPlugin.this;
                textInputPlugin.setTextInputEditingState(textInputPlugin.mView, textEditState);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void setEditableSizeAndTransform(double d, double d2, double[] dArr) {
                TextInputPlugin.this.saveEditableSizeAndTransform(d, d2, dArr);
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void clearClient() {
                TextInputPlugin.this.clearTextInputClient();
            }

            @Override // io.flutter.embedding.engine.systemchannels.TextInputChannel.TextInputMethodHandler
            public void sendAppPrivateCommand(String str, Bundle bundle) {
                TextInputPlugin.this.sendTextInputAppPrivateCommand(str, bundle);
            }
        });
        textInputChannel.requestExistingInputState();
        this.platformViewsController = platformViewsController;
        this.platformViewsController.attachTextInputPlugin(this);
    }

    public InputMethodManager getInputMethodManager() {
        return this.mImm;
    }

    Editable getEditable() {
        return this.mEditable;
    }

    ImeSyncDeferringInsetsCallback getImeSyncCallback() {
        return this.imeSyncCallback;
    }

    public void lockPlatformViewInputConnection() {
        if (this.inputTarget.type == InputTarget.Type.VD_PLATFORM_VIEW) {
            this.isInputConnectionLocked = true;
        }
    }

    public void unlockPlatformViewInputConnection() {
        this.isInputConnectionLocked = false;
    }

    public void destroy() {
        this.platformViewsController.detachTextInputPlugin();
        this.textInputChannel.setTextInputMethodHandler(null);
        notifyViewExited();
        this.mEditable.removeEditingStateListener(this);
        ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = this.imeSyncCallback;
        if (imeSyncDeferringInsetsCallback != null) {
            imeSyncDeferringInsetsCallback.remove();
        }
    }

    private static int inputTypeFromTextInputType(TextInputChannel.InputType inputType, boolean z, boolean z2, boolean z3, boolean z4, TextInputChannel.TextCapitalization textCapitalization) {
        int i;
        if (inputType.type == TextInputChannel.TextInputType.DATETIME) {
            return 4;
        }
        if (inputType.type == TextInputChannel.TextInputType.NUMBER) {
            int i2 = 2;
            if (inputType.isSigned) {
                i2 = 4098;
            }
            return inputType.isDecimal ? i2 | 8192 : i2;
        } else if (inputType.type == TextInputChannel.TextInputType.PHONE) {
            return 3;
        } else {
            if (inputType.type == TextInputChannel.TextInputType.NONE) {
                return 0;
            }
            int i3 = 1;
            if (inputType.type == TextInputChannel.TextInputType.MULTILINE) {
                i3 = 131073;
            } else if (inputType.type == TextInputChannel.TextInputType.EMAIL_ADDRESS) {
                i3 = 33;
            } else if (inputType.type == TextInputChannel.TextInputType.URL) {
                i3 = 17;
            } else if (inputType.type == TextInputChannel.TextInputType.VISIBLE_PASSWORD) {
                i3 = 145;
            } else if (inputType.type == TextInputChannel.TextInputType.NAME) {
                i3 = 97;
            } else if (inputType.type == TextInputChannel.TextInputType.POSTAL_ADDRESS) {
                i3 = 113;
            }
            if (z) {
                i = 524288 | i3 | 128;
            } else {
                if (z2) {
                    i3 |= 32768;
                }
                i = !z3 ? 524288 | i3 : i3;
            }
            if (textCapitalization == TextInputChannel.TextCapitalization.CHARACTERS) {
                return i | 4096;
            }
            if (textCapitalization == TextInputChannel.TextCapitalization.WORDS) {
                return i | 8192;
            }
            return textCapitalization == TextInputChannel.TextCapitalization.SENTENCES ? i | 16384 : i;
        }
    }

    public InputConnection createInputConnection(View view, KeyboardManager keyboardManager, EditorInfo editorInfo) {
        int i;
        if (this.inputTarget.type == InputTarget.Type.NO_TARGET) {
            this.lastInputConnection = null;
            return null;
        } else if (this.inputTarget.type == InputTarget.Type.HC_PLATFORM_VIEW) {
            return null;
        } else {
            if (this.inputTarget.type != InputTarget.Type.VD_PLATFORM_VIEW) {
                editorInfo.inputType = inputTypeFromTextInputType(this.configuration.inputType, this.configuration.obscureText, this.configuration.autocorrect, this.configuration.enableSuggestions, this.configuration.enableIMEPersonalizedLearning, this.configuration.textCapitalization);
                editorInfo.imeOptions = 33554432;
                if (Build.VERSION.SDK_INT >= 26 && !this.configuration.enableIMEPersonalizedLearning) {
                    editorInfo.imeOptions |= 16777216;
                }
                if (this.configuration.inputAction == null) {
                    i = (131072 & editorInfo.inputType) != 0 ? 1 : 6;
                } else {
                    i = this.configuration.inputAction.intValue();
                }
                if (this.configuration.actionLabel != null) {
                    editorInfo.actionLabel = this.configuration.actionLabel;
                    editorInfo.actionId = i;
                }
                editorInfo.imeOptions = i | editorInfo.imeOptions;
                InputConnectionAdaptor inputConnectionAdaptor = new InputConnectionAdaptor(view, this.inputTarget.id, this.textInputChannel, keyboardManager, this.mEditable, editorInfo);
                editorInfo.initialSelStart = this.mEditable.getSelectionStart();
                editorInfo.initialSelEnd = this.mEditable.getSelectionEnd();
                this.lastInputConnection = inputConnectionAdaptor;
                return this.lastInputConnection;
            } else if (this.isInputConnectionLocked) {
                return this.lastInputConnection;
            } else {
                this.lastInputConnection = this.platformViewsController.getPlatformViewById(Integer.valueOf(this.inputTarget.id)).onCreateInputConnection(editorInfo);
                return this.lastInputConnection;
            }
        }
    }

    public InputConnection getLastInputConnection() {
        return this.lastInputConnection;
    }

    public void clearPlatformViewClient(int i) {
        if ((this.inputTarget.type == InputTarget.Type.VD_PLATFORM_VIEW || this.inputTarget.type == InputTarget.Type.HC_PLATFORM_VIEW) && this.inputTarget.id == i) {
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
            notifyViewExited();
            this.mImm.hideSoftInputFromWindow(this.mView.getApplicationWindowToken(), 0);
            this.mImm.restartInput(this.mView);
            this.mRestartInputPending = false;
        }
    }

    public void sendTextInputAppPrivateCommand(String str, Bundle bundle) {
        this.mImm.sendAppPrivateCommand(this.mView, str, bundle);
    }

    private boolean canShowTextInput() {
        TextInputChannel.Configuration configuration = this.configuration;
        if (configuration == null || configuration.inputType == null || this.configuration.inputType.type != TextInputChannel.TextInputType.NONE) {
            return true;
        }
        return false;
    }

    void showTextInput(View view) {
        if (canShowTextInput()) {
            view.requestFocus();
            this.mImm.showSoftInput(view, 0);
            return;
        }
        hideTextInput(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTextInput(View view) {
        notifyViewExited();
        this.mImm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    void setTextInputClient(int i, TextInputChannel.Configuration configuration) {
        notifyViewExited();
        this.configuration = configuration;
        if (canShowTextInput()) {
            this.inputTarget = new InputTarget(InputTarget.Type.FRAMEWORK_CLIENT, i);
        } else {
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, i);
        }
        this.mEditable.removeEditingStateListener(this);
        this.mEditable = new ListenableEditingState(configuration.autofill != null ? configuration.autofill.editState : null, this.mView);
        updateAutofillConfigurationIfNeeded(configuration);
        this.mRestartInputPending = true;
        unlockPlatformViewInputConnection();
        this.lastClientRect = null;
        this.mEditable.addEditingStateListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlatformViewTextInputClient(int i, boolean z) {
        if (z) {
            this.mView.requestFocus();
            this.inputTarget = new InputTarget(InputTarget.Type.VD_PLATFORM_VIEW, i);
            this.mImm.restartInput(this.mView);
            this.mRestartInputPending = false;
            return;
        }
        this.inputTarget = new InputTarget(InputTarget.Type.HC_PLATFORM_VIEW, i);
        this.lastInputConnection = null;
    }

    private static boolean composingChanged(TextInputChannel.TextEditState textEditState, TextInputChannel.TextEditState textEditState2) {
        int i = textEditState.composingEnd - textEditState.composingStart;
        if (i != textEditState2.composingEnd - textEditState2.composingStart) {
            return true;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (textEditState.text.charAt(textEditState.composingStart + i2) != textEditState2.text.charAt(textEditState2.composingStart + i2)) {
                return true;
            }
        }
        return false;
    }

    void setTextInputEditingState(View view, TextInputChannel.TextEditState textEditState) {
        TextInputChannel.TextEditState textEditState2;
        if (!this.mRestartInputPending && (textEditState2 = this.mLastKnownFrameworkTextEditingState) != null && textEditState2.hasComposing()) {
            this.mRestartInputPending = composingChanged(this.mLastKnownFrameworkTextEditingState, textEditState);
            if (this.mRestartInputPending) {
                Log.i("TextInputPlugin", "Composing region changed by the framework. Restarting the input method.");
            }
        }
        this.mLastKnownFrameworkTextEditingState = textEditState;
        this.mEditable.setEditingState(textEditState);
        if (this.mRestartInputPending) {
            this.mImm.restartInput(view);
            this.mRestartInputPending = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveEditableSizeAndTransform(double d, double d2, final double[] dArr) {
        final double[] dArr2 = new double[4];
        final boolean z = dArr[3] == 0.0d && dArr[7] == 0.0d && dArr[15] == 1.0d;
        double d3 = dArr[12] / dArr[15];
        dArr2[1] = d3;
        dArr2[0] = d3;
        double d4 = dArr[13] / dArr[15];
        dArr2[3] = d4;
        dArr2[2] = d4;
        AnonymousClass2 r14 = new MinMax() { // from class: io.flutter.plugin.editing.TextInputPlugin.2
            @Override // io.flutter.plugin.editing.TextInputPlugin.MinMax
            public void inspect(double d5, double d6) {
                double d7 = 1.0d;
                if (!z) {
                    double[] dArr3 = dArr;
                    d7 = 1.0d / (((dArr3[3] * d5) + (dArr3[7] * d6)) + dArr3[15]);
                }
                double[] dArr4 = dArr;
                double d8 = ((dArr4[0] * d5) + (dArr4[4] * d6) + dArr4[12]) * d7;
                double d9 = ((dArr4[1] * d5) + (dArr4[5] * d6) + dArr4[13]) * d7;
                double[] dArr5 = dArr2;
                if (d8 < dArr5[0]) {
                    dArr5[0] = d8;
                } else if (d8 > dArr5[1]) {
                    dArr5[1] = d8;
                }
                double[] dArr6 = dArr2;
                if (d9 < dArr6[2]) {
                    dArr6[2] = d9;
                } else if (d9 > dArr6[3]) {
                    dArr6[3] = d9;
                }
            }
        };
        r14.inspect(d, 0.0d);
        r14.inspect(d, d2);
        r14.inspect(0.0d, d2);
        Float valueOf = Float.valueOf(this.mView.getContext().getResources().getDisplayMetrics().density);
        this.lastClientRect = new Rect((int) (dArr2[0] * ((double) valueOf.floatValue())), (int) (dArr2[2] * ((double) valueOf.floatValue())), (int) Math.ceil(dArr2[1] * ((double) valueOf.floatValue())), (int) Math.ceil(dArr2[3] * ((double) valueOf.floatValue())));
    }

    void clearTextInputClient() {
        if (this.inputTarget.type != InputTarget.Type.VD_PLATFORM_VIEW) {
            this.mEditable.removeEditingStateListener(this);
            notifyViewExited();
            updateAutofillConfigurationIfNeeded(null);
            this.inputTarget = new InputTarget(InputTarget.Type.NO_TARGET, 0);
            unlockPlatformViewInputConnection();
            this.lastClientRect = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes5.dex */
    public static class InputTarget {
        int id;
        Type type;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
         */
        /* loaded from: classes5.dex */
        public enum Type {
            NO_TARGET,
            FRAMEWORK_CLIENT,
            VD_PLATFORM_VIEW,
            HC_PLATFORM_VIEW
        }

        public InputTarget(Type type, int i) {
            this.type = type;
            this.id = i;
        }
    }

    public boolean handleKeyEvent(KeyEvent keyEvent) {
        InputConnection inputConnection;
        if (!getInputMethodManager().isAcceptingText() || (inputConnection = this.lastInputConnection) == null) {
            return false;
        }
        if (inputConnection instanceof InputConnectionAdaptor) {
            return ((InputConnectionAdaptor) inputConnection).handleKeyEvent(keyEvent);
        }
        return inputConnection.sendKeyEvent(keyEvent);
    }

    @Override // io.flutter.plugin.editing.ListenableEditingState.EditingStateWatcher
    public void didChangeEditingState(boolean z, boolean z2, boolean z3) {
        if (z) {
            notifyValueChanged(this.mEditable.toString());
        }
        int selectionStart = this.mEditable.getSelectionStart();
        int selectionEnd = this.mEditable.getSelectionEnd();
        int composingStart = this.mEditable.getComposingStart();
        int composingEnd = this.mEditable.getComposingEnd();
        ArrayList<TextEditingDelta> extractBatchTextEditingDeltas = this.mEditable.extractBatchTextEditingDeltas();
        if (!(this.mLastKnownFrameworkTextEditingState == null || (this.mEditable.toString().equals(this.mLastKnownFrameworkTextEditingState.text) && selectionStart == this.mLastKnownFrameworkTextEditingState.selectionStart && selectionEnd == this.mLastKnownFrameworkTextEditingState.selectionEnd && composingStart == this.mLastKnownFrameworkTextEditingState.composingStart && composingEnd == this.mLastKnownFrameworkTextEditingState.composingEnd))) {
            Log.v("TextInputPlugin", "send EditingState to flutter: " + this.mEditable.toString());
            if (this.configuration.enableDeltaModel) {
                this.textInputChannel.updateEditingStateWithDeltas(this.inputTarget.id, extractBatchTextEditingDeltas);
                this.mEditable.clearBatchDeltas();
            } else {
                this.textInputChannel.updateEditingState(this.inputTarget.id, this.mEditable.toString(), selectionStart, selectionEnd, composingStart, composingEnd);
            }
            this.mLastKnownFrameworkTextEditingState = new TextInputChannel.TextEditState(this.mEditable.toString(), selectionStart, selectionEnd, composingStart, composingEnd);
            return;
        }
        this.mEditable.clearBatchDeltas();
    }

    private boolean needsAutofill() {
        return this.mAutofillConfigurations != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewEntered() {
        if (Build.VERSION.SDK_INT >= 26 && this.afm != null && needsAutofill()) {
            String str = this.configuration.autofill.uniqueIdentifier;
            int[] iArr = new int[2];
            this.mView.getLocationOnScreen(iArr);
            Rect rect = new Rect(this.lastClientRect);
            rect.offset(iArr[0], iArr[1]);
            this.afm.notifyViewEntered(this.mView, str.hashCode(), rect);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewExited() {
        TextInputChannel.Configuration configuration;
        if (Build.VERSION.SDK_INT >= 26 && this.afm != null && (configuration = this.configuration) != null && configuration.autofill != null && needsAutofill()) {
            this.afm.notifyViewExited(this.mView, this.configuration.autofill.uniqueIdentifier.hashCode());
        }
    }

    private void notifyValueChanged(String str) {
        if (Build.VERSION.SDK_INT >= 26 && this.afm != null && needsAutofill()) {
            this.afm.notifyValueChanged(this.mView, this.configuration.autofill.uniqueIdentifier.hashCode(), AutofillValue.forText(str));
        }
    }

    private void updateAutofillConfigurationIfNeeded(TextInputChannel.Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 26) {
            if (configuration == null || configuration.autofill == null) {
                this.mAutofillConfigurations = null;
                return;
            }
            TextInputChannel.Configuration[] configurationArr = configuration.fields;
            this.mAutofillConfigurations = new SparseArray<>();
            if (configurationArr == null) {
                this.mAutofillConfigurations.put(configuration.autofill.uniqueIdentifier.hashCode(), configuration);
                return;
            }
            for (TextInputChannel.Configuration configuration2 : configurationArr) {
                TextInputChannel.Configuration.Autofill autofill = configuration2.autofill;
                if (autofill != null) {
                    this.mAutofillConfigurations.put(autofill.uniqueIdentifier.hashCode(), configuration2);
                    this.afm.notifyValueChanged(this.mView, autofill.uniqueIdentifier.hashCode(), AutofillValue.forText(autofill.editState.text));
                }
            }
        }
    }

    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        Rect rect;
        if (Build.VERSION.SDK_INT >= 26 && needsAutofill()) {
            String str = this.configuration.autofill.uniqueIdentifier;
            AutofillId autofillId = viewStructure.getAutofillId();
            for (int i2 = 0; i2 < this.mAutofillConfigurations.size(); i2++) {
                int keyAt = this.mAutofillConfigurations.keyAt(i2);
                TextInputChannel.Configuration.Autofill autofill = this.mAutofillConfigurations.valueAt(i2).autofill;
                if (autofill != null) {
                    viewStructure.addChildCount(1);
                    ViewStructure newChild = viewStructure.newChild(i2);
                    newChild.setAutofillId(autofillId, keyAt);
                    newChild.setAutofillHints(autofill.hints);
                    newChild.setAutofillType(1);
                    newChild.setVisibility(0);
                    if (autofill.hintText != null) {
                        newChild.setHint(autofill.hintText);
                    }
                    if (str.hashCode() != keyAt || (rect = this.lastClientRect) == null) {
                        newChild.setDimens(0, 0, 0, 0, 1, 1);
                        newChild.setAutofillValue(AutofillValue.forText(autofill.editState.text));
                    } else {
                        newChild.setDimens(rect.left, this.lastClientRect.top, 0, 0, this.lastClientRect.width(), this.lastClientRect.height());
                        newChild.setAutofillValue(AutofillValue.forText(this.mEditable));
                    }
                }
            }
        }
    }

    public void autofill(SparseArray<AutofillValue> sparseArray) {
        TextInputChannel.Configuration.Autofill autofill;
        if (Build.VERSION.SDK_INT >= 26 && (autofill = this.configuration.autofill) != null) {
            HashMap<String, TextInputChannel.TextEditState> hashMap = new HashMap<>();
            for (int i = 0; i < sparseArray.size(); i++) {
                TextInputChannel.Configuration configuration = this.mAutofillConfigurations.get(sparseArray.keyAt(i));
                if (!(configuration == null || configuration.autofill == null)) {
                    TextInputChannel.Configuration.Autofill autofill2 = configuration.autofill;
                    String charSequence = sparseArray.valueAt(i).getTextValue().toString();
                    TextInputChannel.TextEditState textEditState = new TextInputChannel.TextEditState(charSequence, charSequence.length(), charSequence.length(), -1, -1);
                    if (autofill2.uniqueIdentifier.equals(autofill.uniqueIdentifier)) {
                        this.mEditable.setEditingState(textEditState);
                    } else {
                        hashMap.put(autofill2.uniqueIdentifier, textEditState);
                    }
                }
            }
            this.textInputChannel.updateEditingStateWithTag(this.inputTarget.id, hashMap);
        }
    }
}
