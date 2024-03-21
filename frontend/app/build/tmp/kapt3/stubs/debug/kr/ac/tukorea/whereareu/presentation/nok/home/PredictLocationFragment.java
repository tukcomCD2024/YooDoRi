package kr.ac.tukorea.whereareu.presentation.nok.home;

import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import dagger.hilt.android.AndroidEntryPoint;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.databinding.FragmentPredictLocationBinding;
import kr.ac.tukorea.whereareu.databinding.FragmentSafeAreaBinding;
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001a"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/nok/home/PredictLocationFragment;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseFragment;", "Lkr/ac/tukorea/whereareu/databinding/FragmentPredictLocationBinding;", "Lcom/naver/maps/map/OnMapReadyCallback;", "()V", "callback", "Landroidx/activity/OnBackPressedCallback;", "naverMap", "Lcom/naver/maps/map/NaverMap;", "navigator", "Landroidx/navigation/NavController;", "getNavigator", "()Landroidx/navigation/NavController;", "navigator$delegate", "Lkotlin/Lazy;", "viewModel", "Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeViewModel;", "getViewModel", "()Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeViewModel;", "viewModel$delegate", "initMap", "", "initObserver", "initView", "onMapReady", "p0", "app_debug"})
public final class PredictLocationFragment extends kr.ac.tukorea.whereareu.presentation.base.BaseFragment<kr.ac.tukorea.whereareu.databinding.FragmentPredictLocationBinding> implements com.naver.maps.map.OnMapReadyCallback {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.naver.maps.map.NaverMap naverMap;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy navigator$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.OnBackPressedCallback callback = null;
    
    public PredictLocationFragment() {
        super(0);
    }
    
    private final kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel getViewModel() {
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
    
    private final void initMap() {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.naver.maps.map.NaverMap p0) {
    }
}