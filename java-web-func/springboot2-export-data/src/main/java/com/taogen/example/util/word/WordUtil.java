package com.taogen.example.util.word;

import com.taogen.example.util.word.vo.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author Taogen
 */
public class WordUtil {
    public static final String WORD_SUFFIX = ".docx";

    public static XWPFDocument generateDocument() throws IOException, XmlException, InvalidFormatException, URISyntaxException {
        XWPFDocument document = new XWPFDocument();
        addCustomHeadingStyle(document, "标题 1", 1);

        int blankLines = 11;
        addBlankLineParagraph(document, blankLines);
        XWPFParagraph outlineOneParagraph = document.createParagraph();
        outlineOneParagraph.setStyle("标题 1");
        outlineOneParagraph.createRun().setText("Heading 1");

        TextParagraph textParagraph = TextParagraph.builder()
                .textAndFontSettingList(Arrays.asList(TextAndFontSetting.builder()
                        .text("互联网媒体内容合规检查" + "\r\n" + "审核报告")
                        .fontSetting(FontSetting.builder()
                                .fontFamily("黑体")
                                .fontSize(24)
                                .build())
                        .build()))
                .paragraphAlignment(ParagraphAlignment.CENTER)
                .indentationSetting(null)
                .spacingSetting(null)
                .build();
        addTextToDocument(document, textParagraph);

        blankLines = 8;
        addBlankLineParagraph(document, blankLines);

        addTextToDocument(document, TextParagraph.builder()
                .textAndFontSettingList(Arrays.asList(TextAndFontSetting.builder()
                        .text("站点组：蚌埠市宣传部、昆山教育、无锡产权交易所、全椒网信办、绍兴市网信办、抚州市网信办、马鞍山市网信办、望江宣传部2、江西日报社、嵊州网信办、嵊州网信办1、丰城市人民政府、南京市网信办、赣州网信办、江西省网信办、泰兴市融媒体中心、苏高新集团有限公司、江西省林业局"
                                + "\r\n" + "站点/新媒体数量：1401" + "\r\n" + "生成时间：2021年10月9日")
                        .fontSetting(FontSetting.builder()
                                .fontSize(10)
                                .fontFamily("黑体")
                                .color("FF0000")
                                .build())
                        .build()))
                .paragraphAlignment(ParagraphAlignment.CENTER)
                .spacingSetting(SpacingSetting.builder()
                        .lineSpacingRule(LineSpacingRule.EXACT)
                        .spacingBetween(30)
                        .build())
                .build());

        String filepath = "D:\\Repositories\\GitRepositories\\Code Repositories\\code-example-java\\java-web-func\\springboot2-export-data\\src\\main\\resources\\forestcon.jpg";
        ImageParagraph imageParagraph = ImageParagraph.builder()
                .paragraphAlignment(ParagraphAlignment.CENTER)
                .textPosition(20)
                .inputStream(new FileInputStream(filepath))
                .filename(UUID.randomUUID().toString())
                .pictureType(XWPFDocument.PICTURE_TYPE_JPEG)
                .width(200)
                .height(200)
                .build();
        addPictureToDocument(document, imageParagraph);

        addTextToDocument(document, TextParagraph.builder()
                .textAndFontSettingList(Arrays.asList(
                        TextAndFontSetting.builder()
                                .text("end")
                                .build(),
                        TextAndFontSetting.builder()
                                .text("abc")
                                .fontSetting(FontSetting.builder()
                                        .color("FF0000")
                                        .build())
                                .build()
                ))
                .build());
        return document;
    }

    private static void addTextToDocument(XWPFDocument document, TextParagraph textParagraph) {
        XWPFParagraph paragraph = document.createParagraph();
        if (textParagraph.getParagraphAlignment() != null) {
            paragraph.setAlignment(textParagraph.getParagraphAlignment());
        }
        if (textParagraph.getIndentationSetting() != null) {
            if (textParagraph.getIndentationSetting().getIndentationFirstLine() != null) {
                paragraph.setIndentationFirstLine(textParagraph.getIndentationSetting().getIndentationFirstLine());
            }
            if (textParagraph.getIndentationSetting().getIndentationLeft() != null) {
                paragraph.setIndentationLeft(textParagraph.getIndentationSetting().getIndentationLeft());
            }
            if (textParagraph.getIndentationSetting().getIndentationRight() != null) {
                paragraph.setIndentationRight(textParagraph.getIndentationSetting().getIndentationRight());
            }
        }
        if (textParagraph.getSpacingSetting() != null) {
            if (textParagraph.getSpacingSetting().getSpacingBefore() != null) {
                paragraph.setSpacingBefore(textParagraph.getSpacingSetting().getSpacingBefore());
            }
            if (textParagraph.getSpacingSetting().getSpacingBeforeLines() != null) {
                paragraph.setSpacingBeforeLines(textParagraph.getSpacingSetting().getSpacingBeforeLines());
            }
            if (textParagraph.getSpacingSetting().getSpacingAfter() != null) {
                paragraph.setSpacingAfter(textParagraph.getSpacingSetting().getSpacingAfter());
            }
            if (textParagraph.getSpacingSetting().getSpacingAfterLines() != null) {
                paragraph.setSpacingAfterLines(textParagraph.getSpacingSetting().getSpacingAfterLines());
            }
            if (textParagraph.getSpacingSetting().getLineSpacingRule() != null) {
                if (LineSpacingRule.AUTO.equals(textParagraph.getSpacingSetting().getLineSpacingRule())) {
                    paragraph.setSpacingBetween(textParagraph.getSpacingSetting().getSpacingBetween(), LineSpacingRule.AUTO);
                } else {
                    paragraph.setSpacingBetween(textParagraph.getSpacingSetting().getSpacingBetween(), LineSpacingRule.EXACT);
                }
            }
        }
        if (textParagraph.getTextAndFontSettingList() != null && textParagraph.getTextAndFontSettingList().size() > 0) {
            for (TextAndFontSetting textAndFontSetting : textParagraph.getTextAndFontSettingList()) {
                XWPFRun run = paragraph.createRun();
                if (textAndFontSetting.getFontSetting() != null) {
                    FontSetting fontSetting = textAndFontSetting.getFontSetting();
                    if (fontSetting.getFontSize() != null) {
                        run.setFontSize(fontSetting.getFontSize());
                    }
                    if (fontSetting.getFontFamily() != null) {
                        run.setFontFamily(fontSetting.getFontFamily());
                    }
                    if (fontSetting.getColor() != null) {
                        run.setColor(fontSetting.getColor());
                    }
                    if (fontSetting.getBold() != null) {
                        run.setBold(fontSetting.getBold());
                    }
                    if (fontSetting.getItalic() != null) {
                        run.setItalic(fontSetting.getItalic());
                    }
                    if (fontSetting.getUnderline() != null) {
                        run.setUnderline(fontSetting.getUnderline());
                    }
                    if (fontSetting.getStrikeThrough() != null) {
                        run.setStrikeThrough(fontSetting.getStrikeThrough());
                    }
                    if (fontSetting.getCharacterSpacing() != null) {
                        run.setCharacterSpacing(fontSetting.getCharacterSpacing());
                    }
                }
                if (textAndFontSetting.getText() != null) {
                    String[] sections = textAndFontSetting.getText().split("\r\n");
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

    private static void addPictureToDocument(XWPFDocument document,
                                             ImageParagraph imageParagraph)
            throws IOException, InvalidFormatException {
        XWPFParagraph image = document.createParagraph();
        if (imageParagraph.getParagraphAlignment() != null) {
            image.setAlignment(imageParagraph.getParagraphAlignment());
        }
        XWPFRun imageRun = image.createRun();
        if (imageParagraph.getTextPosition() != null) {
            imageRun.setTextPosition(imageParagraph.getTextPosition());
        }
        imageRun.addPicture(imageParagraph.getInputStream(),
                imageParagraph.getPictureType(),
                imageParagraph.getFilename(),
                Units.toEMU(imageParagraph.getWidth()),
                Units.toEMU(imageParagraph.getHeight()));
    }

    private static void addCustomHeadingStyle(XWPFDocument docxDocument,
                                              String strStyleId,
                                              int headingLevel) {

        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(strStyleId);

        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal(strStyleId);
        ctStyle.setName(styleName);

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(headingLevel));

        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority(indentNumber);

        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed(onoffnull);

        // style shows up in the formats bar
        ctStyle.setQFormat(onoffnull);

        // style defines a heading of the given level
        CTPPrGeneral ppr = CTPPrGeneral.Factory.newInstance();
        ppr.setOutlineLvl(indentNumber);
        ctStyle.setPPr(ppr);

        XWPFStyle style = new XWPFStyle(ctStyle);

        // is a null op if already defined
        XWPFStyles styles = docxDocument.createStyles();

        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);

    }

    private static void addBlankLineParagraph(XWPFDocument document, int blankLines) {
        XWPFParagraph paragraph = document.createParagraph();
        addBreak(paragraph.createRun(), blankLines - 1);
    }

    private static void addBreak(XWPFRun run, int blankLines) {
        for (int i = 0; i < blankLines; i++) {
            run.addBreak();
        }
    }

    public static void main(String[] args) {
        System.out.println(1 * 20 / 2.54 * 72);
    }
}
