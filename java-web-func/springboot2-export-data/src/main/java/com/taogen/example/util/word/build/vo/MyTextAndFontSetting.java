package com.taogen.example.util.word.build.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Taogen
 */
@Data
@Builder
public class MyTextAndFontSetting {
    /**
     * convert /r/n to poi break line
     */
    private String text;
    private MyFontSetting myFontSetting;
}
