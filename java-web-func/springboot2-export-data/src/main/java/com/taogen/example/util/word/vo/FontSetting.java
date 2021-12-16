package com.taogen.example.util.word.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

/**
 * font:
 * - FontSize: (int)
 * - FontFamily: (String)
 * - Color: (RGB string)
 * - Bold: (boolean)
 * - Italic: (boolean)
 * - CharacterSpacing: (字符间距)
 *
 * @author Taogen
 */
@Data
@Builder
public class FontSetting {
    /**
     * unit: point
     * default 11 points
     */
    private Integer fontSize = 11;

    private String fontFamily = "Calibri";

    /**
     * RGB string
     */
    private String color = "000000";
    private Boolean bold = false;
    private Boolean italic = false;
    private UnderlinePatterns underline;
    private Boolean strikeThrough = false;
    private Integer characterSpacing;
}
