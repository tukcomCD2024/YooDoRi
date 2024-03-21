package kr.ac.tukorea.whereareu.presentation.nok;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import dagger.hilt.android.AndroidEntryPoint;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.databinding.ActivityNokMainBinding;
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity;
import kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/nok/NokMainActivity;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseActivity;", "Lkr/ac/tukorea/whereareu/databinding/ActivityNokMainBinding;", "()V", "mMessageReceiver", "Landroid/content/BroadcastReceiver;", "updateLocationJob", "Lkotlinx/coroutines/Job;", "viewModel", "Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeViewModel;", "getViewModel", "()Lkr/ac/tukorea/whereareu/presentation/nok/home/NokHomeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getDementiaLocation", "", "initNavigator", "initObserver", "initView", "onDestroy", "stopGetDementiaLocation", "app_debug"})
public final class NokMainActivity extends kr.ac.tukorea.whereareu.presentation.base.BaseActivity<kr.ac.tukorea.whereareu.databinding.ActivityNokMainBinding> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job updateLocationJob;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver mMessageReceiver = null;
    
    public NokMainActivity() {
        super(0);
    }
    
    private final kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    private final void initNavigator() {
    }
    
    private final void getDementiaLocation() {
    }
    
    private final void stopGetDementiaLocation() {
    }
    
    @java.lang.Override()
    protected void initObserver() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
}