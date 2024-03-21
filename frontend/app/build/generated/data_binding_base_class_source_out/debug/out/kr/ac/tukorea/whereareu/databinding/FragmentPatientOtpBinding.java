// Generated by data binding compiler. Do not edit!
package kr.ac.tukorea.whereareu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel;
import kr.ac.tukorea.whereareu.presentation.login.dementia.PatientOtpFragment;

public abstract class FragmentPatientOtpBinding extends ViewDataBinding {
  @NonNull
  public final ImageView backBtn;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final TextView displayOtpTv;

  @NonNull
  public final Button finishBtn;

  @NonNull
  public final TextView inputOptTv;

  @NonNull
  public final TextView patientOptWarningTv;

  @NonNull
  public final TextView patientTv;

  @Bindable
  protected PatientOtpFragment mView;

  @Bindable
  protected LoginViewModel mViewModel;

  protected FragmentPatientOtpBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView backBtn, CardView cardView, TextView displayOtpTv, Button finishBtn,
      TextView inputOptTv, TextView patientOptWarningTv, TextView patientTv) {
    super(_bindingComponent, _root, _localFieldCount);
    this.backBtn = backBtn;
    this.cardView = cardView;
    this.displayOtpTv = displayOtpTv;
    this.finishBtn = finishBtn;
    this.inputOptTv = inputOptTv;
    this.patientOptWarningTv = patientOptWarningTv;
    this.patientTv = patientTv;
  }

  public abstract void setView(@Nullable PatientOtpFragment view);

  @Nullable
  public PatientOtpFragment getView() {
    return mView;
  }

  public abstract void setViewModel(@Nullable LoginViewModel viewModel);

  @Nullable
  public LoginViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static FragmentPatientOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_patient_otp, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentPatientOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentPatientOtpBinding>inflateInternal(inflater, R.layout.fragment_patient_otp, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentPatientOtpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_patient_otp, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentPatientOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentPatientOtpBinding>inflateInternal(inflater, R.layout.fragment_patient_otp, null, false, component);
  }

  public static FragmentPatientOtpBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentPatientOtpBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentPatientOtpBinding)bind(component, view, R.layout.fragment_patient_otp);
  }
}
