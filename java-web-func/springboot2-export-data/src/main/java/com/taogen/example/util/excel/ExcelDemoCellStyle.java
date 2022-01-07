package com.taogen.example.util.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Taogen
 */
public class ExcelDemoCellStyle {
    public static void main(String[] args) throws IOException {
        String outputFilepath = "C:\\Users\\Taogen\\Desktop\\test_" + System.currentTimeMillis() + ".xlsx";
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFilepath))) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
            cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setTopBorderColor(IndexedColors.BLUE.getIndex());


            XSSFFont font = workbook.createFont();
            font.setFontName("仿宋");
            font.setFontHeightInPoints((short) 14);
            font.setColor(IndexedColors.RED.getIndex());
            font.setBold(true);
            font.setItalic(true);
            font.setUnderline(FontUnderline.SINGLE);
            font.setStrikeout(true);
            cellStyle.setFont(font);

            XSSFSheet sheet = workbook.createSheet();
            sheet.setColumnWidth(0, 256 * 30);
            // row1
            int rowNum = 0;
            XSSFRow row = sheet.createRow(rowNum);
            row.setHeight((short) (50 * 20));
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Hello darkness my old friend! I come to talk with you again.");
            cell.setCellStyle(cellStyle);
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue("测试啊");
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));
            rowNum++;

            //row2
            XSSFRow row2 = sheet.createRow(rowNum);
            XSSFCell row2Cell = row2.createCell(0);
            row2Cell.setCellValue("World");
            CellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setAlignment(HorizontalAlignment.CENTER);
            cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle1.setBorderBottom(BorderStyle.MEDIUM);
            row2Cell.setCellStyle(cellStyle1);
            rowNum++;

            //row3
            XSSFRow row3 = sheet.createRow(rowNum);
            XSSFCell row3Cell = row3.createCell(0);
            row3Cell.setCellValue("Hello3");
            rowNum++;

            // row4
            XSSFRow row4 = sheet.createRow(rowNum);
            XSSFCell row4Cell = row4.createCell(0);
            row4Cell.setCellValue("Hello4");
            CellStyle cellStyle4 = workbook.createCellStyle();
            cellStyle4.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle4.setTopBorderColor(IndexedColors.BLUE.getIndex());
            cellStyle4.setBottomBorderColor(IndexedColors.BLUE.getIndex());
            row4Cell.setCellStyle(cellStyle4);
            rowNum++;

            sheet.addMergedRegion(CellRangeAddress.valueOf("A2:A3"));

            workbook.write(bufferedOutputStream);
        }
        System.out.println("生成excel文件成功，路径为：" + outputFilepath);
    }

}
