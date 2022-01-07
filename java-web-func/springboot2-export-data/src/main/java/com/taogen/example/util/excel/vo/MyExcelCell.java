package com.taogen.example.util.excel.vo;

import lombok.Builder;
import lombok.Data;

/**
 * MyExcelCell for complex Excel sheet.
 * Generate cells one by one with (startRow, endRow, startColumn, endColumn).
 * @author Taogen
 */
@Data
@Builder
public class MyExcelCell {
    private String text;

    private MyCellStyle cellStyle;

    private MyCellPosition cellPosition;
}
