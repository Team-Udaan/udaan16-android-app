package in.udaan16.udaan_16.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONException;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.NightAdapter;
import in.udaan16.udaan_16.model.Event;
import in.udaan16.udaan_16.util.DataSingleton;

public class NightsActivity extends AppCompatActivity {

  private RecyclerView nightsRecyclerView;
  private NightAdapter nightsAdapter;
  private RecyclerView.LayoutManager nightsLayoutManager;

  private ArrayList<Event> nights;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.initializeElements();
  }

  private void initializeElements() {
    this.setContentView(R.layout.activity_nights);

    ActionBar actionBar = this.getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(this.getString(R.string.title_activity_nights));
    }

    try {
      this.nights = DataSingleton.getInstance(this).getDataNights();

      this.nightsRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView_nights);

      if (nights.size() > 0) {
        this.nightsRecyclerView.setVisibility(View.VISIBLE);
        this.findViewById(R.id.textView_coming_soon).setVisibility(View.GONE);
        this.nightsRecyclerView.setHasFixedSize(true);

//        this.nightsLayoutManager = new LinearLayoutManager(this);
        this.nightsLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
//      this.nightsLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        this.nightsRecyclerView.setLayoutManager(this.nightsLayoutManager);

        this.nightsAdapter = new NightAdapter(nights, this);
        this.nightsRecyclerView.setAdapter(nightsAdapter);
      } else {
        this.nightsRecyclerView.setVisibility(View.GONE);
        this.findViewById(R.id.textView_coming_soon).setVisibility(View.VISIBLE);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
