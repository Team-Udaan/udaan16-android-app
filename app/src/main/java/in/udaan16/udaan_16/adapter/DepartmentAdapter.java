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
import in.udaan16.udaan_16.activity.TechEventsActivity;
import in.udaan16.udaan_16.model.Department;
import in.udaan16.udaan_16.util.Helper;

/**
 * Creator: vbarad
 * Date: 2016-03-13
 * Project: udaan16-android-app
 */
public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {
  private ArrayList<Department> departments;
  private Activity activity;

  public DepartmentAdapter(ArrayList<Department> departments, Activity activity) {
    this.departments = departments;
    this.activity = activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView eventCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycler_view_department, parent, false);
    ViewHolder viewHolder = new ViewHolder(eventCard, this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int colorPosition = position % Helper.colors.length;
    holder.cardItem.setCardBackgroundColor(this.activity.getResources().getColor(Helper.colors[colorPosition]));

    AppCompatTextView departmentName = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_department);
    departmentName.setText(departments.get(position).getAlias());
  }

  @Override
  public int getItemCount() {
    return this.departments.size();
  }

  public void onItemClick(int position) {
    Intent eventIntent = new Intent(this.activity, TechEventsActivity.class);
    eventIntent.putExtra(this.activity.getString(R.string.activity_key_position), position);
    eventIntent.putExtra(this.activity.getString(R.string.activity_key_title_name), departments.get(position).getAlias());
    this.activity.startActivity(eventIntent);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements CardView.OnClickListener {

    public CardView cardItem;
    public DepartmentAdapter adapter;

    public ViewHolder(CardView cardItem, DepartmentAdapter adapter) {
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
