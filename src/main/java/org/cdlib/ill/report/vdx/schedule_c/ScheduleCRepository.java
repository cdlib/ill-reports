package org.cdlib.ill.report.vdx.schedule_c;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Native queries necessary to produce a Schedule C report.
 */
@Transactional(readOnly = true)
@Repository
@NamedNativeQueries(
        @NamedNativeQuery(name = "", query = ""))
public class ScheduleCRepository {

    @Autowired
    private EntityManager em;

}
