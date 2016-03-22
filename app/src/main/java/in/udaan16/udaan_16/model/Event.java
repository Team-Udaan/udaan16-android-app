package in.udaan16.udaan_16.model;

import com.google.gson.Gson;
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

  @SerializedName("eventDescription")
  private String description;

  @SerializedName("email")
  private String email;

  @SerializedName("fees")
  private String fees;

  @SerializedName("participants")
  private String participants;

  @SerializedName("round1Description")
  private String round1Description;

  @SerializedName("round2Description")
  private String round2Description;

  @SerializedName("round3Description")
  private String round3Description;

  @SerializedName("managers")
  private ArrayList<EventManager> managers;

  public Event(String name, String description, String email, String fees, String participants, String round1Description, String round2Description, String round3Description, ArrayList<EventManager> managers) {
    this.name = name;
    this.description = description;
    this.email = email;
    this.fees = fees;
    this.participants = participants;
    this.round1Description = round1Description;
    this.round2Description = round2Description;
    this.round3Description = round3Description;
    this.managers = managers;
  }

  public static Event parseJson(String jsonEvent) {
    Gson gson = new Gson();
    Event event = gson.fromJson(jsonEvent, Event.class);
    return event;
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

  public String getFees() {
    return fees;
  }

  public void setFees(String fees) {
    this.fees = fees;
  }

  public String getParticipants() {
    return participants;
  }

  public void setParticipants(String participants) {
    this.participants = participants;
  }

  public String getRound3Description() {
    return round3Description;
  }

  public void setRound3Description(String round3Description) {
    this.round3Description = round3Description;
  }

  public String getRound2Description() {
    return round2Description;
  }

  public void setRound2Description(String round2Description) {
    this.round2Description = round2Description;
  }

  public String getRound1Description() {
    return round1Description;
  }

  public void setRound1Description(String round1Description) {
    this.round1Description = round1Description;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
