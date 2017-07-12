package org.cdlib.ill.report.vdx.procedures;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxBorrowerCategory;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class SpVdxJournalBorrowingRepository {

    @Autowired
    private EntityManager em;

    public Stream<SpVdxJournalBorrowing> getJournalBorrowing(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_journal_borrowing(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.isTrue(values.length == 8, Constants.BAD_PROCEDURE_MSG);
            Assert.noNullElements(values, Constants.NULL_DATA_MSG);
            return new SpVdxJournalBorrowing(
                    VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                    String.valueOf(values[1]),
                    String.valueOf(values[2]),
                    Year.parse(String.valueOf(values[3])),
                    String.valueOf(values[4]),
                    String.valueOf(values[5]),
                    VdxBorrowerCategory.fromCode(String.valueOf(values[6])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                    Long.valueOf(String.valueOf(values[7])));
        });
    }
}
