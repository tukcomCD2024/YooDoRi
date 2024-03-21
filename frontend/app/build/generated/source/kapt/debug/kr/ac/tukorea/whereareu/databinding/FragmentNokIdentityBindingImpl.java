package kr.ac.tukorea.whereareu.databinding;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentNokIdentityBindingImpl extends FragmentNokIdentityBinding implements kr.ac.tukorea.whereareu.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cardView, 3);
        sViewsWithIds.put(R.id.nok_tv, 4);
        sViewsWithIds.put(R.id.input_name_tv, 5);
        sViewsWithIds.put(R.id.name_textInputLayout, 6);
        sViewsWithIds.put(R.id.name_et, 7);
        sViewsWithIds.put(R.id.input_phone_number_tv, 8);
        sViewsWithIds.put(R.id.phone_number_textInputLayout, 9);
        sViewsWithIds.put(R.id.phone_number_et, 10);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.Button mboundView2;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback7;
    @Nullable
    private final android.view.View.OnClickListener mCallback6;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentNokIdentityBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private FragmentNokIdentityBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[3]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (com.google.android.material.textfield.TextInputEditText) bindings[7]
            , (com.google.android.material.textfield.TextInputLayout) bindings[6]
            , (android.widget.TextView) bindings[4]
            , (com.google.android.material.textfield.TextInputEditText) bindings[10]
            , (com.google.android.material.textfield.TextInputLayout) bindings[9]
            );
        this.backBtn.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.Button) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        // listeners
        mCallback7 = new kr.ac.tukorea.whereareu.generated.callback.OnClickListener(this, 2);
        mCallback6 = new kr.ac.tukorea.whereareu.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
            setView((kr.ac.tukorea.whereareu.presentation.login.nok.NokIdentityFragment) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((kr.ac.tukorea.whereareu.presentation.login.LoginViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setView(@Nullable kr.ac.tukorea.whereareu.presentation.login.nok.NokIdentityFragment View) {
        this.mView = View;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.view);
        super.requestRebind();
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
        kr.ac.tukorea.whereareu.presentation.login.nok.NokIdentityFragment view = mView;
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.backBtn.setOnClickListener(mCallback6);
            this.mboundView2.setOnClickListener(mCallback7);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // view
                kr.ac.tukorea.whereareu.presentation.login.nok.NokIdentityFragment view = mView;
                // view != null
                boolean viewJavaLangObjectNull = false;



                viewJavaLangObjectNull = (view) != (null);
                if (viewJavaLangObjectNull) {


                    view.onClickInputDone();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // view
                kr.ac.tukorea.whereareu.presentation.login.nok.NokIdentityFragment view = mView;
                // view != null
                boolean viewJavaLangObjectNull = false;



                viewJavaLangObjectNull = (view) != (null);
                if (viewJavaLangObjectNull) {


                    view.onClickBackBtn();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): view
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}