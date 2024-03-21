package kr.ac.tukorea.whereareu.databinding;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDementiaSettingBindingImpl extends FragmentDementiaSettingBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.patient_iv, 1);
        sViewsWithIds.put(R.id.user_name_tv, 2);
        sViewsWithIds.put(R.id.user_type_tv, 3);
        sViewsWithIds.put(R.id.phone_iv, 4);
        sViewsWithIds.put(R.id.phone_number_tv, 5);
        sViewsWithIds.put(R.id.user_phone_number_tv, 6);
        sViewsWithIds.put(R.id.phone_edit_btn, 7);
        sViewsWithIds.put(R.id.name_iv, 8);
        sViewsWithIds.put(R.id.other_name_tv, 9);
        sViewsWithIds.put(R.id.other_name_edit_tv, 10);
        sViewsWithIds.put(R.id.other_name_edit_btn, 11);
        sViewsWithIds.put(R.id.other_phone_iv, 12);
        sViewsWithIds.put(R.id.other_phone_tv, 13);
        sViewsWithIds.put(R.id.other_phone_number_tv, 14);
        sViewsWithIds.put(R.id.other_phone_edit_btn, 15);
        sViewsWithIds.put(R.id.start_btn, 16);
        sViewsWithIds.put(R.id.stop_btn, 17);
        sViewsWithIds.put(R.id.post_info_tv, 18);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDementiaSettingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private FragmentDementiaSettingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[8]
            , (androidx.appcompat.widget.AppCompatButton) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[9]
            , (androidx.appcompat.widget.AppCompatButton) bindings[15]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[13]
            , (android.widget.ImageView) bindings[1]
            , (androidx.appcompat.widget.AppCompatButton) bindings[7]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[18]
            , (android.widget.Button) bindings[16]
            , (android.widget.Button) bindings[17]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
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
        if (BR.viewModel == variableId) {
            setViewModel((kr.ac.tukorea.whereareu.presentation.login.LoginViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable kr.ac.tukorea.whereareu.presentation.login.LoginViewModel ViewModel) {
        this.mViewModel = ViewModel;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}