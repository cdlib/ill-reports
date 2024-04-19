package org.cdlib.ill.report;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.EnumSet;
import javax.validation.Valid;
import org.cdlib.ill.model.CampusILLReport;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportHtmlController {

  private Logger logger = LoggerFactory.getLogger(ReportHtmlController.class);

  @Autowired
  private CampusILLReportService repo;

  private CampusILLReport getAllCampusReport(LocalDate reportStartDate, LocalDate reportEndDate) {
    return repo.getILLCampusReport("All Campuses", "all", "%", reportStartDate, reportEndDate);
  }

  private CampusILLReport getCampusReport(String campusCode, LocalDate reportStartDate,
      LocalDate reportEndDate) {
    return repo.getILLCampusReport(campusCode, reportStartDate, reportEndDate);
  }

  private LocalDate parseDate(String formatted) {
    return LocalDate.parse(formatted, DateTimeFormatter.ISO_LOCAL_DATE);
  }

  @GetMapping("/")
  public String home(@ModelAttribute("queryForm") ReportHtmlForm queryForm, Model model) {
    model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
    model.addAttribute("searchStartDate", null);
    model.addAttribute("searchEndDate", null);
    return "report";
  }

  @PostMapping("/")
  public String query(@Valid @ModelAttribute("queryForm") ReportHtmlForm queryForm,
      BindingResult bindingResult, Model model) {

    logger.info("Received form: " + queryForm.toString());

    if (!isValid(queryForm, bindingResult)) {
      return "redirect:/";
    }

    LocalDate from = parseDate(queryForm.getFrom());
    LocalDate to = parseDate(queryForm.getFrom());

    model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
    model.addAttribute("searchStartDate", from);
    model.addAttribute("searchEndDate", to);
    model.addAttribute("campusDefault", VdxCampus.fromCode(queryForm
        .getCampus())
        .map(VdxCampus::getCode)
        .orElse("all"));
    model.addAttribute("reports",
        Arrays.asList(VdxCampus.fromCode(queryForm.getCampus()).isPresent()
            ? getCampusReport(VdxCampus.fromCode(queryForm.getCampus())
                .filter((campus) -> campus != VdxCampus.None)
                .map(VdxCampus::getCode)
                .orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                from,
                to)
            : getAllCampusReport(
                from,
                to)));
    return "report";
  }

  private boolean isValid(ReportHtmlForm form, BindingResult bindingResult) {
    if (form == null) {
      return false;
    }
    if (bindingResult.hasErrors()) {
      return false;
    }
    try {
      LocalDate from = parseDate(form.getFrom());
      LocalDate to = parseDate(form.getTo());
      if (to.isBefore(from)) {
        return false;
      }
    } catch (DateTimeParseException | NullPointerException ex) {
      return false;
    }
    return true;
  }

}
