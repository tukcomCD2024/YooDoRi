package kr.ac.tukorea.whereareu.data.repository.login;

import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse;
import kr.ac.tukorea.whereareu.data.api.LoginService;
import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.CheckInterConnectResponse;
import kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.ResponseBody;
import kr.ac.tukorea.whereareu.data.model.login.response.DementiaIdentityResponse;
import kr.ac.tukorea.whereareu.util.network.NetworkResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\b\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00062\u0006\u0010\b\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lkr/ac/tukorea/whereareu/data/repository/login/LoginRepositoryImpl;", "Lkr/ac/tukorea/whereareu/data/repository/login/LoginRepository;", "api", "Lkr/ac/tukorea/whereareu/data/api/LoginService;", "(Lkr/ac/tukorea/whereareu/data/api/LoginService;)V", "checkInterConnected", "Lkr/ac/tukorea/whereareu/util/network/NetworkResult;", "Lkr/ac/tukorea/whereareu/data/model/login/response/CheckInterConnectResponse;", "request", "Lkr/ac/tukorea/whereareu/data/model/login/request/CheckInterConnectRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/CheckInterConnectRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendDementiaIdentity", "Lkr/ac/tukorea/whereareu/data/model/login/response/DementiaIdentityResponse;", "Lkr/ac/tukorea/whereareu/data/model/login/request/DementiaIdentityRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/DementiaIdentityRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendNokIdentity", "Lkr/ac/tukorea/whereareu/data/model/login/response/NokIdentityResponse;", "Lkr/ac/tukorea/whereareu/data/model/login/request/NokIdentityRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/NokIdentityRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LoginRepositoryImpl implements kr.ac.tukorea.whereareu.data.repository.login.LoginRepository {
    @org.jetbrains.annotations.NotNull()
    private final kr.ac.tukorea.whereareu.data.api.LoginService api = null;
    
    @javax.inject.Inject()
    public LoginRepositoryImpl(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.api.LoginService api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendNokIdentity(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendDementiaIdentity(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<kr.ac.tukorea.whereareu.data.model.login.response.DementiaIdentityResponse>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object checkInterConnected(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<kr.ac.tukorea.whereareu.data.model.login.response.CheckInterConnectResponse>> $completion) {
        return null;
    }
}