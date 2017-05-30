package org.cdlib.ill.report.business;

import org.cdlib.ill.report.vdx.VdxBorrowing;
import org.cdlib.ill.report.vdx.VdxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mmorrisp
 */
@Transactional(readOnly = true)
@Service
public class ReportsService {

    @Autowired
    VdxRepository repo;

}
