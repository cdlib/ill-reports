import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ReportErrorController implements ErrorController {
  static final Logger LOG = LoggerFactory.getLogger(ReportErrorController.class);


  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Exception ex) {
    // get error status
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    // TODO: log error details here
    LOG.trace("An error has occurred.");
    LOG.trace(ex.toString());

    // display generic error
    return "error";
  }
}
