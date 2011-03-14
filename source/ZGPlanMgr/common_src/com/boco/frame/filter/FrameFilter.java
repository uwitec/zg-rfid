package com.boco.frame.filter;

import java.io.IOException;
import java.util.List;

import javacommon.base.dao.BaseDao;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwSystemNotice;
import com.boco.frame.sys.base.model.FwSystemNoticeUserLog;
import com.boco.frame.sys.base.service.FwSystemNoticeBo;
import com.boco.frame.sys.base.service.FwSystemNoticeUserLogBo;

public class FrameFilter implements Filter {
	
	private ApplicationContext ctx;
	
	protected String timeoutpage;
	
	protected boolean ignore = true;
	
	protected FilterConfig filterConfig = null;
	
	private BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}
	
	private FwSystemNoticeBo getFwSystemNoticeBo() {
		return (FwSystemNoticeBo) ApplicationContextHolder
				.getBean("fwSystemNoticeBo");
	}
	
	private FwSystemNoticeUserLogBo getFwSystemNoticeUserLogBo() {
		return (FwSystemNoticeUserLogBo) ApplicationContextHolder
				.getBean("fwSystemNoticeUserLogBo");
	}
	
	public void destroy() {
		this.filterConfig = null;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		ctx = ApplicationContextHolder.getApplicationContext();
		this.filterConfig = filterConfig;
		this.timeoutpage = filterConfig.getInitParameter("timeoutpage");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.addHeader("P3P","CP=CAO PSA OUR");
		HttpSession session = httpRequest.getSession();
		boolean success = true;
		String msg = "";
		String uri = httpRequest.getRequestURI();
		uri = uri.replaceAll("//", "/");
		String path = httpRequest.getServletPath();
		path = path.replaceAll("//", "/");
		if (path.equalsIgnoreCase("/login.jsp")||uri.equalsIgnoreCase("/ZGPlanMgr/")||path.equalsIgnoreCase("/Login/login.do")||uri.equalsIgnoreCase("/ZGPlanMgr/Login/login.do")||path.equalsIgnoreCase("/loginByK.jsp")||path.equalsIgnoreCase("/Login/loginByK.do")||path.equalsIgnoreCase("/ZGPlanMgr/Login/loginByK.do")||path.equalsIgnoreCase("/download.jsp")||path.equalsIgnoreCase("/ZGPlanMgr/download.jsp")) {
				success = true;
				msg = "";
		}else {
			if(session.getAttribute("operatorInfo")==null)   
			{   
					msg = "<script>top.window.location='"+httpRequest.getContextPath()+"/login.jsp?msg=sessionOut'</script>";
					success = false;
			}else{//读取系统公告
				if(path.contains("query.do")||path.contains("list.do")){
					OperatorInfo operatorInfo = (OperatorInfo) session.getAttribute("operatorInfo");
					String operatorId=operatorInfo.getOperatorId();
					List<FwSystemNotice> noticeList=getFwSystemNoticeBo().getListByOperatorId(operatorId);
					for(FwSystemNotice notice:noticeList){
						FwSystemNoticeUserLog log=new FwSystemNoticeUserLog();
						log.setUserId(operatorId);
						log.setNoticeId(notice.getCuid());
						getFwSystemNoticeUserLogBo().save(log);
					}
					httpRequest.setAttribute("noticeList", noticeList);
				}
				
			}
		}
		if(success)	{
			chain.doFilter(httpRequest, httpResponse);  
		}else{
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.getWriter().println(msg);
		}
	} 

}
