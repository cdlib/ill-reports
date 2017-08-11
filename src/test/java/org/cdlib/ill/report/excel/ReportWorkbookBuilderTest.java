package org.cdlib.ill.report.excel;

import org.junit.Assert;
import org.junit.Test;

public class ReportWorkbookBuilderTest {

    @Test
    public void testGetLastDataColumnLetter() {
        Assert.assertEquals("A", ReportWorkbookBuilder.newWorkbook(Object.class)
                .fieldText(null, null)
                .getLastDataColumnLetter());
        Assert.assertEquals("B", ReportWorkbookBuilder.newWorkbook(Object.class)
                .fieldText(null, null)
                .fieldText(null, null)
                .getLastDataColumnLetter());
        Assert.assertEquals("AB", ReportWorkbookBuilder.newWorkbook(Object.class)
                .fieldText(null, null).fieldText(null, null).fieldText(null, null).fieldText(null, null)
                .fieldText(null, null).fieldText(null, null).fieldText(null, null).fieldText(null, null)
                .fieldText(null, null).fieldText(null, null).fieldText(null, null).fieldText(null, null)
                .fieldText(null, null).fieldText(null, null).fieldText(null, null).fieldText(null, null)
                .fieldText(null, null).fieldText(null, null).fieldText(null, null).fieldText(null, null)
                .fieldText(null, null).fieldText(null, null).fieldText(null, null).fieldText(null, null)
                .fieldText(null, null).fieldText(null, null).fieldText(null, null).fieldText(null, null)
                .getLastDataColumnLetter());
    }

}
