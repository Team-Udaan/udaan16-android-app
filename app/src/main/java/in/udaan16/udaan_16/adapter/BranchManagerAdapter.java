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
import in.udaan16.udaan_16.model.BranchManager;

/**
 * Creator: vbarad
 * Date: 2016-03-18
 * Project: udaan16-android-app
 */
public class BranchManagerAdapter extends RecyclerView.Adapter<BranchManagerAdapter.ViewHolder> {
  private ArrayList<BranchManager> branchManagers;
  private Activity activity;

  public BranchManagerAdapter(ArrayList<BranchManager> branchManagers, Activity activity) {
    this.branchManagers = branchManagers;
    this.activity = activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView eventCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycler_view_branch_manager, parent, false);
    ViewHolder viewHolder = new ViewHolder(eventCard, this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    AppCompatTextView branchManagerName = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_branch_manager_name);
    branchManagerName.setText(branchManagers.get(position).getName());
    AppCompatTextView eventManagerMobile = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_branch_manager_mobile);
    eventManagerMobile.setText(branchManagers.get(position).getMobile());
  }

  @Override
  public int getItemCount() {
    return this.branchManagers.size();
  }

  public void onItemClick(int position) {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:" + this.branchManagers.get(position).getMobile()));
    this.activity.startActivity(intent);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements CardView.OnClickListener {

    public CardView cardItem;
    public BranchManagerAdapter adapter;

    public ViewHolder(CardView cardItem, BranchManagerAdapter adapter) {
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
