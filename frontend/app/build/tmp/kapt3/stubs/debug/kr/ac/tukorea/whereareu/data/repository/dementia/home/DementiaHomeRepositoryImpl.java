package kr.ac.tukorea.whereareu.data.repository.dementia.home;

import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService;
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo;
import kr.ac.tukorea.whereareu.data.model.home.PostLocationInfoResponse;
import kr.ac.tukorea.whereareu.util.network.NetworkResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lkr/ac/tukorea/whereareu/data/repository/dementia/home/DementiaHomeRepositoryImpl;", "Lkr/ac/tukorea/whereareu/data/repository/dementia/home/DementiaHomeRepository;", "api", "Lkr/ac/tukorea/whereareu/data/api/dementia/DementiaHomeService;", "(Lkr/ac/tukorea/whereareu/data/api/dementia/DementiaHomeService;)V", "postLocationInfo", "Lkr/ac/tukorea/whereareu/util/network/NetworkResult;", "Lkr/ac/tukorea/whereareu/data/model/home/PostLocationInfoResponse;", "request", "Lkr/ac/tukorea/whereareu/data/model/home/LocationInfo;", "(Lkr/ac/tukorea/whereareu/data/model/home/LocationInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DementiaHomeRepositoryImpl implements kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepository {
    @org.jetbrains.annotations.NotNull()
    private final kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService api = null;
    
    @javax.inject.Inject()
    public DementiaHomeRepositoryImpl(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object postLocationInfo(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.home.LocationInfo request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kr.ac.tukorea.whereareu.util.network.NetworkResult<kr.ac.tukorea.whereareu.data.model.home.PostLocationInfoResponse>> $completion) {
        return null;
    }
}