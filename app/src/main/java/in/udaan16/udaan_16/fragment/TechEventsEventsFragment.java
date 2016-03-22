package in.udaan16.udaan_16.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.TechEventAdapter;
import in.udaan16.udaan_16.model.Event;
import in.udaan16.udaan_16.util.DataSingleton;

/**
 * Creator: vbarad
 * Date: 2016-03-18
 * Project: udaan16-android-app
 */
public class TechEventsEventsFragment extends Fragment {
  private View rootView;

  private RecyclerView techEventsRecyclerView;
  private TechEventAdapter techEventsAdapter;
  private RecyclerView.LayoutManager techEventsLayoutManager;

  private ArrayList<Event> events;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_activity_tech_events_events, container, false);

    try {
      int position = this.getArguments().getInt(this.getString(R.string.activity_key_position), 0);

      this.events = DataSingleton.getInstance(this.getActivity()).getDataDepartments().get(position).getEvents();

      this.techEventsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_tech_events);
      this.techEventsRecyclerView.setHasFixedSize(true);

//      this.techEventsLayoutManager = new LinearLayoutManager(this.rootView.getContext());
      this.techEventsLayoutManager = new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false);
//      this.techEventsLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.techEventsRecyclerView.setLayoutManager(this.techEventsLayoutManager);

      this.techEventsAdapter = new TechEventAdapter(this.events, getActivity());
      this.techEventsRecyclerView.setAdapter(techEventsAdapter);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return this.rootView;
  }
}
