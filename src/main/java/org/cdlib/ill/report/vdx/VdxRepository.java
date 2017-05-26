package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.stream.Stream;
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

    Stream<VdxBorrowing> findAll();
    
    @Query("select b from VdxBorrowing b where b.recDate >= ?1 and b.recDate < ?2")
    Stream<VdxBorrowing> findAllBorrowingInDateRange(LocalDate begin, LocalDate end);
    
}
