package org.cdlib.ill.report.vdx;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "vdx_borrowing")
public class VdxBorrowing extends VdxData implements Serializable {

}
