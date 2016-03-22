package in.udaan16.udaan_16.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.fragment.EventContactDetailsFragment;
import in.udaan16.udaan_16.fragment.EventDetailsFragment;

/**
 * Creator: vbarad
 * Date: 2016-03-15
 * Project: udaan16-android-app
 */
public class EventDetailPagerAdapter extends FragmentPagerAdapter {
  private String eventJson;
  private Context context;

  public EventDetailPagerAdapter(FragmentManager fragmentManager, Context context, String eventJson) {
    super(fragmentManager);
    this.context = context;
    this.eventJson = eventJson;
  }

  @Override
  public Fragment getItem(int position) {
    Fragment fragment;
    Bundle bundle = new Bundle();
    bundle.putString(this.context.getString(R.string.activity_key_event_data), eventJson);
    if (position == 0) {
      fragment = new EventDetailsFragment();
      fragment.setArguments(bundle);
    } else if (position == 1) {
      fragment = new EventContactDetailsFragment();
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
