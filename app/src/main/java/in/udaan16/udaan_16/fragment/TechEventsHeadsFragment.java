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
import in.udaan16.udaan_16.adapter.BranchManagerAdapter;
import in.udaan16.udaan_16.model.BranchManager;
import in.udaan16.udaan_16.util.DataSingleton;

/**
 * Creator: vbarad
 * Date: 2016-03-18
 * Project: udaan16-android-app
 */
public class TechEventsHeadsFragment extends Fragment {
  private View rootView;

  private RecyclerView headsRecyclerView;
  private BranchManagerAdapter headsAdapter;
  private RecyclerView.LayoutManager headsLayoutManager;

  private RecyclerView coHeadsRecyclerView;
  private BranchManagerAdapter coHeadsAdapter;
  private RecyclerView.LayoutManager coHeadsLayoutManager;

  private ArrayList<BranchManager> heads;
  private ArrayList<BranchManager> coHeads;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_activity_tech_events_managers, container, false);

    int position = this.getArguments().getInt(this.getString(R.string.activity_key_position), 0);

    try {
      this.heads = DataSingleton.getInstance(this.getActivity()).getDataDepartments().get(position).getHeads();
      this.coHeads = DataSingleton.getInstance(this.getActivity()).getDataDepartments().get(position).getCoheads();

      this.headsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_heads);
      this.headsRecyclerView.setHasFixedSize(true);

      this.headsLayoutManager = new LinearLayoutManager(this.rootView.getContext());
//      this.headsLayoutManager = new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false);
//      this.headsLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.headsRecyclerView.setLayoutManager(this.headsLayoutManager);

      this.headsAdapter = new BranchManagerAdapter(this.heads, getActivity());
      this.headsRecyclerView.setAdapter(headsAdapter);

      this.coHeadsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_co_heads);
      this.coHeadsRecyclerView.setHasFixedSize(true);

      this.coHeadsLayoutManager = new LinearLayoutManager(this.rootView.getContext());
//      this.coHeadsLayoutManager = new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false);
//      this.coHeadsLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.coHeadsRecyclerView.setLayoutManager(this.coHeadsLayoutManager);

      this.coHeadsAdapter = new BranchManagerAdapter(this.coHeads, getActivity());
      this.coHeadsRecyclerView.setAdapter(coHeadsAdapter);
    } catch (JSONException e) {
      e.printStackTrace();

    }

    return this.rootView;
  }
}
