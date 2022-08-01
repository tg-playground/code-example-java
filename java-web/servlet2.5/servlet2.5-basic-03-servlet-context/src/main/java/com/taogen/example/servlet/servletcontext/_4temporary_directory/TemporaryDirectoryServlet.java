package com.taogen.example.servlet.servletcontext._4temporary_directory;

import com.taogen.example.servlet.servletcontext.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class TemporaryDirectoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File tmpDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        logger.info("servlet context temporary dir real path is {}", tmpDir.getAbsolutePath());

        File privFile = new File(tmpDir, "myTempFile");
        if (privFile.exists()) {
            privFile.delete();
            logger.info("file exist!");
        }
        privFile.createNewFile();
        logger.info("file new!");

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(privFile));
        ) {
            writer.write("hello world");
        } catch (Exception e) {
            logger.error(e.getClass().getName(), e.getMessage(), e);
        }
        logger.info("write file successfully!");

        logger.info("read file begin...");
        try (
                BufferedReader reader = new BufferedReader(new FileReader(privFile));
        ) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        } catch (Exception e) {
            logger.error(e.getClass().getName(), e.getMessage(), e);
        }
        logger.info("read file end!");

        privFile.delete();

        resp.setContentType("text/html");
        String result = "Hello by TemporaryDirectoryServlet! <br>Knowing what happen to check out the log messages of console.";
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, result);
    }
}
