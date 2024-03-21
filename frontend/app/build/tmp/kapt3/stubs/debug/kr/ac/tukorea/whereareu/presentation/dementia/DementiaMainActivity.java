package kr.ac.tukorea.whereareu.presentation.dementia;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;
import dagger.hilt.android.AndroidEntryPoint;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.databinding.ActivityDementiaMainBinding;
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity;
import kr.ac.tukorea.whereareu.util.location.LocationService;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\tH\u0002J\b\u0010\f\u001a\u00020\tH\u0014J\b\u0010\r\u001a\u00020\tH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0002J\b\u0010\u000f\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/dementia/DementiaMainActivity;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseActivity;", "Lkr/ac/tukorea/whereareu/databinding/ActivityDementiaMainBinding;", "()V", "BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE", "", "checkBackGroundLocationPermission", "", "checkGpsStatus", "", "initLocationService", "initNavigator", "initObserver", "initView", "requestBackGroundLocationPermission", "requestSystemGPSEnable", "app_debug"})
public final class DementiaMainActivity extends kr.ac.tukorea.whereareu.presentation.base.BaseActivity<kr.ac.tukorea.whereareu.databinding.ActivityDementiaMainBinding> {
    private final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 456;
    
    public DementiaMainActivity() {
        super(0);
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    private final void initNavigator() {
    }
    
    private final boolean checkBackGroundLocationPermission() {
        return false;
    }
    
    private final void requestBackGroundLocationPermission() {
    }
    
    private final void initLocationService() {
    }
    
    private final void requestSystemGPSEnable() {
    }
    
    private final void checkGpsStatus() {
    }
    
    @java.lang.Override()
    protected void initObserver() {
    }
}