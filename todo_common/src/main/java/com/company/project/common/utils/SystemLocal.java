package com.company.project.common.utils;

import java.util.Locale;

public class SystemLocal {
	private Locale locale;

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void init(String key) {
		if ("CHINA".equalsIgnoreCase(key)) {
			this.locale = Locale.CHINA;
		}
		this.locale = Locale.CHINA;
	}

}
