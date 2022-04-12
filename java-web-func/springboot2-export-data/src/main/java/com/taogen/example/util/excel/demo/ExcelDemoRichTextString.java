package com.taogen.example.util.excel.demo;

import com.taogen.example.util.excel.build.vo.MyCellRichTextString;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Taogen
 */
public class ExcelDemoRichTextString {
    public static void main(String[] args) throws IOException {
        String outputFilepath = "C:\\Users\\Taogen\\Desktop\\test_" + System.currentTimeMillis() + ".xlsx";
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFilepath))) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("test");
            crateCell1(sheet, 0, 0);
            crateCell2(sheet, 1, 0);
            sheet.setColumnWidth(0, 150 * 20);
            workbook.write(bufferedOutputStream);
        }
        System.out.println("success");
        System.out.println("output filepath: " + outputFilepath);
    }

    /**
     * 原始的方式
     *
     * @param sheet
     * @param rowNum
     * @param columnNum
     */
    private static void crateCell1(XSSFSheet sheet, int rowNum, int columnNum) {
        XSSFCell cell = sheet.createRow(rowNum).createCell(columnNum);
        XSSFFont font = new XSSFFont();
        font.setColor(IndexedColors.RED.getIndex());
        XSSFFont font2 = new XSSFFont();
        font2.setColor(IndexedColors.GREEN.getIndex());
        RichTextString richTextString = sheet.getWorkbook().getCreationHelper()
                .createRichTextString("hello world");
        richTextString.applyFont(0, 5, font);
        richTextString.applyFont(6, 11, font2);
        cell.setCellValue(richTextString);
    }

    /**
     * 封装的方法
     *
     * @param sheet
     * @param rowNum
     * @param columnNum
     */
    private static void crateCell2(XSSFSheet sheet, int rowNum, int columnNum) {
        XSSFCell cell = sheet.createRow(rowNum).createCell(columnNum);
        XSSFFont font = new XSSFFont();
        font.setColor(IndexedColors.RED.getIndex());
        XSSFFont font2 = new XSSFFont();
        font2.setColor(IndexedColors.GREEN.getIndex());
        XSSFWorkbook workbook = sheet.getWorkbook();
        MyCellRichTextString myCellRichTextString = MyCellRichTextString.builder()
                .startIndex(0)
                .endIndex(5)
                .font(font)
                .build();
        MyCellRichTextString myCellRichTextString2 = MyCellRichTextString.builder()
                .startIndex(6)
                .endIndex(11)
                .font(font2)
                .build();
        RichTextString richTextString = MyCellRichTextString.createRichTextString(
                workbook, "Hello 12345", Arrays.asList(myCellRichTextString, myCellRichTextString2));
        cell.setCellValue(richTextString);
    }
}
