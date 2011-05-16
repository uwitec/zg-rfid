package com.boco.zg.plan.common.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

/**
 * 公交服务类
 * @author wengq
 *
 */
public class CommonService {

	/**
	 * 开始时间默认设定为当前时间默认前两天，结束时间默认设定为当前时间延后5天，总共的开始和结束时间跨度为7天
	 * @param req
	 * @param startDateName 开始时间的属性名称
	 * @param endDateName 结束时间的属性名称
	 */
	public static void defultDateSet(HttpServletRequest req,String startDateName,String endDateName){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String startDateStr=dateFormat.format(calendar.getTime());
		calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, 5);
		String endDateStr=dateFormat.format(calendar.getTime());
		req.setAttribute(startDateName, startDateStr);
		req.setAttribute(endDateName,endDateStr );
	}
	
	/**
	 * 开始时间默认设定为当前时间默认前两天，结束时间默认设定为当前时间延后5天，总共的开始和结束时间跨度为7天
	 * @param req
	 * @param startDateName 开始时间的属性名称
	 * @param endDateName 结束时间的属性名称
	 */
	public static void defultDateSet(HttpServletRequest req,String startDateName,String endDateName,int start,int end){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, start);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String startDateStr=dateFormat.format(calendar.getTime());
		calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, end);
		String endDateStr=dateFormat.format(calendar.getTime());
		req.setAttribute(startDateName, startDateStr);
		req.setAttribute(endDateName,endDateStr );
	}
	
	/**
	 * 开始时间默认设定为当前时间默认前两天，结束时间默认设定为当前时间延后5天，总共的开始和结束时间跨度为7天
	 * @param req
	 * @param startDateName 开始时间的属性名称
	 * @param endDateName 结束时间的属性名称
	 */
	public static void defultDateTimeSet(HttpServletRequest req,String startDateName,String endDateName,int start,int end){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, start);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDateStr=dateFormat.format(calendar.getTime());
		calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, end);
		String endDateStr=dateFormat1.format(calendar.getTime());
		req.setAttribute(startDateName, startDateStr+" 00:00:00");
		req.setAttribute(endDateName,endDateStr );
	}
	
	

}
