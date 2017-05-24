package org.cdlib.ill.report.business;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.cdlib.ill.model.Loan;

/**
 *
 * @author mmorrisp
 */
public interface DataWarehouseRepository {
    
    // This is for reports to satisfy business as usual.
    void doBatchOperation(LocalDate beginDate, LocalDate endDate, Consumer<Loan> op);
    
    // This is for the new REST service.
    Stream<Loan> getBorrowing(LocalDate beginDate, LocalDate endDate);
    
}
