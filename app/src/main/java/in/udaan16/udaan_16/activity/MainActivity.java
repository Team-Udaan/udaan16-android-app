package in.udaan16.udaan_16.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.MainActivityPagerAdapter;

public class MainActivity extends AppCompatActivity {

  private MainActivityPagerAdapter viewPagerAdapter; //Adapter for viewPager. Used to populate the pages in a viewpager
  private ViewPager viewPager; //Layout manager that allows the user to flip through pages of data. You supply an implementation of the pagerAdapter to generate the pages that the view shows.

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_main);

    //Initialize pagerAdapter with fragment manager of this activity
    this.viewPagerAdapter = new MainActivityPagerAdapter(this.getSupportFragmentManager());
    this.viewPager = (ViewPager) this.findViewById(R.id.viewPager_activity_main);
    this.viewPager.setAdapter(viewPagerAdapter);  //Bind viewPager with viewPagerAdapter

    //listen for a page change
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
          MainActivity.this.viewPager.setCurrentItem(tab.getPosition(), true);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
      };

      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_categories).setTabListener(tabListener), 0, true);
      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_about_us).setTabListener(tabListener), 1, false);
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
  }
}
