package com.taogen.example.controller;

import com.taogen.example.util.FileUtil;
import com.taogen.example.util.excel.ExcelUtil;
import com.taogen.example.util.word.WordUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

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
        WordUtil.generateDocument()
                .write(response.getOutputStream());
    }
}
