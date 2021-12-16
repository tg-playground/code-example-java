package com.taogen.example.util.word.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;

/**
 * Spacing:
 * - SpacingBefore: poundNumber * 20 （段前，1/20 磅）
 * - SpacingBeforeLines: lineNumber * 100 （段前，1/100 行）
 * - SpacingBetween: lineNumber （行距：n行）
 * - fixed SpacingBetween:
 * @author Taogen
 */
@Data
@Builder
public class SpacingSetting {

    /**
     * unit: 1/20 point
     */
    private Integer spacingBefore;


    /**
     * unit: 1/100 line
     */
    private Integer spacingBeforeLines;

    private Integer spacingAfter;

    private Integer spacingAfterLines;

    /**
     * Line spacing
     * unit: line or point
     */
    private Integer spacingBetween;

    /**
     * AUTO: lines
     * EXACT: points
     */
    private LineSpacingRule lineSpacingRule;
}
