package com.taogen.example.util.excel.build.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    public static XSSFFont createFont(XSSFWorkbook workbook, MyCellFont myCellFont) {
        XSSFFont font = workbook.createFont();
        if (myCellFont.getFontName() != null) {
            font.setFontName(myCellFont.getFontName());
        }
        if (myCellFont.getFontHeight() != null) {
            font.setFontHeightInPoints(myCellFont.getFontHeight());
        }
        if (myCellFont.getColor() != null) {
            font.setColor(myCellFont.getColor());
        }
        if (myCellFont.getBold() != null) {
            font.setBold(myCellFont.getBold());
        }
        if (myCellFont.getItalic() != null) {
            font.setItalic(myCellFont.getItalic());
        }
        if (myCellFont.getUnderline() != null) {
            font.setUnderline(myCellFont.getUnderline());
        }
        if (myCellFont.getStrikeout() != null) {
            font.setStrikeout(myCellFont.getStrikeout());
        }
        return font;
    }
}

