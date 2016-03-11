package in.udaan16.udaan_16.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.util.VolleySingleton;

public class LoginActivity extends AppCompatActivity {

  private static final String LOG_TAG = "LoginActivity";

  private AppCompatEditText editTextUserName;
  private AppCompatEditText editTextPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_login);
  }

  @Override
  protected void onStart() {
    super.onStart();

    Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_activity_login);
    this.setSupportActionBar(toolbar);
    ActionBar actionBar = this.getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(R.string.title_activity_login);
    }

    SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    final boolean loggedIn = sharedPreferences.getBoolean(this.getString(R.string.prefs_key_logged_in), false);
    final boolean remindLater = sharedPreferences.getBoolean(this.getString(R.string.prefs_key_remind_later), false);

    if (loggedIn || !remindLater) {
      Intent mainActivityIntent = new Intent(this, MainActivity.class);
      this.startActivity(mainActivityIntent);
    } else {
      this.initializeElements();
    }
  }

  private void initializeElements() {
    this.editTextUserName = (AppCompatEditText) this.findViewById(R.id.editText_fragment_main_login_prompt_username);
    this.editTextPassword = (AppCompatEditText) this.findViewById(R.id.editText_fragment_main_login_prompt_password);
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_fragment_main_login_prompt_login:
        this.login();
        break;
      case R.id.button_fragment_main_login_prompt_remind_later:
        this.remindLater();
        break;
      case R.id.button_fragment_main_login_prompt_skip:
        this.skip();
        break;
    }
  }

  private void login() {
    String stringUserName = this.editTextUserName.getText().toString();
    String stringPassword = this.editTextPassword.getText().toString();

    try {
      final String loginRequestUrl = this.getString(R.string.api_endpoint_login);

      JSONObject requestParameters = new JSONObject();

      requestParameters.put(this.getString(R.string.api_key_username), stringUserName);
      requestParameters.put(this.getString(R.string.api_key_password), stringPassword);

      JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, loginRequestUrl, requestParameters, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
          LoginActivity.this.successfulLogin(response);
        }
      }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Log.e(LoginActivity.LOG_TAG, (new String(error.networkResponse.data, Charset.defaultCharset())));
        }
      });

      VolleySingleton.getInstance(this).addToRequestQueue(loginRequest);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void successfulLogin(JSONObject response) {
    String stringAuthorizationToken;

    try {
      stringAuthorizationToken = response.getString(this.getString(R.string.api_key_authorization_token));
    } catch (JSONException e) {
      stringAuthorizationToken = "invalid_token";
      e.printStackTrace();
    }

    SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean(this.getString(R.string.prefs_key_remind_later), false);
    editor.putString(this.getString(R.string.prefs_key_authorization_token), stringAuthorizationToken);
    editor.putBoolean(this.getString(R.string.prefs_key_logged_in), true);
    editor.apply();
  }

  private void remindLater() {
    SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    sharedPreferences.edit().putBoolean(this.getString(R.string.prefs_key_remind_later), true).apply();
  }

  private void skip() {
    SharedPreferences sharedPreferences = this.getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    sharedPreferences.edit().putBoolean(this.getString(R.string.prefs_key_remind_later), false).apply();
  }
}
