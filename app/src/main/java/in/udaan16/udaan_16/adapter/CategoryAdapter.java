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
import in.udaan16.udaan_16.activity.CulturalActivity;
import in.udaan16.udaan_16.activity.DepartmentsActivity;
import in.udaan16.udaan_16.activity.GirlsEventsActivity;
import in.udaan16.udaan_16.activity.NightsActivity;
import in.udaan16.udaan_16.activity.NonTechActivity;
import in.udaan16.udaan_16.model.Category;
import in.udaan16.udaan_16.util.Helper;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
  private ArrayList<Category> categories;
  private Activity activity;

  public CategoryAdapter(ArrayList<Category> categories, Activity activity) {
    this.categories = categories;
    this.activity = activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView departmentCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycler_view_category, parent, false);
    ViewHolder viewHolder = new ViewHolder(departmentCard, this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int colorPosition = position % Helper.colors.length;
    holder.cardItem.setCardBackgroundColor(this.activity.getResources().getColor(Helper.colors[colorPosition]));

    AppCompatTextView departmentName = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_category);
    departmentName.setText(categories.get(position).getName());
  }

  @Override
  public int getItemCount() {
    return this.categories.size();
  }

  public void onItemClick(int position) {
    if (categories.get(position).getAlias().equals("tech")) {
      Intent departmentIntent = new Intent(this.activity, DepartmentsActivity.class);

      this.activity.startActivity(departmentIntent);
    } else if (categories.get(position).getAlias().equals("non-tech")) {
      Intent nonTechIntent = new Intent(this.activity, NonTechActivity.class);

      this.activity.startActivity(nonTechIntent);
    } else if (categories.get(position).getAlias().equals("cultural")) {
      Intent culturalIntent = new Intent(this.activity, CulturalActivity.class);

      this.activity.startActivity(culturalIntent);
    } else if (categories.get(position).getAlias().equals("girls-special")) {
      Intent girlsEventsIntent = new Intent(this.activity, GirlsEventsActivity.class);

      this.activity.startActivity(girlsEventsIntent);
    } else if (categories.get(position).getAlias().equals("nights")) {
      Intent nightsIntent = new Intent(this.activity, NightsActivity.class);

      this.activity.startActivity(nightsIntent);
    }
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements CardView.OnClickListener {

    public CardView cardItem;
    public CategoryAdapter adapter;

    public ViewHolder(CardView cardItem, CategoryAdapter adapter) {
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
