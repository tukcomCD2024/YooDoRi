package kr.ac.tukorea.whereareu.util.network;

import kr.ac.tukorea.whereareu.WhereAreUApplication;
import kr.ac.tukorea.whereareu.di.NetworkModule;
import retrofit2.Response;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0002\u00a2\u0006\u0002\u0010\u0007\u001a`\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\n*\u00020\u00032\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00060\r\u0012\u0006\u0012\u0004\u0018\u00010\u00030\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\fH\u0086@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"getFailDataResult", "Lkr/ac/tukorea/whereareu/util/network/NetworkResult$Fail;", "T", "", "body", "response", "Lretrofit2/Response;", "(Ljava/lang/Object;Lretrofit2/Response;)Lkr/ac/tukorea/whereareu/util/network/NetworkResult$Fail;", "handleApi", "Lkr/ac/tukorea/whereareu/util/network/NetworkResult;", "R", "execute", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "mapper", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class HandleApiKt {
    
    @org.jetbrains.annotations.Nullable()
    public static final <T extends java.lang.Object, R extends java.lang.Object>java.lang.Object handleApi(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super retrofit2.Response<T>>, ? extends java.lang.Object> execute, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, ? extends R> mapper, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<? extends R>> $completion) {
        return null;
    }
    
    private static final <T extends java.lang.Object>kr.ac.tukorea.whereareu.util.network.NetworkResult.Fail getFailDataResult(T body, retrofit2.Response<T> response) {
        return null;
    }
}