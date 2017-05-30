package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.stream.Stream;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mapping for the {@link VdxBorrowing} table.
 *
 * @author mmorrisp
 */
@Transactional(readOnly = true)
public interface VdxRepository extends Repository<VdxBorrowing, Long> {
    
    @QueryHints(value = @QueryHint(name = org.eclipse.persistence.config.QueryHints.JDBC_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @Query("select b from VdxBorrowing b")
    Stream<VdxBorrowing> streamAll();

    @QueryHints(value = @QueryHint(name = org.eclipse.persistence.config.QueryHints.JDBC_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @Query("select b from VdxBorrowing b where b.recDate >= ?1 and b.recDate < ?2")
    Stream<VdxBorrowing> streamAllInDateRange(LocalDate begin, LocalDate end);

}
