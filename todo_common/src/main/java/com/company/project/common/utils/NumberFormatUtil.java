package com.company.project.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatUtil {
	public static String format(BigDecimal decimal) {
		if (decimal == null) {
			return "0.00";
		}
		DecimalFormat format = new DecimalFormat("#0.00");
		return format.format(decimal);
	}

	public static String format(Double d) {
		if (d == null) {
			return "0.0";
		}
		return format(new BigDecimal(d));
	}
}
