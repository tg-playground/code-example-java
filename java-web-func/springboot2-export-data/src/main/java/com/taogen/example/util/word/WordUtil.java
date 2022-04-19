package com.taogen.example.util.word;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * @author Taogen
 */
public class WordUtil {

    public static XWPFRun addPictureToParagraph(XWPFParagraph paragraph,
                                                InputStream inputStream,
                                                String fileName,
                                                Integer pictureType,
                                                Integer width,
                                                Integer height) throws IOException, InvalidFormatException {
        XWPFRun imageRun = paragraph.createRun();
        imageRun.addPicture(inputStream, pictureType, fileName,
                Units.toEMU(width), Units.toEMU(height));
        return imageRun;
    }

    public static void addCustomHeadingStyle(XWPFDocument docxDocument,
                                             String strStyleId,
                                             int headingLevel) {
        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(strStyleId);

        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal(strStyleId);
        ctStyle.setName(styleName);

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(headingLevel));

        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority(indentNumber);

        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed(onoffnull);

        // style shows up in the formats bar
        ctStyle.setQFormat(onoffnull);

        // style defines a heading of the given level
        CTPPrGeneral ppr = CTPPrGeneral.Factory.newInstance();
        ppr.setOutlineLvl(indentNumber);
        ctStyle.setPPr(ppr);

        XWPFStyle style = new XWPFStyle(ctStyle);

        // is a null op if already defined
        XWPFStyles styles = docxDocument.createStyles();

        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);
    }

    public static void addBlankLineParagraph(XWPFDocument document, int blankLines) {
        XWPFParagraph paragraph = document.createParagraph();
        addBreak(paragraph.createRun(), blankLines - 1);
    }

    private static void addBreak(XWPFRun run, int blankLines) {
        for (int i = 0; i < blankLines; i++) {
            run.addBreak();
        }
    }
}
