package com.taogen.example.util.word.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Taogen
 */
@Data
@Builder
public class TextAndFontSetting {
    /**
     * convert /r/n to poi break line
     */
    private String text;
    private FontSetting fontSetting;
}
