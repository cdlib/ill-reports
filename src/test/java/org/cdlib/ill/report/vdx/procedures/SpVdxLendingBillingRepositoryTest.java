package org.cdlib.ill.report.vdx.procedures;

import javax.persistence.EntityManager;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class SpVdxLendingBillingRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxLendingBillingRepository repo;

}
