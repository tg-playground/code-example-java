package com.taogen.example.servlet.filter.filter.Wrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.Charsets;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

public class ParamUtils {
    public static String getParameter(HttpServletRequest request, String key) {
        return request.getParameter(key);
//		return request.getHeader(key);
    }

    public static int getIntParameter(HttpServletRequest request, String key) {
        return getIntParameter(request, key, -1);
    }

    public static int getIntParameter(HttpServletRequest request, String key, int def) {
        return StringUtils.str2Int(getParameter(request, key), def);
    }

    public static boolean getBooleanParameter(HttpServletRequest request, String key) {
        return getBooleanParameter(request, key, false);
    }

    public static Boolean getBooleanParameter(HttpServletRequest request, String key, Boolean def) {
        String value = getParameter(request, key);

        if (!StringUtils.isEmpty(value)) {
            try {
                int i = Integer.parseInt(value);
                def = (i == 0) ? false : true;
            } catch (NumberFormatException exp) {
                def = Boolean.parseBoolean(value);
            }
        }
        return def;
    }

    public static long getLongParameter(HttpServletRequest request, String key) {
        return getLongParameter(request, key, -1);
    }

    public static long getLongParameter(HttpServletRequest request, String key, long def) {
        String value = request.getParameter(key);
        if (!StringUtils.isEmpty(value)) {
            try {
                def = Long.valueOf(value);
            } catch (NumberFormatException exp) {
                exp.printStackTrace();
            }
        }
        return def;
    }

    public static double getDoubleParameter(HttpServletRequest request, String key) {
        return getDoubleParameter(request, key, -1);
    }

    public static double getDoubleParameter(HttpServletRequest request, String key, double def) {
        String value = request.getParameter(key);
        if (!StringUtils.isEmpty(value)) {
            try {
                def = Double.valueOf(value);
            } catch (NumberFormatException exp) {
                exp.printStackTrace();
            }
        }
        return def;
    }

    /**
     * 获取request中的所有参数JSON字符串
     *
     * @param request
     * @return
     */
    public static String getRequestParamsJSON(HttpServletRequest req) {
        if (req == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        @SuppressWarnings("unchecked")
        Map<String, String[]> params = req.getParameterMap();
        if (!params.isEmpty()) {
            sb.append("{");
            for (String key : params.keySet()) {

                sb.append(key);
                sb.append(":");
                String values[] = params.get(key);
                if (values.length > 1) {
                    sb.append("[");
                }
                for (String value : values) {
                    sb.append(value);
                    if (values.length > 1) {
                        sb.append(",");
                    }
                }
                if (values.length > 1) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                if (values.length > 1) {
                    sb.append("]");
                }
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("}");
        }
        String requestParamsJSON = sb.toString();
        return requestParamsJSON;
    }

    /**
     * 获取 response 中的 body
     *
     * @param responseWrapper
     * @return
     */
    public static String getResponseBodyString(ResponseWrapper responseWrapper) {
        if (responseWrapper == null) {
            return null;
        }
        String responseBody = null;
        ResponseWriter myWriter = responseWrapper.getMyWriter();
        if (myWriter != null) {
            responseBody = myWriter.getContent();
        }
        return responseBody;
    }

    /**
     * 获取 HTTP 请求所有参数JSON字符串 content-type=form-data
     *
     * @param req
     * @return
     */
    public static String getRequestFormDataJSON(HttpServletRequest req) {
        String result = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        StringBuilder sb = new StringBuilder();
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> items = upload.parseRequest(req);
                sb.append("{");
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        sb.append(item.getFieldName());
                        sb.append(":");
                        sb.append(item.getString(Charsets.UTF_8.name()));
                        sb.append(",");
                    } else {
                        sb.append(item.getFieldName());
                        sb.append(":");
                        sb.append(item.getName());
                        sb.append(",");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("}");
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
        return result;
    }

    /**
     * 获取 HTTP 请求所有参数JOSN字符串 content-type=form-data
     *
     * @param req
     * @return
     */
    public static String getRequestFormDataJSONByWrapper(RequestWrapper req) {
        String result = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        StringBuilder sb = new StringBuilder();
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> items = upload.parseRequest(req);
                sb.append("{");
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        sb.append(item.getFieldName());
                        sb.append(":");
                        sb.append(item.getString(Charsets.UTF_8.name()));
                        sb.append(",");
                    } else {
                        sb.append(item.getFieldName());
                        sb.append(":");
                        sb.append(item.getName());
                        sb.append(",");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("}");
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
        return result;
    }

    /**
     * 获取 HTTP 请求所有参数 content-type=text/plain
     *
     * @param req
     * @return
     */
    public static String getRequestRawData(HttpServletRequest req) {
        String result = null;
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = buffer.toString();
        return result;
    }

    /**
     * 获取 HTTP 请求所有参数 content-type=text/plain
     *
     * @param req
     * @return
     */
    public static String getRequestRawDataByWrapper(RequestWrapper req) {
        String result = null;
        StringBuilder buffer = new StringBuilder();
        try {
            InputStream in = req.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = buffer.toString();
        return result;
    }
}
