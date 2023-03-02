package com.taogen.demo.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.taogen.demo.mybatisplus.dao.RecoveryDataMapper;
import com.taogen.demo.mybatisplus.entity.HeiMaErrorWordItem;
import com.taogen.demo.mybatisplus.entity.RecoveryData;
import com.taogen.demo.mybatisplus.util.JacksonJsonUtil;
import com.taogen.demo.mybatisplus.util.annotation.ExcelAnnotationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ${comments}
 *
 * @author hj
 * @date ${datetime}
 */
@Slf4j
@Service
public class RecoveryDataService extends ServiceImpl<RecoveryDataMapper, RecoveryData> {


    public static void main(String[] args) {
        String s = "a==》b aa=》bb  a1 ==》b1  习总书记   不忘初心牢记使命 a2=》 b2";
        System.out.println(getAllWordsFromPushErrorWords(s));
//        String regex = "(\\w+[ ]*[=]+》[ ]*\\w+)";
//        Matcher matcher = Pattern.compile(regex).matcher(s);
//        while (matcher.find()) {
//            System.out.println(matcher.groupCount()); // 1, 1
//            System.out.println(matcher.group()); // a Java developer, a Web developer
//            System.out.println(matcher.group(1)); // Java, Web
//        }
//        System.out.println();
    }

    public void extractWordsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String wordsLine = reader.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRecoveryDateToExcel(List<RecoveryData> recoveryDataList) throws IOException {
        XSSFWorkbook workbook = ExcelAnnotationUtil.generateWorkbook(
                RecoveryData.class, getAllPushErrorWorlds(null), "推送文章错误关键词");
        String outputDir = "D:/temp";
        String outputFileName = "pushErrorWords-%s.xlsx";
        outputFileName = String.format(outputFileName, System.currentTimeMillis());
        workbook.write(new FileOutputStream(outputDir + "/" + outputFileName));
        log.info("write to file: {}/{}", outputDir, outputFileName);
    }

    public void writeDeduplicateWordsToExcel(List<RecoveryData> allPushErrorWorlds) throws IOException {
        List<HeiMaErrorWordItem> heiMaErrorWordItems = new ArrayList<>();
        allPushErrorWorlds.stream()
                .filter(item -> item.getErrorWordItemList() != null && !item.getErrorWordItemList().isEmpty())
                .map(RecoveryData::getErrorWordItemList)
                .forEach(heiMaErrorWordItems::addAll);
        Set<String> wordUniqueSet = new HashSet<>();
        Iterator<HeiMaErrorWordItem> i = heiMaErrorWordItems.iterator();
        while (i.hasNext()) {
            HeiMaErrorWordItem item = i.next(); // must be called before you can call i.remove()
            if (!CollectionUtils.isEmpty(item.getCorWord())) {
                item.setSuggestions(String.join(",", item.getCorWord()));
            }
            if (wordUniqueSet.contains(item.getErrWord() + "-" + item.getSuggestions())) {
                i.remove();
            } else {
                wordUniqueSet.add(item.getErrWord() + "-" + item.getSuggestions());
            }
        }
        XSSFWorkbook workbook = ExcelAnnotationUtil.generateWorkbook(
                HeiMaErrorWordItem.class, heiMaErrorWordItems, "推送文章错误关键词");
        String outputDir = "D:/temp";
        String outputFileName = "pushErrorWords-%s.xlsx";
        outputFileName = String.format(outputFileName, System.currentTimeMillis());
        workbook.write(new FileOutputStream(outputDir + "/" + outputFileName));
        log.info("write to file: {}/{}", outputDir, outputFileName);
    }
    public void writeToFile(List<String> stringList) {
        if (stringList == null || stringList.isEmpty()) {
            return;
        }
        String outputDir = "D:/temp";
        String outputFileName = "pushErrorWords-%s.txt";
        outputFileName = String.format(outputFileName, System.currentTimeMillis());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputDir + "/" + outputFileName))) {
            for (String s : stringList) {
                bufferedWriter.write(s
                        .replace("\r\n", "  ")
                        .replace("\n", "  ")
                        .replace("\r", "  "));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("write to file: {}/{}", outputDir, outputFileName);
    }

    public List<RecoveryData> getAllPushErrorWorlds(Integer pushErrorType) {
        long startTime = System.currentTimeMillis();
        int maxTotalSize =  Integer.MAX_VALUE;
//        int maxTotalSize = 1000;
        int count = 0;
        int lastId = 0;
        int fetchSize = 100;
//        List<String> pushErrorWordsList = new ArrayList<>();
        List<RecoveryData> resultList = new ArrayList<>();
        List<RecoveryData> recoveryDataList = findRecoveryDataPage(lastId + 1, fetchSize, pushErrorType);
        if (CollectionUtils.isEmpty(recoveryDataList)) {
            return Collections.emptyList();
        }
        lastId = recoveryDataList.get(recoveryDataList.size() - 1).getId();
        count += recoveryDataList.size();
        recoveryDataList.forEach(item -> {
            item.setPushErrorTypeName(RecoveryData.getPushErrorTypeName(item.getPushErrorType()));
            item.setErrorAndSuggestions(getPushArticleErrorAndSuggestion(item));
        });
        resultList.addAll(recoveryDataList);
//        pushErrorWordsList.addAll(recoveryDataList.stream()
//                .map(RecoveryData::getPushErrorWords)
//                .collect(Collectors.toList()));
        while (recoveryDataList.size() >= fetchSize && count < maxTotalSize) {
            recoveryDataList = findRecoveryDataPage(lastId + 1, fetchSize, pushErrorType);
            lastId = recoveryDataList.get(recoveryDataList.size() - 1).getId();
            count += recoveryDataList.size();
            recoveryDataList.forEach(item -> {
                item.setPushErrorTypeName(RecoveryData.getPushErrorTypeName(item.getPushErrorType()));
                item.setErrorAndSuggestions(getPushArticleErrorAndSuggestion(item));
            });
            resultList.addAll(recoveryDataList);
//            pushErrorWordsList.addAll(recoveryDataList.stream()
//                    .map(RecoveryData::getPushErrorWords)
//                    .collect(Collectors.toList()));
        }
//        log.info("push error words size: " + pushErrorWordsList.size());
        log.info("Elapsed time: {} ms", System.currentTimeMillis() - startTime);
        return resultList;
    }


    private List<RecoveryData> findRecoveryDataPage(int start, int size, Integer pushErrorType) {
        LambdaQueryWrapper<RecoveryData> queryWrapper = new LambdaQueryWrapper<RecoveryData>()
                .select(RecoveryData::getId,
                        RecoveryData::getPushErrorWords,
                        RecoveryData::getPushErrorType,
                        RecoveryData::getContentCorrectWords)
                .isNotNull(RecoveryData::getPushErrorWords)
                .ne(RecoveryData::getPushErrorWords, "")
                .isNotNull(RecoveryData::getContentCorrectWords)
                .ne(RecoveryData::getContentCorrectWords, "")
                .ge(RecoveryData::getId, start)
                .orderByAsc(RecoveryData::getId)
                .last("limit " + size);
        if (pushErrorType != null) {
            queryWrapper.eq(RecoveryData::getPushErrorType, pushErrorType);
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    private static String getPushArticleErrorAndSuggestion(RecoveryData recoveryDataVo) {
        List<String> errorList = new ArrayList<>();
        List<HeiMaErrorWordItem> resultHeimaErrorWordList = new ArrayList<>();
        List<HeiMaErrorWordItem> heimaCheckResult = getHeimaCheckResult(recoveryDataVo);
        List<String> errorWordList = getAllWordsFromPushErrorWords(recoveryDataVo.getPushErrorWords());
        if (!CollectionUtils.isEmpty(heimaCheckResult) && !CollectionUtils.isEmpty(errorWordList)) {
            for (HeiMaErrorWordItem heimaErrorItem : heimaCheckResult) {
                Optional<String> errorWordItem = errorWordList.stream()
                        .filter(errorWord -> !heimaErrorItem.getErrWord().trim().equals("") &&
                                (heimaErrorItem.getErrWord().equals(errorWord) ||
                                heimaErrorItem.getErrWord().contains(errorWord) ||
                                        errorWord.contains(heimaErrorItem.getErrWord())))
                        .findFirst();
                if (errorWordItem.isPresent()) {
                    errorList.add(String.format("%s（%s）", heimaErrorItem.getErrWord(), String.join("/", heimaErrorItem.getCorWord())));
                    resultHeimaErrorWordList.add(heimaErrorItem);
                }
            }
            recoveryDataVo.setErrorWordItemList(resultHeimaErrorWordList);
        }
        return String.join(" ", errorList);
    }

    public static List<HeiMaErrorWordItem> getHeimaCheckResult(RecoveryData recoveryDataVo) {
        String contentCorrectWords = recoveryDataVo.getContentCorrectWords();
        if (StringUtils.isEmpty(contentCorrectWords)) {
            return Collections.emptyList();
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = JacksonJsonUtil.jsonStrToJsonObject(contentCorrectWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonNode.get("CheckResultList") == null) {
            return Collections.emptyList();
        }
        String checkResultList = jsonNode.get("CheckResultList").toString();
        List<HeiMaErrorWordItem> heiMaErrorWordItems = null;
        try {
            heiMaErrorWordItems = JacksonJsonUtil.jsonArrayStrToList(checkResultList, HeiMaErrorWordItem[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return heiMaErrorWordItems;
    }

    /**
     *
     * @param pushErrorWords
     * @return
     */
    @Deprecated
//    public static List<String> getPushErrorWordList(String pushErrorWords) {
//        if (StringUtils.isEmpty(pushErrorWords)) {
//            return Collections.emptyList();
//        }
//        List<String> result = new ArrayList<>();
//        String[] errorWords = pushErrorWords.split(" ");
//        String errorWordMark = "==》";
//        for (String errorWord : errorWords) {
//            errorWord = errorWord.trim();
//            if (StringUtils.hasLength(errorWord)) {
//                if (errorWord.contains(errorWordMark)) {
//                    try {
//                        result.add(errorWord.split(errorWordMark)[0]);
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        log.error("errorWord:{}", errorWord, e);
//                    }
//                } else {
//                    result.add(errorWord);
//                }
//            }
//        }
//        return result;
//    }

    public static List<String> getAllWordsFromPushErrorWords(String pushErrorWords) {
        List<String> result = new ArrayList<>();
        Arrays.stream(pushErrorWords.split(" "))
                .map(item -> Arrays.asList(item.split("》")))
                .forEach(result::addAll);
        result = result.stream()
                .map(item -> item.replace("=", ""))
                .filter(item -> !item.trim().isEmpty())
                .collect(Collectors.toList());
        return result;
    }
}
