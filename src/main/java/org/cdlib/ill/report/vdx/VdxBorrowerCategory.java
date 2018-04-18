package org.cdlib.ill.report.vdx;

import java.util.Optional;

public enum VdxBorrowerCategory {
  Unknown("Unkn", "Unknown"),
  GraduateStudent("Grad", "GraduateStudent"),
  Staff("Staff", "Staff"),
  UndergratuateStudent("UGrad", "Undergraduate Student"),
  Faculty("Fac", "Faculty");

  private final String code;
  private final String description;

  private VdxBorrowerCategory(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static Optional<VdxBorrowerCategory> fromCode(String code) {
    for (VdxBorrowerCategory category : values()) {
      if (category.getCode().equalsIgnoreCase(code)) {
        return Optional.of(category);
      }
    }
    return Optional.empty();
  }
}
