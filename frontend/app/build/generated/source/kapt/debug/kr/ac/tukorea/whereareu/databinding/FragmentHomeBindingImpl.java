package kr.ac.tukorea.whereareu.databinding;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.homeFragment, 1);
        sViewsWithIds.put(R.id.map_fragment, 2);
        sViewsWithIds.put(R.id.user_info_layout, 3);
        sViewsWithIds.put(R.id.oval_v, 4);
        sViewsWithIds.put(R.id.dementia_name_tv, 5);
        sViewsWithIds.put(R.id.state, 6);
        sViewsWithIds.put(R.id.state_tv, 7);
        sViewsWithIds.put(R.id.line_v, 8);
        sViewsWithIds.put(R.id.wifi_iv, 9);
        sViewsWithIds.put(R.id.internet_status_tv, 10);
        sViewsWithIds.put(R.id.gps_iv, 11);
        sViewsWithIds.put(R.id.gps_status_tv, 12);
        sViewsWithIds.put(R.id.ring_mode_iv, 13);
        sViewsWithIds.put(R.id.ring_mode_tv, 14);
        sViewsWithIds.put(R.id.battery_iv, 15);
        sViewsWithIds.put(R.id.battery_tv, 16);
        sViewsWithIds.put(R.id.predict_tv, 17);
        sViewsWithIds.put(R.id.emergency_tv, 18);
        sViewsWithIds.put(R.id.home_group, 19);
        sViewsWithIds.put(R.id.bottom_sheet, 20);
        sViewsWithIds.put(R.id.bottom_sheet_top_iv, 21);
        sViewsWithIds.put(R.id.predict_location_title_tv, 22);
        sViewsWithIds.put(R.id.rv, 23);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[15]
            , (android.widget.TextView) bindings[16]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[20]
            , (android.widget.ImageView) bindings[21]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[18]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.TextView) bindings[12]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (androidx.constraintlayout.widget.Group) bindings[19]
            , (android.widget.TextView) bindings[10]
            , (android.view.View) bindings[8]
            , (androidx.fragment.app.FragmentContainerView) bindings[2]
            , (android.view.View) bindings[4]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[17]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.TextView) bindings[14]
            , (androidx.recyclerview.widget.RecyclerView) bindings[23]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[3]
            , (android.widget.ImageView) bindings[9]
            );
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}