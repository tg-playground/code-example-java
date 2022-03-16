package com.taogen.example.util.excel.build.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Taogen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCellPosition {
    /**
     * 0~n
     */
    private Integer startRow;

    /**
     * 0~n
     */
    private Integer endRow;

    /**
     * 0~n
     */
    private Integer startColumn;

    /**
     * 0~n
     */
    private Integer endColumn;
}
