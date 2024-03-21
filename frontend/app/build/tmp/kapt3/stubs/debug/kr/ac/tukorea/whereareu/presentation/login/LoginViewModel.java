package kr.ac.tukorea.whereareu.presentation.login;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest;
import kr.ac.tukorea.whereareu.domain.login.NokInfo;
import kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest;
import kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse;
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\nJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020&R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013\u00a8\u0006\'"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/login/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkr/ac/tukorea/whereareu/data/repository/login/LoginRepositoryImpl;", "(Lkr/ac/tukorea/whereareu/data/repository/login/LoginRepositoryImpl;)V", "_dementiaKeyFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "_isOnBackPressedAtDementiaOtp", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_navigateToDementiaMainEvent", "Lkr/ac/tukorea/whereareu/domain/login/NokInfo;", "_navigateToNokMainEvent", "Lkr/ac/tukorea/whereareu/data/model/login/response/NokIdentityResponse;", "_toastEvent", "dementiaKeyFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getDementiaKeyFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "isOnBackPressedAtDementiaOtp", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "navigateToDementiaMainEvent", "getNavigateToDementiaMainEvent", "navigateToNokMainEvent", "getNavigateToNokMainEvent", "toastEvent", "getToastEvent", "checkConnected", "", "request", "Lkr/ac/tukorea/whereareu/data/model/login/request/CheckInterConnectRequest;", "onBackPressedAtDementiaOtp", "isPressed", "sendDementiaIdentity", "Lkr/ac/tukorea/whereareu/data/model/login/request/DementiaIdentityRequest;", "sendNokIdentity", "Lkr/ac/tukorea/whereareu/data/model/login/request/NokIdentityRequest;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.String> _dementiaKeyFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<java.lang.String> dementiaKeyFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse> _navigateToNokMainEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse> navigateToNokMainEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<kr.ac.tukorea.whereareu.domain.login.NokInfo> _navigateToDementiaMainEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<kr.ac.tukorea.whereareu.domain.login.NokInfo> navigateToDementiaMainEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isOnBackPressedAtDementiaOtp = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOnBackPressedAtDementiaOtp = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.String> _toastEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<java.lang.String> toastEvent = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<java.lang.String> getDementiaKeyFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse> getNavigateToNokMainEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<kr.ac.tukorea.whereareu.domain.login.NokInfo> getNavigateToDementiaMainEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOnBackPressedAtDementiaOtp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<java.lang.String> getToastEvent() {
        return null;
    }
    
    public final void onBackPressedAtDementiaOtp(boolean isPressed) {
    }
    
    public final void sendNokIdentity(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest request) {
    }
    
    public final void sendDementiaIdentity(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest request) {
    }
    
    public final void checkConnected(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest request) {
    }
}