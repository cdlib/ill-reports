package org.cdlib.ill.report.business.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.cdlib.ill.model.Institution;
import org.cdlib.ill.model.Loan;
import org.cdlib.ill.report.business.DataWarehouseRepository;
import org.cdlib.ill.report.vdx.VdxBorrowing;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Data warehouse for UC library ILL data, provided solely by VDX.
 *
 * @author mmorrisp
 */
@Repository
public class DataWarehouseRepositoryImpl implements DataWarehouseRepository {
    
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
    @Override
    public void doBatchOperation(LocalDate beginDate, LocalDate endDate, Consumer<Loan> op) {
        getBorrowing(beginDate, endDate).forEach(op);
    }
    
    @Override
    public Stream<Loan> getBorrowing(LocalDate beginDate, LocalDate endDate) {
        return vdxRepo.findAllBorrowingInDateRange(beginDate, endDate).map(DataWarehouseRepositoryImpl::vdxBorrowingToCommonModel);
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
