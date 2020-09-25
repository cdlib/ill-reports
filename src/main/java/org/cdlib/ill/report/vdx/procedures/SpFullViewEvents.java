package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
  "Borrowing Campus",
  "Full View Links Presented",
  "Full View Links Clicked",
  "Total Full View Requests Placed",
  "Returnables Full View Requests Placed",
  "Non-Returnables Full View Requests Placed",
  "Requests Not Placed"
})
public class SpFullViewEvents {
  @JsonProperty("Borrowing Campus")
  private String borrowingCampus;
  @JsonProperty("Full View Links Presented")
  private Long fullViewLinkTotal;
  @JsonProperty("Full View Links Clicked")
  private Long fullViewClickTotal;
  @JsonProperty("Total Full View Requests Placed")
  private Long fullViewRequestNumber;
  @JsonProperty("Returnables Full View Requests Placed")
  private Long returnableRequestNumber;
  @JsonProperty("Non-Returnables Full View Requests Placed")
  private Long nonreturnableRequestNumber;
  @JsonProperty("Requests Not Placed")
  private Long requestNotPlacedNumber;
  
  public SpFullViewEvents() {
  }

  public SpFullViewEvents(String borrowingCampus, Long fullViewLinkTotal, Long fullViewClickTotal, Long fullViewRequestNumber, Long returnableRequestNumber, Long nonreturnableRequestNumber, Long requestNotPlacedNumber) {
    super();
    this.borrowingCampus = borrowingCampus;
    this.fullViewLinkTotal = fullViewLinkTotal;
    this.fullViewClickTotal = fullViewClickTotal;
    this.fullViewRequestNumber = fullViewRequestNumber;
    this.returnableRequestNumber = returnableRequestNumber;
    this.nonreturnableRequestNumber = nonreturnableRequestNumber;
    this.requestNotPlacedNumber = requestNotPlacedNumber;
  }

  public String getBorrowingCampus() {
    return borrowingCampus;
  }

  public void setBorrowingCampus(String borrowingCampus) {
    this.borrowingCampus = borrowingCampus;
  }

  public Long getFullViewLinkTotal() {
    return fullViewLinkTotal;
  }

  public void setFullViewLinkTotal(Long fullViewLinkTotal) {
    this.fullViewLinkTotal = fullViewLinkTotal;
  }

  public Long getFullViewRequestNumber() {
    return fullViewRequestNumber;
  }

  public void setFullViewRequestNumber(Long fullViewRequestNumber) {
    this.fullViewRequestNumber = fullViewRequestNumber;
  }

  public Long getReturnableRequestNumber() {
    return returnableRequestNumber;
  }

  public void setReturnableRequestNumber(Long returnableRequestNumber) {
    this.returnableRequestNumber = returnableRequestNumber;
  }

  public Long getNonreturnableRequestNumber() {
    return nonreturnableRequestNumber;
  }

  public void setNonreturnableRequestNumber(Long nonreturnableRequestNumber) {
    this.nonreturnableRequestNumber = nonreturnableRequestNumber;
  }

  public Long getRequestNotPlacedNumber() {
    return requestNotPlacedNumber;
  }

  public void setRequestNotPlacedNumber(Long requestNotPlacedNumber) {
    this.requestNotPlacedNumber = requestNotPlacedNumber;
  }

  public Long getFullViewClickTotal() {
    return fullViewClickTotal;
  }

  public void setFullViewClickTotal(Long fullViewClickTotal) {
    this.fullViewClickTotal = fullViewClickTotal;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((borrowingCampus == null) ? 0 : borrowingCampus.hashCode());
    result = prime * result + ((fullViewClickTotal == null) ? 0 : fullViewClickTotal.hashCode());
    result = prime * result + ((fullViewLinkTotal == null) ? 0 : fullViewLinkTotal.hashCode());
    result = prime * result + ((fullViewRequestNumber == null) ? 0 : fullViewRequestNumber.hashCode());
    result = prime * result + ((nonreturnableRequestNumber == null) ? 0 : nonreturnableRequestNumber.hashCode());
    result = prime * result + ((requestNotPlacedNumber == null) ? 0 : requestNotPlacedNumber.hashCode());
    result = prime * result + ((returnableRequestNumber == null) ? 0 : returnableRequestNumber.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SpFullViewEvents other = (SpFullViewEvents) obj;
    if (borrowingCampus == null) {
      if (other.borrowingCampus != null)
        return false;
    } else if (!borrowingCampus.equals(other.borrowingCampus))
      return false;
    if (fullViewClickTotal == null) {
      if (other.fullViewClickTotal != null)
        return false;
    } else if (!fullViewClickTotal.equals(other.fullViewClickTotal))
      return false;
    if (fullViewLinkTotal == null) {
      if (other.fullViewLinkTotal != null)
        return false;
    } else if (!fullViewLinkTotal.equals(other.fullViewLinkTotal))
      return false;
    if (fullViewRequestNumber == null) {
      if (other.fullViewRequestNumber != null)
        return false;
    } else if (!fullViewRequestNumber.equals(other.fullViewRequestNumber))
      return false;
    if (nonreturnableRequestNumber == null) {
      if (other.nonreturnableRequestNumber != null)
        return false;
    } else if (!nonreturnableRequestNumber.equals(other.nonreturnableRequestNumber))
      return false;
    if (requestNotPlacedNumber == null) {
      if (other.requestNotPlacedNumber != null)
        return false;
    } else if (!requestNotPlacedNumber.equals(other.requestNotPlacedNumber))
      return false;
    if (returnableRequestNumber == null) {
      if (other.returnableRequestNumber != null)
        return false;
    } else if (!returnableRequestNumber.equals(other.returnableRequestNumber))
      return false;
    return true;
  }
  
}
