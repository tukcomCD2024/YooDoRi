package kr.ac.tukorea.whereareu.presentation.login.nok;

import android.view.inputmethod.EditorInfo;
import dagger.hilt.android.AndroidEntryPoint;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest;
import kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBinding;
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment;
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001d"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/login/nok/NokOtpFragment;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseFragment;", "Lkr/ac/tukorea/whereareu/databinding/FragmentNokOtpBinding;", "()V", "args", "Lkr/ac/tukorea/whereareu/presentation/login/nok/NokOtpFragmentArgs;", "getArgs", "()Lkr/ac/tukorea/whereareu/presentation/login/nok/NokOtpFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "navigator", "Landroidx/navigation/NavController;", "getNavigator", "()Landroidx/navigation/NavController;", "navigator$delegate", "Lkotlin/Lazy;", "viewModel", "Lkr/ac/tukorea/whereareu/presentation/login/LoginViewModel;", "getViewModel", "()Lkr/ac/tukorea/whereareu/presentation/login/LoginViewModel;", "viewModel$delegate", "initObserver", "", "initView", "onClickBackBtn", "onClickInputDone", "validOtp", "", "Companion", "app_debug"})
public final class NokOtpFragment extends kr.ac.tukorea.whereareu.presentation.base.BaseFragment<kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBinding> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy navigator$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String REGEX_OTP = "^([0-9]{6})";
    @org.jetbrains.annotations.NotNull()
    public static final kr.ac.tukorea.whereareu.presentation.login.nok.NokOtpFragment.Companion Companion = null;
    
    public NokOtpFragment() {
        super(0);
    }
    
    private final kr.ac.tukorea.whereareu.presentation.login.LoginViewModel getViewModel() {
        return null;
    }
    
    private final kr.ac.tukorea.whereareu.presentation.login.nok.NokOtpFragmentArgs getArgs() {
        return null;
    }
    
    private final androidx.navigation.NavController getNavigator() {
        return null;
    }
    
    @java.lang.Override()
    public void initObserver() {
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    public final void onClickBackBtn() {
    }
    
    public final void onClickInputDone() {
    }
    
    private final boolean validOtp() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/login/nok/NokOtpFragment$Companion;", "", "()V", "REGEX_OTP", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}