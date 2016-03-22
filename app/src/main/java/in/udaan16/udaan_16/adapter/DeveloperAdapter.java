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
import in.udaan16.udaan_16.model.Developer;

/**
 * Creator: vbarad
 * Date: 2016-03-18
 * Project: udaan16-android-app
 */
public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {
  private ArrayList<Developer> developers;
  private Activity activity;

  public DeveloperAdapter(ArrayList<Developer> developers, Activity activity) {
    this.developers = developers;
    this.activity = activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CardView eventCard = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycler_view_developers, parent, false);
    ViewHolder viewHolder = new ViewHolder(eventCard, this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    AppCompatTextView developerName = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_developer_name);
    developerName.setText(developers.get(position).getName());
    AppCompatTextView developerTitle = (AppCompatTextView) holder.cardItem.findViewById(R.id.text_view_card_developer_title);
    developerTitle.setText(developers.get(position).getTitle());

    int colorID;
    switch (developers.get(position).getColor()) {
      case "android":
        colorID = R.color.colorAndroid;
        break;
      case "windows-phone":
        colorID = R.color.colorWindows;
        break;
      case "web":
        colorID = R.color.colorWeb;
        break;
      case "ui":
        colorID = R.color.colorUI;
        break;
      case "backend":
        colorID = R.color.colorBackend;
        break;
      default:
        colorID = R.color.colorPrimary;
        break;
    }
    holder.cardItem.setCardBackgroundColor(this.activity.getResources().getColor(colorID));
  }

  @Override
  public int getItemCount() {
    return this.developers.size();
  }

  public void onItemClick(int position, View view) {
    switch (view.getId()) {
      case R.id.button_card_developer_mobile:
        this.makeCall(position);
        break;
      case R.id.button_card_developer_email:
        this.sendEmail(position);
        break;
      case R.id.button_card_developer_github:
        this.openGithub(position);
        break;
    }
  }

  public void makeCall(int position) {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:" + this.developers.get(position).getMobile()));
    this.activity.startActivity(intent);
  }

  public void sendEmail(int position) {
    Uri emailUri = Uri.parse("mailto:" + this.developers.get(position).getEmail());
    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);
    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{this.developers.get(position).getEmail()});
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Review regarding work done in Udaan-16");
    this.activity.startActivity(Intent.createChooser(emailIntent, "Send mail"));
  }

  public void openGithub(int position) {
    Uri githubUri = Uri.parse(this.developers.get(position).getGithub());
    Intent githubIntent = new Intent(Intent.ACTION_VIEW, githubUri);
    this.activity.startActivity(githubIntent);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements CardView.OnClickListener {

    public CardView cardItem;
    public DeveloperAdapter adapter;

    public ViewHolder(CardView cardItem, DeveloperAdapter adapter) {
      super(cardItem);
      this.cardItem = cardItem;
      this.adapter = adapter;
      cardItem.setOnClickListener(this);
      cardItem.findViewById(R.id.button_card_developer_mobile).setOnClickListener(this);
      cardItem.findViewById(R.id.button_card_developer_email).setOnClickListener(this);
      cardItem.findViewById(R.id.button_card_developer_github).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      this.adapter.onItemClick(getAdapterPosition(), view);
    }
  }
}
