package com.jitendra.logasservice.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtility {

	@SuppressWarnings("deprecation")
	public static Timestamp getTodayStartTime() {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);

		return new Timestamp(date.getTime());
	}

	@SuppressWarnings("deprecation")
	public static Timestamp getTodayEndTime() {
		Date date = new Date();
		date.setHours(23);
		date.setMinutes(59);
		return new Timestamp(date.getTime());
	}

	@SuppressWarnings("deprecation")
	public static Timestamp getTodayStartTime(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());
		date.setHours(0);
		date.setMinutes(0);

		return new Timestamp(date.getTime());
	}

	@SuppressWarnings("deprecation")
	public static Timestamp getTodayEndTime(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());
		date.setHours(23);
		date.setMinutes(59);
		return new Timestamp(date.getTime());
	}

	/**
	 * add days to date in java
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/**
	 * subtract days to date in java
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);

		return cal.getTime();
	}

	/**
	 * add days to date in java
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDaysToDay(int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/**
	 * subtract days to date in java
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date subtractDaysToDay(int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.add(Calendar.DATE, -days);

		return cal.getTime();
	}

	// "yyyy-mm-dd" - 2019-07-25
	public static Date convertToDate(String format, String date) {
		String sDate1 = date;
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat(format,Locale.ENGLISH).parse(sDate1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}

	public static void main(String[] args) {
		System.out.println( convertToDate("yyyy-MM-dd", "2019-07-25") );
		//System.out.println(new Timestamp(addDays(new Date(), 1).getTime()).toString());
		//System.out.println(subtractDays(new Date(), 1));

	}
}
