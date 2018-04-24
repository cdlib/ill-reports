package org.cdlib.ill.report.excel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchCampusException extends IllegalArgumentException {

  public NoSuchCampusException() {
  }

  public NoSuchCampusException(String s) {
    super(s);
  }

  public NoSuchCampusException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoSuchCampusException(Throwable cause) {
    super(cause);
  }

}
