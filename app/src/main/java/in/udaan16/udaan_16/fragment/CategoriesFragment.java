package in.udaan16.udaan_16.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class CategoriesFragment extends Fragment {
    private View rootView;

    private RecyclerView categoriesRecyclerView;
    private CategoryAdapter categoriesAdapter;
    private RecyclerView.LayoutManager categoriesLayoutManager;

    private ArrayList<Category> categories;

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return a view which populates the fragment
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_activity_main_categories, container, false);

        try {
            this.categories = DataSingleton
                    .getInstance(this.getActivity())
                    .getDataCategories();

            this.categoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_departments);
            this.categoriesRecyclerView.setHasFixedSize(true);

//      this.categoriesLayoutManager = new LinearLayoutManager(this.rootView.getContext());
            this.categoriesLayoutManager = new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false);
//      this.categoriesLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            this.categoriesRecyclerView.setLayoutManager(this.categoriesLayoutManager);

            this.categoriesAdapter = new CategoryAdapter(categories, getActivity());
            this.categoriesRecyclerView.setAdapter(categoriesAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this.rootView;
    }
}
