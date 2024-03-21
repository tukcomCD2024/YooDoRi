package kr.ac.tukorea.whereareu.util.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u00020\u00070\u0004H\u0086\b\u00f8\u0001\u0000\u001a:\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u00020\u00070\u0004H\u0086\b\u00f8\u0001\u0000\u001a6\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\u0004H\u0086\b\u00f8\u0001\u0000\u001a6\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u0004H\u0086\b\u00f8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\r"}, d2 = {"onError", "Lkr/ac/tukorea/whereareu/util/network/NetworkResult;", "T", "action", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "onException", "onFail", "resultCode", "", "onSuccess", "app_debug"})
public final class NetworkResultKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>kr.ac.tukorea.whereareu.util.network.NetworkResult<T> onSuccess(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.network.NetworkResult<? extends T> $this$onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> action) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>kr.ac.tukorea.whereareu.util.network.NetworkResult<T> onFail(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.network.NetworkResult<? extends T> $this$onFail, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> resultCode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>kr.ac.tukorea.whereareu.util.network.NetworkResult<T> onError(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.network.NetworkResult<? extends T> $this$onError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> action) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>kr.ac.tukorea.whereareu.util.network.NetworkResult<T> onException(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.network.NetworkResult<? extends T> $this$onException, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> action) {
        return null;
    }
}