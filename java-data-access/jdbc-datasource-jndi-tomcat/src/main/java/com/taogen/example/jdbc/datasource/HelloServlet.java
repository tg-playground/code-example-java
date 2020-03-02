package com.taogen.example.jdbc.datasource;

import com.taogen.example.jdbc.datasource.util.DataSourceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet() called.");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Hello!</h3>");
        resp.flushBuffer();
        printDatabaseTable(out);
    }

    private void printDatabaseTable(PrintWriter out) {
        Connection connection = DataSourceUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            if (resultSet != null) {
                out.println("user database table: ");
                out.println("<table border='1'>");
                while (resultSet.next()) {
                    out.print("<tr>");
                    out.print("<td>" + resultSet.getInt("id") + "</td>");
                    out.print("<td>" + resultSet.getString("name") + "</td>");
                    out.print("<td>" + resultSet.getInt("age") + "</td>");
                    out.print("</tr>");
                }
                out.println("</table>");
            }
        } catch (SQLException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }
}
