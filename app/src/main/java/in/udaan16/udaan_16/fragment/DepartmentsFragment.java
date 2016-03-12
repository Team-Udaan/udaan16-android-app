package in.udaan16.udaan_16.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.CategoryAdapter;
import in.udaan16.udaan_16.model.Category;
import in.udaan16.udaan_16.util.DataSingleton;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class DepartmentsFragment extends Fragment {
  private View rootView;

  private RecyclerView categoryRecyclerView;
  private CategoryAdapter categoryAdapter;
  private RecyclerView.LayoutManager categoryLayoutManager;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_activity_main_departments, container, false);

    try {
      ArrayList<Category> branches = DataSingleton.getInstance(this.getActivity()).getDataCategories();

      this.categoryRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_departments);
      this.categoryRecyclerView.setHasFixedSize(true);

//      this.categoryLayoutManager = new LinearLayoutManager(this.rootView.getContext());
//      this.categoryLayoutManager = new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false);
      this.categoryLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.categoryRecyclerView.setLayoutManager(this.categoryLayoutManager);

      this.categoryAdapter = new CategoryAdapter(branches);
      this.categoryRecyclerView.setAdapter(categoryAdapter);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return this.rootView;
  }
}
