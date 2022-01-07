package com.taogen.example.util.excel.util;

import com.taogen.example.util.excel.vo.MyCellPosition;
import com.taogen.example.util.excel.vo.MyCellStyle;
import com.taogen.example.util.excel.vo.MyExcelCell;
import com.taogen.example.util.excel.vo.MyExcelRow;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taogen
 */
public class ExcelBuildUtil {


    /**
     * Add picture to Excel sheet
     *
     * @param workbook
     * @param sheet
     * @param rowNum start with 1
     * @param colNum start with 1
     * @param imageFilePath
     * @param imageScale range of double value from 0 to 1
     * @throws IOException
     */
    public static void addPictureToExcel(XSSFWorkbook workbook, XSSFSheet sheet,
                                         int rowNum, int colNum,
                                         String imageFilePath, double imageScale) throws IOException {
        InputStream inputStream = new FileInputStream(imageFilePath);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        inputStream.close();
        //Returns an object that handles instantiating concrete classes
        CreationHelper helper = workbook.getCreationHelper();
        //Creates the top-level drawing patriarch.
        Drawing drawing = sheet.createDrawingPatriarch();
        //Create an anchor that is attached to the worksheet
        ClientAnchor anchor = helper.createClientAnchor();
        //set top-left corner for the image
        anchor.setRow1(rowNum);
        anchor.setCol1(colNum);
        //Creates a picture
        Picture picture = drawing.createPicture(anchor, pictureIdx);
        //Reset the image size
        picture.resize(imageScale);
    }

    public static void generateOneLineRowByValues(XSSFSheet sheet, int rowNum, List<String> values) {
        List<MyExcelCell> cells = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            cells.add(MyExcelCell.builder()
                    .text(values.get(i))
                    .cellPosition(new MyCellPosition(rowNum, rowNum, i, i))
                    .build());
        }
        addRowToExcel(sheet, MyExcelRow.builder()
                .startRow(rowNum)
                .endRow(rowNum)
                .cells(cells)
                .build());
    }

    public static void addRowToExcel(XSSFSheet sheet, MyExcelRow myExcelRow) {
        createRows(sheet, myExcelRow.getStartRow(), myExcelRow.getEndRow());
        for (MyExcelCell myExcelCell : myExcelRow.getCells()) {
            XSSFRow row = sheet.getRow(myExcelCell.getCellPosition().getStartRow());
            XSSFCell cell = row.createCell(myExcelCell.getCellPosition().getStartColumn());
            if (myExcelCell.getText() != null) {
                cell.setCellValue(myExcelCell.getText());
            }
            if (myExcelCell.getCellStyle() != null) {
                setCellStyle(cell, myExcelCell.getCellStyle());
            }
        }
        for (MyExcelCell myExcelCell : myExcelRow.getCells()) {
            mergeCellAndAlignCenter(sheet, myExcelCell.getCellPosition());
        }
    }

    public static void setCellStyle(XSSFCell cell, MyCellStyle myCellStyle) {
        CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();
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
            cellStyle.setFillForegroundColor(myCellStyle.getFillForegroundColor().getIndex());
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
        if (myCellStyle.getFont() != null) {
            XSSFFont font = cell.getSheet().getWorkbook().createFont();
            if (myCellStyle.getFont().getFontName() != null) {
                font.setFontName(myCellStyle.getFont().getFontName());
            }
            if (myCellStyle.getFont().getFontHeight() != null) {
                font.setFontHeightInPoints(myCellStyle.getFont().getFontHeight());
            }
            if (myCellStyle.getFont().getColor() != null) {
                font.setColor(myCellStyle.getFont().getColor().getIndex());
            }
            if (myCellStyle.getFont().getBold() != null) {
                font.setBold(myCellStyle.getFont().getBold());
            }
            if (myCellStyle.getFont().getItalic() != null) {
                font.setItalic(myCellStyle.getFont().getItalic());
            }
            if (myCellStyle.getFont().getUnderline() != null) {
                font.setUnderline(myCellStyle.getFont().getUnderline());
            }
            if (myCellStyle.getFont().getStrikeout() != null) {
                font.setStrikeout(myCellStyle.getFont().getStrikeout());
            }
            cellStyle.setFont(font);
        }
        cell.setCellStyle(cellStyle);
    }

    public static void createRows(XSSFSheet sheet, int startRow, int endRow) {
        for (int i = startRow; i <= endRow; i++) {
            sheet.createRow(i);
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
