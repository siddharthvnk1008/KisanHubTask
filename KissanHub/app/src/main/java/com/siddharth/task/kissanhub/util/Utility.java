package com.siddharth.task.kissanhub.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Siddharth Vanakudari on 27/12/18.
 */
public class Utility {
	static Map<String,String>  MONTH_MAP;

	static
	{
		MONTH_MAP = new HashMap<String, String>();
		MONTH_MAP.put("1", "Jan");
		MONTH_MAP.put("2", "Feb");
		MONTH_MAP.put("3", "Mar");
		MONTH_MAP.put("4", "Apr");

		MONTH_MAP.put("5", "May");
		MONTH_MAP.put("6", "Jun");
		MONTH_MAP.put("7", "Jul");
		MONTH_MAP.put("8", "Aug");

		MONTH_MAP.put("9", "Sep");
		MONTH_MAP.put("10", "Oct");
		MONTH_MAP.put("11", "Nov");
		MONTH_MAP.put("12", "Dec");
	}

	public static String getMonth(String str){
		return MONTH_MAP.get(str);
	}
}
