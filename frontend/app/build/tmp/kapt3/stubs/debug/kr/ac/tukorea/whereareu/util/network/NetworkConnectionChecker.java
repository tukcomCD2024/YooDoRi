package kr.ac.tukorea.whereareu.util.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lkr/ac/tukorea/whereareu/util/network/NetworkConnectionChecker;", "Landroid/net/ConnectivityManager$NetworkCallback;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "connectivityManager", "Landroid/net/ConnectivityManager;", "networkRequest", "Landroid/net/NetworkRequest;", "isOnline", "", "register", "", "unregister", "app_debug"})
public final class NetworkConnectionChecker extends android.net.ConnectivityManager.NetworkCallback {
    @org.jetbrains.annotations.NotNull()
    private final android.net.NetworkRequest networkRequest = null;
    @org.jetbrains.annotations.NotNull()
    private final android.net.ConnectivityManager connectivityManager = null;
    
    public NetworkConnectionChecker(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void register() {
    }
    
    public final void unregister() {
    }
    
    public final boolean isOnline() {
        return false;
    }
}