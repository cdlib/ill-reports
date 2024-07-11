package org.cdlib.ill.report.vdx;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "vdx_lending")
public class VdxLending extends VdxData implements Serializable {

}
