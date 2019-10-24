package org.cdlib.ill.report;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.EnumSet;
import javax.validation.Valid;
import org.cdlib.ill.model.CampusILLReport;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportHtmlController {

  @Autowired
  private CampusILLReportService repo;

  private CampusILLReport getAllCampusReport(LocalDate reportStartDate, LocalDate reportEndDate) {
    return repo.getILLCampusReport("All Campuses", "all", "%", reportStartDate, reportEndDate);
  }

  private CampusILLReport getCampusReport(String campusCode, LocalDate reportStartDate, LocalDate reportEndDate) {
    return repo.getILLCampusReport(campusCode, reportStartDate, reportEndDate);
  }

  private String convertDateString(String date) {
      if(date.contains("/")) {
          String[] arrDate = date.split("/");
          String newDate = arrDate[2] + "-" + arrDate[0] + "-" + arrDate[1];
          return newDate;
      }
      
      return date;
  }
  
  private LocalDate parseDate(String formatted) {
    if(formatted.contains("/")) {
        return LocalDate.parse(formatted, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
    
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
  public String query(@Valid @ModelAttribute("queryForm") ReportHtmlForm queryForm, BindingResult bindingResult, Model model) {
    model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
    model.addAttribute("searchStartDate", convertDateString(queryForm.getFrom()));
    model.addAttribute("searchEndDate", convertDateString(queryForm.getTo()));

    LocalDate from = LocalDate.MIN;
    LocalDate to = LocalDate.MAX;

    try {
      from = parseDate(queryForm.getFrom());
    } catch (DateTimeParseException ex) {
      bindingResult.addError(new FieldError("queryForm", "from", "Date does not match YYYY-MM-DD."));
    }
    try {
      to = parseDate(queryForm.getTo());
    } catch (DateTimeParseException ex) {
      bindingResult.addError(new FieldError("queryForm", "to", "Date does not match YYYY-MM-DD."));
    }

    if (bindingResult.hasErrors()) {
      model.addAttribute("campusDefault", queryForm.getCampus());
    } else {
      model.addAttribute("campusDefault", VdxCampus.fromCode(queryForm.getCampus()).map(VdxCampus::getCode).orElse("all"));
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
                  to)
          ));
    }
    return "report";
  }

}
