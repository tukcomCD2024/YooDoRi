package kr.ac.tukorea.whereareu.util.extension;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import androidx.core.view.WindowCompat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u00a8\u0006\u0007"}, d2 = {"navigationHeight", "", "Landroid/content/Context;", "setStatusBarTransparent", "", "Landroid/app/Activity;", "statusBarHeight", "app_debug"})
public final class TransparentStatusBarExtensionKt {
    
    public static final void setStatusBarTransparent(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$setStatusBarTransparent) {
    }
    
    public static final int statusBarHeight(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$statusBarHeight) {
        return 0;
    }
    
    public static final int navigationHeight(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$navigationHeight) {
        return 0;
    }
}