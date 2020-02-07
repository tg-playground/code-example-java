package com.taogen.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

@WebServlet("/serverPush")
public class ServerPushServlet extends HttpServlet {
    private static final long serialVersionUID = -3462096228274971485L;
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        PushBuilder pushBuilder = req.newPushBuilder();
        String picturePath = "images/tinyAim.jpg";
        pushBuilder.path(picturePath)
                .addHeader("content-type", "image/png")
                .push(); 
        try(PrintWriter respWriter = resp.getWriter()){
            respWriter.write("<html><img src='" + picturePath + "'></html>");
        }
    }
}
