package com.taogen.example.util.excel;

import com.taogen.example.util.excel.entity.Article;
import com.taogen.example.util.excel.util.ExcelBuildUtil;
import com.taogen.example.util.excel.vo.MyCellPosition;
import com.taogen.example.util.excel.vo.MyExcelCell;
import com.taogen.example.util.excel.vo.MyExcelRow;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Taogen
 */
@Log4j2
public class ExcelDemoBuildMultilevelByWrapperClass {
    public static void main(String[] args) throws IOException {
        generateThreeLevelsExcel(Article.getData());
    }

    private static void generateThreeLevelsExcel(List<Article> articles) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet");

        int rowNum = 0;
        MyExcelRow topRow = MyExcelRow.builder()
                .startRow(rowNum)
                .endRow(rowNum)
                .cells(Arrays.asList(MyExcelCell.builder()
                        .text("互联网媒体内容审核报告")
                        .cellPosition(new MyCellPosition(rowNum, rowNum, 0, 5))
                        .build()))
                .build();
        ExcelBuildUtil.addRowToExcel(sheet, topRow);
        rowNum += (topRow.getEndRow() - topRow.getStartRow() + 1);

        List<String> titles = Arrays.asList("序号", "站点", "发布时间", "标题", "错误类型", "错误和修改建议");
        ExcelBuildUtil.generateOneLineRowByValues(sheet, rowNum, titles);
        rowNum++;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            int totalLinesOfCurrentRow = 0;
            for (int j = 0; j < article.getErrors().size(); j++) {
                for (int k = 0; k < article.getErrors().get(j).getErrorWords().size(); k++) {
                    totalLinesOfCurrentRow++;
                }
            }
            int endRow = rowNum + totalLinesOfCurrentRow - 1;
            List<MyExcelCell> cells = new ArrayList<>();
            // first level
            int columnNum = 0;
            cells.add(MyExcelCell.builder()
                    .text(article.getId().toString())
                    .cellPosition(new MyCellPosition(rowNum, endRow, columnNum, columnNum++))
                    .build());
            cells.add(MyExcelCell.builder()
                    .text(article.getSite())
                    .cellPosition(new MyCellPosition(rowNum, endRow, columnNum, columnNum++))
                    .build());
            cells.add(MyExcelCell.builder()
                    .text(article.getPublishTime())
                    .cellPosition(new MyCellPosition(rowNum, endRow, columnNum, columnNum++))
                    .build());
            cells.add(MyExcelCell.builder()
                    .text(article.getTitle())
                    .cellPosition(new MyCellPosition(rowNum, endRow, columnNum, columnNum++))
                    .build());
            int errorTypeStartRowNum = rowNum;
            int errorTypeEndRowNum = rowNum;
            for (int j = 0; j < article.getErrors().size(); j++) {
                int currentColumnNum = columnNum;
                for (int k = 0; k < article.getErrors().get(j).getErrorWords().size(); k++) {
                    errorTypeEndRowNum++;
                    // second level
                    String errorWord = article.getErrors().get(j).getErrorWords().get(k);
                    cells.add(MyExcelCell.builder()
                            .text(errorWord)
                            .cellPosition(new MyCellPosition(errorTypeEndRowNum - 1, errorTypeEndRowNum - 1, currentColumnNum + 1, currentColumnNum + 1))
                            .build());
                }
                // third level
                String errorType = article.getErrors().get(j).getErrorType();
                MyExcelCell errorTypeCell = MyExcelCell.builder()
                        .text(errorType)
                        .cellPosition(new MyCellPosition(
                                errorTypeStartRowNum, errorTypeEndRowNum - 1, currentColumnNum, currentColumnNum))
                        .build();
                cells.add(errorTypeCell);
                log.debug("errorTypeCell: {}", errorTypeCell);
                errorTypeStartRowNum = errorTypeEndRowNum;
            }
            MyExcelRow articleRow = MyExcelRow.builder()
                    .startRow(rowNum)
                    .endRow(rowNum + totalLinesOfCurrentRow - 1)
                    .cells(cells)
                    .build();
            log.debug("articleRow: {}", articleRow);
            ExcelBuildUtil.addRowToExcel(sheet, articleRow);
            rowNum += totalLinesOfCurrentRow;
        }

        String outputFilePath = "C:\\Users\\Taogen\\Desktop\\test" + System.currentTimeMillis() + ".xlsx";
        workbook.write(new FileOutputStream(outputFilePath));
        System.out.println("生成excel文件成功，路径为：" + outputFilePath);
    }
}
