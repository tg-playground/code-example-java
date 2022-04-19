package com.taogen.example.util.word.demo;

import com.taogen.example.util.word.WordUtil;
import com.taogen.example.util.word.build.WordBuildUtil;
import com.taogen.example.util.word.build.vo.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author Taogen
 */
public class WordDemoBuildReport {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String outputFilePath = new StringBuilder()
                .append("C:\\Users\\Taogen\\Desktop\\report_")
                .append(System.currentTimeMillis())
                .append(".docx")
                .toString();
        XWPFDocument document = new XWPFDocument();
        WordUtil.addCustomHeadingStyle(document, "标题 1", 1);

        int blankLines = 11;
        WordUtil.addBlankLineParagraph(document, blankLines);
        XWPFParagraph outlineOneParagraph = document.createParagraph();
        outlineOneParagraph.setStyle("标题 1");
        outlineOneParagraph.createRun().setText("Heading 1");

        MyTextParagraph myTextParagraph = MyTextParagraph.builder()
                .myTextAndFontSettingList(Arrays.asList(MyTextAndFontSetting.builder()
                        .text("互联网媒体内容合规检查" + "\r\n" + "审核报告")
                        .myFontSetting(MyFontSetting.builder()
                                .fontFamily("黑体")
                                .fontSize(24)
                                .build())
                        .build()))
                .paragraphAlignment(ParagraphAlignment.CENTER)
                .myIndentationSetting(null)
                .mySpacingSetting(null)
                .build();
        WordBuildUtil.addTextToDocument(document, myTextParagraph);

        blankLines = 8;
        WordUtil.addBlankLineParagraph(document, blankLines);

        WordBuildUtil.addTextToDocument(document, MyTextParagraph.builder()
                .myTextAndFontSettingList(Arrays.asList(MyTextAndFontSetting.builder()
                        .text("站点组：蚌埠市宣传部、昆山教育、无锡产权交易所、全椒网信办、绍兴市网信办、抚州市网信办、马鞍山市网信办、望江宣传部2、江西日报社、嵊州网信办、嵊州网信办1、丰城市人民政府、南京市网信办、赣州网信办、江西省网信办、泰兴市融媒体中心、苏高新集团有限公司、江西省林业局"
                                + "\r\n" + "站点/新媒体数量：1401" + "\r\n" + "生成时间：2021年10月9日")
                        .myFontSetting(MyFontSetting.builder()
                                .fontSize(10)
                                .fontFamily("黑体")
                                .color("FF0000")
                                .build())
                        .build()))
                .paragraphAlignment(ParagraphAlignment.CENTER)
                .mySpacingSetting(MySpacingSetting.builder()
                        .lineSpacingRule(LineSpacingRule.EXACT)
                        .spacingBetween(30)
                        .build())
                .build());

        String filepath = "D:\\Repositories\\GitRepositories\\Code Repositories\\code-example-java\\java-web-func\\springboot2-export-data\\src\\main\\resources\\forestcon.jpg";
        MyImageParagraph myImageParagraph = MyImageParagraph.builder()
                .paragraphAlignment(ParagraphAlignment.CENTER)
                .textPosition(20)
                .inputStream(new FileInputStream(filepath))
                .filename(UUID.randomUUID().toString())
                .pictureType(XWPFDocument.PICTURE_TYPE_JPEG)
                .width(200)
                .height(200)
                .build();
        WordBuildUtil.addPictureToDocument(document, myImageParagraph);

        WordBuildUtil.addTextToDocument(document, MyTextParagraph.builder()
                .myTextAndFontSettingList(Arrays.asList(
                        MyTextAndFontSetting.builder()
                                .text("end")
                                .build(),
                        MyTextAndFontSetting.builder()
                                .text("abc")
                                .myFontSetting(MyFontSetting.builder()
                                        .color("FF0000")
                                        .build())
                                .build()
                ))
                .build());
        FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
        document.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("生成word文档成功，文件路径：" + outputFilePath);
    }
}
