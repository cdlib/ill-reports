package org.cdlib.ill.report.business;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.cdlib.ill.model.Institution;
import org.cdlib.ill.model.Loan;
import org.cdlib.ill.report.vdx.VdxBorrowing;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data warehouse for UC library ILL data, provided solely by VDX.
 *
 * @author mmorrisp
 */
@Transactional(readOnly = true)
@Repository
public class DataWarehouseRepository {

    @Autowired
    VdxRepository vdxRepo;

    /**
     * TODO: Data analysis for correspondence of borrowing vs lending tables.
     * This method uses only the borrowing data from vdx. How do I union the two
     * sets to just get all loans (to or from) the UC system?
     *
     * Runs {@code op} on all loans in the warehouse requested between
     * {@code beginDate} and {@code endDate}.
     *
     * @param beginDate
     * @param endDate
     * @param op
     */
    public void doBatchOperation(LocalDate beginDate, LocalDate endDate, Consumer<Loan> op) {
        getBorrowingHistory(beginDate, endDate).forEach(op);
    }

    public Stream<Loan> getBorrowingHistory(LocalDate beginDate, LocalDate endDate) {
        return vdxRepo.streamAllInDateRange(beginDate, endDate).map(DataWarehouseRepository::vdxBorrowingToCommonModel);
    }

    private static Loan vdxBorrowingToCommonModel(VdxBorrowing vdx) {
        Loan loan = new Loan();

        Institution borrower = new Institution();
        borrower.setName(vdx.getBorrower().getName());
        borrower.setCampus(Optional.ofNullable(vdx.getBorrower().getCampus()).map(VdxCampus::getCode).orElse(null));
        loan.setBorrower(borrower);

        Institution lender = new Institution();
        lender.setName(vdx.getLender().getName());
        lender.setCampus(Optional.ofNullable(vdx.getLender().getCampus()).map(VdxCampus::getCode).orElse(null));
        loan.setLender(lender);

        return loan;
    }

}
