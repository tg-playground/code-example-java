package com.taogen.example.util.excel.demo;

import com.taogen.example.util.excel.common.ExcelUtil;
import com.taogen.example.util.word.demo.WordDemoAddPicture;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Taogen
 */
public class ExcelDemoAddPicture {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("Hello World");
        String imageFilePath = "image/icon.jpg";
        InputStream inputStream = WordDemoAddPicture.class.getClassLoader().getResourceAsStream(imageFilePath);
        ExcelUtil.addPictureToExcel(workbook, sheet, 2, 1,
                inputStream, 0.5);
        String filePath = new StringBuilder()
                .append("C:\\Users\\Taogen\\Desktop\\excel_add_picture")
                .append(System.currentTimeMillis())
                .append(".xlsx")
                .toString();
        workbook.write(new FileOutputStream(filePath));
        System.out.println("文件生成成功，路径为：" + filePath);
    }

}
