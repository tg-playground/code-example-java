package com.taogen.example.controller;

import com.taogen.example.util.FileUtil;
import com.taogen.example.util.word.WordUtil;
import com.taogen.example.util.word.vo.FontSetting;
import com.taogen.example.util.word.vo.TextAndFontSetting;
import com.taogen.example.util.word.vo.TextParagraph;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/word")
public class WordExportController extends BaseController {

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException, XmlException, URISyntaxException, InvalidFormatException {
        setFileDownloadResponse(response, FileUtil.getFileNameWithSuffix(
                "用户信息", WordUtil.WORD_SUFFIX));
        generateDocument().write(response.getOutputStream());
    }

    public static XWPFDocument generateDocument() throws IOException, XmlException, InvalidFormatException, URISyntaxException {
        XWPFDocument document = new XWPFDocument();
        WordUtil.addTextToDocument(document, TextParagraph.builder()
                .textAndFontSettingList(Arrays.asList(
                        TextAndFontSetting.builder()
                                .text("Hello World")
                                .fontSetting(FontSetting.builder()
                                        .fontSize(20)
                                        .build())
                                .build()
                ))
                .build());
        return document;
    }

}
