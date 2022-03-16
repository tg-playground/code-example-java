package com.taogen.example.controller;

import com.taogen.example.entity.User;
import com.taogen.example.service.UserService;
import com.taogen.example.util.FileUtil;
import com.taogen.example.util.excel.annotation.ExcelAnnotationUtil;
import com.taogen.example.vo.UserVoForExcelExport;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/excel")
@Log4j2
public class ExcelExportController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        setFileDownloadResponse(response, FileUtil.addDatetimeToFileName(
                "用户信息", ExcelAnnotationUtil.EXCEL_SUFFIX));
        List<User> userList = userService.list();
        log.info("userList: {}", userList);
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelAnnotationUtil.generateWorkbook(UserVoForExcelExport.class,
                        UserVoForExcelExport.fromUserList(userList),
                        "用户列表")
                .write(outputStream);
    }
}
