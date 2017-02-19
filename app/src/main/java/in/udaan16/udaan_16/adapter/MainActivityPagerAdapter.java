package in.udaan16.udaan_16.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.udaan16.udaan_16.fragment.AboutUsFragment;
import in.udaan16.udaan_16.fragment.CategoriesFragment;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
//Adapter for viewPager. Used to populate the pages in a viewpager
public class MainActivityPagerAdapter extends FragmentPagerAdapter {

  //FragmentPagerAdapter: Implementation of PagerAdapter
  // that represents each page as a Fragment that is persistently kept in the fragment manager as long as the user can return to the page.
  //For larger sets of pages, use FragmentStateAdapter

  //Constructor
  public MainActivityPagerAdapter(FragmentManager fragmentManager) {
    //Fragment manager is an interface for interacting with Fragment objects inside of an Activity. Used for supporting devices running android 2.3 and below.
    //Doubt?? What for android 3.0 and above?
    super(fragmentManager);
  }


  //Return fragment associated with specified position
  @Override
  public Fragment getItem(int position) {
    Fragment fragment;
    if (position == 0) {
      fragment = new CategoriesFragment();
    } else if (position == 1) {
      fragment = new AboutUsFragment();
    } else {
      fragment = null;
    }
    return fragment;
  }

  @Override
  public int getCount() {
    return 2;
  }
}
