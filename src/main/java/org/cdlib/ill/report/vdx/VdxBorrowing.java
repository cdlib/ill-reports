package org.cdlib.ill.report.vdx;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@Entity
@Table(name = "vdx_borrowing")
public class VdxBorrowing extends VdxData implements Serializable {
  
  @Column(name = "entry_date", nullable = false)
  @JsonProperty("entry_date")
  private Date entryDateDate;

  public Date getEntryDateDate() {
    return entryDateDate;
  }

  public void setEntryDateDate(Date entryDate) {
    this.entryDateDate = entryDate;
  }

  
}
