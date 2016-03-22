package in.udaan16.udaan_16.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.fragment.TechEventsEventsFragment;
import in.udaan16.udaan_16.fragment.TechEventsHeadsFragment;

/**
 * Creator: vbarad
 * Date: 2016-03-18
 * Project: udaan16-android-app
 */
public class DepartmentPagerAdapter extends FragmentPagerAdapter {
  private int position;
  private Context context;

  public DepartmentPagerAdapter(FragmentManager fragmentManager, Context context, int position) {
    super(fragmentManager);
    this.context = context;
    this.position = position;
  }

  @Override
  public Fragment getItem(int position) {
    Fragment fragment;
    Bundle bundle = new Bundle();
    bundle.putInt(this.context.getString(R.string.activity_key_position), this.position);
    if (position == 0) {
      fragment = new TechEventsEventsFragment();
      fragment.setArguments(bundle);
    } else if (position == 1) {
      fragment = new TechEventsHeadsFragment();
      fragment.setArguments(bundle);
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
