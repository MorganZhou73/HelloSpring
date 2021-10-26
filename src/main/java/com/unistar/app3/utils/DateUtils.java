package com.unistar.app3.utils;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date; 
import java.util.TimeZone;

public class DateUtils {

	public static String getUTCString(Date date) {
		// ISO 8601 format: Z: RFC 822 Time Zone (-0800) ; X: ISO 8601 Time Zone (-08, -0800, -8:00)
		SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		frm.setTimeZone(TimeZone.getTimeZone("UTC"));
		return frm.format(date);
	}

	public static String getNowString() {
		return getUTCString(Calendar.getInstance().getTime());
	}

	/**
	* parse ISO 8601 time zone format string.
	• e.g. "2021-04-16T16:00:47Z" , "2021-04-16T16:00-05", "2021-04-16T16:00-05:00"
	* , "2021-04-16T16:00-0500" , "2021-04-16T16:00Z"
	* @param isoformatDate
	* @return
	* @throws ParseException
	*/
	public static Date parseISODate(String isoformatDate) throws ParseException {
		SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"); 
		return frm.parse(isoformatDate);
	}

	public static Date now() {
		return Calendar.getInstance().getTime();
	}
}
