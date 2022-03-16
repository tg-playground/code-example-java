package com.taogen.example.util.excel.annotation;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Generate Excel file by @Excel annotation
 *
 * @author Taogen
 */
public class ExcelAnnotationUtil {

    public static final String EXCEL_SUFFIX = ".xlsx";

    public static final List<Class> DATE_TIME_CLASS_TYPES = Arrays.asList(Date.class, LocalTime.class);

    public static XSSFWorkbook generateWorkbook(Class entityClass, List entityList,
                                                String sheetName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        List<FieldExcel> fieldExcelList = getFieldExcelList(entityClass);
        writeHeaderToSheet(workbook, sheet, fieldExcelList);
        writeDataToSheet(workbook, sheet, fieldExcelList, entityList);
        return workbook;
    }

    public static List<FieldExcel> getFieldExcelList(Class cls) {
        List<Field> fields = new ArrayList<>();
        if (!Object.class.equals(cls.getSuperclass())) {
            fields.addAll(Arrays.asList(cls.getSuperclass().getDeclaredFields()));
        }
        fields.addAll(Arrays.asList(cls.getDeclaredFields()));
        if (fields.isEmpty()) {
            return Collections.emptyList();
        }
        return fields.stream()
                .filter(item -> item.isAnnotationPresent(Excel.class))
                .map(item -> {
                    item.setAccessible(true);
                    return new FieldExcel(item, item.getAnnotation(Excel.class));
                })
                .collect(Collectors.toList());
    }

    private static void writeHeaderToSheet(XSSFWorkbook workbook, XSSFSheet sheet, List<FieldExcel> fieldExcelList) {
        int rowNum = 0;
        int columnNum = 0;
        Row row = sheet.createRow(rowNum);
        for (FieldExcel fieldExcel : fieldExcelList) {
            sheet.setColumnWidth(columnNum, (int) ((fieldExcel.getExcel().width() + 0.72) * 256));
            CellUtil.createCell(row, columnNum++, fieldExcel.getExcel().name(), getHeaderCellStyle(workbook, fieldExcel.getExcel()));
        }
    }

    private static CellStyle getHeaderCellStyle(XSSFWorkbook workbook, Excel excel) {
        CellStyle labelStyle = workbook.createCellStyle();
        XSSFFont labelFont = workbook.createFont();
        labelFont.setBold(true);
        labelFont.setFontHeight(excel.height());
        labelStyle.setFont(labelFont);
        return labelStyle;
    }

    private static void writeDataToSheet(XSSFWorkbook workbook, XSSFSheet sheet, List<FieldExcel> fieldExcelList, List list) {
        int rowNum = 1;
        int columnNum = 0;
        for (Object obj : list) {
            Row row = sheet.createRow(rowNum);
            columnNum = 0;
            for (FieldExcel fieldExcel : fieldExcelList) {
                Object fieldValue = null;
                try {
                    fieldValue = fieldExcel.getField().get(obj);
                    if (DATE_TIME_CLASS_TYPES.contains(fieldExcel.getField().getType()) && fieldExcel.getExcel().dateFormat() != null) {
                        fieldValue = formatDateTime(fieldValue, fieldExcel.getExcel().dateFormat());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
                CellUtil.createCell(row, columnNum,
                        fieldValue != null ? fieldValue.toString() : "",
                        getContentStyle(workbook));
                columnNum++;
            }
            rowNum++;
        }
    }

    private static Object formatDateTime(Object fieldValue, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(fieldValue);
    }

    private static CellStyle getContentStyle(XSSFWorkbook workbook) {
        CellStyle contentStyle = workbook.createCellStyle();
        XSSFFont rightFont = workbook.createFont();
        rightFont.setFontHeight(12);
        contentStyle.setFont(rightFont);
        return contentStyle;
    }
}
