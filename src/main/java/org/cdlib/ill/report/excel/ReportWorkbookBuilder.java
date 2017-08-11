package org.cdlib.ill.report.excel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STItemType;

public class ReportWorkbookBuilder<T> {

    private final XSSFWorkbook workbook;
    private XSSFSheet dataSheet;
    private XSSFSheet pivotTableSheet;
    private XSSFPivotTable pivotTable;
    private AreaReference source;
    private final List<Field<T>> fields = new ArrayList<>();
    private Collection<T> data;

    private ReportWorkbookBuilder() {
        workbook = new XSSFWorkbook();
    }

    public static <T> ReportWorkbookBuilder<T> newWorkbook(Class<T> klass) {
        return new ReportWorkbookBuilder<>();
    }

    public ReportWorkbookBuilder<T> fieldText(String header, Function<T, ? extends Object> mapper) {
        return field(header, CellType.STRING, mapper);
    }

    public ReportWorkbookBuilder<T> fieldNum(String header, Function<T, ? extends Object> mapper) {
        return field(header, CellType.NUMERIC, mapper);
    }

    private ReportWorkbookBuilder<T> field(String header, CellType cellType, Function<T, ? extends Object> mapper) {
        fields.add(Field.newField(header, cellType, mapper));
        return this;
    }

    public ReportWorkbookBuilder<T> data(Collection<T> data) {
        this.data = data;
        dataSheet = dataSheet == null
                ? workbook.createSheet("CDL_Data")
                : dataSheet;
        int rowCount = 0;
        addDataHeaderRow(dataSheet.createRow(rowCount++), fields);
        for (T datum : data) {
            addDataRow(dataSheet.createRow(rowCount++), datum);
        }
        return this;
    }

    public ReportWorkbookBuilder<T> pivotRow(int sourceColumn) {
        pivotTableSheet = pivotTableSheet == null
                ? workbook.createSheet("CDL_Analysis")
                : pivotTableSheet;
        source = source == null
                ? new AreaReference("CDL_Data!A1:F" + String.valueOf(1 + data.size()), SpreadsheetVersion.EXCEL2007)
                : source;
        pivotTable = pivotTable == null
                ? pivotTableSheet.createPivotTable(
                        source,
                        new CellReference("A1"))
                : pivotTable;
        pivotTable.addRowLabel(sourceColumn);
        return this;
    }

    public ReportWorkbookBuilder<T> pivotColumn(int sourceColumn) {
        addColumnLabel(pivotTable, source, sourceColumn);
        return this;
    }

    public ReportWorkbookBuilder<T> pivotValue(int sourceColumn, DataConsolidateFunction function, String title) {
        pivotTable.addColumnLabel(function, sourceColumn, title);
        return this;
    }

    public XSSFWorkbook build() {
        return workbook;
    }

    private void addDataHeaderRow(Row row, List<Field<T>> fields) {
        int cellCount = 0;
        for (Field<T> field : fields) {
            row.createCell(cellCount++, field.getCellType()).setCellValue(field.getHeader());
        }
    }

    private void addDataRow(Row row, T datum) {
        for (int cell = 0; cell < fields.size(); cell++) {
            switch (fields.get(cell).getCellType()) {
                case NUMERIC:
                    row.createCell(cell, fields.get(cell).getCellType())
                            .setCellValue(Double.parseDouble(String.valueOf(fields.get(cell).getMapper().apply(datum))));
                    break;
                default:
                    row.createCell(cell, fields.get(cell).getCellType())
                            .setCellValue(String.valueOf(fields.get(cell).getMapper().apply(datum)));
                    break;
            }

        }
    }

    /**
     * Adapted from the row label source
     * {@link org.apache.poi.xssf.usermodel.XSSFPivotTable}.
     *
     * Strategy suggested by {@link https://stackoverflow.com/a/33701734}
     */
    private void addColumnLabel(XSSFPivotTable pivotTable, AreaReference pivotArea, int columnIndex) {
        CTPivotTableDefinition definition = pivotTable.getCTPivotTableDefinition();

        final int lastRowIndex = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();

        CTPivotFields pivotFields = definition.getPivotFields();

        CTPivotField pivotField = CTPivotField.Factory.newInstance();
        CTItems items = pivotField.addNewItems();

        pivotField.setAxis(STAxis.AXIS_COL);
        pivotField.setShowAll(false);
        for (int i = 0; i <= lastRowIndex; i++) {
            items.addNewItem().setT(STItemType.DEFAULT);
        }
        items.setCount(items.sizeOfItemArray());
        pivotFields.setPivotFieldArray(columnIndex, pivotField);

        CTColFields colFields;
        if (definition.getColFields() != null) {
            colFields = definition.getColFields();
        } else {
            colFields = definition.addNewColFields();
        }

        colFields.addNewField().setX(columnIndex);
        colFields.setCount(colFields.sizeOfFieldArray());
    }

}
