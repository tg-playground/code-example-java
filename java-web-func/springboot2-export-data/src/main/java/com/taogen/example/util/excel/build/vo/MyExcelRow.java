package com.taogen.example.util.excel.build.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MyExcelRow for generate structured data of a row.
 *
 * A row can have multiple sub-rows.
 * Assume that two adjacent complete rows do not intersect.
 * Each complete row can only be merged in one direction. horizontal or vertical.
 * Structure of row like a tree. For example,
 *
 *        row 0
 * row1 |- sub-row 1.1
 *      |- sub-row 1.2
 * row2 |- sub-row 2.1
 *      |- sub-row 2.2
 *
 * @author Taogen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyExcelRow {
    private List<MyExcelCell> cells;
    private Integer startRow;
    private Integer endRow;
    private Integer rowHeight;
}
