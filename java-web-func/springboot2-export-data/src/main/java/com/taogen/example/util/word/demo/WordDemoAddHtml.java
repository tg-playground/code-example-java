package com.taogen.example.util.word.demo;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTBodyImpl;

import javax.xml.namespace.QName;
import java.io.*;

/**
 * @author Taogen
 */
public class WordDemoAddHtml {
    public static void main(String[] args) throws Exception  {
        XWPFDocument doc = new XWPFDocument();
        doc.createParagraph().createRun().setText("AltChunk below:");
        addHtml(doc, "chunk0", formatHtml("<p style=\"color:red;\">This is a paragraph.</p>"));
        addHtml(doc,"chunk1","<!DOCTYPE html><html><head><style></style><title></title></head><body><b>Hello World!</b></body></html>");
        addHtml(doc,"chunk2","<!DOCTYPE html><html><head><style></style><title></title></head><body><p>aaa</p><p><img src=\"https://yuwoyg.oss-cn-hangzhou.aliyuncs.com/yuqing_project/reference/reference_img/dfd283a0-46af-428c-8826-8c27f45a9b4b.png\" style=\"max-width:100%;\"><br></p><p>123</p></body></html>");
        doc.createParagraph().createRun().setText("END");
        String outputFilePath = new StringBuilder()
                .append("C:\\Users\\Taogen\\Desktop\\hello")
                .append(System.currentTimeMillis())
                .append(".docx")
                .toString();
        FileOutputStream out = new FileOutputStream(outputFilePath);
        doc.write(out);
        System.out.println("文档生成成功，路径为：" + outputFilePath);
    }

    private static String formatHtml(String html) {
        String htmlTemplate = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style type=\"text/css\"></style></head><body>%s</body></html>";
        if (!html.contains("<html>") || !html.contains("</html>")) {
            html = String.format(htmlTemplate, html);
        }
        return html;
    }

    static void addHtml(XWPFDocument doc, String id,String html) throws Exception {
        OPCPackage oPCPackage = doc.getPackage();
        PackagePartName partName = PackagingURIHelper.createPartName("/word/" + id + ".html");
        PackagePart part = oPCPackage.createPart(partName, "text/html");
        class HtmlRelation extends POIXMLRelation {
            private HtmlRelation() {
                super(  "text/html",
                        "http://schemas.openxmlformats.org/officeDocument/2006/relationships/aFChunk",
                        "/word/htmlDoc#.html");
            }
        }
        class HtmlDocumentPart extends POIXMLDocumentPart {
            private HtmlDocumentPart(PackagePart part) throws Exception {
                super(part);
            }

            @Override
            protected void commit() throws IOException {
                try (OutputStream out = part.getOutputStream()) {
                    try (Writer writer = new OutputStreamWriter(out, "UTF-8")) {
                        writer.write(html);
                    }
                }
            }
        };
        HtmlDocumentPart documentPart = new HtmlDocumentPart(part);
        doc.addRelation(id, new HtmlRelation(), documentPart);
        CTBodyImpl b = (CTBodyImpl) doc.getDocument().getBody();
        QName ALTCHUNK = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "altChunk");
        XmlComplexContentImpl altchunk = (XmlComplexContentImpl) b.get_store().add_element_user(ALTCHUNK);
        QName ID = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
        SimpleValue target = (SimpleValue)altchunk.get_store().add_attribute_user(ID);
        target.setStringValue(id);
    }
}
