package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VdxRequestData implements Serializable {

  @Column(name = "req_title", nullable = false)
  @JsonProperty("req_title")
  private String reqTitle;

  @Column(name = "req_subtitle", nullable = false)
  @JsonProperty("req_subtitle")
  private String reqSubtitle;

  @Column(name = "req_body", nullable = false)
  @JsonProperty("req_body")
  private String reqBody;

  @Column(name = "req_pubdate", nullable = false)
  @JsonProperty("req_pubdate")
  private String reqPubdate;

  @Column(name = "req_part_pubdate", nullable = false)
  @JsonProperty("req_part_pubdate")
  private String reqPartPubdate;

  @Column(name = "req_article_title", nullable = false)
  @JsonProperty("req_article_title")
  private String reqArticleTitle;

  @Column(name = "req_article_author", nullable = false)
  @JsonProperty("req_article_author")
  private String reqArticleAuthor;

  @Column(name = "req_publisher", nullable = false)
  @JsonProperty("req_publisher")
  private String reqPublisher;

  @Column(name = "req_pubplace", nullable = false)
  @JsonProperty("req_pubplace")
  private String reqPubplace;

  @Column(name = "req_issn", nullable = false)
  @JsonProperty("req_issn")
  private String reqIssn;

  @Column(name = "req_isbn", nullable = false)
  @JsonProperty("req_isbn")
  private String reqIsbn;

  @Column(name = "req_pagination", nullable = false)
  @JsonProperty("req_pagination")
  private String reqPagination;

  @Column(name = "req_edition", nullable = false)
  @JsonProperty("req_edition")
  private String reqEdition;

  @Column(name = "req_shelf", nullable = false)
  @JsonProperty("req_shelf")
  private String reqShelf;

  @Column(name = "req_author", nullable = false)
  @JsonProperty("req_author")
  private String reqAuthor;
  
  @Column(name="language", nullable = false)
  @JsonProperty("language")
  private String language;

  @Column(name = "req_issue_title", nullable = false)
  @JsonProperty("req_issue_title")
  private String reqIssueTitle;

  @Column(name = "req_serial_title", nullable = false)
  @JsonProperty("req_serial_title")
  private String reqSerialTitle;

  @Column(name = "req_verify_source", nullable = false)
  @JsonProperty("req_verify_source")
  private String reqVerifySource;

  public String getReqTitle() {
    return reqTitle;
  }

  public void setReqTitle(String reqTitle) {
    this.reqTitle = reqTitle;
  }

  public String getReqSubtitle() {
    return reqSubtitle;
  }

  public void setReqSubtitle(String reqSubtitle) {
    this.reqSubtitle = reqSubtitle;
  }

  public String getReqBody() {
    return reqBody;
  }

  public void setReqBody(String reqBody) {
    this.reqBody = reqBody;
  }

  public String getReqPubdate() {
    return reqPubdate;
  }

  public void setReqPubdate(String reqPubdate) {
    this.reqPubdate = reqPubdate;
  }

  public String getReqPartPubdate() {
    return reqPartPubdate;
  }

  public void setReqPartPubdate(String reqPartPubdate) {
    this.reqPartPubdate = reqPartPubdate;
  }

  public String getReqArticleTitle() {
    return reqArticleTitle;
  }

  public void setReqArticleTitle(String reqArticleTitle) {
    this.reqArticleTitle = reqArticleTitle;
  }

  public String getReqArticleAuthor() {
    return reqArticleAuthor;
  }

  public void setReqArticleAuthor(String reqArticleAuthor) {
    this.reqArticleAuthor = reqArticleAuthor;
  }

  public String getReqPublisher() {
    return reqPublisher;
  }

  public void setReqPublisher(String reqPublisher) {
    this.reqPublisher = reqPublisher;
  }

  public String getReqPubplace() {
    return reqPubplace;
  }

  public void setReqPubplace(String reqPubplace) {
    this.reqPubplace = reqPubplace;
  }

  public String getReqIssn() {
    return reqIssn;
  }

  public void setReqIssn(String reqIssn) {
    this.reqIssn = reqIssn;
  }

  public String getReqIsbn() {
    return reqIsbn;
  }

  public void setReqIsbn(String reqIsbn) {
    this.reqIsbn = reqIsbn;
  }

  public String getReqPagination() {
    return reqPagination;
  }

  public void setReqPagination(String reqPagination) {
    this.reqPagination = reqPagination;
  }

  public String getReqEdition() {
    return reqEdition;
  }

  public void setReqEdition(String reqEdition) {
    this.reqEdition = reqEdition;
  }

  public String getReqShelf() {
    return reqShelf;
  }

  public void setReqShelf(String reqShelf) {
    this.reqShelf = reqShelf;
  }

  public String getReqAuthor() {
    return reqAuthor;
  }

  public void setReqAuthor(String reqAuthor) {
    this.reqAuthor = reqAuthor;
  }

  public String getLanguage() {
    return language;
  }
  
  public void setLanguage(String language) {
    this.language = language;
  }
  
  public String getReqIssueTitle() {
    return reqIssueTitle;
  }

  public void setReqIssueTitle(String reqIssueTitle) {
    this.reqIssueTitle = reqIssueTitle;
  }

  public String getReqSerialTitle() {
    return reqSerialTitle;
  }

  public void setReqSerialTitle(String reqSerialTitle) {
    this.reqSerialTitle = reqSerialTitle;
  }

  public String getReqVerifySource() {
    return reqVerifySource;
  }

  public void setReqVerifySource(String reqVerifySource) {
    this.reqVerifySource = reqVerifySource;
  }

}
