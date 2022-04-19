package com.taogen.example.util.word.build.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import java.io.InputStream;

/**
 * @author Taogen
 */
@Data
@Builder
public class ImageParagraph {
    private ParagraphAlignment paragraphAlignment = ParagraphAlignment.CENTER;

    /**
     * the amount by which text shall be raised or lowered for this picture
     * unit: 1/2nd point
     */
    private Integer textPosition = 0;

    private InputStream inputStream;

    private String filename;

    /**
     * e.g. XWPFDocument.PICTURE_TYPE_PNG
     */
    private Integer pictureType;

    /**
     * unit: 1 point
     */
    private Integer width;

    /**
     * unit: 1 point
     */
    private Integer height;
}
