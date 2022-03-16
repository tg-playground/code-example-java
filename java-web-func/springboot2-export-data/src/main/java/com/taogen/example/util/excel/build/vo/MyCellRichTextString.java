package com.taogen.example.util.excel.build.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @author Taogen
 */
@Data
@Builder
public class MyCellRichTextString {
    private Integer startIndex;
    private Integer endIndex;
    private Font font;

    public static RichTextString createRichTextString(XSSFWorkbook workbook, String text,
                                                      List<MyCellRichTextString> myCellRichTextStringList) {
        RichTextString richText = workbook.getCreationHelper().createRichTextString(text);
        if (myCellRichTextStringList != null && !myCellRichTextStringList.isEmpty()) {
            for (MyCellRichTextString myCellRichTextString : myCellRichTextStringList) {
                richText.applyFont(myCellRichTextString.getStartIndex(), myCellRichTextString.getEndIndex(), myCellRichTextString.getFont());
            }
        }
        return richText;
    }
}
