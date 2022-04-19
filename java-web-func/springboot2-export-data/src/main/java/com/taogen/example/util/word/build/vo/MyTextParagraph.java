package com.taogen.example.util.word.build.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import java.util.List;

/**
 * Paragraph
 * @author Taogen
 */
@Data
@Builder
public class MyTextParagraph {

    private List<MyTextAndFontSetting> myTextAndFontSettingList;

    private ParagraphAlignment paragraphAlignment;

    private MyIndentationSetting myIndentationSetting;

    private MySpacingSetting mySpacingSetting;
}
