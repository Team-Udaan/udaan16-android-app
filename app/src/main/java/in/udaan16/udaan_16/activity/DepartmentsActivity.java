package in.udaan16.udaan_16.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.adapter.DepartmentAdapter;
import in.udaan16.udaan_16.model.Department;
import in.udaan16.udaan_16.util.DataSingleton;

public class DepartmentsActivity extends AppCompatActivity {

  private RecyclerView departmentsRecyclerView;
  private DepartmentAdapter departmentsAdapter;
  private RecyclerView.LayoutManager departmentsLayoutManager;

  private ArrayList<Department> departments;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.initializeElements();
  }

  private void initializeElements() {
    this.setContentView(R.layout.activity_departments);

    ActionBar actionBar = this.getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(this.getString(R.string.title_activity_departments));
    }

    try {
      this.departments = DataSingleton.getInstance(this).getDataDepartments();

      this.departmentsRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView_departments);
      this.departmentsRecyclerView.setHasFixedSize(true);

//      this.departmentsLayoutManager = new LinearLayoutManager(this);
      this.departmentsLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
//      this.departmentsLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      this.departmentsRecyclerView.setLayoutManager(this.departmentsLayoutManager);

      this.departmentsAdapter = new DepartmentAdapter(departments, this);
      this.departmentsRecyclerView.setAdapter(departmentsAdapter);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
