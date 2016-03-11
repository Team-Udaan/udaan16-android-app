package in.udaan16.udaan_16.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.MainActivityPagerAdapter;

public class MainActivity extends AppCompatActivity {

  private MainActivityPagerAdapter viewPagerAdapter;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStart() {
    super.onStart();

    this.viewPagerAdapter = new MainActivityPagerAdapter(this.getSupportFragmentManager());
    this.viewPager = (ViewPager) this.findViewById(R.id.viewPager_activity_main);
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
//      actionBar.setTitle(R.string.title_activity_login);
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

      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_departments).setTabListener(tabListener), 0, true);
      actionBar.addTab(actionBar.newTab().setText(R.string.title_tab_about_us).setTabListener(tabListener), 1, false);
    }
  }
}
