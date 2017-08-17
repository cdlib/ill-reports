package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VdxMoney implements Serializable {

    @Column(name = "cost", nullable = true)
    @JsonProperty("cost")
    private BigDecimal cost;

    @Column(name = "cost_ex_tax", nullable = true)
    @JsonProperty("cost_ex_tax")
    private BigDecimal costExTax;

    @Column(name = "req_max_cost", nullable = true)
    @JsonProperty("req_max_cost")
    private BigDecimal reqMaxCost;

    @Column(name = "cost_to_user", nullable = true)
    @JsonProperty("cost_to_user")
    private BigDecimal costToUser;

    @Column(name = "base_cost", nullable = true)
    @JsonProperty("base_cost")
    private BigDecimal baseCost;

    @Column(name = "vat_cost", nullable = true)
    @JsonProperty("vat_cost")
    private BigDecimal vatCost;

    @Column(name = "vat_rate", nullable = true)
    @JsonProperty("vat_rate")
    private BigDecimal vatRate;

    @Column(name = "vat_code", nullable = false)
    @JsonProperty("vat_code")
    private String vatCode;

    @Column(name = "total_charge", nullable = true)
    @JsonProperty("total_charge")
    private BigDecimal totalCharge;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCostExTax() {
        return costExTax;
    }

    public void setCostExTax(BigDecimal costExTax) {
        this.costExTax = costExTax;
    }

    public BigDecimal getReqMaxCost() {
        return reqMaxCost;
    }

    public void setReqMaxCost(BigDecimal reqMaxCost) {
        this.reqMaxCost = reqMaxCost;
    }

    public BigDecimal getCostToUser() {
        return costToUser;
    }

    public void setCostToUser(BigDecimal costToUser) {
        this.costToUser = costToUser;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }

    public BigDecimal getVatCost() {
        return vatCost;
    }

    public void setVatCost(BigDecimal vatCost) {
        this.vatCost = vatCost;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

}
