package com.company.project.common.utils;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

public final class LongDataFormatUtil {
	public final static String formatDate(Long time) {
		return format(time, "yyyy-MM-dd");
	}

	public final static String formatDateTime(Long time) {
		return format(time, "yyyy-MM-dd HH:mm:ss");
	}

	public final static String format(Long time, String format) {
		if (time == null || time.longValue() < 1) {
			return "";
		}
		return DateUtil.format(new Date(time.longValue()), format);
	}
}
