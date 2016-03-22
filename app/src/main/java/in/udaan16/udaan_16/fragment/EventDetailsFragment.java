package in.udaan16.udaan_16.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.model.Event;

/**
 * Creator: vbarad
 * Date: 2016-03-15
 * Project: udaan16-android-app
 */
public class EventDetailsFragment extends Fragment {
  private View rootView;

  private Event eventData;

  private AppCompatTextView textViewName;
  private Space spaceNameDescription;
  private AppCompatTextView textViewDescriptionLabel;
  private AppCompatTextView textViewDescription;
  private Space spaceDescriptionParticipants;
  private AppCompatTextView textViewParticipantsLabel;
  private AppCompatTextView textViewParticipants;
  private Space spaceParticipantsFees;
  private AppCompatTextView textViewFeesLabel;
  private AppCompatTextView textViewFees;
  private Space spaceFeesRoundsInfo;
  private AppCompatTextView textViewRoundsInfoLabel;
  private Space spaceRoundsInfoRound1;
  private AppCompatTextView textViewRound1Label;
  private AppCompatTextView textViewRound1;
  private Space spaceRound1Round2;
  private AppCompatTextView textViewRound2Label;
  private AppCompatTextView textViewRound2;
  private Space spaceRound2Round3;
  private AppCompatTextView textViewRound3Label;
  private AppCompatTextView textViewRound3;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_activity_event_details_event_details, container, false);

    this.eventData = Event.parseJson(this.getArguments().getString(this.getString(R.string.activity_key_event_data)));

    this.initializeElements();

    this.populateUI();

    return this.rootView;
  }

  private void initializeElements() {
    this.textViewName = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_name);
    this.spaceNameDescription = (Space) this.rootView.findViewById(R.id.space_event_details_name_description);
    this.textViewDescriptionLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_description_label);
    this.textViewDescription = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_description);
    this.spaceDescriptionParticipants = (Space) this.rootView.findViewById(R.id.space_event_details_description_participants);
    this.textViewParticipantsLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_participants_label);
    this.textViewParticipants = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_participants);
    this.spaceParticipantsFees = (Space) this.rootView.findViewById(R.id.space_event_details_participants_fees);
    this.textViewFeesLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_fees_label);
    this.textViewFees = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_fees);
    this.spaceFeesRoundsInfo = (Space) this.rootView.findViewById(R.id.space_event_details_fees_round_info);
    this.textViewRoundsInfoLabel = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_info_label);
    this.spaceRoundsInfoRound1 = (Space) this.rootView.findViewById(R.id.space_event_details_round_info_round_1);
    this.textViewRound1Label = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_1_label);
    this.textViewRound1 = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_1);
    this.spaceRound1Round2 = (Space) this.rootView.findViewById(R.id.space_event_details_round_1_round_2);
    this.textViewRound2Label = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_2_label);
    this.textViewRound2 = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_2);
    this.spaceRound2Round3 = (Space) this.rootView.findViewById(R.id.space_event_details_round_2_round_3);
    this.textViewRound3Label = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_3_label);
    this.textViewRound3 = (AppCompatTextView) this.rootView.findViewById(R.id.text_view_event_details_round_3);
  }

  private void populateUI() {
    if (this.eventData.getName() != null && this.eventData.getName().length() > 0) {
      this.textViewName.setText(this.eventData.getName());
    } else {
      this.textViewName.setVisibility(View.GONE);
      this.spaceNameDescription.setVisibility(View.GONE);
    }

    if (this.eventData.getDescription() != null && this.eventData.getDescription().length() > 0) {
      this.textViewDescription.setText(this.eventData.getDescription());
    } else {
      this.spaceNameDescription.setVisibility(View.GONE);
      this.textViewDescriptionLabel.setVisibility(View.GONE);
      this.textViewDescription.setVisibility(View.GONE);
    }

    if (this.eventData.getParticipants() != null && this.eventData.getParticipants().length() > 0) {
      this.textViewParticipants.setText(this.eventData.getParticipants());
    } else {
      this.spaceDescriptionParticipants.setVisibility(View.GONE);
      this.textViewParticipantsLabel.setVisibility(View.GONE);
      this.textViewParticipants.setVisibility(View.GONE);
    }

    if (this.eventData.getFees() != null && this.eventData.getFees().length() > 0) {
      this.textViewFees.setText(this.getString(R.string.symbol_rupee) + " " + this.eventData.getFees());
    } else {
      this.spaceParticipantsFees.setVisibility(View.GONE);
      this.textViewFeesLabel.setVisibility(View.GONE);
      this.textViewFees.setVisibility(View.GONE);
    }

    if (this.eventData.getRound1Description() != null && this.eventData.getRound1Description().length() > 0) {
      this.textViewRound1.setText(this.eventData.getRound1Description());

      if (this.eventData.getRound2Description() != null && this.eventData.getRound2Description().length() > 0) {
        this.textViewRound2.setText(this.eventData.getRound2Description());

        if (this.eventData.getRound3Description() != null && this.eventData.getRound3Description().length() > 0) {
          this.textViewRound3.setText(this.eventData.getRound3Description());
        } else {
          this.spaceRound2Round3.setVisibility(View.GONE);
          this.textViewRound3Label.setVisibility(View.GONE);
          this.textViewRound3.setVisibility(View.GONE);
        }
      } else {
        this.spaceRound1Round2.setVisibility(View.GONE);
        this.textViewRound2Label.setVisibility(View.GONE);
        this.textViewRound2.setVisibility(View.GONE);
        this.spaceRound2Round3.setVisibility(View.GONE);
        this.textViewRound3Label.setVisibility(View.GONE);
        this.textViewRound3.setVisibility(View.GONE);
      }
    } else {
      this.spaceFeesRoundsInfo.setVisibility(View.GONE);
      this.textViewRoundsInfoLabel.setVisibility(View.GONE);
      this.spaceRoundsInfoRound1.setVisibility(View.GONE);
      this.textViewRound1Label.setVisibility(View.GONE);
      this.textViewRound1.setVisibility(View.GONE);
      this.spaceRound1Round2.setVisibility(View.GONE);
      this.textViewRound2Label.setVisibility(View.GONE);
      this.textViewRound2.setVisibility(View.GONE);
      this.spaceRound2Round3.setVisibility(View.GONE);
      this.textViewRound3Label.setVisibility(View.GONE);
      this.textViewRound3.setVisibility(View.GONE);
    }
  }
}
