package com.jitendra.logasservice.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.00.01.00");
	
	
	public static Timestamp getTodayStartTime() {
		Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        
        return new Timestamp(date.getTime());
	}
	
	public static Timestamp getTodayEndTime() {
		Date date = new Date();
        date.setHours(23);
        date.setMinutes(59);        
        return new Timestamp(date.getTime());
	}
	
	public static void main(String[] args) {
		        
        System.out.println(getTodayEndTime());
        
        System.out.println(getTodayStartTime());
         
	}
}
