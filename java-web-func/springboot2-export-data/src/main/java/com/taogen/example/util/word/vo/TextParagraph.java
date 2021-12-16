package com.taogen.example.util.word.vo;

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
public class TextParagraph {

    private List<TextAndFontSetting> textAndFontSettingList;

    private ParagraphAlignment paragraphAlignment;

    private IndentationSetting indentationSetting;

    private SpacingSetting spacingSetting;
}
