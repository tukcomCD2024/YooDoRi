package kr.ac.tukorea.whereareu.databinding;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentLoginBindingImpl extends FragmentLoginBinding implements kr.ac.tukorea.whereareu.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.title_tv, 3);
        sViewsWithIds.put(R.id.nok_cardView, 4);
        sViewsWithIds.put(R.id.patient_cardView, 5);
        sViewsWithIds.put(R.id.nok_tv, 6);
        sViewsWithIds.put(R.id.patient_tv, 7);
        sViewsWithIds.put(R.id.describe_tv, 8);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback8;
    @Nullable
    private final android.view.View.OnClickListener mCallback9;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentLoginBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentLoginBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatButton) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[4]
            , (android.widget.TextView) bindings[6]
            , (androidx.appcompat.widget.AppCompatButton) bindings[2]
            , (androidx.cardview.widget.CardView) bindings[5]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nokBtn.setTag(null);
        this.patientBtn.setTag(null);
        setRootTag(root);
        // listeners
        mCallback8 = new kr.ac.tukorea.whereareu.generated.callback.OnClickListener(this, 1);
        mCallback9 = new kr.ac.tukorea.whereareu.generated.callback.OnClickListener(this, 2);
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
            setView((kr.ac.tukorea.whereareu.presentation.login.LoginFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setView(@Nullable kr.ac.tukorea.whereareu.presentation.login.LoginFragment View) {
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
        kr.ac.tukorea.whereareu.presentation.login.LoginFragment view = mView;
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.nokBtn.setOnClickListener(mCallback8);
            this.patientBtn.setOnClickListener(mCallback9);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // view
                kr.ac.tukorea.whereareu.presentation.login.LoginFragment view = mView;
                // view != null
                boolean viewJavaLangObjectNull = false;



                viewJavaLangObjectNull = (view) != (null);
                if (viewJavaLangObjectNull) {


                    view.onClickNok();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // view
                kr.ac.tukorea.whereareu.presentation.login.LoginFragment view = mView;
                // view != null
                boolean viewJavaLangObjectNull = false;



                viewJavaLangObjectNull = (view) != (null);
                if (viewJavaLangObjectNull) {


                    view.onClickPatient();
                }
                break;
            }
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