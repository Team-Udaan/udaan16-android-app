package in.udaan16.udaan_16.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class Branch {

  @SerializedName("alias")
  private String alias;

  @SerializedName("name")
  private String name;

  @SerializedName("tech-events")
  private ArrayList<Event> eventsTech;

  @SerializedName("non-tech-events")
  private ArrayList<Event> eventsNonTech;

  public Branch(String alias, String name, ArrayList<Event> eventsTech, ArrayList<Event> eventsNonTech) {
    this.alias = alias;
    this.name = name;
    this.eventsTech = eventsTech;
    this.eventsNonTech = eventsNonTech;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Event> getEventsTech() {
    return eventsTech;
  }

  public void setEventsTech(ArrayList<Event> eventsTech) {
    this.eventsTech = eventsTech;
  }

  public ArrayList<Event> getEventsNonTech() {
    return eventsNonTech;
  }

  public void setEventsNonTech(ArrayList<Event> eventsNonTech) {
    this.eventsNonTech = eventsNonTech;
  }
}
