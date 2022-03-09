package com.taogen.example.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
public class HttpRequestUtil {

    public static String multiValueMapToQueryString(Map<String, List<String>> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            List<String> values = entry.getValue();
            if (values != null) {
                for (int i = 0; i < values.size(); i++) {
                    sb.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(values.get(i).toString(), StandardCharsets.UTF_8));
                    if (i < values.size() - 1) {
                        sb.append("&");
                    }
                }
            }
        }
        return sb.toString();
    }

    public static byte[] multiValueMapToMultipartData(Map<String, List<Object>> data, String boundary) throws IOException {
        if (data == null || data.isEmpty()) {
            return new byte[0];
        }
        // Result request body
        List<byte[]> byteArrays = new ArrayList<>();

        // Separator with boundary
        byte[] separator = (new StringBuilder()
                .append("--")
                .append(boundary)
                .append("\r\nContent-Disposition: form-data; name=")
                .toString()
                .getBytes(StandardCharsets.UTF_8));

        // Iterating over data parts
        for (Map.Entry<String, List<Object>> entry : data.entrySet()) {

            List<Object> values = entry.getValue();
            if (values.size() > 0) {
                for (Object value : values) {
                    // Opening boundary
                    byteArrays.add(separator);

                    // If value is type of Path (file) append content type with file name and file binaries, otherwise simply append key=value
                    if (value instanceof File) {
                        var path = ((File) value).toPath();
                        String mimeType = Files.probeContentType(path);
                        byteArrays.add(new StringBuilder()
                                .append("\"")
                                .append(entry.getKey())
                                .append("\"; filename=\"")
                                .append(path.getFileName())
                                .append("\"\r\nContent-Type: ")
                                .append(mimeType)
                                .append("\r\n\r\n")
                                .toString()
                                .getBytes(StandardCharsets.UTF_8));
                        byteArrays.add(Files.readAllBytes(path));
                        byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
                    } else {
                        byteArrays.add(new StringBuilder()
                                .append("\"")
                                .append(entry.getKey())
                                .append("\"\r\n\r\n")
                                .append(value)
                                .append("\r\n")
                                .toString()
                                .getBytes(StandardCharsets.UTF_8));
                    }
                }
            }
        }

        // Closing boundary
        byteArrays.add(new StringBuilder()
                .append("--")
                .append(boundary)
                .append("--")
                .toString()
                .getBytes(StandardCharsets.UTF_8));
        Integer byteLen = byteArrays.stream().map(item -> item.length).collect(Collectors.summingInt(Integer::intValue));
        byte[] result = new byte[byteLen];
        ByteBuffer buff = ByteBuffer.wrap(result);
        for (byte[] byteArray : byteArrays) {
            buff.put(byteArray);
        }
        return buff.array();
    }
}
