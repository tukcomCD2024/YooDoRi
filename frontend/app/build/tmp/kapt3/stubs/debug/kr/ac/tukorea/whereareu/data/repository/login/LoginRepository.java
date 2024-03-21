package kr.ac.tukorea.whereareu.data.repository.login;

import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.CheckInterConnectResponse;
import kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.DementiaIdentityResponse;
import kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse;
import kr.ac.tukorea.whereareu.util.network.NetworkResult;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u0005\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lkr/ac/tukorea/whereareu/data/repository/login/LoginRepository;", "", "checkInterConnected", "Lkr/ac/tukorea/whereareu/util/network/NetworkResult;", "Lkr/ac/tukorea/whereareu/data/model/login/response/CheckInterConnectResponse;", "request", "Lkr/ac/tukorea/whereareu/data/model/login/request/CheckInterConnectRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/CheckInterConnectRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendDementiaIdentity", "Lkr/ac/tukorea/whereareu/data/model/login/response/DementiaIdentityResponse;", "Lkr/ac/tukorea/whereareu/data/model/login/request/DementiaIdentityRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/DementiaIdentityRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendNokIdentity", "Lkr/ac/tukorea/whereareu/data/model/login/response/NokIdentityResponse;", "Lkr/ac/tukorea/whereareu/data/model/login/request/NokIdentityRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/NokIdentityRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface LoginRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendNokIdentity(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendDementiaIdentity(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<kr.ac.tukorea.whereareu.data.model.login.response.DementiaIdentityResponse>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkInterConnected(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<kr.ac.tukorea.whereareu.data.model.login.response.CheckInterConnectResponse>> $completion);
}