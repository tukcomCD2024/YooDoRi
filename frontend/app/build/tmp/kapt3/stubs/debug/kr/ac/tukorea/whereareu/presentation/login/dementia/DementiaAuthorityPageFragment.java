package kr.ac.tukorea.whereareu.presentation.login.dementia;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.databinding.FragmentDementiaAuthorityPageBinding;
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment;
import kr.ac.tukorea.whereareu.presentation.dementia.DementiaMainActivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0007J\b\u0010\u0011\u001a\u00020\fH\u0002J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\u0006\u0010\u0014\u001a\u00020\fJ-\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00052\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016\u00a2\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\fH\u0002J\b\u0010\u001d\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/login/dementia/DementiaAuthorityPageFragment;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseFragment;", "Lkr/ac/tukorea/whereareu/databinding/FragmentDementiaAuthorityPageBinding;", "()V", "BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE", "", "LOCATION_PERMISSION_REQUEST_CODE", "requestNotificationPermission", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "checkAndRequestLocationPermissions", "", "checkBackGroundLocationPermission", "", "checkLocationPermission", "checkNotificationPermission", "goMainActivity", "initObserver", "initView", "onClickBackBtn", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestBackGroundLocationPermission", "requestLocationPermission", "app_debug"})
public final class DementiaAuthorityPageFragment extends kr.ac.tukorea.whereareu.presentation.base.BaseFragment<kr.ac.tukorea.whereareu.databinding.FragmentDementiaAuthorityPageBinding> {
    private final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 456;
    private final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> requestNotificationPermission = null;
    
    public DementiaAuthorityPageFragment() {
        super(0);
    }
    
    @java.lang.Override()
    public void initObserver() {
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    public final void onClickBackBtn() {
    }
    
    private final void goMainActivity() {
    }
    
    private final boolean checkBackGroundLocationPermission() {
        return false;
    }
    
    private final void requestBackGroundLocationPermission() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void checkAndRequestLocationPermissions() {
    }
    
    private final boolean checkLocationPermission() {
        return false;
    }
    
    private final void requestLocationPermission() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.TIRAMISU)
    public final void checkNotificationPermission() {
    }
}