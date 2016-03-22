package in.udaan16.udaan_16.model;

import com.google.gson.annotations.SerializedName;

/**
 * Creator: vbarad
 * Date: 2016-03-18
 * Project: udaan16-android-app
 */
public class BranchManager {

  @SerializedName("name")
  private String name;

  @SerializedName("mobile")
  private String mobile;

  public BranchManager(String name, String mobile) {
    this.name = name;
    this.mobile = mobile;
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
}
