package com.taogen.demo.mybatisplus.service;

import com.taogen.demo.mybatisplus.SpringBootWithMyBatisPlusApp;
import com.taogen.demo.mybatisplus.entity.RecoveryData;
import com.taogen.demo.mybatisplus.util.annotation.ExcelAnnotationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SpringBootWithMyBatisPlusApp.class})
@Slf4j
class RecoveryDataServiceTest {

    @Autowired
    private RecoveryDataService recoveryDataService;

    @Test
    void getAllPushErrorWorlds() throws IOException {
        int pushErrorType = 5;
        recoveryDataService.writeDeduplicateWordsToExcel(recoveryDataService.getAllPushErrorWorlds(pushErrorType));
//        recoveryDataService.writeToFile(recoveryDataService.getAllPushErrorWorlds());
    }
}
