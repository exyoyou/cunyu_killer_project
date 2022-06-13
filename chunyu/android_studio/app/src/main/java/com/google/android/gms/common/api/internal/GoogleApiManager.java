package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zal;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class GoogleApiManager implements Handler.Callback {
    private static GoogleApiManager zaib;
    private final Handler handler;
    private final Context zaic;
    private final GoogleApiAvailability zaid;
    private final GoogleApiAvailabilityCache zaie;
    public static final Status zahw = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zahx = new Status(4, "The user must be signed in to make this API call.");
    private static final Object lock = new Object();
    private long zahy = 5000;
    private long zahz = 120000;
    private long zaia = 10000;
    private final AtomicInteger zaif = new AtomicInteger(1);
    private final AtomicInteger zaig = new AtomicInteger(0);
    private final Map<zai<?>, zaa<?>> zaih = new ConcurrentHashMap(5, 0.75f, 1);
    private zaae zaii = null;
    private final Set<zai<?>> zaij = new ArraySet();
    private final Set<zai<?>> zaik = new ArraySet();

    public static GoogleApiManager zab(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zaib == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zaib = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zaib;
        }
        return googleApiManager;
    }

    /* loaded from: classes4.dex */
    public static class zab {
        private final zai<?> zaja;
        private final Feature zajb;

        private zab(zai<?> zai, Feature feature) {
            this.zaja = zai;
            this.zajb = feature;
        }

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof zab)) {
                zab zab = (zab) obj;
                if (Objects.equal(this.zaja, zab.zaja) && Objects.equal(this.zajb, zab.zajb)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zaja, this.zajb);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", this.zaja).add("feature", this.zajb).toString();
        }

        /* synthetic */ zab(zai zai, Feature feature, zabi zabi) {
            this(zai, feature);
        }
    }

    /* loaded from: classes4.dex */
    public class zac implements zach, BaseGmsClient.ConnectionProgressReportCallbacks {
        private final zai<?> zafp;
        private final Api.Client zain;
        private IAccountAccessor zajc = null;
        private Set<Scope> zajd = null;
        private boolean zaje = false;

        public zac(Api.Client client, zai<?> zai) {
            GoogleApiManager.this = r1;
            this.zain = client;
            this.zafp = zai;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post(new zabo(this, connectionResult));
        }

        @Override // com.google.android.gms.common.api.internal.zach
        public final void zag(ConnectionResult connectionResult) {
            ((zaa) GoogleApiManager.this.zaih.get(this.zafp)).zag(connectionResult);
        }

        @Override // com.google.android.gms.common.api.internal.zach
        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zag(new ConnectionResult(4));
                return;
            }
            this.zajc = iAccountAccessor;
            this.zajd = set;
            zabr();
        }

        public final void zabr() {
            IAccountAccessor iAccountAccessor;
            if (this.zaje && (iAccountAccessor = this.zajc) != null) {
                this.zain.getRemoteService(iAccountAccessor, this.zajd);
            }
        }
    }

    public static GoogleApiManager zabc() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zaib, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zaib;
        }
        return googleApiManager;
    }

    public static void reportSignOut() {
        synchronized (lock) {
            if (zaib != null) {
                GoogleApiManager googleApiManager = zaib;
                googleApiManager.zaig.incrementAndGet();
                googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
            }
        }
    }

    /* loaded from: classes4.dex */
    public class zaa<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zar {
        private final zai<O> zafp;
        private final Api.Client zain;
        private final Api.AnyClient zaio;
        private final zaab zaip;
        private final int zais;
        private final zace zait;
        private boolean zaiu;
        private final Queue<zab> zaim = new LinkedList();
        private final Set<zak> zaiq = new HashSet();
        private final Map<ListenerHolder.ListenerKey<?>, zabw> zair = new HashMap();
        private final List<zab> zaiv = new ArrayList();
        private ConnectionResult zaiw = null;

        public zaa(GoogleApi<O> googleApi) {
            GoogleApiManager.this = r4;
            this.zain = googleApi.zaa(r4.handler.getLooper(), this);
            Api.Client client = this.zain;
            if (client instanceof SimpleClientAdapter) {
                this.zaio = ((SimpleClientAdapter) client).getClient();
            } else {
                this.zaio = client;
            }
            this.zafp = googleApi.zak();
            this.zaip = new zaab();
            this.zais = googleApi.getInstanceId();
            if (this.zain.requiresSignIn()) {
                this.zait = googleApi.zaa(r4.zaic, r4.handler);
            } else {
                this.zait = null;
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnected(Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabg();
            } else {
                GoogleApiManager.this.handler.post(new zabj(this));
            }
        }

        public final void zabg() {
            zabl();
            zai(ConnectionResult.RESULT_SUCCESS);
            zabn();
            Iterator<zabw> it = this.zair.values().iterator();
            while (it.hasNext()) {
                zabw next = it.next();
                if (zaa(next.zajw.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        next.zajw.registerListener(this.zaio, new TaskCompletionSource<>());
                    } catch (DeadObjectException unused) {
                        onConnectionSuspended(1);
                        this.zain.disconnect();
                    } catch (RemoteException unused2) {
                        it.remove();
                    }
                }
            }
            zabi();
            zabo();
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabh();
            } else {
                GoogleApiManager.this.handler.post(new zabk(this));
            }
        }

        public final void zabh() {
            zabl();
            this.zaiu = true;
            this.zaip.zaai();
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafp), GoogleApiManager.this.zahy);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 11, this.zafp), GoogleApiManager.this.zahz);
            GoogleApiManager.this.zaie.flush();
        }

        public final void zag(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zain.disconnect();
            onConnectionFailed(connectionResult);
        }

        private final boolean zah(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                if (GoogleApiManager.this.zaii == null || !GoogleApiManager.this.zaij.contains(this.zafp)) {
                    return false;
                }
                GoogleApiManager.this.zaii.zab(connectionResult, this.zais);
                return true;
            }
        }

        @Override // com.google.android.gms.common.api.internal.zar
        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                GoogleApiManager.this.handler.post(new zabl(this, connectionResult));
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zace zace = this.zait;
            if (zace != null) {
                zace.zabs();
            }
            zabl();
            GoogleApiManager.this.zaie.flush();
            zai(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zac(GoogleApiManager.zahx);
            } else if (this.zaim.isEmpty()) {
                this.zaiw = connectionResult;
            } else if (!zah(connectionResult) && !GoogleApiManager.this.zac(connectionResult, this.zais)) {
                if (connectionResult.getErrorCode() == 18) {
                    this.zaiu = true;
                }
                if (this.zaiu) {
                    GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafp), GoogleApiManager.this.zahy);
                    return;
                }
                String zan = this.zafp.zan();
                StringBuilder sb = new StringBuilder(String.valueOf(zan).length() + 38);
                sb.append("API: ");
                sb.append(zan);
                sb.append(" is not available on this device.");
                zac(new Status(17, sb.toString()));
            }
        }

        private final void zabi() {
            ArrayList arrayList = new ArrayList(this.zaim);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                zab zab = (zab) obj;
                if (!this.zain.isConnected()) {
                    return;
                }
                if (zab(zab)) {
                    this.zaim.remove(zab);
                }
            }
        }

        public final void zaa(zab zab) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zain.isConnected()) {
                this.zaim.add(zab);
                ConnectionResult connectionResult = this.zaiw;
                if (connectionResult == null || !connectionResult.hasResolution()) {
                    connect();
                } else {
                    onConnectionFailed(this.zaiw);
                }
            } else if (zab(zab)) {
                zabo();
            } else {
                this.zaim.add(zab);
            }
        }

        public final void zabj() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zac(GoogleApiManager.zahw);
            this.zaip.zaah();
            for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.zair.keySet().toArray(new ListenerHolder.ListenerKey[this.zair.size()])) {
                zaa(new zah(listenerKey, new TaskCompletionSource()));
            }
            zai(new ConnectionResult(4));
            if (this.zain.isConnected()) {
                this.zain.onUserSignOut(new zabm(this));
            }
        }

        public final Api.Client zaab() {
            return this.zain;
        }

        public final Map<ListenerHolder.ListenerKey<?>, zabw> zabk() {
            return this.zair;
        }

        public final void zabl() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaiw = null;
        }

        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            return this.zaiw;
        }

        private final boolean zab(zab zab) {
            if (!(zab instanceof zac)) {
                zac(zab);
                return true;
            }
            zac zac = (zac) zab;
            Feature zaa = zaa(zac.zab((zaa<?>) this));
            if (zaa == null) {
                zac(zab);
                return true;
            } else if (zac.zac(this)) {
                zab zab2 = new zab(this.zafp, zaa, null);
                int indexOf = this.zaiv.indexOf(zab2);
                if (indexOf >= 0) {
                    zab zab3 = this.zaiv.get(indexOf);
                    GoogleApiManager.this.handler.removeMessages(15, zab3);
                    GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zab3), GoogleApiManager.this.zahy);
                    return false;
                }
                this.zaiv.add(zab2);
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zab2), GoogleApiManager.this.zahy);
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 16, zab2), GoogleApiManager.this.zahz);
                ConnectionResult connectionResult = new ConnectionResult(2, null);
                if (zah(connectionResult)) {
                    return false;
                }
                GoogleApiManager.this.zac(connectionResult, this.zais);
                return false;
            } else {
                zac.zaa(new UnsupportedApiCallException(zaa));
                return false;
            }
        }

        private final void zac(zab zab) {
            zab.zaa(this.zaip, requiresSignIn());
            try {
                zab.zaa((zaa<?>) this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.zain.disconnect();
            }
        }

        public final void zac(Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            for (zab zab : this.zaim) {
                zab.zaa(status);
            }
            this.zaim.clear();
        }

        public final void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiu) {
                connect();
            }
        }

        private final void zabn() {
            if (this.zaiu) {
                GoogleApiManager.this.handler.removeMessages(11, this.zafp);
                GoogleApiManager.this.handler.removeMessages(9, this.zafp);
                this.zaiu = false;
            }
        }

        public final void zaav() {
            Status status;
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiu) {
                zabn();
                if (GoogleApiManager.this.zaid.isGooglePlayServicesAvailable(GoogleApiManager.this.zaic) == 18) {
                    status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                } else {
                    status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                zac(status);
                this.zain.disconnect();
            }
        }

        private final void zabo() {
            GoogleApiManager.this.handler.removeMessages(12, this.zafp);
            GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(12, this.zafp), GoogleApiManager.this.zaia);
        }

        public final boolean zabp() {
            return zac(true);
        }

        public final boolean zac(boolean z) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zain.isConnected() || this.zair.size() != 0) {
                return false;
            }
            if (this.zaip.zaag()) {
                if (z) {
                    zabo();
                }
                return false;
            }
            this.zain.disconnect();
            return true;
        }

        public final void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zain.isConnected() && !this.zain.isConnecting()) {
                int clientAvailability = GoogleApiManager.this.zaie.getClientAvailability(GoogleApiManager.this.zaic, this.zain);
                if (clientAvailability != 0) {
                    onConnectionFailed(new ConnectionResult(clientAvailability, null));
                    return;
                }
                zac zac = new zac(this.zain, this.zafp);
                if (this.zain.requiresSignIn()) {
                    this.zait.zaa(zac);
                }
                this.zain.connect(zac);
            }
        }

        public final void zaa(zak zak) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaiq.add(zak);
        }

        private final void zai(ConnectionResult connectionResult) {
            for (zak zak : this.zaiq) {
                String str = null;
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.zain.getEndpointPackageName();
                }
                zak.zaa(this.zafp, connectionResult, str);
            }
            this.zaiq.clear();
        }

        final boolean isConnected() {
            return this.zain.isConnected();
        }

        public final boolean requiresSignIn() {
            return this.zain.requiresSignIn();
        }

        public final int getInstanceId() {
            return this.zais;
        }

        final zad zabq() {
            zace zace = this.zait;
            if (zace == null) {
                return null;
            }
            return zace.zabq();
        }

        private final Feature zaa(Feature[] featureArr) {
            if (!(featureArr == null || featureArr.length == 0)) {
                Feature[] availableFeatures = this.zain.getAvailableFeatures();
                if (availableFeatures == null) {
                    availableFeatures = new Feature[0];
                }
                ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
                for (Feature feature : availableFeatures) {
                    arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
                }
                for (Feature feature2 : featureArr) {
                    if (!arrayMap.containsKey(feature2.getName()) || ((Long) arrayMap.get(feature2.getName())).longValue() < feature2.getVersion()) {
                        return feature2;
                    }
                }
            }
            return null;
        }

        public final void zaa(zab zab) {
            if (!this.zaiv.contains(zab) || this.zaiu) {
                return;
            }
            if (!this.zain.isConnected()) {
                connect();
            } else {
                zabi();
            }
        }

        public final void zab(zab zab) {
            Feature[] zab2;
            if (this.zaiv.remove(zab)) {
                GoogleApiManager.this.handler.removeMessages(15, zab);
                GoogleApiManager.this.handler.removeMessages(16, zab);
                Feature feature = zab.zajb;
                ArrayList arrayList = new ArrayList(this.zaim.size());
                for (zab zab3 : this.zaim) {
                    if ((zab3 instanceof zac) && (zab2 = ((zac) zab3).zab((zaa<?>) this)) != null && ArrayUtils.contains(zab2, feature)) {
                        arrayList.add(zab3);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zab zab4 = (zab) obj;
                    this.zaim.remove(zab4);
                    zab4.zaa(new UnsupportedApiCallException(feature));
                }
            }
        }

        public static /* synthetic */ void zae(zaa zaa) {
            zaa.zabg();
        }
    }

    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zaic = context;
        this.handler = new zal(looper, this);
        this.zaid = googleApiAvailability;
        this.zaie = new GoogleApiAvailabilityCache(googleApiAvailability);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(6));
    }

    public final int zabd() {
        return this.zaif.getAndIncrement();
    }

    public final void zaa(GoogleApi<?> googleApi) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    private final void zab(GoogleApi<?> googleApi) {
        zai<?> zak = googleApi.zak();
        zaa<?> zaa2 = this.zaih.get(zak);
        if (zaa2 == null) {
            zaa2 = new zaa<>(googleApi);
            this.zaih.put(zak, zaa2);
        }
        if (zaa2.requiresSignIn()) {
            this.zaik.add(zak);
        }
        zaa2.connect();
    }

    public final void zaa(zaae zaae) {
        synchronized (lock) {
            if (this.zaii != zaae) {
                this.zaii = zaae;
                this.zaij.clear();
            }
            this.zaij.addAll(zaae.zaaj());
        }
    }

    public final void zab(zaae zaae) {
        synchronized (lock) {
            if (this.zaii == zaae) {
                this.zaii = null;
                this.zaij.clear();
            }
        }
    }

    public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> iterable) {
        zak zak = new zak(iterable);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(2, zak));
        return zak.getTask();
    }

    public final void zao() {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void maybeSignOut() {
        this.zaig.incrementAndGet();
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(10));
    }

    public final Task<Boolean> zac(GoogleApi<?> googleApi) {
        zaaf zaaf = new zaaf(googleApi.zak());
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(14, zaaf));
        return zaaf.zaal().getTask();
    }

    public final <O extends Api.ApiOptions> void zaa(GoogleApi<O> googleApi, int i, BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> apiMethodImpl) {
        zae zae = new zae(i, apiMethodImpl);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, new zabv(zae, this.zaig.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions, ResultT> void zaa(GoogleApi<O> googleApi, int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        zag zag = new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, new zabv(zag, this.zaig.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions> Task<Void> zaa(GoogleApi<O> googleApi, RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaf zaf = new zaf(new zabw(registerListenerMethod, unregisterListenerMethod), taskCompletionSource);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(8, new zabv(zaf, this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends Api.ApiOptions> Task<Boolean> zaa(GoogleApi<O> googleApi, ListenerHolder.ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zah zah = new zah(listenerKey, taskCompletionSource);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(13, new zabv(zah, this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        zaa<?> zaa2;
        long j = 300000;
        switch (message.what) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.zaia = j;
                this.handler.removeMessages(12);
                for (zai<?> zai : this.zaih.keySet()) {
                    Handler handler = this.handler;
                    handler.sendMessageDelayed(handler.obtainMessage(12, zai), this.zaia);
                }
                break;
            case 2:
                zak zak = (zak) message.obj;
                Iterator<zai<?>> it = zak.zap().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        zai<?> next = it.next();
                        zaa<?> zaa3 = this.zaih.get(next);
                        if (zaa3 == null) {
                            zak.zaa(next, new ConnectionResult(13), null);
                            break;
                        } else if (zaa3.isConnected()) {
                            zak.zaa(next, ConnectionResult.RESULT_SUCCESS, zaa3.zaab().getEndpointPackageName());
                        } else if (zaa3.zabm() != null) {
                            zak.zaa(next, zaa3.zabm(), null);
                        } else {
                            zaa3.zaa(zak);
                            zaa3.connect();
                        }
                    }
                }
            case 3:
                for (zaa<?> zaa4 : this.zaih.values()) {
                    zaa4.zabl();
                    zaa4.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zabv zabv = (zabv) message.obj;
                zaa<?> zaa5 = this.zaih.get(zabv.zajs.zak());
                if (zaa5 == null) {
                    zab(zabv.zajs);
                    zaa5 = this.zaih.get(zabv.zajs.zak());
                }
                if (!zaa5.requiresSignIn() || this.zaig.get() == zabv.zajr) {
                    zaa5.zaa(zabv.zajq);
                    break;
                } else {
                    zabv.zajq.zaa(zahw);
                    zaa5.zabj();
                    break;
                }
                break;
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zaa<?>> it2 = this.zaih.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zaa2 = it2.next();
                        if (zaa2.getInstanceId() == i) {
                        }
                    } else {
                        zaa2 = null;
                    }
                }
                if (zaa2 != null) {
                    String errorString = this.zaid.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    StringBuilder sb = new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length());
                    sb.append("Error resolution was canceled by the user, original error message: ");
                    sb.append(errorString);
                    sb.append(": ");
                    sb.append(errorMessage);
                    zaa2.zac(new Status(17, sb.toString()));
                    break;
                } else {
                    StringBuilder sb2 = new StringBuilder(76);
                    sb2.append("Could not find API instance ");
                    sb2.append(i);
                    sb2.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb2.toString(), new Exception());
                    break;
                }
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zaic.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zaic.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabi(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaia = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zab((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zaih.containsKey(message.obj)) {
                    this.zaih.get(message.obj).resume();
                    break;
                }
                break;
            case 10:
                for (zai<?> zai2 : this.zaik) {
                    this.zaih.remove(zai2).zabj();
                }
                this.zaik.clear();
                break;
            case 11:
                if (this.zaih.containsKey(message.obj)) {
                    this.zaih.get(message.obj).zaav();
                    break;
                }
                break;
            case 12:
                if (this.zaih.containsKey(message.obj)) {
                    this.zaih.get(message.obj).zabp();
                    break;
                }
                break;
            case 14:
                zaaf zaaf = (zaaf) message.obj;
                zai<?> zak2 = zaaf.zak();
                if (!this.zaih.containsKey(zak2)) {
                    zaaf.zaal().setResult(false);
                    break;
                } else {
                    zaaf.zaal().setResult(Boolean.valueOf(this.zaih.get(zak2).zac(false)));
                    break;
                }
            case 15:
                zab zab2 = (zab) message.obj;
                if (this.zaih.containsKey(zab2.zaja)) {
                    this.zaih.get(zab2.zaja).zaa(zab2);
                    break;
                }
                break;
            case 16:
                zab zab3 = (zab) message.obj;
                if (this.zaih.containsKey(zab3.zaja)) {
                    this.zaih.get(zab3.zaja).zab(zab3);
                    break;
                }
                break;
            default:
                int i2 = message.what;
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i2);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
        return true;
    }

    public final PendingIntent zaa(zai<?> zai, int i) {
        zad zabq;
        zaa<?> zaa2 = this.zaih.get(zai);
        if (zaa2 == null || (zabq = zaa2.zabq()) == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaic, i, zabq.getSignInIntent(), 134217728);
    }

    final boolean zac(ConnectionResult connectionResult, int i) {
        return this.zaid.zaa(this.zaic, connectionResult, i);
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        if (!zac(connectionResult, i)) {
            Handler handler = this.handler;
            handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
        }
    }
}
