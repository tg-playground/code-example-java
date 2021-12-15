package com.taogen.example.controller;

import com.taogen.example.entity.User;
import com.taogen.example.service.UserService;
import com.taogen.example.util.excel.ExcelUtil;
import com.taogen.example.vo.UserVoForExcelExport;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/excel")
@Log4j2
public class ExcelExportController {

    @Autowired
    private UserService userService;

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        String filename = "中 文.xlsx";
        setFileDownloadResponse(response, filename);
        List<User> userList = userService.list();
        log.info("userList: {}", userList);
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil.generateWorkbook(UserVoForExcelExport.class,
                        UserVoForExcelExport.fromUserList(userList),
                        "用户列表")
                .write(outputStream);
    }

    private void setFileDownloadResponse(HttpServletResponse response, String filename) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        String resultFileName = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + resultFileName + "\"");
    }
}
