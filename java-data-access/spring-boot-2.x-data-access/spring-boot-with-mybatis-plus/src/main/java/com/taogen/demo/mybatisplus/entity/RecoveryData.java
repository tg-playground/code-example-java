package com.taogen.demo.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.taogen.demo.mybatisplus.util.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hj
 * @since 2019-05-10
 */
@Data
public class RecoveryData extends Model<RecoveryData> {

    public static final Integer SPAM_WORDS_YES = 1;

    private static final long serialVersionUID = 1L;

    @Excel(name="ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
//    @ApiModelProperty("标题")
    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pubtime;

    private String url;

    private String urlMd5;

    private String genus;

    private String host;
    private Integer siteId;
    private String keyword;
    private String jobId;
    private String subjectId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

//    private Date gmtModified;

    /**
     * 完成标记1:完成分析0未完成2异常
     */
    private Integer status;

    @TableField(exist = false)
    private Integer site;
    /**
     * 修复后的文本
     */
    private String contentCorrect;

    /**
     * 修复后的标题
     */
    private String titleCorrect;

    /**
     * 没错0，1有错
     */
    private Integer correctStatus;
    private Integer dataType;

    /**
     * pass审核0，1错
     */
    private Integer spam;
    private Integer spamKeyword;
    private Integer readStatus;

    private Integer auditStatus;
    //审核状态操作者名称
    private String auditedStatusUpdateBy;
    private String auditedStatusUpdateUserId;
    private Integer userAudited;
    private Integer corrected;
    private Integer groupId;

    private Integer imgStatus;
    private Integer spamImg;
    private String userAuditHits;
    private Integer imgAudited;

    private String  titleCorrectWords;
    private String  contentCorrectWords;
    private String  titleCorrectWordsUser;
    private String  contentCorrectWordsUser;
    private Integer wordsAudited;
    private Integer spamWords;
    private Integer wordsAuditedUser;
    private Integer spamWordsUser;

    private Integer auditStatusAdmin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  auditedStatusUpdateDateAdmin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditedStatusUpdateDate;
    private String auditedStatusUpdateByAdmin;

    private Integer auditedStatusUpdateAdminId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reAuditedDate;
    private Integer pushStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pushTime;
    private String pushUserName;
    private String pushImg;

    String pushTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date pushPubtime;
    String pushErrorWords;
    String pushErrorType;

    String pushSiteName;
    String pushGroupId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date pushAuditTime;

    private Long pushWxUserId;
    private Date outsourcingTime;
    private String pushWxUserName;


    private Integer offerStatus;

    //错误上报相关
    private String offerErrorWords;
    private String offerCorrectWords;
    private String offerRemark;
    private Date  offerTime;
    private String offerUser;
    private String auditedStatusUpdateAdminName;

    private Integer pushMarkStatus;
    private String remark;

    @Excel(name = "推送类型")
    @TableField(exist = false)
    private String pushErrorTypeName;
    @Excel(name = "错误和修改意见", width = 100)
    @TableField(exist = false)
    private String ErrorAndSuggestions;
    @TableField(exist = false)
    private List<HeiMaErrorWordItem> errorWordItemList;

    public static String getErrorTypeNameByLevel(int level) {
        if (level == 1) {
            return "错误";
        } else if (level == 2) {
            return "疑错";
        } else if (level == 3) {
            return "严重错误";
        } else if (level == -3) {
            return "领导人排序错误";
        }
        return null;
    }

    public static String getPushErrorTypeName(String pushErrorType) {
        String pushErrorName = "";
        if ("1".equals(pushErrorType)) {
            pushErrorName = "意识形态表述";
        }
        if ("2".equals(pushErrorType)) {
            pushErrorName = "领导人表述";
        }
        if ("3".equals(pushErrorType)) {
            pushErrorName = "语句错误";
        }
        if ("4".equals(pushErrorType)) {
            pushErrorName = "违禁内容";
        }
        if ("5".equals(pushErrorType)) {
            pushErrorName = "其他";
        }
        return pushErrorName;
    }
}
