package org.cdlib.ill.model;

import java.time.LocalDate;

/**
 * An exchange of an item between two {@link Institution}.
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
    private Institution borrower;
    private Institution lender;

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public Institution getBorrower() {
        return borrower;
    }

    public void setBorrower(Institution borrower) {
        this.borrower = borrower;
    }

    public Institution getLender() {
        return lender;
    }

    public void setLender(Institution lender) {
        this.lender = lender;
    }

}
