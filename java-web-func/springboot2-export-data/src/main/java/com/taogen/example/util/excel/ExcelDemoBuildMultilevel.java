package com.taogen.example.util.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Taogen
 */
public class ExcelDemoBuildMultilevel {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        CellStyle horizontalCenterCellStyle = workbook.createCellStyle();
        horizontalCenterCellStyle.setAlignment(HorizontalAlignment.CENTER);
        CellStyle verticalCenterCellStyle = workbook.createCellStyle();
        verticalCenterCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFSheet sheet = workbook.createSheet("sheet");
        int rowNum = 0;
        int maxCol = 6;
        XSSFRow row = sheet.createRow(rowNum);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("互联网媒体内容审核报告");
        cell.setCellStyle(horizontalCenterCellStyle);
//        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$F$1"));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, maxCol - 1));
        XSSFRow row1 = sheet.createRow(++rowNum);
        row1.createCell(0).setCellValue("序号");
        row1.createCell(1).setCellValue("站点");
        row1.createCell(2).setCellValue("发布时间");
        row1.createCell(3).setCellValue("标题");
        row1.createCell(4).setCellValue("错误类型");
        row1.createCell(5).setCellValue("错误和修改建议");
        XSSFRow row2 = sheet.createRow(++rowNum);
        XSSFCell row2Cell = row2.createCell(0);
        row2Cell.setCellValue("1");
        row2Cell.setCellStyle(verticalCenterCellStyle);
        row2.createCell(1).setCellValue("网易");
        row2.createCell(2).setCellValue("2019-01-01 12:00:00");
        row2.createCell(3).setCellValue("网易新闻");
        row2.createCell(4).setCellValue("严重错误");
        row2.createCell(5).setCellValue("己经（已经）");
        XSSFRow row3 = sheet.createRow(++rowNum);
        row3.createCell(4).setCellValue("错误");
        row3.createCell(5).setCellValue("中秧（中央）");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$A$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$B$3:$B$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$C$3:$C$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$D$3:$D$4"));
        String filepath = "C:\\Users\\Taogen\\Desktop\\test_" + System.currentTimeMillis() + ".xlsx";
        workbook.write(new FileOutputStream(filepath));
        System.out.println("文件生成成功，路径为：" + filepath);
    }

}
