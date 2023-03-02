package com.taogen.demo.mybatisplus.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taogen.demo.mybatisplus.util.annotation.Excel;
import lombok.Data;

import java.util.List;

/**
 * @author Taogen
 */
@Data
public class HeiMaErrorWordItem {
    @JsonProperty("Pos")
    private Integer pos;

    private Integer level;

    @Excel(name = "错误词")
    @JsonProperty("ErrWord")
    private String errWord;

    @JsonProperty("CorWord")
    private List<String> corWord;

    @Excel(name = "修改建议")
    private String suggestions;
}
