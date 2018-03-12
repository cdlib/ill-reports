package org.cdlib.ill.report.excel;

import java.util.function.Function;
import org.apache.poi.ss.usermodel.CellType;

class Field<T> {

    private final String header;
    private final CellType cellType;
    private final Function<T, ? extends Object> mapper;

    private Field(String header, CellType cellType, Function<T, ? extends Object> mapper) {
        this.header = header;
        this.cellType = cellType;
        this.mapper = mapper;
    }

    public static <T> Field<T> newField(String header, CellType cellType, Function<T, ? extends Object> mapper) {
        return new Field<>(header, cellType, mapper);
    }

    public String getHeader() {
        return header;
    }

    public CellType getCellType() {
        return cellType;
    }

    public Function<T, ? extends Object> getMapper() {
        return mapper;
    }

}
