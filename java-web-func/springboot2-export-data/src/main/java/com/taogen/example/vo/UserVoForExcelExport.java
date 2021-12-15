package com.taogen.example.vo;

import com.taogen.example.entity.User;
import com.taogen.example.util.excel.annotation.Excel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Taogen
 */
@Data
public class UserVoForExcelExport {
    @Excel(name = "ID")
    private Integer id;
    @Excel(name = "用户名")
    private String name;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Date modifyTime;

    public static List<UserVoForExcelExport> fromUserList(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return Collections.emptyList();
        }
        List<UserVoForExcelExport> userVoList = new ArrayList<>();
        for (User user : userList) {
            UserVoForExcelExport userVoForExcelExport = new UserVoForExcelExport();
            BeanUtils.copyProperties(user, userVoForExcelExport);
            userVoList.add(userVoForExcelExport);
        }
        return userVoList;
    }
}
