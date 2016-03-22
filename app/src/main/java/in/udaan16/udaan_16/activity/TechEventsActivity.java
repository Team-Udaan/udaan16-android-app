package in.udaan16.udaan_16.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.DepartmentPagerAdapter;

public class TechEventsActivity extends AppCompatActivity {

  private int position;

  private DepartmentPagerAdapter viewPagerAdapter;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_tech_events);

    this.position = this.getIntent().getExtras().getInt(getString(R.string.activity_key_position), 0);
    String activityTitle = getIntent().getExtras().getString(this.getString(R.string.activity_key_title_name), this.getString(R.string.activity_key_title_name));

    this.viewPagerAdapter = new DepartmentPagerAdapter(this.getSupportFragmentManager(), this, this.position);
    this.viewPager = (ViewPager) this.findViewById(R.id.viewPager_activity_tech_events);
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
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(activityTitle);

      actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

      ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
          TechEventsActivity.this.viewPager.setCurrentItem(tab.getPosition(), true);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
      };

      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_tech_events).setTabListener(tabListener), 0, true);
      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_branch_managers).setTabListener(tabListener), 1, false);
    }
  }

}
