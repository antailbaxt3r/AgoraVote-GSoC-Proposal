package org.aossie.agoraandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Voter {
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("hash")
  @Expose
  private String hash;

  public Voter() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }
}
