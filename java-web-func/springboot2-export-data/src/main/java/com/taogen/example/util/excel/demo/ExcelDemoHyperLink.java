package com.taogen.example.util.excel.demo;

import com.taogen.example.util.excel.common.ExcelUtil;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Taogen
 */
public class ExcelDemoHyperLink {
    public static void main(String[] args) throws IOException {
        String outputFilepath = "C:\\Users\\Taogen\\Desktop\\test_" + System.currentTimeMillis() + ".xlsx";
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFilepath))) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("test");
            getCell1(sheet, 0, 0, "https://weibo.com");
            getCell2(sheet, 1, 0, "https://zhihu.com");
            workbook.write(bufferedOutputStream);
        }
        System.out.println("success");
        System.out.println("output filepath: " + outputFilepath);
    }

    /**
     * 原始的方法
     *
     * @param sheet
     * @param rowNum
     * @param columnNum
     * @param url
     * @return
     */
    public static XSSFCell getCell1(XSSFSheet sheet, int rowNum, int columnNum,
                                    String url) {
        XSSFCell cell = sheet.createRow(rowNum).createCell(columnNum);
        cell.setCellValue("Open " + url);
        XSSFWorkbook workbook = sheet.getWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        XSSFHyperlink hyperlink = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
        hyperlink.setAddress(url);
        cell.setHyperlink(hyperlink);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(ExcelUtil.getHyperLinkFont(workbook));
        cell.setCellStyle(cellStyle);
        return cell;
    }

    /**
     * 封装的方法
     *
     * @param sheet
     * @param rowNum
     * @param columnNum
     * @param url
     * @return
     */
    public static XSSFCell getCell2(XSSFSheet sheet, int rowNum, int columnNum,
                                    String url) {
        XSSFWorkbook workbook = sheet.getWorkbook();
        XSSFCell cell = sheet.createRow(rowNum).createCell(columnNum);
        cell.setCellValue("Open " + url);
        XSSFHyperlink hyperlink = ExcelUtil.createHyperlinkByUrl(workbook, url);
        cell.setHyperlink(hyperlink);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(ExcelUtil.getHyperLinkFont(workbook));
        cell.setCellStyle(cellStyle);
        return cell;
    }
}
