package in.udaan16.udaan_16.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.activity.EventDetailsActivity;
import in.udaan16.udaan_16.model.Event;
import in.udaan16.udaan_16.util.Helper;

/**
 * Creator: vbarad
 * Date: 2016-03-13
 * Project: udaan16-android-app
 */
public class NonTechEventAdapter extends RecyclerView.Adapter<NonTechEventAdapter.ViewHolder> {
  private ArrayList<Event> events;
  private Activity activity;

  public NonTechEventAdapter(ArrayList<Event> events, Activity activity) {
    this.events = events;
    this.activity = activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView eventCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycler_view_non_tech, parent, false);
    ViewHolder viewHolder = new ViewHolder(eventCard, this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int colorPosition = position % Helper.colors.length;
    holder.cardItem.setCardBackgroundColor(this.activity.getResources().getColor(Helper.colors[colorPosition]));

    AppCompatTextView departmentName = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_non_tech_event_name);
    departmentName.setText(events.get(position).getName());
  }

  @Override
  public int getItemCount() {
    return this.events.size();
  }

  public void onItemClick(int position) {
    Intent eventDetailsIntent = new Intent(NonTechEventAdapter.this.activity, EventDetailsActivity.class);
    eventDetailsIntent.putExtra(NonTechEventAdapter.this.activity.getString(R.string.activity_key_event_data), events.get(position).toString());
    NonTechEventAdapter.this.activity.startActivity(eventDetailsIntent);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements CardView.OnClickListener {

    public CardView cardItem;
    public NonTechEventAdapter adapter;

    public ViewHolder(CardView cardItem, NonTechEventAdapter adapter) {
      super(cardItem);
      this.cardItem = cardItem;
      this.adapter = adapter;
      cardItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      this.adapter.onItemClick(getAdapterPosition());
    }
  }
}
