package kr.ac.tukorea.whereareu.databinding;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentNokSettingBindingImpl extends FragmentNokSettingBinding  {

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
        sViewsWithIds.put(R.id.line1, 8);
        sViewsWithIds.put(R.id.name_iv, 9);
        sViewsWithIds.put(R.id.other_name_tv, 10);
        sViewsWithIds.put(R.id.other_name_edit_tv, 11);
        sViewsWithIds.put(R.id.other_name_edit_btn, 12);
        sViewsWithIds.put(R.id.line2, 13);
        sViewsWithIds.put(R.id.other_phone_iv, 14);
        sViewsWithIds.put(R.id.other_phone_tv, 15);
        sViewsWithIds.put(R.id.other_phone_number_tv, 16);
        sViewsWithIds.put(R.id.other_phone_edit_btn, 17);
        sViewsWithIds.put(R.id.line3, 18);
        sViewsWithIds.put(R.id.update_iv, 19);
        sViewsWithIds.put(R.id.update_setting_tv, 20);
        sViewsWithIds.put(R.id.update_time_tv, 21);
        sViewsWithIds.put(R.id.update_edit_btn, 22);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentNokSettingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private FragmentNokSettingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.view.View) bindings[8]
            , (android.view.View) bindings[13]
            , (android.view.View) bindings[18]
            , (android.widget.ImageView) bindings[9]
            , (androidx.appcompat.widget.AppCompatButton) bindings[12]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (androidx.appcompat.widget.AppCompatButton) bindings[17]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[15]
            , (android.widget.ImageView) bindings[1]
            , (androidx.appcompat.widget.AppCompatButton) bindings[7]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.Button) bindings[22]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[21]
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