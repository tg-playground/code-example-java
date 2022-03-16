package com.taogen.example.util.excel.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

/**
 * Java Object list to Excel rows
 *
 * @author Taogen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldExcel {
    private Field field;
    private Excel excel;
}
