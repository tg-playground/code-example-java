package com.taogen.example.util.word.demo;

import com.taogen.example.util.word.WordUtil;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author Taogen
 */
public class WordDemoAddPicture {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String outputFilePath = new StringBuilder()
                .append("C:\\Users\\Taogen\\Desktop\\hello")
                .append(System.currentTimeMillis())
                .append(".docx")
                .toString();
        String imageFilePath = "C:\\Users\\Taogen\\Desktop\\androidparty.png";
        try (
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));
                XWPFDocument document = new XWPFDocument();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(imageFilePath));
        ) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Hello World");
            run.setBold(true);
            run.setFontSize(20);

            XWPFParagraph imageParagraph = document.createParagraph();
            imageParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun imageRun = imageParagraph.createRun();
            Integer textPosition = 100; // 1/2nd points
            imageRun.setTextPosition(textPosition);
            byte[] imageBytes = IOUtils.toByteArray(bufferedInputStream);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();
            double scalePercent = 0.2;
            int scaledWidth = (int) (imageWidth * scalePercent);
            int scaledHeight = (int) (imageHeight * scalePercent);
            String fileName = imageFilePath.substring(imageFilePath.lastIndexOf(File.separator) + 1);
            imageRun.addPicture(new ByteArrayInputStream(imageBytes),
                    WordUtil.getImageFormat(fileName),
                    fileName,
                    Units.toEMU(scaledWidth),
                    Units.toEMU(scaledHeight));
            document.write(bufferedOutputStream);
        }
        System.out.println("文件生成成功，路径为：" + outputFilePath);
    }
}
