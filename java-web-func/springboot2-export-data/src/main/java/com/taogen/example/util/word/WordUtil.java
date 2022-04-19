package com.taogen.example.util.word;

import com.taogen.example.util.word.build.vo.ImageParagraph;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;

/**
 * @author Taogen
 */
public class WordUtil {
    public static void addPictureToDocument(XWPFDocument document,
                                            ImageParagraph imageParagraph)
            throws IOException, InvalidFormatException {
        XWPFParagraph image = document.createParagraph();
        if (imageParagraph.getParagraphAlignment() != null) {
            image.setAlignment(imageParagraph.getParagraphAlignment());
        }
        XWPFRun imageRun = image.createRun();
        if (imageParagraph.getTextPosition() != null) {
            imageRun.setTextPosition(imageParagraph.getTextPosition());
        }
        imageRun.addPicture(imageParagraph.getInputStream(),
                imageParagraph.getPictureType(),
                imageParagraph.getFilename(),
                Units.toEMU(imageParagraph.getWidth()),
                Units.toEMU(imageParagraph.getHeight()));
    }

}
