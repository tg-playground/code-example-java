package com.taogen.example.util.word.vo;

import lombok.Builder;
import lombok.Data;

/**
 * Indent:
 * - IndentationFirstLine: poundNumber * 20 （首行缩进，1/20 磅）
 * - IndentationLeft: poundNumber * 20 （左侧缩进，1/20 磅）
 * - IndentationRight: poundNumber * 20 （右侧缩进，1/20 磅）
 * @author Taogen
 */
@Data
@Builder
public class IndentationSetting {
    /**
     * 1/20th point
     */
    private Integer indentationFirstLine;

    /**
     * 1/20th point
     */
    private Integer indentationLeft;

    /**
     * 1/20th point
     */
    private Integer indentationRight;
}
