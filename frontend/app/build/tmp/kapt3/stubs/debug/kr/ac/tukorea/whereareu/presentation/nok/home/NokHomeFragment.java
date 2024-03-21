package kr.ac.tukorea.whereareu.presentation.nok.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.OverlayImage;
import dagger.hilt.android.AndroidEntryPoint;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse;
import kr.ac.tukorea.whereareu.databinding.IconLocationOverlayLayoutBinding;
import kr.ac.tukorea.whereareu.domain.home.MeaningfulPlace;
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u000e\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\b\u0010&\u001a\u00020\u001fH\u0002J\b\u0010\'\u001a\u00020\u001fH\u0002J\b\u0010(\u001a\u00020\u001fH\u0016J\b\u0010)\u001a\u00020\u001fH\u0016J\u0010\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u0013H\u0016J-\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00062\u000e\u0010.\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0/2\u0006\u00100\u001a\u000201H\u0016\u00a2\u0006\u0002\u00102J\b\u00103\u001a\u00020\u001fH\u0016J(\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u000208H\u0002J\u0010\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u0006H\u0002J\b\u0010=\u001a\u00020\u001fH\u0002J\u0010\u0010>\u001a\u00020\u001f2\u0006\u0010?\u001a\u00020@H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006A"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeFragment;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseFragment;", "Lkr/ac/tukorea/whereareu/databinding/FragmentHomeBinding;", "Lcom/naver/maps/map/OnMapReadyCallback;", "()V", "LOCATION_PERMISSION_REQUEST_CODE", "", "behavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "dementiaName", "", "meaningfulListRVA", "Lkr/ac/tukorea/whereareu/presentation/nok/home/MeaningfulListRVA;", "getMeaningfulListRVA", "()Lkr/ac/tukorea/whereareu/presentation/nok/home/MeaningfulListRVA;", "meaningfulListRVA$delegate", "Lkotlin/Lazy;", "naverMap", "Lcom/naver/maps/map/NaverMap;", "navigator", "Landroidx/navigation/NavController;", "getNavigator", "()Landroidx/navigation/NavController;", "navigator$delegate", "viewModel", "Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeViewModel;", "getViewModel", "()Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeViewModel;", "viewModel$delegate", "checkLocationPermission", "", "getNaviBarHeight", "context", "Landroid/content/Context;", "getStatusBarHeight", "goToPredictLocationFragment", "initBottomSheet", "initMap", "initMeaningfulListRVA", "initObserver", "initView", "onMapReady", "p0", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "trackingDementiaLocation", "coord", "Lcom/naver/maps/geometry/LatLng;", "bearing", "", "name", "speed", "updateDementiaMovementStatus", "status", "updateDementiaName", "updateDementiaStatus", "dementiaStatus", "Lkr/ac/tukorea/whereareu/data/model/home/GetLocationInfoResponse;", "app_debug"})
public final class NokHomeFragment extends kr.ac.tukorea.whereareu.presentation.base.BaseFragment<kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding> implements com.naver.maps.map.OnMapReadyCallback {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    @org.jetbrains.annotations.Nullable()
    private com.naver.maps.map.NaverMap naverMap;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String dementiaName;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy navigator$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy meaningfulListRVA$delegate = null;
    private com.google.android.material.bottomsheet.BottomSheetBehavior<androidx.constraintlayout.widget.ConstraintLayout> behavior;
    
    public NokHomeFragment() {
        super(0);
    }
    
    private final kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel getViewModel() {
        return null;
    }
    
    private final androidx.navigation.NavController getNavigator() {
        return null;
    }
    
    private final kr.ac.tukorea.whereareu.presentation.nok.home.MeaningfulListRVA getMeaningfulListRVA() {
        return null;
    }
    
    @java.lang.Override()
    public void initObserver() {
    }
    
    private final java.lang.String updateDementiaMovementStatus(int status) {
        return null;
    }
    
    private final void updateDementiaStatus(kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse dementiaStatus) {
    }
    
    private final void trackingDementiaLocation(com.naver.maps.geometry.LatLng coord, float bearing, java.lang.String name, float speed) {
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    private final void goToPredictLocationFragment() {
    }
    
    private final void initMeaningfulListRVA() {
    }
    
    private final void initBottomSheet() {
    }
    
    private final void updateDementiaName() {
    }
    
    public final int getStatusBarHeight(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    public final int getNaviBarHeight(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    private final void initMap() {
    }
    
    private final void checkLocationPermission() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.naver.maps.map.NaverMap p0) {
    }
}