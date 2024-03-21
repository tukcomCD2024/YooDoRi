package kr.ac.tukorea.whereareu.databinding;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDementiaAuthorityPageBindingImpl extends FragmentDementiaAuthorityPageBinding implements kr.ac.tukorea.whereareu.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.guide1_tv, 2);
        sViewsWithIds.put(R.id.permisson_tv, 3);
        sViewsWithIds.put(R.id.gps_iv, 4);
        sViewsWithIds.put(R.id.gps_tv, 5);
        sViewsWithIds.put(R.id.gps_detail_tv, 6);
        sViewsWithIds.put(R.id.alarm_iv, 7);
        sViewsWithIds.put(R.id.alarm_tv, 8);
        sViewsWithIds.put(R.id.alarm_detail_tv, 9);
        sViewsWithIds.put(R.id.guide2_tv, 10);
        sViewsWithIds.put(R.id.finish_btn, 11);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDementiaAuthorityPageBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private FragmentDementiaAuthorityPageBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[9]
            , (androidx.cardview.widget.CardView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.ImageView) bindings[1]
            , (androidx.appcompat.widget.AppCompatButton) bindings[11]
            , (android.widget.TextView) bindings[6]
            , (androidx.cardview.widget.CardView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[3]
            );
        this.backBtn.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new kr.ac.tukorea.whereareu.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.view == variableId) {
            setView((kr.ac.tukorea.whereareu.presentation.login.dementia.DementiaAuthorityPageFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setView(@Nullable kr.ac.tukorea.whereareu.presentation.login.dementia.DementiaAuthorityPageFragment View) {
        this.mView = View;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.view);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        kr.ac.tukorea.whereareu.presentation.login.dementia.DementiaAuthorityPageFragment view = mView;
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.backBtn.setOnClickListener(mCallback2);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // view
        kr.ac.tukorea.whereareu.presentation.login.dementia.DementiaAuthorityPageFragment view = mView;
        // view != null
        boolean viewJavaLangObjectNull = false;



        viewJavaLangObjectNull = (view) != (null);
        if (viewJavaLangObjectNull) {


            view.onClickBackBtn();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): view
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}