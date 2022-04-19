package com.taogen.example.util.word.build;

import com.taogen.example.util.word.build.vo.MyFontSetting;
import com.taogen.example.util.word.build.vo.MyImageParagraph;
import com.taogen.example.util.word.build.vo.MyTextAndFontSetting;
import com.taogen.example.util.word.build.vo.MyTextParagraph;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Generate Word files by custom wrapper classes
 *
 * @author Taogen
 */
public class WordBuildUtil {
    public static final String WORD_SUFFIX = ".docx";

    public static void addTextToDocument(XWPFDocument document, MyTextParagraph myTextParagraph) {
        XWPFParagraph paragraph = document.createParagraph();
        if (myTextParagraph.getParagraphAlignment() != null) {
            paragraph.setAlignment(myTextParagraph.getParagraphAlignment());
        }
        if (myTextParagraph.getMyIndentationSetting() != null) {
            if (myTextParagraph.getMyIndentationSetting().getIndentationFirstLine() != null) {
                paragraph.setIndentationFirstLine(myTextParagraph.getMyIndentationSetting().getIndentationFirstLine());
            }
            if (myTextParagraph.getMyIndentationSetting().getIndentationLeft() != null) {
                paragraph.setIndentationLeft(myTextParagraph.getMyIndentationSetting().getIndentationLeft());
            }
            if (myTextParagraph.getMyIndentationSetting().getIndentationRight() != null) {
                paragraph.setIndentationRight(myTextParagraph.getMyIndentationSetting().getIndentationRight());
            }
        }
        if (myTextParagraph.getMySpacingSetting() != null) {
            if (myTextParagraph.getMySpacingSetting().getSpacingBefore() != null) {
                paragraph.setSpacingBefore(myTextParagraph.getMySpacingSetting().getSpacingBefore());
            }
            if (myTextParagraph.getMySpacingSetting().getSpacingBeforeLines() != null) {
                paragraph.setSpacingBeforeLines(myTextParagraph.getMySpacingSetting().getSpacingBeforeLines());
            }
            if (myTextParagraph.getMySpacingSetting().getSpacingAfter() != null) {
                paragraph.setSpacingAfter(myTextParagraph.getMySpacingSetting().getSpacingAfter());
            }
            if (myTextParagraph.getMySpacingSetting().getSpacingAfterLines() != null) {
                paragraph.setSpacingAfterLines(myTextParagraph.getMySpacingSetting().getSpacingAfterLines());
            }
            if (myTextParagraph.getMySpacingSetting().getLineSpacingRule() != null) {
                if (LineSpacingRule.AUTO.equals(myTextParagraph.getMySpacingSetting().getLineSpacingRule())) {
                    paragraph.setSpacingBetween(myTextParagraph.getMySpacingSetting().getSpacingBetween(), LineSpacingRule.AUTO);
                } else {
                    paragraph.setSpacingBetween(myTextParagraph.getMySpacingSetting().getSpacingBetween(), LineSpacingRule.EXACT);
                }
            }
        }
        if (myTextParagraph.getMyTextAndFontSettingList() != null && myTextParagraph.getMyTextAndFontSettingList().size() > 0) {
            for (MyTextAndFontSetting myTextAndFontSetting : myTextParagraph.getMyTextAndFontSettingList()) {
                XWPFRun run = paragraph.createRun();
                if (myTextAndFontSetting.getMyFontSetting() != null) {
                    MyFontSetting myFontSetting = myTextAndFontSetting.getMyFontSetting();
                    if (myFontSetting.getFontSize() != null) {
                        run.setFontSize(myFontSetting.getFontSize());
                    }
                    if (myFontSetting.getFontFamily() != null) {
                        run.setFontFamily(myFontSetting.getFontFamily());
                    }
                    if (myFontSetting.getColor() != null) {
                        run.setColor(myFontSetting.getColor());
                    }
                    if (myFontSetting.getBold() != null) {
                        run.setBold(myFontSetting.getBold());
                    }
                    if (myFontSetting.getItalic() != null) {
                        run.setItalic(myFontSetting.getItalic());
                    }
                    if (myFontSetting.getUnderline() != null) {
                        run.setUnderline(myFontSetting.getUnderline());
                    }
                    if (myFontSetting.getStrikeThrough() != null) {
                        run.setStrikeThrough(myFontSetting.getStrikeThrough());
                    }
                    if (myFontSetting.getCharacterSpacing() != null) {
                        run.setCharacterSpacing(myFontSetting.getCharacterSpacing());
                    }
                }
                if (myTextAndFontSetting.getText() != null) {
                    String[] sections = myTextAndFontSetting.getText().split("\r\n");
                    for (int i = 0; i < sections.length; i++) {
                        run.setText(sections[i]);
                        if (i < sections.length - 1) {
                            run.addBreak();
                        }
                    }
                }
            }
        }
    }

    public static void addPictureToDocument(XWPFDocument document,
                                            MyImageParagraph myImageParagraph)
            throws IOException, InvalidFormatException {
        XWPFParagraph image = document.createParagraph();
        if (myImageParagraph.getParagraphAlignment() != null) {
            image.setAlignment(myImageParagraph.getParagraphAlignment());
        }
        XWPFRun imageRun = image.createRun();
        if (myImageParagraph.getTextPosition() != null) {
            imageRun.setTextPosition(myImageParagraph.getTextPosition());
        }
        imageRun.addPicture(myImageParagraph.getInputStream(),
                myImageParagraph.getPictureType(),
                myImageParagraph.getFilename(),
                Units.toEMU(myImageParagraph.getWidth()),
                Units.toEMU(myImageParagraph.getHeight()));
    }

    public static int getImageFormat(String imgFileName) {
        int format;
        if (imgFileName.endsWith(".emf"))
            format = XWPFDocument.PICTURE_TYPE_EMF;
        else if (imgFileName.endsWith(".wmf"))
            format = XWPFDocument.PICTURE_TYPE_WMF;
        else if (imgFileName.endsWith(".pict"))
            format = XWPFDocument.PICTURE_TYPE_PICT;
        else if (imgFileName.endsWith(".jpeg") || imgFileName.endsWith(".jpg"))
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if (imgFileName.endsWith(".png"))
            format = XWPFDocument.PICTURE_TYPE_PNG;
        else if (imgFileName.endsWith(".dib"))
            format = XWPFDocument.PICTURE_TYPE_DIB;
        else if (imgFileName.endsWith(".gif"))
            format = XWPFDocument.PICTURE_TYPE_GIF;
        else if (imgFileName.endsWith(".tiff"))
            format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if (imgFileName.endsWith(".eps"))
            format = XWPFDocument.PICTURE_TYPE_EPS;
        else if (imgFileName.endsWith(".bmp"))
            format = XWPFDocument.PICTURE_TYPE_BMP;
        else if (imgFileName.endsWith(".wpg"))
            format = XWPFDocument.PICTURE_TYPE_WPG;
        else {
            return 0;
        }
        return format;
    }

}
