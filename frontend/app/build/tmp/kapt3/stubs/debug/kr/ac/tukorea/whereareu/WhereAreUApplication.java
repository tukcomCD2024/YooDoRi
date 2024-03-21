package kr.ac.tukorea.whereareu;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.StringRes;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import kr.ac.tukorea.whereareu.util.network.NetworkConnectionChecker;
import dagger.hilt.android.HiltAndroidApp;

@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \n2\u00020\u00012\u00020\u0002:\u0001\nB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\u000b"}, d2 = {"Lkr/ac/tukorea/whereareu/WhereAreUApplication;", "Landroid/app/Application;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "()V", "onCreate", "", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "Companion", "app_debug"})
public final class WhereAreUApplication extends android.app.Application implements androidx.lifecycle.DefaultLifecycleObserver {
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    private static android.content.Context context;
    private static kr.ac.tukorea.whereareu.util.network.NetworkConnectionChecker networkConnectionChecker;
    private static kr.ac.tukorea.whereareu.WhereAreUApplication instance;
    @org.jetbrains.annotations.NotNull()
    public static final kr.ac.tukorea.whereareu.WhereAreUApplication.Companion Companion = null;
    
    public WhereAreUApplication() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onStop(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner owner) {
    }
    
    @java.lang.Override()
    public void onStart(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner owner) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004J\u0010\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lkr/ac/tukorea/whereareu/WhereAreUApplication$Companion;", "", "()V", "context", "Landroid/content/Context;", "instance", "Lkr/ac/tukorea/whereareu/WhereAreUApplication;", "networkConnectionChecker", "Lkr/ac/tukorea/whereareu/util/network/NetworkConnectionChecker;", "applicationContext", "getString", "", "stringResId", "", "isOnline", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getString(@androidx.annotation.StringRes()
        int stringResId) {
            return null;
        }
        
        public final boolean isOnline() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Context applicationContext() {
            return null;
        }
    }
}