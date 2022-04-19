package com.taogen.example.util.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * @author Taogen
 */
public interface WordExporter<T> {
    /**
     * @param t data object for building word file
     * @return
     */
    XWPFDocument export(T t);
}
