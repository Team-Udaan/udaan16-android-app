package in.udaan16.udaan_16.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import in.udaan16.udaan_16.R;
import in.udaan16.udaan_16.util.VolleySingleton;

/**
 * Creator: vbarad
 * Date: 2016-03-07
 * Project: udaan16-android-app
 */
public class LoginPromptFragment extends Fragment implements View.OnClickListener {

  private static final String LOG_TAG = "LoginPromptFragment";

  private AppCompatEditText editTextUserName;
  private AppCompatEditText editTextPassword;
  private AppCompatButton buttonLogin;
  private AppCompatButton buttonRemindLater;
  private AppCompatButton buttonSkip;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main_login_prompt, container, false);
  }

  @Override
  public void onStart() {
    super.onStart();
    this.initializeComponents();
  }

  public void initializeComponents() {
    this.editTextUserName = (AppCompatEditText) this.getView().findViewById(R.id.editText_fragment_main_login_prompt_username);
    this.editTextPassword = (AppCompatEditText) this.getView().findViewById(R.id.editText_fragment_main_login_prompt_password);
    this.buttonLogin = (AppCompatButton) this.getView().findViewById(R.id.button_fragment_main_login_prompt_login);
    this.buttonRemindLater = (AppCompatButton) this.getView().findViewById(R.id.button_fragment_main_login_prompt_remind_later);
    this.buttonSkip = (AppCompatButton) this.getView().findViewById(R.id.button_fragment_main_login_prompt_skip);

    this.buttonLogin.setOnClickListener(this);
    this.buttonRemindLater.setOnClickListener(this);
    this.buttonSkip.setOnClickListener(this);
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
          LoginPromptFragment.this.successfulLogin(response);
        }
      }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Log.e(LoginPromptFragment.LOG_TAG, error.toString());
        }
      });

      VolleySingleton.getInstance(this.getContext()).addToRequestQueue(loginRequest);
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

    SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean(this.getString(R.string.prefs_key_remind_later), false);
    editor.putString(this.getString(R.string.prefs_authorization_token), stringAuthorizationToken);
    editor.putBoolean(this.getString(R.string.prefs_logged_in), true);
    editor.apply();
  }
  
  private void remindLater() {
    SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    sharedPreferences.edit().putBoolean(this.getString(R.string.prefs_key_remind_later), true).apply();
  }

  private void skip() {
    SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    sharedPreferences.edit().putBoolean(this.getString(R.string.prefs_key_remind_later), false).apply();
  }
}
