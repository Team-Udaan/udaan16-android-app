package in.udaan16.udaan_16.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.model.EventManager;

/**
 * Creator: vbarad
 * Date: 2016-03-15
 * Project: udaan16-android-app
 */
public class EventManagerAdapter extends RecyclerView.Adapter<EventManagerAdapter.ViewHolder> {
  private ArrayList<EventManager> eventManagers;
  private Activity activity;

  public EventManagerAdapter(ArrayList<EventManager> eventManagers, Activity activity) {
    this.eventManagers = eventManagers;
    this.activity = activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView eventCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycler_view_event_manager, parent, false);
    ViewHolder viewHolder = new ViewHolder(eventCard, this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    AppCompatTextView eventManagerName = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_event_manager_name);
    eventManagerName.setText(eventManagers.get(position).getName());
    AppCompatTextView eventManagerMobile = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_event_manager_mobile);
    eventManagerMobile.setText(eventManagers.get(position).getMobile());
  }

  @Override
  public int getItemCount() {
    return this.eventManagers.size();
  }

  public void onItemClick(int position) {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:" + this.eventManagers.get(position).getMobile()));
    this.activity.startActivity(intent);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements CardView.OnClickListener {

    public CardView cardItem;
    public EventManagerAdapter adapter;

    public ViewHolder(CardView cardItem, EventManagerAdapter adapter) {
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
