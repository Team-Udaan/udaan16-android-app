package in.udaan16.udaan_16.model;

import com.google.gson.annotations.SerializedName;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class EventManager {

  @SerializedName("name")
  private String name;

  @SerializedName("mobile")
  private String mobile;

  @SerializedName("email")
  private String email;

  public EventManager(String name, String mobile, String email) {
    this.name = name;
    this.mobile = mobile;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
