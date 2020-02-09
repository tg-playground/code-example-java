package com.taogen.example.servlet.request._1requestmethods;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/httpRequestMethods")
public class HttpRequestMethodsServlet extends HttpServlet {
 
   private static final long serialVersionUID = 1L;

   public void init() throws ServletException {
      // Do required initialization
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      String result = "This is HTTP Get result page"; 
      out.println("<h1>" + result + "</h1>");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      String result = "This is HTTP Post result page"; 
      out.println("<h1>" + result + "</h1>");
   }

   public void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      String result = "This is HTTP Put result page"; 
      out.println("<h1>" + result + "</h1>");
   }

   public void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      String result = "This is HTTP Delete result page"; 
      out.println("<h1>" + result + "</h1>");
   }

   public void destroy() {
      // do nothing.
   }
}