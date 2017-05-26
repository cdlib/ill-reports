package org.cdlib.ill.report.business.impl;

import org.cdlib.ill.report.business.ReportService;
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
public class ReportsServiceImpl implements ReportService {

    @Autowired
    VdxRepository repo;

    @Override
    public void test() {
        System.err.println(repo.findAll().count());
    }

}
