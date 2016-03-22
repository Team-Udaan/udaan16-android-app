package in.udaan16.udaan_16.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.GirlsEventAdapter;
import in.udaan16.udaan_16.model.Event;
import in.udaan16.udaan_16.util.DataSingleton;

public class GirlsEventsActivity extends AppCompatActivity {

  private RecyclerView eventsRecyclerView;
  private GirlsEventAdapter eventsAdapter;
  private RecyclerView.LayoutManager eventsLayoutManager;

  private ArrayList<Event> girlsEvents;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.initializeElements();
  }

  private void initializeElements() {
    this.setContentView(R.layout.activity_girls_events);

    ActionBar actionBar = this.getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(this.getString(R.string.title_activity_girls_events));
    }

    try {
      this.girlsEvents = DataSingleton.getInstance(this).getDataGirlsSpecial();

      this.eventsRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView_girls_events);
      this.eventsRecyclerView.setHasFixedSize(true);

//      this.eventsLayoutManager = new LinearLayoutManager(this);
      this.eventsLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
//      this.eventsLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.eventsRecyclerView.setLayoutManager(this.eventsLayoutManager);

      this.eventsAdapter = new GirlsEventAdapter(girlsEvents, this);
      this.eventsRecyclerView.setAdapter(eventsAdapter);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
