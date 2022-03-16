package com.taogen.example.util.excel.build.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Taogen
 */
@Data
@Builder
public class MyCellStyle {
    private HorizontalAlignment alignment;
    private VerticalAlignment verticalAlignment;
    private Boolean wrapText;
    private XSSFColor fillForegroundColor;
    private BorderStyle borderTop;
    private BorderStyle borderBottom;
    private BorderStyle borderLeft;
    private BorderStyle borderRight;
    private XSSFColor topBorderColor;
    private XSSFColor bottomBorderColor;
    private XSSFColor leftBorderColor;
    private XSSFColor rightBorderColor;
    private MyCellFont font;

    public static CellStyle createCellStyle(XSSFWorkbook workbook, MyCellStyle myCellStyle) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        if (myCellStyle.getAlignment() != null) {
            cellStyle.setAlignment(myCellStyle.getAlignment());
        }
        if (myCellStyle.getVerticalAlignment() != null) {
            cellStyle.setVerticalAlignment(myCellStyle.getVerticalAlignment());
        }
        if (myCellStyle.getWrapText() != null) {
            cellStyle.setWrapText(myCellStyle.getWrapText());
        }
        if (myCellStyle.getFillForegroundColor() != null) {
            cellStyle.setFillForegroundColor(myCellStyle.getFillForegroundColor());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        if (myCellStyle.getBorderTop() != null) {
            cellStyle.setBorderTop(myCellStyle.getBorderTop());
        }
        if (myCellStyle.getBorderBottom() != null) {
            cellStyle.setBorderBottom(myCellStyle.getBorderBottom());
        }
        if (myCellStyle.getBorderLeft() != null) {
            cellStyle.setBorderLeft(myCellStyle.getBorderLeft());
        }
        if (myCellStyle.getBorderRight() != null) {
            cellStyle.setBorderRight(myCellStyle.getBorderRight());
        }
        if (myCellStyle.getTopBorderColor() != null) {
            cellStyle.setTopBorderColor(myCellStyle.getTopBorderColor());
        }
        if (myCellStyle.getBottomBorderColor() != null) {
            cellStyle.setBottomBorderColor(myCellStyle.getBottomBorderColor());
        }
        if (myCellStyle.getLeftBorderColor() != null) {
            cellStyle.setLeftBorderColor(myCellStyle.getLeftBorderColor());
        }
        if (myCellStyle.getRightBorderColor() != null) {
            cellStyle.setRightBorderColor(myCellStyle.getRightBorderColor());
        }
        if (myCellStyle.getFont() != null) {
            cellStyle.setFont(MyCellFont.createFont(workbook, myCellStyle.getFont()));
        }
        return cellStyle;
    }
}
