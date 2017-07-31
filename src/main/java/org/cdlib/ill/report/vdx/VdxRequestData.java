package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VdxRequestData implements Serializable {

    @JsonProperty("req_title")
    @Column(name = "req_title", nullable = false)
    private String reqTitle;

    @JsonProperty("req_subtitle")
    @Column(name = "req_subtitle", nullable = false)
    private String reqSubtitle;

    @JsonProperty("req_body")
    @Column(name = "req_body", nullable = false)
    private String reqBody;

    @JsonProperty("req_pubdate")
    @Column(name = "req_pubdate", nullable = false)
    private String reqPubdate;

    @JsonProperty("req_part_pubdate")
    @Column(name = "req_part_pubdate", nullable = false)
    private String reqPartPubdate;

    @JsonProperty("req_article_title")
    @Column(name = "req_article_title", nullable = false)
    private String reqArticleTitle;

    @JsonProperty("req_article_author")
    @Column(name = "req_article_author", nullable = false)
    private String reqArticleAuthor;

    @JsonProperty("req_publisher")
    @Column(name = "req_publisher", nullable = false)
    private String reqPublisher;

    @JsonProperty("req_pubplace")
    @Column(name = "req_pubplace", nullable = false)
    private String reqPubplace;

    @JsonProperty("req_issn")
    @Column(name = "req_issn", nullable = false)
    private String reqIssn;

    @JsonProperty("req_isbn")
    @Column(name = "req_isbn", nullable = false)
    private String reqIsbn;

    @JsonProperty("req_pagination")
    @Column(name = "req_pagination", nullable = false)
    private String reqPagination;

    @JsonProperty("req_edition")
    @Column(name = "req_edition", nullable = false)
    private String reqEdition;

    @JsonProperty("req_shelf")
    @Column(name = "req_shelf", nullable = false)
    private String reqShelf;

    @JsonProperty("req_author")
    @Column(name = "req_author", nullable = false)
    private String reqAuthor;

    @JsonProperty("req_issue_title")
    @Column(name = "req_issue_title", nullable = false)
    private String reqIssueTitle;

    @JsonProperty("req_serial_title")
    @Column(name = "req_serial_title", nullable = false)
    private String reqSerialTitle;

    @JsonProperty("req_verify_source")
    @Column(name = "req_verify_source", nullable = false)
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
