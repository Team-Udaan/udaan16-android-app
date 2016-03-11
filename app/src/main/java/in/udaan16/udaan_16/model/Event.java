package in.udaan16.udaan_16.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class Event {

  @SerializedName("name")
  private String name;

  @SerializedName("description")
  private String description;

  @SerializedName("managers")
  private ArrayList<EventManager> managers;

  public Event(String name, String description, ArrayList<EventManager> managers) {
    this.name = name;
    this.description = description;
    this.managers = managers;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ArrayList<EventManager> getManagers() {
    return managers;
  }

  public void setManagers(ArrayList<EventManager> managers) {
    this.managers = managers;
  }
}
