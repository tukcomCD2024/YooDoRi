package kr.ac.tukorea.whereareu.data.api;

import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.CheckInterConnectResponse;
import kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.DementiaIdentityResponse;
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse;
import kr.ac.tukorea.whereareu.data.model.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lkr/ac/tukorea/whereareu/data/api/LoginService;", "", "postDementiaIdentity", "Lretrofit2/Response;", "Lkr/ac/tukorea/whereareu/data/model/ResponseBody;", "Lkr/ac/tukorea/whereareu/data/model/login/response/DementiaIdentityResponse;", "request", "Lkr/ac/tukorea/whereareu/data/model/login/request/DementiaIdentityRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/DementiaIdentityRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postIsConnected", "Lkr/ac/tukorea/whereareu/data/model/login/response/CheckInterConnectResponse;", "Lkr/ac/tukorea/whereareu/data/model/login/request/CheckInterConnectRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/CheckInterConnectRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postNokIdentity", "Lkr/ac/tukorea/whereareu/data/model/login/response/NokIdentityResponse;", "Lkr/ac/tukorea/whereareu/data/model/login/request/NokIdentityRequest;", "(Lkr/ac/tukorea/whereareu/data/model/login/request/NokIdentityRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface LoginService {
    
    @retrofit2.http.POST(value = "receive-nok-info")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object postNokIdentity(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kr.ac.tukorea.whereareu.data.model.ResponseBody<kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse>>> $completion);
    
    @retrofit2.http.POST(value = "receive-dementia-info")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object postDementiaIdentity(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kr.ac.tukorea.whereareu.data.model.ResponseBody<kr.ac.tukorea.whereareu.data.model.login.response.DementiaIdentityResponse>>> $completion);
    
    @retrofit2.http.POST(value = "is-connected")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object postIsConnected(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kr.ac.tukorea.whereareu.data.model.ResponseBody<kr.ac.tukorea.whereareu.data.model.login.response.CheckInterConnectResponse>>> $completion);
}