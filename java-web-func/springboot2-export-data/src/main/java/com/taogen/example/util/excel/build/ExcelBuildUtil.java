package com.taogen.example.util.excel.build;

import com.taogen.example.util.excel.common.ExcelUtil;
import com.taogen.example.util.excel.build.vo.MyCellPosition;
import com.taogen.example.util.excel.build.vo.MyCellStyle;
import com.taogen.example.util.excel.build.vo.MyExcelCell;
import com.taogen.example.util.excel.build.vo.MyExcelRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for Using My Wrapper Classes to Build POI Excel
 * <p>
 * Generate multiple levels Excel file by custom wrapper class
 *
 * @author Taogen
 */
public class ExcelBuildUtil {

    /**
     * Warning: If multiple rows have the same cellStyle, do not create a new cellStyle for each row.
     * <p>
     * @param sheet
     * @param rowNum
     * @param rowHeight
     * @param values
     * @param cellStyle
     */
    public static void generateOneLineRowByValues(XSSFSheet sheet,
                                                  Integer rowNum,
                                                  Integer rowHeight,
                                                  List<?> values,
                                                  MyCellStyle cellStyle) {
        List<MyExcelCell> cells = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            cells.add(MyExcelCell.builder()
                    .text(values.get(i))
                    .cellPosition(new MyCellPosition(rowNum, rowNum, i, i))
                    .cellStyle(cellStyle)
                    .build());
        }
        addRowToExcel(sheet, MyExcelRow.builder()
                .startRow(rowNum)
                .endRow(rowNum)
                .cells(cells)
                .rowHeight(rowHeight)
                .build());
    }

    public static void addRowToExcel(XSSFSheet sheet, MyExcelRow myExcelRow) {
        ExcelUtil.createRows(sheet, myExcelRow.getStartRow(), myExcelRow.getEndRow());
        for (MyExcelCell myExcelCell : myExcelRow.getCells()) {
            XSSFRow row = sheet.getRow(myExcelCell.getCellPosition().getStartRow());
            if (myExcelRow.getRowHeight() != null) {
                row.setHeight((short) (myExcelRow.getRowHeight() * 20));
            }
            MyExcelCell.createCell(myExcelCell, row);
        }
        for (MyExcelCell myExcelCell : myExcelRow.getCells()) {
            mergeCellAndAlignCenter(sheet, myExcelCell.getCellPosition());
        }
    }

    public static void mergeCellAndAlignCenter(XSSFSheet sheet, MyCellPosition myCellPosition) {
        int startRow = myCellPosition.getStartRow();
        int endRow = myCellPosition.getEndRow();
        int startColumn = myCellPosition.getStartColumn();
        int endColumn = myCellPosition.getEndColumn();
        if (endColumn - startColumn > 0 ||
                endRow - startRow > 0) {
            sheet.addMergedRegion(new CellRangeAddress(
                    startRow, endRow, startColumn, endColumn));
            if (endColumn - startColumn > 0) {
                Cell cell = CellUtil.getCell(CellUtil.getRow(startRow, sheet), startColumn);
                CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);
            }
            if (endRow - startRow > 0) {
                Cell cell = CellUtil.getCell(CellUtil.getRow(startRow, sheet), startColumn);
                CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
            }
        }
    }
}
