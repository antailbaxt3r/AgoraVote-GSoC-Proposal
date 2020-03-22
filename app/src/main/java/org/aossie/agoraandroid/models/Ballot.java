package org.aossie.agoraandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ballot {
  @SerializedName("voteBallot")
  @Expose
  private String voteBallot;
  @SerializedName("hash")
  @Expose
  private String hash;

  public Ballot() {
  }

  public String getVoteBallot() {
    return voteBallot;
  }

  public void setVoteBallot(String voteBallot) {
    this.voteBallot = voteBallot;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }
}
