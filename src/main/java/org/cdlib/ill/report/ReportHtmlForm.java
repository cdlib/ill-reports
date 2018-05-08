package org.cdlib.ill.report;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ReportHtmlForm {

  @NotNull
  @Pattern(regexp = "(Search)")
  private String command;
  @NotNull
  @Pattern(regexp = "all|OELA|NRLF|SRLF|UCB|UCD|UCI|UCLA|UCM|UCR|UCSB|UCSC|UCSD|UCSF")
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

}
