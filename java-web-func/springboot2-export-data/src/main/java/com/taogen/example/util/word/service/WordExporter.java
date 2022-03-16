package com.taogen.example.util.word.service;

import javax.swing.text.Document;

/**
 * @author Taogen
 */
public interface WordExporter<T> {
    Document export(T t);
}
