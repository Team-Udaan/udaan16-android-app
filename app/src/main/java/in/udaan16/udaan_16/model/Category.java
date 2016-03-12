package in.udaan16.udaan_16.model;

import com.google.gson.annotations.SerializedName;

/**
 * Creator: vbarad
 * Date: 2016-03-13
 * Project: udaan16-android-app
 */
public class Category {

  @SerializedName("name")
  private String name;

  @SerializedName("alias")
  private String alias;

  public Category(String name, String alias) {
    this.name = name;
    this.alias = alias;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }
}
