package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
  "Borrowing Campus",
  "ETAS Links Presented",
  "ETAS Links Clicked",
  "Total ETAS Requests Placed",
  "Returnables ETAS Requests Placed",
  "Non-Returnables ETAS Requests Placed",
  "Requests Not Placed",
  "Total UC Requests Placed",
  "Total OCLC Requests Placed"
})
public class SpEtasEvents {
  @JsonProperty("Borrowing Campus")
  private String borrowingCampus;
  @JsonProperty("ETAS Links Presented")
  private Long etasLinkTotal;
  @JsonProperty("ETAS Links Clicked")
  private Long etasClickTotal;
  @JsonProperty("Total ETAS Requests Placed")
  private Long etasRequestNumber;
  @JsonProperty("Returnables ETAS Requests Placed")
  private Long returnableRequestNumber;
  @JsonProperty("Non-Returnables ETAS Requests Placed")
  private Long nonreturnableRequestNumber;
  @JsonProperty("Requests Not Placed")
  private Long requestNotPlacedNumber;
  @JsonProperty("Total UC Requests Placed")
  private Long totalUCRequestsPlaced;
  @JsonProperty("Total OCLC Requests Placed")
  private Long totalOCLCRequestsPlaced;

  public SpEtasEvents() {
  }

  public SpEtasEvents(String borrowingCampus, Long etasLinkTotal, Long etasClickTotal, Long etasRequestNumber, Long returnableRequestNumber, Long nonreturnableRequestNumber, Long requestNotPlacedNumber, Long totalUCRequestsPlaced, Long totalOCLCRequestsPlaced) {
    super();
    this.borrowingCampus = borrowingCampus;
    this.etasLinkTotal = etasLinkTotal;
    this.etasClickTotal = etasClickTotal;
    this.etasRequestNumber = etasRequestNumber;
    this.returnableRequestNumber = returnableRequestNumber;
    this.nonreturnableRequestNumber = nonreturnableRequestNumber;
    this.requestNotPlacedNumber = requestNotPlacedNumber;
    this.totalUCRequestsPlaced = totalUCRequestsPlaced;
    this.totalOCLCRequestsPlaced = totalOCLCRequestsPlaced;
  }

  public String getBorrowingCampus() {
    return borrowingCampus;
  }

  public void setBorrowingCampus(String borrowingCampus) {
    this.borrowingCampus = borrowingCampus;
  }

  public Long getEtasLinkTotal() {
    return etasLinkTotal;
  }

  public void setEtasLinkTotal(Long etasLinkTotal) {
    this.etasLinkTotal = etasLinkTotal;
  }

  public Long getEtasRequestNumber() {
    return etasRequestNumber;
  }

  public void setEtasRequestNumber(Long etasRequestNumber) {
    this.etasRequestNumber = etasRequestNumber;
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

  public Long getEtasClickTotal() {
    return etasClickTotal;
  }

  public void setEtasClickTotal(Long etasClickTotal) {
    this.etasClickTotal = etasClickTotal;
  }

  public Long getTotalUCRequestsPlaced() {
    return totalUCRequestsPlaced;
  }

  public void setTotalUCRequestsPlaced(Long totalUCRequestsPlaced) {
    this.totalUCRequestsPlaced = totalUCRequestsPlaced;
  }

  public Long getTotalOCLCRequestsPlaced() {
    return totalOCLCRequestsPlaced;
  }

  public void setTotalOCLCRequestsPlaced(Long totalOCLCRequestsPlaced) {
    this.totalOCLCRequestsPlaced = totalOCLCRequestsPlaced;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((borrowingCampus == null) ? 0 : borrowingCampus.hashCode());
    result = prime * result + ((etasClickTotal == null) ? 0 : etasClickTotal.hashCode());
    result = prime * result + ((etasLinkTotal == null) ? 0 : etasLinkTotal.hashCode());
    result = prime * result + ((etasRequestNumber == null) ? 0 : etasRequestNumber.hashCode());
    result = prime * result + ((nonreturnableRequestNumber == null) ? 0 : nonreturnableRequestNumber.hashCode());
    result = prime * result + ((requestNotPlacedNumber == null) ? 0 : requestNotPlacedNumber.hashCode());
    result = prime * result + ((returnableRequestNumber == null) ? 0 : returnableRequestNumber.hashCode());
    result = prime * result + ((totalOCLCRequestsPlaced == null) ? 0 : totalOCLCRequestsPlaced.hashCode());
    result = prime * result + ((totalUCRequestsPlaced == null) ? 0 : totalUCRequestsPlaced.hashCode());
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
    SpEtasEvents other = (SpEtasEvents) obj;
    if (borrowingCampus == null) {
      if (other.borrowingCampus != null)
        return false;
    } else if (!borrowingCampus.equals(other.borrowingCampus))
      return false;
    if (etasClickTotal == null) {
      if (other.etasClickTotal != null)
        return false;
    } else if (!etasClickTotal.equals(other.etasClickTotal))
      return false;
    if (etasLinkTotal == null) {
      if (other.etasLinkTotal != null)
        return false;
    } else if (!etasLinkTotal.equals(other.etasLinkTotal))
      return false;
    if (etasRequestNumber == null) {
      if (other.etasRequestNumber != null)
        return false;
    } else if (!etasRequestNumber.equals(other.etasRequestNumber))
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
    if (totalOCLCRequestsPlaced == null) {
      if (other.totalOCLCRequestsPlaced != null)
        return false;
    } else if (!totalOCLCRequestsPlaced.equals(other.totalOCLCRequestsPlaced))
      return false;
    if (totalUCRequestsPlaced == null) {
      if (other.totalUCRequestsPlaced != null)
        return false;
    } else if (!totalUCRequestsPlaced.equals(other.totalUCRequestsPlaced))
      return false;
    return true;
  }
  
}
