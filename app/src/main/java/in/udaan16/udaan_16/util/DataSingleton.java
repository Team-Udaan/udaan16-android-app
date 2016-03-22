package in.udaan16.udaan_16.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.model.Category;
import in.udaan16.udaan_16.model.Department;
import in.udaan16.udaan_16.model.Developer;
import in.udaan16.udaan_16.model.Event;

/**
 * Creator: vbarad
 * Date: 2016-03-10
 * Project: udaan16-android-app
 */
public class DataSingleton {

  private static DataSingleton dataSingletonInstance;

  private ArrayList<Category> dataCategories;
  private ArrayList<Department> dataDepartments;
  private ArrayList<Event> dataNonTechEvents;
  private ArrayList<Event> dataGirlsSpecial;
  private ArrayList<Event> dataNights;
  private ArrayList<Event> dataCultural;
  private ArrayList<Developer> dataDeveloper;

  private DataSingleton(Activity activity) throws JSONException {
    SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    String stringData = sharedPreferences.getString(activity.getString(R.string.prefs_key_data_json), "");

    JSONObject data = new JSONObject(stringData);
    JSONArray developers = new JSONArray(activity.getString(R.string.developers_json));

    this.parseAndLoadData(data, developers);
  }

  public static DataSingleton getInstance(Activity activity) throws JSONException {
    if (DataSingleton.dataSingletonInstance == null) {
      DataSingleton.dataSingletonInstance = new DataSingleton(activity);
    }
    return DataSingleton.dataSingletonInstance;
  }

  private void parseAndLoadData(JSONObject data, JSONArray developers) throws JSONException {
    Gson gson = new Gson();

    this.dataDepartments = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("departments").toString(), Department[].class)));
    this.dataCategories = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("categories").toString(), Category[].class)));
    this.dataNonTechEvents = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("non-tech").toString(), Event[].class)));
    this.dataGirlsSpecial = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("girls-special").toString(), Event[].class)));
    this.dataNights = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("nights").toString(), Event[].class)));
    this.dataCultural = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("cultural").toString(), Event[].class)));
    this.dataDeveloper = new ArrayList<>(Arrays.asList(gson.fromJson(developers.toString(), Developer[].class)));
  }

  public ArrayList<Department> getDataDepartments() {
    return dataDepartments;
  }

  public ArrayList<Category> getDataCategories() {
    return dataCategories;
  }

  public ArrayList<Event> getDataNonTechEvents() {
    return dataNonTechEvents;
  }

  public ArrayList<Event> getDataGirlsSpecial() {
    return dataGirlsSpecial;
  }

  public ArrayList<Event> getDataNights() {
    return dataNights;
  }

  public ArrayList<Event> getDataCultural() {
    return dataCultural;
  }

  public ArrayList<Developer> getDataDeveloper() {
    return dataDeveloper;
  }
}
