package in.udaan16.udaan_16.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.util.Helper;
import in.udaan16.udaan_16.util.VolleySingleton;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_splash);

    ActionBar actionBar = this.getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(R.string.title_activity_splash);
    }
  }

  @Override
  protected void onStart() {
    super.onStart();

    this.initializeElements();

    if (Helper.hasNetworkConnection(this)) {
      String timeStampUrl = this.getString(R.string.api_endpoint_timestamp);
      JsonObjectRequest timeStampRequest = new JsonObjectRequest(Request.Method.GET, timeStampUrl, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
          try {
            float lastModified = Float.parseFloat(response.getString("message"));

            SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(SplashActivity.this.getString(R.string.prefs_file), Context.MODE_PRIVATE);

            if (sharedPreferences.getFloat(SplashActivity.this.getString(R.string.prefs_key_last_modified), 0) < lastModified) { //If file does not have a key
              sharedPreferences.edit().putFloat(SplashActivity.this.getString(R.string.prefs_key_last_modified), lastModified).apply(); //store new value of last modified in shared pref

              String dataUrl = SplashActivity.this.getString(R.string.api_endpoint_all);
              /**
               * Type of request(get in this case)
               * url
               * listener
               *
               */
              JsonObjectRequest dataRequest = new JsonObjectRequest(Request.Method.GET, dataUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                  SplashActivity.this.successfulDataResponse(response);
                }
              }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                  Log.e("Data request error", String.valueOf(error.networkResponse));
                }
              });
              VolleySingleton.getInstance(SplashActivity.this).addToRequestQueue(dataRequest);
            } else {
              SplashActivity.this.startMainActivity();
            }

          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Log.e("Timestamp request error", String.valueOf(error.networkResponse));
        }
      });
      VolleySingleton.getInstance(this).addToRequestQueue(timeStampRequest);
    } else if (!this.getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE).contains(this.getString(R.string.prefs_key_data_json))) {
      Helper.showNetworkAlertPopUp(this);
    } else {
      this.startMainActivity();
    }
  }

  private void startMainActivity() {
    Intent mainActivityIntent = new Intent(this, MainActivity.class);
    this.startActivity(mainActivityIntent);
  }

  private void initializeElements() {
    ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.progress_bar_splash_screen);

  }

  private void successfulDataResponse(JSONObject response) {
    try {
      SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
      sharedPreferences.edit().putString(this.getString(R.string.prefs_key_data_json), response.getJSONObject("message").toString()).apply();
      this.startMainActivity();
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }
}
