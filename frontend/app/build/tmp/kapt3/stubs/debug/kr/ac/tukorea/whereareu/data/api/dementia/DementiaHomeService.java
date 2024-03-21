package kr.ac.tukorea.whereareu.data.api.dementia;

import kr.ac.tukorea.whereareu.data.model.ResponseBody;
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo;
import kr.ac.tukorea.whereareu.data.model.home.PostLocationInfoResponse;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lkr/ac/tukorea/whereareu/data/api/dementia/DementiaHomeService;", "", "postLocationInfo", "Lretrofit2/Response;", "Lkr/ac/tukorea/whereareu/data/model/home/PostLocationInfoResponse;", "request", "Lkr/ac/tukorea/whereareu/data/model/home/LocationInfo;", "(Lkr/ac/tukorea/whereareu/data/model/home/LocationInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface DementiaHomeService {
    
    @retrofit2.http.POST(value = "receive-location-info")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object postLocationInfo(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.home.LocationInfo request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kr.ac.tukorea.whereareu.data.model.home.PostLocationInfoResponse>> $completion);
}