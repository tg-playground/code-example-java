package com.taogen.example.servlet.filter.filter.Wrapper;

import java.io.PrintWriter;
import java.io.Writer;

public class ResponseWriter extends PrintWriter
{
	private StringBuilder buffer;

	public ResponseWriter(Writer out)
	{
		super(out);
		buffer = new StringBuilder();
	}

	@Override
	public void write(char[] buf, int off, int len)
	{
		// super.write(buf, off, len);
		char[] dest = new char[len];
		System.arraycopy(buf, off, dest, 0, len);
		buffer.append(dest);
	}

	@Override
	public void write(char[] buf)
	{
		super.write(buf);
	}

	@Override
	public void write(int c)
	{
		super.write(c);
	}

	@Override
	public void write(String s, int off, int len)
	{
		super.write(s, off, len);
		buffer.append(s);
	}

	@Override
	public void write(String s)
	{
		super.write(s);
	}

	public String getContent()
	{
		return buffer.toString();
	}
}
