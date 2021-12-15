package com.taogen.example.util.excel.vo;

import com.taogen.example.util.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

/**
 * @author Taogen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldExcel {
    private Field field;
    private Excel excel;
}
