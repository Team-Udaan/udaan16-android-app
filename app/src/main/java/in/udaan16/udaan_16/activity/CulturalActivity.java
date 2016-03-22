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
import in.udaan16.udaan_16.adapter.CulturalEventAdapter;
import in.udaan16.udaan_16.model.Event;
import in.udaan16.udaan_16.util.DataSingleton;

public class CulturalActivity extends AppCompatActivity {

  private RecyclerView eventsRecyclerView;
  private CulturalEventAdapter eventsAdapter;
  private RecyclerView.LayoutManager eventsLayoutManager;

  private ArrayList<Event> culturalEvents;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.initializeElements();
  }

  private void initializeElements() {
    this.setContentView(R.layout.activity_cultural);

    ActionBar actionBar = this.getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(this.getString(R.string.title_activity_cultural));
    }

    try {
      this.culturalEvents = DataSingleton.getInstance(this).getDataCultural();

      this.eventsRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView_cultural_events);
      this.eventsRecyclerView.setHasFixedSize(true);

//      this.eventsLayoutManager = new LinearLayoutManager(this);
      this.eventsLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
//      this.eventsLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.eventsRecyclerView.setLayoutManager(this.eventsLayoutManager);

      this.eventsAdapter = new CulturalEventAdapter(culturalEvents, this);
      this.eventsRecyclerView.setAdapter(eventsAdapter);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
