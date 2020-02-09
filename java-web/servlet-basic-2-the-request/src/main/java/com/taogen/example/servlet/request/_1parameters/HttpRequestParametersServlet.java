package com.taogen.example.servlet.request._1parameters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONObject;

@WebServlet("/httpRequestParams")
public class HttpRequestParametersServlet extends HttpServlet {
 
   private static final long serialVersionUID = 1L;

   public void init() throws ServletException {
      // Do required initialization
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      StringBuilder result = new StringBuilder("This is HTTP Get result page. \nYour parameter name is "); 
      String name = request.getParameter("name");
      result.append(name);
      out.println("<h1>" + result.toString() + "</h1>");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      StringBuilder result = new StringBuilder("This is HTTP Post result page. \nYour parameter name is "); 
      String name = getParameterValue("name", request);

      result.append(name);
      out.println("<h1>" + result.toString() + "</h1>");
   }
   
   public String getParameterValue(String key, HttpServletRequest request){
      String value = null;
      String requestContentType = request.getContentType();
      System.out.println("Your request contentType is : " + requestContentType);   
      
      StringBuilder sb = new StringBuilder();
      String line = null;
      if ("application/x-www-form-urlencoded".equals(requestContentType)){
         value = request.getParameter(key);
      }else{
         try(BufferedReader reader = request.getReader();){
            while((line = reader.readLine()) != null){
               sb.append(line);
            }
            System.out.println("Your json parameter is : " + sb.toString());
            JSONObject jsonObject = new JSONObject(sb.toString());
            value = jsonObject.getString(key);
         }catch (Exception e) {
            e.printStackTrace();
         }
      }
      return value;
   }   

   public void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      StringBuilder result = new StringBuilder("This is HTTP Put result page. \nYour parameter name is "); 
      String name = request.getParameter("name");
      result.append(name);
      out.println("<h1>" + result.toString() + "</h1>");
   }

   public void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      StringBuilder result = new StringBuilder("This is HTTP Delete result page. \nYour parameter name is "); 
      String name = request.getParameter("name");
      result.append(name);
      out.println("<h1>" + result.toString() + "</h1>");
   }

   public void destroy() {
      // do nothing.
   }
}