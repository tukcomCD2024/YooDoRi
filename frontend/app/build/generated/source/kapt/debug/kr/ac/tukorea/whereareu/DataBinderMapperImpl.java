package kr.ac.tukorea.whereareu;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kr.ac.tukorea.whereareu.databinding.ActivityDementiaMainBindingImpl;
import kr.ac.tukorea.whereareu.databinding.ActivityLoginBindingImpl;
import kr.ac.tukorea.whereareu.databinding.ActivityNokMainBindingImpl;
import kr.ac.tukorea.whereareu.databinding.DialogSettingUpdateTimeBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentDementiaAuthorityPageBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentDementiaSettingBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentHomeBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentLocationHistoryBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentLoginBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentMeaningfulListBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentMeaningfulPlaceBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentNokAuthorityPageBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentNokIdentityBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentNokSettingBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentPatientIdentifyBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentPatientOtpBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentPredictLocationBindingImpl;
import kr.ac.tukorea.whereareu.databinding.FragmentSafeAreaBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYDEMENTIAMAIN = 1;

  private static final int LAYOUT_ACTIVITYLOGIN = 2;

  private static final int LAYOUT_ACTIVITYNOKMAIN = 3;

  private static final int LAYOUT_DIALOGSETTINGUPDATETIME = 4;

  private static final int LAYOUT_FRAGMENTDEMENTIAAUTHORITYPAGE = 5;

  private static final int LAYOUT_FRAGMENTDEMENTIASETTING = 6;

  private static final int LAYOUT_FRAGMENTHOME = 7;

  private static final int LAYOUT_FRAGMENTLOCATIONHISTORY = 8;

  private static final int LAYOUT_FRAGMENTLOGIN = 9;

  private static final int LAYOUT_FRAGMENTMEANINGFULLIST = 10;

  private static final int LAYOUT_FRAGMENTMEANINGFULPLACE = 11;

  private static final int LAYOUT_FRAGMENTNOKAUTHORITYPAGE = 12;

  private static final int LAYOUT_FRAGMENTNOKIDENTITY = 13;

  private static final int LAYOUT_FRAGMENTNOKOTP = 14;

  private static final int LAYOUT_FRAGMENTNOKSETTING = 15;

  private static final int LAYOUT_FRAGMENTPATIENTIDENTIFY = 16;

  private static final int LAYOUT_FRAGMENTPATIENTOTP = 17;

  private static final int LAYOUT_FRAGMENTPREDICTLOCATION = 18;

  private static final int LAYOUT_FRAGMENTSAFEAREA = 19;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(19);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.activity_dementia_main, LAYOUT_ACTIVITYDEMENTIAMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.activity_nok_main, LAYOUT_ACTIVITYNOKMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.dialog_setting_update_time, LAYOUT_DIALOGSETTINGUPDATETIME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_dementia_authority_page, LAYOUT_FRAGMENTDEMENTIAAUTHORITYPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_dementia_setting, LAYOUT_FRAGMENTDEMENTIASETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_location_history, LAYOUT_FRAGMENTLOCATIONHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_login, LAYOUT_FRAGMENTLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_meaningful_list, LAYOUT_FRAGMENTMEANINGFULLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_meaningful_place, LAYOUT_FRAGMENTMEANINGFULPLACE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_nok_authority_page, LAYOUT_FRAGMENTNOKAUTHORITYPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_nok_identity, LAYOUT_FRAGMENTNOKIDENTITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_nok_otp, LAYOUT_FRAGMENTNOKOTP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_nok_setting, LAYOUT_FRAGMENTNOKSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_patient_identify, LAYOUT_FRAGMENTPATIENTIDENTIFY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_patient_otp, LAYOUT_FRAGMENTPATIENTOTP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_predict_location, LAYOUT_FRAGMENTPREDICTLOCATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(kr.ac.tukorea.whereareu.R.layout.fragment_safe_area, LAYOUT_FRAGMENTSAFEAREA);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYDEMENTIAMAIN: {
          if ("layout/activity_dementia_main_0".equals(tag)) {
            return new ActivityDementiaMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_dementia_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNOKMAIN: {
          if ("layout/activity_nok_main_0".equals(tag)) {
            return new ActivityNokMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_nok_main is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGSETTINGUPDATETIME: {
          if ("layout/dialog_setting_update_time_0".equals(tag)) {
            return new DialogSettingUpdateTimeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_setting_update_time is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDEMENTIAAUTHORITYPAGE: {
          if ("layout/fragment_dementia_authority_page_0".equals(tag)) {
            return new FragmentDementiaAuthorityPageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_dementia_authority_page is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDEMENTIASETTING: {
          if ("layout/fragment_dementia_setting_0".equals(tag)) {
            return new FragmentDementiaSettingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_dementia_setting is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOCATIONHISTORY: {
          if ("layout/fragment_location_history_0".equals(tag)) {
            return new FragmentLocationHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_location_history is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGIN: {
          if ("layout/fragment_login_0".equals(tag)) {
            return new FragmentLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMEANINGFULLIST: {
          if ("layout/fragment_meaningful_list_0".equals(tag)) {
            return new FragmentMeaningfulListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_meaningful_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMEANINGFULPLACE: {
          if ("layout/fragment_meaningful_place_0".equals(tag)) {
            return new FragmentMeaningfulPlaceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_meaningful_place is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNOKAUTHORITYPAGE: {
          if ("layout/fragment_nok_authority_page_0".equals(tag)) {
            return new FragmentNokAuthorityPageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_nok_authority_page is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNOKIDENTITY: {
          if ("layout/fragment_nok_identity_0".equals(tag)) {
            return new FragmentNokIdentityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_nok_identity is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNOKOTP: {
          if ("layout/fragment_nok_otp_0".equals(tag)) {
            return new FragmentNokOtpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_nok_otp is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNOKSETTING: {
          if ("layout/fragment_nok_setting_0".equals(tag)) {
            return new FragmentNokSettingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_nok_setting is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPATIENTIDENTIFY: {
          if ("layout/fragment_patient_identify_0".equals(tag)) {
            return new FragmentPatientIdentifyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_patient_identify is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPATIENTOTP: {
          if ("layout/fragment_patient_otp_0".equals(tag)) {
            return new FragmentPatientOtpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_patient_otp is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPREDICTLOCATION: {
          if ("layout/fragment_predict_location_0".equals(tag)) {
            return new FragmentPredictLocationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_predict_location is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSAFEAREA: {
          if ("layout/fragment_safe_area_0".equals(tag)) {
            return new FragmentSafeAreaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_safe_area is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "view");
      sKeys.put(2, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(19);

    static {
      sKeys.put("layout/activity_dementia_main_0", kr.ac.tukorea.whereareu.R.layout.activity_dementia_main);
      sKeys.put("layout/activity_login_0", kr.ac.tukorea.whereareu.R.layout.activity_login);
      sKeys.put("layout/activity_nok_main_0", kr.ac.tukorea.whereareu.R.layout.activity_nok_main);
      sKeys.put("layout/dialog_setting_update_time_0", kr.ac.tukorea.whereareu.R.layout.dialog_setting_update_time);
      sKeys.put("layout/fragment_dementia_authority_page_0", kr.ac.tukorea.whereareu.R.layout.fragment_dementia_authority_page);
      sKeys.put("layout/fragment_dementia_setting_0", kr.ac.tukorea.whereareu.R.layout.fragment_dementia_setting);
      sKeys.put("layout/fragment_home_0", kr.ac.tukorea.whereareu.R.layout.fragment_home);
      sKeys.put("layout/fragment_location_history_0", kr.ac.tukorea.whereareu.R.layout.fragment_location_history);
      sKeys.put("layout/fragment_login_0", kr.ac.tukorea.whereareu.R.layout.fragment_login);
      sKeys.put("layout/fragment_meaningful_list_0", kr.ac.tukorea.whereareu.R.layout.fragment_meaningful_list);
      sKeys.put("layout/fragment_meaningful_place_0", kr.ac.tukorea.whereareu.R.layout.fragment_meaningful_place);
      sKeys.put("layout/fragment_nok_authority_page_0", kr.ac.tukorea.whereareu.R.layout.fragment_nok_authority_page);
      sKeys.put("layout/fragment_nok_identity_0", kr.ac.tukorea.whereareu.R.layout.fragment_nok_identity);
      sKeys.put("layout/fragment_nok_otp_0", kr.ac.tukorea.whereareu.R.layout.fragment_nok_otp);
      sKeys.put("layout/fragment_nok_setting_0", kr.ac.tukorea.whereareu.R.layout.fragment_nok_setting);
      sKeys.put("layout/fragment_patient_identify_0", kr.ac.tukorea.whereareu.R.layout.fragment_patient_identify);
      sKeys.put("layout/fragment_patient_otp_0", kr.ac.tukorea.whereareu.R.layout.fragment_patient_otp);
      sKeys.put("layout/fragment_predict_location_0", kr.ac.tukorea.whereareu.R.layout.fragment_predict_location);
      sKeys.put("layout/fragment_safe_area_0", kr.ac.tukorea.whereareu.R.layout.fragment_safe_area);
    }
  }
}
