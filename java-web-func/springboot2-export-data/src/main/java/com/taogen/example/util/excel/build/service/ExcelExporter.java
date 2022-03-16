package com.taogen.example.util.excel.build.service;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Taogen
 */
public interface ExcelExporter<T> {
    Workbook export(T t);
}
