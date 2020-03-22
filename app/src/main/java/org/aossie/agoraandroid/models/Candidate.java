package org.aossie.agoraandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Candidate {
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("party")
  @Expose
  private String party;

  public Candidate() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getParty() {
    return party;
  }

  public void setParty(String party) {
    this.party = party;
  }
}
