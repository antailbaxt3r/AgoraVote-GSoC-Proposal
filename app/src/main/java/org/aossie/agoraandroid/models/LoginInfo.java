package org.aossie.agoraandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {
  @SerializedName("providerID")
  @Expose
  private String providerID;
  @SerializedName("providerKey")
  @Expose
  private String providerKey;

  public LoginInfo() {
  }

  public String getProviderID() {
    return providerID;
  }

  public void setProviderID(String providerID) {
    this.providerID = providerID;
  }

  public String getProviderKey() {
    return providerKey;
  }

  public void setProviderKey(String providerKey) {
    this.providerKey = providerKey;
  }
}
