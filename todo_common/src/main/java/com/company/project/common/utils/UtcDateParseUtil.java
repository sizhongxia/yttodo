package com.company.project.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UtcDateParseUtil {
	public static Date parse(String utcTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date after = null;
		try {
			after = df.parse(utcTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return after;
	}
}
