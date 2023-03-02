package com.taogen.example.servlet.filter.filter.Wrapper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
	public static boolean isEmpty(String str)
	{
		return (str == null || str.length() == 0) ? true : false;
	}
	
	public static boolean isEmpty(String[] arr)
	{
		return (arr == null || arr.length == 0) ? true : false;
	}
	
	public static String nullToEmpty(String string)
	  {
	    return string == null ? "" : string;
	  }
	
	public static int str2Int(String str, int defaults)
	{
		if (str == null)
		{
			return defaults;
        }
        try
        {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            return defaults;
        }
    }
	
	public static float str2Float(String str, float defaults)
	{
		if (str == null)
		{
			return defaults;
        }
        try
        {
            return Float.parseFloat(str);
        }
        catch (NumberFormatException e)
        {
            return defaults;
        }
    }
	
	public static boolean str2Boolean(String str, boolean defaults)
	{
		if (str == null)
		{
			return defaults;
        }
        try
        {
            return Boolean.parseBoolean(str);
        }
        catch (NumberFormatException e)
        {
            return defaults;
        }
    }
	
	public static double str2Double(String str, double defaults)
	{
		if (str == null)
		{
			return defaults;
        }
        try
        {
            return Double.parseDouble(str);
        }
        catch (NumberFormatException e)
        {
            return defaults;
        }
    }
	
	public static long str2Long(String str, long defaults)
	{
		if (str == null)
		{
			return defaults;
        }
        try
        {
            return Long.parseLong(str);
        }
        catch (NumberFormatException e)
        {
            return defaults;
        }
    }

	// 1、\\w表示@之前至少要输入一个匹配字母或数字或下划线
	// 2、(\\w+\\.)表示域名, 可以出现一次或两次或者三次"."
	public static final String REGEX_EMAIL = "(\\w+\\.){0,1}\\w+@(\\w+\\.){1,3}\\w+";
	public static boolean isEmail(String email)
	{
		return isMatch(email, REGEX_EMAIL);
	}

    //当前运营商号段分配
    //中国移动号段 1340-1348 135 136 137 138 139 150 151 152 157 158 159 187 188 147
    //中国联通号段 130 131 132 155 156 185 186 145
    //中国电信号段 133 1349 153 180 189
	public static final String REGEX_MOBILE = "1[3-9]{1}\\d{9}";
	public static boolean isMobile(String mobile)
	{
		return isMatch(mobile, REGEX_MOBILE);
	}
	
	public static String encodeMobile(String mobile)
	{
		if (isMobile(mobile))
		{
			StringBuffer sb = new StringBuffer();
			sb.append(mobile.substring(0, 3));
			sb.append("****");
			sb.append(mobile.substring(7));
			
			mobile = sb.toString();
		}
		return mobile;
	}
	
	public static String encodeBankCard(String card)
	{
		if (! isEmpty(card))
		{
			StringBuffer sb = new StringBuffer();
			sb.append(card.substring(0, 4));
			sb.append(" **** **** ");
			sb.append(card.substring(card.length() - 4));
			
			card = sb.toString();
		}
		return card;
	}
	
	public static String controlTextLength(String string)
	{
		if(string.length() > 15)
		{
			string = new String(string.substring(0, 14)+"......");
		}
		return string;
	}
	
	public static final String REGEX_PHONE = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
	public static boolean isPhone(String phone)
    {
    	Pattern p = Pattern.compile(REGEX_PHONE);
    	Matcher m = p.matcher(phone);
    	
    	return m.matches();
    } 
	
	public static boolean isMatch(String email, String regex)
	{
		Pattern pattern = Pattern.compile(regex);
		boolean flag = false;
		if(email != null)
		{
			Matcher matcher = pattern.matcher(email);
			flag = matcher.matches();
		}
		return flag;
	}
	

	/**
	 * @Description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集）
	 * @param str 待转换字符串
	 * @return 转换后字符串
	 */
	public static String emojiConvert(String str)
	{
		if (str == null)
		{
			return null;
		}
		String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find())
		{
			try
			{
//				matcher.appendReplacement(sb, "[["
//						+ URLEncoder.encode(matcher.group(1), "UTF-8") + "]]");
				matcher.appendReplacement(sb, URLEncoder.encode(matcher.group(1), "UTF-8"));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
	 * @param str 转换后的字符串
	 * @return 转换前的字符串
	 */
	public static String emojiRecovery(String str)
	{
		if (str == null)
		{
			return null;
		}
//		String patternString = "\\[\\[(.*?)\\]\\]";
//
//		Pattern pattern = Pattern.compile(patternString);
//		Matcher matcher = pattern.matcher(str);
//
//		StringBuffer sb = new StringBuffer();
//		while (matcher.find())
//		{
//			
//			try
//			{
//				matcher.appendReplacement(sb, URLDecoder.decode(matcher.group(1), "UTF-8"));
//			}
//			catch (UnsupportedEncodingException e)
//			{
//				e.printStackTrace();
//			}
//			
//		}
//		matcher.appendTail(sb);
//		return sb.toString();
		 String output = null;
		try
		{
			output = URLDecoder.decode(str, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} 
		 return output;
	}

	public static String toNotNull(String source)
	{
		if (source != null)
		{
			return source;
		}
		else
		{
			return "";
		}
	}
	
	public static String builderString(String... strs)
	{
		StringBuilder sb = new StringBuilder();
		if (strs != null)
		{
			for (int i = 0; i < strs.length; i++)
			{
				sb.append(strs[i]);
			}
		}
		return sb.toString();
	}
}
