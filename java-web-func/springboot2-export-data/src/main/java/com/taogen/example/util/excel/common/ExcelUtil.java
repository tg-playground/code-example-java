package com.taogen.example.util.excel.common;

import org.apache.commons.io.IOUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility for handling POI Excel API
 *
 * @author Taogen
 */
public class ExcelUtil {

    public static boolean predicateExcel(String excelFilePath, Predicate<XSSFWorkbook> excelPredicate) {
        try (
                XSSFWorkbook workbook = new XSSFWorkbook(new File(excelFilePath));
        ) {
            return excelPredicate.test(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setCellValueByObject(XSSFCell cell, Object object) {
        if (object == null) {
            cell.setCellValue("");
        }
        if (object instanceof String) {
            cell.setCellValue((String) object);
        } else if (object instanceof Byte) {
            cell.setCellValue((Byte) object);
        } else if (object instanceof Short) {
            cell.setCellValue((Short) object);
        } else if (object instanceof Integer) {
            cell.setCellValue((Integer) object);
        } else if (object instanceof Long) {
            cell.setCellValue((Long) object);
        } else if (object instanceof Float) {
            cell.setCellValue((Float) object);
        } else if (object instanceof Double) {
            cell.setCellValue((Double) object);
        } else if (object instanceof Boolean) {
            cell.setCellValue((Boolean) object);
        } else if (object instanceof Date) {
            cell.setCellValue((Date) object);
        } else if (object instanceof LocalDate) {
            cell.setCellValue((LocalDate) object);
        } else if (object instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) object);
        } else if (object instanceof Calendar) {
            cell.setCellValue((Calendar) object);
        } else if (object instanceof RichTextString) {
            cell.setCellValue((RichTextString) object);
        } else {
            cell.setCellValue(object.toString());
        }
    }

    public static XSSFHyperlink createHyperlinkByUrl(XSSFWorkbook workbook, String url) {
        CreationHelper createHelper = workbook.getCreationHelper();
        XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress(url);
        return link;
    }

    public static XSSFFont getHyperLinkFont(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setUnderline(FontUnderline.SINGLE);
        font.setColor(IndexedColors.BLUE.getIndex());
        return font;
    }

    public static XSSFColor getXssfColorByRgb(int red, int green, int blue) {
        return new XSSFColor(new byte[]{(byte) red, (byte) green, (byte) blue}, new DefaultIndexedColorMap());
    }

    public static XSSFColor getXssfColorByIndexColor(IndexedColors indexedColors) {
        return new XSSFColor(indexedColors, new DefaultIndexedColorMap());
    }

    public static void setSheetColumnsWidth(XSSFSheet sheet, List<Integer> columnWidths) {
        if (columnWidths != null && columnWidths.size() > 0) {
            for (int i = 0; i < columnWidths.size(); i++) {
                sheet.setColumnWidth(i, columnWidths.get(i) * 256);
            }
        }
    }

    public static void createRows(XSSFSheet sheet, int startRow, int endRow) {
        for (int i = startRow; i <= endRow; i++) {
            sheet.createRow(i);
        }
    }

    /**
     * Add picture to Excel sheet
     *
     * @param workbook
     * @param sheet
     * @param rowNum      start with 1
     * @param colNum      start with 1
     * @param inputStream
     * @param imageScale  range of double value from 0 to 1
     * @throws IOException
     */
    public static void addPictureToExcel(XSSFWorkbook workbook, XSSFSheet sheet,
                                         int rowNum, int colNum,
                                         InputStream inputStream, double imageScale) throws IOException {
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
}
