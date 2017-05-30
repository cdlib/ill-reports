package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mapping for the {@link VdxBorrowing} table.
 *
 * @author mmorrisp
 */
@Transactional(readOnly = true)
public interface VdxRepository extends Repository<VdxBorrowing, Long> {

    @Query(value = "call sp_vdx_borrowing_summary(?1, ?2, ?3)", nativeQuery = true)
    List<VdxBorrowingSummary> getBorrowingSummary(String campus, LocalDate begin, LocalDate end);

}
