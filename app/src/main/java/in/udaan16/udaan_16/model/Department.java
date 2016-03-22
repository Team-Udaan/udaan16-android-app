package in.udaan16.udaan_16.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Creator: vbarad
 * Date: 2016-03-11
 * Project: udaan16-android-app
 */
public class Department {

  @SerializedName("alias")
  private String alias;

  @SerializedName("name")
  private String name;

  @SerializedName("events")
  private ArrayList<Event> events;

  @SerializedName("heads")
  private ArrayList<BranchManager> heads;

  @SerializedName("coheads")
  private ArrayList<BranchManager> coheads;

  public Department(String alias, String name, ArrayList<Event> events, ArrayList<BranchManager> heads, ArrayList<BranchManager> coheads) {
    this.alias = alias;
    this.name = name;
    this.events = events;
    this.heads = heads;
    this.coheads = coheads;
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

  public ArrayList<Event> getEvents() {
    return events;
  }

  public void setEvents(ArrayList<Event> events) {
    this.events = events;
  }

  public ArrayList<BranchManager> getHeads() {
    return heads;
  }

  public void setHeads(ArrayList<BranchManager> heads) {
    this.heads = heads;
  }

  public ArrayList<BranchManager> getCoheads() {
    return coheads;
  }

  public void setCoheads(ArrayList<BranchManager> coheads) {
    this.coheads = coheads;
  }
}
