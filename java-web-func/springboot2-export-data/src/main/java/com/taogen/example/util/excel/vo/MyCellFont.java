package com.taogen.example.util.excel.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * @author Taogen
 */
@Data
@Builder
public class MyCellFont {

    private Short fontHeight;
    private FontFamily family;
    private Boolean bold;
    private XSSFColor color;
    private String fontName;
    private Boolean italic;
    private FontUnderline underline;
    private Boolean strikeout;
    private FontCharset charset;

    public static void main(String[] args) {
        XSSFFont xssfFont = null;
        xssfFont.setFamily(FontFamily.ROMAN);
    }
}

