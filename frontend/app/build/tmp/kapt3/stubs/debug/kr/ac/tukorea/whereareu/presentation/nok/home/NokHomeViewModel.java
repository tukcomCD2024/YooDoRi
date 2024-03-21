package kr.ac.tukorea.whereareu.presentation.nok.home;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse;
import kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\nJ\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkr/ac/tukorea/whereareu/data/repository/nok/home/NokHomeRepositoryImpl;", "(Lkr/ac/tukorea/whereareu/data/repository/nok/home/NokHomeRepositoryImpl;)V", "_dementiaLocation", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkr/ac/tukorea/whereareu/data/model/home/GetLocationInfoResponse;", "_isPredicted", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_updateDuration", "", "dementiaLocation", "Lkotlinx/coroutines/flow/SharedFlow;", "getDementiaLocation", "()Lkotlinx/coroutines/flow/SharedFlow;", "isPredicted", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "getRepository", "()Lkr/ac/tukorea/whereareu/data/repository/nok/home/NokHomeRepositoryImpl;", "updateDuration", "getUpdateDuration", "", "dementiaKey", "", "setIsPredicted", "boolean", "setUpdateDuration", "duration", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class NokHomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse> _dementiaLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse> dementiaLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _updateDuration = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> updateDuration = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isPredicted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPredicted = null;
    
    @javax.inject.Inject()
    public NokHomeViewModel(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse> getDementiaLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getUpdateDuration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPredicted() {
        return null;
    }
    
    public final void setIsPredicted(boolean p0_32355860) {
    }
    
    public final void setUpdateDuration(long duration) {
    }
    
    public final void getDementiaLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String dementiaKey) {
    }
}