package org.aossie.agoraandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ElectionData {
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("_id")
  @Expose
  private String _id;
  @SerializedName("electionType")
  @Expose
  private String electionType;
  @SerializedName("candidates")
  @Expose
  private List<String> candidates = null;
  @SerializedName("ballotVisibility")
  @Expose
  private String ballotVisibility;
  @SerializedName("voterListVisibility")
  @Expose
  private Boolean voterListVisibility;
  @SerializedName("startingDate")
  @Expose
  private String startingDate;
  @SerializedName("endingDate")
  @Expose
  private String endingDate;
  @SerializedName("isInvite")
  @Expose
  private Boolean isInvite;
  @SerializedName("isRealTime")
  @Expose
  private Boolean isRealTime;
  @SerializedName("votingAlgo")
  @Expose
  private String votingAlgo;
  @SerializedName("noVacancies")
  @Expose
  private Integer noVacancies;
  @SerializedName("ballot")
  @Expose
  private List<Ballot> ballot = null;

  public ElectionData() {
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

  public String getElectionType() {
    return electionType;
  }

  public void setElectionType(String electionType) {
    this.electionType = electionType;
  }

  public List<String> getCandidates() {
    return candidates;
  }

  public void setCandidates(List<String> candidates) {
    this.candidates = candidates;
  }

  public String getBallotVisibility() {
    return ballotVisibility;
  }

  public void setBallotVisibility(String ballotVisibility) {
    this.ballotVisibility = ballotVisibility;
  }

  public Boolean getVoterListVisibility() {
    return voterListVisibility;
  }

  public void setVoterListVisibility(Boolean voterListVisibility) {
    this.voterListVisibility = voterListVisibility;
  }

  public String getStartingDate() {
    return startingDate;
  }

  public void setStartingDate(String startingDate) {
    this.startingDate = startingDate;
  }

  public String getEndingDate() {
    return endingDate;
  }

  public void setEndingDate(String endingDate) {
    this.endingDate = endingDate;
  }

  public Boolean getIsInvite() {
    return isInvite;
  }

  public void setIsInvite(Boolean isInvite) {
    this.isInvite = isInvite;
  }

  public Boolean getIsRealTime() {
    return isRealTime;
  }

  public void setIsRealTime(Boolean isRealTime) {
    this.isRealTime = isRealTime;
  }

  public String getVotingAlgo() {
    return votingAlgo;
  }

  public void setVotingAlgo(String votingAlgo) {
    this.votingAlgo = votingAlgo;
  }

  public Integer getNoVacancies() {
    return noVacancies;
  }

  public void setNoVacancies(Integer noVacancies) {
    this.noVacancies = noVacancies;
  }

  public List<Ballot> getBallot() {
    return ballot;
  }

  public void setBallot(List<Ballot> ballot) {
    this.ballot = ballot;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }
}
