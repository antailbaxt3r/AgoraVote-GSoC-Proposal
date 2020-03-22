package org.aossie.agoraandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {
  @SerializedName("numerator")
  @Expose
  private Integer numerator;
  @SerializedName("denominator")
  @Expose
  private Integer denominator;

  public Score() {
  }

  public Integer getNumerator() {
    return numerator;
  }

  public void setNumerator(Integer numerator) {
    this.numerator = numerator;
  }

  public Integer getDenominator() {
    return denominator;
  }

  public void setDenominator(Integer denominator) {
    this.denominator = denominator;
  }
}
