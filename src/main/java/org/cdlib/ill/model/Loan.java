package org.cdlib.ill.model;

import java.time.LocalDate;

/**
 * An exchange of an item between two {@link InstitutionReport}.
 *
 * TODO: Talk to data analyst about the validity of existing JReports that
 * aggregate data on VDX ILL instances of undefined status. That is, should we
 * include the loan status in this model, or should we assume that all loans
 * represented in this program are complete, etc.?
 *
 * @author mmorrisp
 */
public class Loan {

    private LocalDate requestDate;
    private InstitutionReport borrower;
    private InstitutionReport lender;

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public InstitutionReport getBorrower() {
        return borrower;
    }

    public void setBorrower(InstitutionReport borrower) {
        this.borrower = borrower;
    }

    public InstitutionReport getLender() {
        return lender;
    }

    public void setLender(InstitutionReport lender) {
        this.lender = lender;
    }

}
