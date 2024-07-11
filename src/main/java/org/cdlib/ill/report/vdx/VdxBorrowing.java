package org.cdlib.ill.report.vdx;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "vdx_borrowing")
public class VdxBorrowing extends VdxData implements Serializable {

}
