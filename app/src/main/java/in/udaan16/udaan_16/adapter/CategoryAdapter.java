package in.udaan16.udaan_16.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.model.Category;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
  private ArrayList<Category> branches;

  public CategoryAdapter(ArrayList<Category> branches) {
    this.branches = branches;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView departmentCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycler_view_department, parent, false);
    ViewHolder viewHolder = new ViewHolder(departmentCard);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    AppCompatTextView departmentName = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_department_name);
    departmentName.setText(branches.get(getItemCount() - position - 1).getName());
  }

  @Override
  public int getItemCount() {
    return this.branches.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public CardView cardItem;

    public ViewHolder(CardView cardItem) {
      super(cardItem);
      this.cardItem = cardItem;
    }
  }
}
