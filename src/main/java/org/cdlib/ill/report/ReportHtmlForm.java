package org.cdlib.ill.report;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReportHtmlForm {

  @NotNull
  @Pattern(regexp = "(Search)")
  private String command;
  
  @NotNull
  @Pattern(regexp = Constants.CAMPUSES_MATCH)
  private String campus;
  
  @NotNull
  private String from;
  
  @NotNull
  private String to;

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getCampus() {
    return campus;
  }

  public void setCampus(String campus) {
    this.campus = campus;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  @Override
  public String toString() {
    return "ReportHtmlForm [command=" + command + ", campus=" + campus + ", from=" + from + ", to="
        + to + "]";
  }

}
