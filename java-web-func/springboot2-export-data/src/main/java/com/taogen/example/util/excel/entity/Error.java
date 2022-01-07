package com.taogen.example.util.excel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Taogen
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Error {
    private String errorType;
    private List<String> errorWords;
}
