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
public class MainActivityPagerAdapter extends FragmentPagerAdapter {
  public MainActivityPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

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
