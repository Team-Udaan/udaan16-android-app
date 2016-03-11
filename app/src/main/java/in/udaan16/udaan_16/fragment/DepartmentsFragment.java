package in.udaan16.udaan_16.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.model.Branch;
import in.udaan16.udaan_16.util.DataSingleton;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class DepartmentsFragment extends Fragment {
  private View rootView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_activity_main_departments, container, false);

    try {
      ArrayList<Branch> branches = DataSingleton.getInstance(this.getActivity()).getDataBranches();

      Gson gson = new Gson();

      ((AppCompatTextView) this.rootView.findViewById(R.id.textView_fragment_departments_temp)).setText(gson.toJson(branches));
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return this.rootView;
  }
}
