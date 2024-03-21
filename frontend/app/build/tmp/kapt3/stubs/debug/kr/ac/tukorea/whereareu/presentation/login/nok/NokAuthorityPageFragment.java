package kr.ac.tukorea.whereareu.presentation.login.nok;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.databinding.FragmentNokAuthorityPageBinding;
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment;
import kr.ac.tukorea.whereareu.presentation.nok.NokMainActivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0007J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\u0006\u0010\u0012\u001a\u00020\u000bJ-\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00052\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016\u00a2\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/login/nok/NokAuthorityPageFragment;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseFragment;", "Lkr/ac/tukorea/whereareu/databinding/FragmentNokAuthorityPageBinding;", "()V", "LOCATION_PERMISSION_REQUEST_CODE", "", "requestNotificationPermission", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "checkAndRequestLocationPermissions", "", "checkLocationPermission", "", "checkNotificationPermission", "goMainActivity", "initObserver", "initView", "onClickBackBtn", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestLocationPermission", "app_debug"})
public final class NokAuthorityPageFragment extends kr.ac.tukorea.whereareu.presentation.base.BaseFragment<kr.ac.tukorea.whereareu.databinding.FragmentNokAuthorityPageBinding> {
    private final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> requestNotificationPermission = null;
    
    public NokAuthorityPageFragment() {
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