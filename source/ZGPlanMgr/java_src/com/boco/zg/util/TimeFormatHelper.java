package com.boco.zg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.util.logging.resources.logging;


public class TimeFormatHelper {
	private static final Log log =LogFactory.getLog(TimeFormatHelper.class);
	
    public static final String TIME_FORMAT_A = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_B = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_C = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String DATE_FORMAT   = "yyyy-MM-dd";
    public static final String YEAR_FORMAT   = "yyyy";

    private TimeFormatHelper() {
    }

    public static String getFormatDate(Date date, String format){
       String dateStr = null;
       try {
           if(date !=null){
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
               dateStr = simpleDateFormat.format(date);
           }
       } catch (Exception ex) {
          	
       }
       return dateStr;
   }

   public static Date convertDate(String dateStr, String format){
       java.util.Date date = new Date();
       try {
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
           date = simpleDateFormat.parse(dateStr);
       } catch (Exception ex) {
    	   log.error("error when convertDate....  dateStr--"+dateStr+"    format--"+format + "  :"+  ex);
       }
       return date;
   }
   
   public static Date convertDate(String dateStr){
       java.util.Date date = null;
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeFormatHelper.TIME_FORMAT_A);
       try {
    	   	date = simpleDateFormat.parse(dateStr);
       } catch (ParseException ex) {
    	   	simpleDateFormat = new SimpleDateFormat(TimeFormatHelper.DATE_FORMAT);
			try {
				date = simpleDateFormat.parse(dateStr);
			} catch (ParseException e) {
				try{
					date = new Date(Long.valueOf(dateStr));
				}catch(Exception ec){
					try {
						date = simpleDateFormat.parse("1999-01-01");
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
       }
       return date;
   }
}
