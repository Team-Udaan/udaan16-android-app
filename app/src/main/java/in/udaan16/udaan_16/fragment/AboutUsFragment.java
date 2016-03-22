package in.udaan16.udaan_16.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.DeveloperAdapter;
import in.udaan16.udaan_16.model.Developer;
import in.udaan16.udaan_16.util.DataSingleton;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class AboutUsFragment extends Fragment {
  private View rootView;

  private RecyclerView developersRecyclerView;
  private DeveloperAdapter developersAdapter;
  private RecyclerView.LayoutManager developersLayoutManager;

  private ArrayList<Developer> developers;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_activity_main_about_us, container, false);

    try {
      this.developers = DataSingleton.getInstance(this.getActivity()).getDataDeveloper();

      this.developersRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_developers);
      this.developersRecyclerView.setHasFixedSize(true);

      this.developersLayoutManager = new LinearLayoutManager(this.rootView.getContext());
//      this.developersLayoutManager = new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false);
//      this.developersLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.developersRecyclerView.setLayoutManager(this.developersLayoutManager);

      this.developersAdapter = new DeveloperAdapter(this.developers, getActivity());
      this.developersRecyclerView.setAdapter(developersAdapter);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return this.rootView;
  }
}
