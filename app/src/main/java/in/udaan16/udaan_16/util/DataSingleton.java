package in.udaan16.udaan_16.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.model.Branch;

/**
 * Creator: vbarad
 * Date: 2016-03-10
 * Project: udaan16-android-app
 */
public class DataSingleton {

  private static DataSingleton dataSingletonInstance;

  private JSONObject data;

  private ArrayList<Branch> dataBranches;

  private DataSingleton(Activity activity) throws JSONException {
    SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    String stringEvents = sharedPreferences.getString(activity.getString(R.string.prefs_key_data_json), "{\n" +
        "  \"branches\": [\n" +
        "    {\n" +
        "      \"name\": \"CP/IT\",\n" +
        "      \"alias\": \"KeyCoders\",\n" +
        "      \"tech-events\": [\n" +
        "        {\n" +
        "          \"name\": \"Bid-to-Make\",\n" +
        "          \"description\": \"This is a Tech event which is based on web development\",\n" +
        "          \"managers\": [\n" +
        "            {\n" +
        "              \"name\": \"Dhwani Katkoria\",\n" +
        "              \"mobile\": \"8734955569\",\n" +
        "              \"email\": \"dhwani@mailinator.org\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"name\": \"Darshana Patel\",\n" +
        "              \"mobile\": \"7048495716\",\n" +
        "              \"email\": \"darshana@mailinator.org\"\n" +
        "            }\n" +
        "          ]\n" +
        "        }\n" +
        "      ],\n" +
        "      \"non-tech-events\": [\n" +
        "        {\n" +
        "          \"name\": \"Bid-to-Play\",\n" +
        "          \"description\": \"This is a Non-Tech event which is based on web development\",\n" +
        "          \"managers\": [\n" +
        "            {\n" +
        "              \"name\": \"Dhwani Kataria\",\n" +
        "              \"mobile\": \"8734955569\",\n" +
        "              \"email\": \"dhwani@mailinator.org\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"name\": \"Darshan Patel\",\n" +
        "              \"mobile\": \"7048495716\",\n" +
        "              \"email\": \"darshan@mailinator.org\"\n" +
        "            }\n" +
        "          ]\n" +
        "        }\n" +
        "      ]\n" +
        "    }\n" +
        "  ]\n" +
        "}");

    this.data = new JSONObject(stringEvents);

    this.parseAndLoadData();
  }

  public static DataSingleton getInstance(Activity activity) throws JSONException {
    if (DataSingleton.dataSingletonInstance == null) {
      DataSingleton.dataSingletonInstance = new DataSingleton(activity);
    }
    return DataSingleton.dataSingletonInstance;
  }

  private void parseAndLoadData() throws JSONException {
    Gson gson = new Gson();

    this.dataBranches = new ArrayList<>(Arrays.asList(gson.fromJson(this.data.getJSONArray("branches").toString(), Branch[].class)));
  }

  public ArrayList<Branch> getDataBranches() {
    return dataBranches;
  }
}
