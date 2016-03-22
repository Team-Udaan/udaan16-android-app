package in.udaan16.udaan_16.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.EventManagerAdapter;
import in.udaan16.udaan_16.model.Event;

/**
 * Creator: vbarad
 * Date: 2016-03-15
 * Project: udaan16-android-app
 */
public class EventContactDetailsFragment extends Fragment implements View.OnClickListener {
  private View rootView;

  private RecyclerView managersRecyclerView;
  private EventManagerAdapter managersAdapter;
  private RecyclerView.LayoutManager managersLayoutManager;

  private Event eventData;

  private AppCompatButton buttonSendEmail;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_activity_event_details_contact_details, container, false);

    this.eventData = Event.parseJson(this.getArguments().getString(this.getString(R.string.activity_key_event_data)));

    this.managersRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_managers);
    this.managersRecyclerView.setHasFixedSize(true);

    this.managersLayoutManager = new LinearLayoutManager(this.rootView.getContext());
//      this.managersLayoutManager = new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false);
//      this.managersLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    this.managersRecyclerView.setLayoutManager(this.managersLayoutManager);

    this.managersAdapter = new EventManagerAdapter(this.eventData.getManagers(), getActivity());
    this.managersRecyclerView.setAdapter(managersAdapter);

    this.buttonSendEmail = (AppCompatButton) this.rootView.findViewById(R.id.button_event_details_email);
    this.buttonSendEmail.setOnClickListener(this);

    return this.rootView;
  }

  @Override
  public void onClick(View view) {
    Uri emailUri = Uri.parse("mailto:" + eventData.getEmail());
    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);
    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{eventData.getEmail()});
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding " + eventData.getName().trim() + " event in Udaan-16");
    this.startActivity(Intent.createChooser(emailIntent, "Send mail"));
  }
}
