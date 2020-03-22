package org.aossie.agoraandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Election {
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("electionType")
  @Expose
  private String electionType;
  @SerializedName("creatorName")
  @Expose
  private String creatorName;
  @SerializedName("creatorEmail")
  @Expose
  private String creatorEmail;
  @SerializedName("start")
  @Expose
  private String start;
  @SerializedName("end")
  @Expose
  private String end;
  @SerializedName("realtimeResult")
  @Expose
  private Boolean realtimeResult;
  @SerializedName("votingAlgo")
  @Expose
  private String votingAlgo;
  @SerializedName("candidates")
  @Expose
  private List<String> candidates = null;
  @SerializedName("ballotVisibility")
  @Expose
  private String ballotVisibility;
  @SerializedName("VoterVisibility")
  @Expose
  private Boolean VoterVisibility;
  @SerializedName("isInvite")
  @Expose
  private Boolean isInvite;
  @SerializedName("isCompleted")
  @Expose
  private Boolean isCompleted;
  @SerializedName("isStarted")
  @Expose
  private Boolean isStarted;
  @SerializedName("createdTime")
  @Expose
  private String createdTime;
  @SerializedName("adminLink")
  @Expose
  private String adminLink;
  @SerializedName("inviteCode")
  @Expose
  private String inviteCode;
  @SerializedName("ballot")
  @Expose
  private List<Ballot> ballot = null;
  @SerializedName("Voter")
  @Expose
  private List<Voter> Voter = null;
  @SerializedName("winners")
  @Expose
  private List<Winner> winners = null;
  @SerializedName("isCounted")
  @Expose
  private Boolean isCounted;
  @SerializedName("noVacancies")
  @Expose
  private Integer noVacancies;
  @SerializedName("loginInfo")
  @Expose
  private LoginInfo loginInfo;
  @SerializedName("electionData")
  @Expose
  private ElectionData electionData;

  public Election() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creatorName) {
    this.creatorName = creatorName;
  }

  public String getCreatorEmail() {
    return creatorEmail;
  }

  public void setCreatorEmail(String creatorEmail) {
    this.creatorEmail = creatorEmail;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public Boolean getRealtimeResult() {
    return realtimeResult;
  }

  public void setRealtimeResult(Boolean realtimeResult) {
    this.realtimeResult = realtimeResult;
  }

  public String getVotingAlgo() {
    return votingAlgo;
  }

  public void setVotingAlgo(String votingAlgo) {
    this.votingAlgo = votingAlgo;
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

  public Boolean getVoterVisibility() {
    return VoterVisibility;
  }

  public void setVoterVisibility(Boolean VoterVisibility) {
    this.VoterVisibility = VoterVisibility;
  }

  public Boolean getIsInvite() {
    return isInvite;
  }

  public void setIsInvite(Boolean isInvite) {
    this.isInvite = isInvite;
  }

  public Boolean getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public Boolean getIsStarted() {
    return isStarted;
  }

  public void setIsStarted(Boolean isStarted) {
    this.isStarted = isStarted;
  }

  public String getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }

  public String getAdminLink() {
    return adminLink;
  }

  public void setAdminLink(String adminLink) {
    this.adminLink = adminLink;
  }

  public String getInviteCode() {
    return inviteCode;
  }

  public void setInviteCode(String inviteCode) {
    this.inviteCode = inviteCode;
  }

  public List<Ballot> getBallot() {
    return ballot;
  }

  public void setBallot(List<Ballot> ballot) {
    this.ballot = ballot;
  }

  public List<Voter> getVoter() {
    return Voter;
  }

  public void setVoter(List<Voter> Voter) {
    this.Voter = Voter;
  }

  public List<Winner> getWinners() {
    return winners;
  }

  public void setWinners(List<Winner> winners) {
    this.winners = winners;
  }

  public Boolean getIsCounted() {
    return isCounted;
  }

  public void setIsCounted(Boolean isCounted) {
    this.isCounted = isCounted;
  }

  public Integer getNoVacancies() {
    return noVacancies;
  }

  public void setNoVacancies(Integer noVacancies) {
    this.noVacancies = noVacancies;
  }

  public LoginInfo getLoginInfo() {
    return loginInfo;
  }

  public void setLoginInfo(LoginInfo loginInfo) {
    this.loginInfo = loginInfo;
  }

  public ElectionData getElectionData() {
    return electionData;
  }

  public void setElectionData(ElectionData electionData) {
    this.electionData = electionData;
  }
}
