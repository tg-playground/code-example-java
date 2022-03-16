package com.taogen.example.util.excel.build.vo;

import com.taogen.example.util.excel.common.ExcelUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * MyExcelCell for complex Excel sheet.
 * Generate cells one by one with (startRow, endRow, startColumn, endColumn).
 * @author Taogen
 */
@Data
@Builder
public class MyExcelCell {
    private Object text;

    private MyCellStyle cellStyle;

    private MyCellPosition cellPosition;

    private XSSFHyperlink hyperlink;

    public static XSSFCell createCell(MyExcelCell myExcelCell, XSSFRow row) {
        XSSFCell cell = row.createCell(myExcelCell.getCellPosition().getStartColumn());
        if (myExcelCell.getText() != null) {
            Object text = myExcelCell.getText();
            ExcelUtil.setCellValueByObject(cell, text);
        }
        if (myExcelCell.getHyperlink() != null) {
            cell.setHyperlink(myExcelCell.getHyperlink());
        }
        if (myExcelCell.getCellStyle() != null) {
            CellStyle cellStyle = MyCellStyle.createCellStyle(row.getSheet().getWorkbook(), myExcelCell.getCellStyle());
            cell.setCellStyle(cellStyle);
        }
        return cell;
    }
}
