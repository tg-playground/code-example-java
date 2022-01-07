package com.taogen.example.util.excel.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @author Taogen
 */
@Data
@Builder
public class MyCellStyle {
    private HorizontalAlignment alignment;
    private VerticalAlignment verticalAlignment;
    private Boolean wrapText;
    private IndexedColors fillForegroundColor;
    private BorderStyle borderTop;
    private BorderStyle borderBottom;
    private BorderStyle borderLeft;
    private BorderStyle borderRight;
    private MyCellFont font;
}
