package kr.ac.tukorea.whereareu.presentation.nok.setting;

import android.content.Context;
import android.util.Log;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.databinding.DialogSettingUpdateTimeBinding;
import kr.ac.tukorea.whereareu.presentation.base.BaseDialogFragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\r"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/nok/setting/SetUpdateTimeDialogFragment;", "Lkr/ac/tukorea/whereareu/presentation/base/BaseDialogFragment;", "Lkr/ac/tukorea/whereareu/databinding/DialogSettingUpdateTimeBinding;", "setUpdateTime", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "getSetUpdateTime", "()Lkotlin/jvm/functions/Function1;", "initObserver", "initView", "SetUpdateTimeListener", "app_debug"})
public final class SetUpdateTimeDialogFragment extends kr.ac.tukorea.whereareu.presentation.base.BaseDialogFragment<kr.ac.tukorea.whereareu.databinding.DialogSettingUpdateTimeBinding> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> setUpdateTime = null;
    
    public SetUpdateTimeDialogFragment(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> setUpdateTime) {
        super(0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getSetUpdateTime() {
        return null;
    }
    
    @java.lang.Override()
    public void initObserver() {
    }
    
    @java.lang.Override()
    public void initView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lkr/ac/tukorea/whereareu/presentation/nok/setting/SetUpdateTimeDialogFragment$SetUpdateTimeListener;", "", "setUpdateTime", "", "time", "", "app_debug"})
    public static abstract interface SetUpdateTimeListener {
        
        public abstract void setUpdateTime(@org.jetbrains.annotations.NotNull()
        java.lang.String time);
    }
}