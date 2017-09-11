package org.cdlib.ill.report.vdx.procedures;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxBorrowerCategory;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class SpVdxBorrowingDetailRepository {

    private static final AttributeConverter<LocalDateTime, Date> LOCAL_DATE_TIME_CONVERTER = new Jsr310JpaConverters.LocalDateTimeConverter();

    @Autowired
    private EntityManager em;

    public List<SpVdxBorrowingDetail> getBorrowingDetail(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_borrowing_detail(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.isTrue(values.length == 16, Constants.BAD_PROCEDURE_MSG);
            Assert.noNullElements(values, Constants.NULL_DATA_MSG);
            return new SpVdxBorrowingDetail(
                    String.valueOf(values[0]),
                    Long.valueOf(String.valueOf(values[1])),
                    LOCAL_DATE_TIME_CONVERTER.convertToEntityAttribute((Timestamp) values[2]),
                    VdxBorrowerCategory.fromCode(String.valueOf(values[3])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                    String.valueOf(values[4]),
                    String.valueOf(values[5]),
                    String.valueOf(values[6]),
                    String.valueOf(values[7]),
                    String.valueOf(values[8]),
                    String.valueOf(values[9]),
                    String.valueOf(values[10]),
                    String.valueOf(values[11]),
                     String.valueOf(values[12]),
                     String.valueOf(values[13]),
                     String.valueOf(values[14]),
                     VdxServiceType.fromCode(String.valueOf(values[15])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER)
            );
        }).collect(Collectors.toList());
    }
}
