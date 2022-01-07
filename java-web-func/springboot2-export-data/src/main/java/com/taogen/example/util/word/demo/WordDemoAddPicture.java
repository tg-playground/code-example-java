package com.taogen.example.util.word.demo;

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
                    getImageFormat(fileName),
                    fileName,
                    Units.toEMU(scaledWidth),
                    Units.toEMU(scaledHeight));
            document.write(bufferedOutputStream);
        }
        System.out.println("文件生成成功，路径为：" + outputFilePath);
    }

    private static int getImageFormat(String imgFileName) {
        int format;
        if (imgFileName.endsWith(".emf"))
            format = XWPFDocument.PICTURE_TYPE_EMF;
        else if (imgFileName.endsWith(".wmf"))
            format = XWPFDocument.PICTURE_TYPE_WMF;
        else if (imgFileName.endsWith(".pict"))
            format = XWPFDocument.PICTURE_TYPE_PICT;
        else if (imgFileName.endsWith(".jpeg") || imgFileName.endsWith(".jpg"))
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if (imgFileName.endsWith(".png"))
            format = XWPFDocument.PICTURE_TYPE_PNG;
        else if (imgFileName.endsWith(".dib"))
            format = XWPFDocument.PICTURE_TYPE_DIB;
        else if (imgFileName.endsWith(".gif"))
            format = XWPFDocument.PICTURE_TYPE_GIF;
        else if (imgFileName.endsWith(".tiff"))
            format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if (imgFileName.endsWith(".eps"))
            format = XWPFDocument.PICTURE_TYPE_EPS;
        else if (imgFileName.endsWith(".bmp"))
            format = XWPFDocument.PICTURE_TYPE_BMP;
        else if (imgFileName.endsWith(".wpg"))
            format = XWPFDocument.PICTURE_TYPE_WPG;
        else {
            return 0;
        }
        return format;
    }
}
