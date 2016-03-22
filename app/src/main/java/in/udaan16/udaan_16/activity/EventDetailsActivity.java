package in.udaan16.udaan_16.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.EventDetailPagerAdapter;

public class EventDetailsActivity extends AppCompatActivity {

  private EventDetailPagerAdapter viewPagerAdapter;
  private ViewPager viewPager;

  private String eventJson;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_event_details);

    this.eventJson = this.getIntent().getExtras().getString(this.getString(R.string.activity_key_event_data));

    this.viewPagerAdapter = new EventDetailPagerAdapter(this.getSupportFragmentManager(), this, eventJson);
    this.viewPager = (ViewPager) this.findViewById(R.id.viewPager_activity_event_details);
    this.viewPager.setAdapter(viewPagerAdapter);
    this.viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
          actionBar.setSelectedNavigationItem(position);
        }
      }
    });

    ActionBar actionBar = this.getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(R.string.title_activity_main);
      actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

      ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
          EventDetailsActivity.this.viewPager.setCurrentItem(tab.getPosition(), true);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
      };

      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_event_details).setTabListener(tabListener), 0, true);
      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_contact_us).setTabListener(tabListener), 1, false);
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
  }

}
