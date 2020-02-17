package com.taogen.example.servlet.filter.filter.Wrapper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper
{
	private ResponseWriter myWriter;

	public ResponseWrapper(HttpServletResponse response)
	{
		super(response);
	}

	@Override
	public PrintWriter getWriter() throws IOException
	{
		myWriter = new ResponseWriter(super.getWriter());
		return myWriter;
	}

	public ResponseWriter getMyWriter()
	{
		return myWriter;
	}
}
