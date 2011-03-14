package javacommon.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javacommon.util.ConvertRegisterHelper;
import javacommon.util.PageRequestFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.util.Assert;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ObjectUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseStruts2Action extends ActionSupport implements RequestAware {
	
	public static final String MESSAGE_PAGE = "/commons/operateResult.jsp";
	protected Map requestMap = null;
	
	static {
		//注册converters
		ConvertRegisterHelper.registerConverters();
	}
	
	public void copyProperties(Object target,Object source) {
		BeanUtils.copyProperties(target, source);
	}

	public <T> T copyProperties(Class<T> destClass,Object orig) {
		return BeanUtils.copyProperties(destClass, orig);
	}
	
	public void setRequest(Map request) {
		this.requestMap = request;
	}

	public void savePage(Page page,PageRequest pageRequest){
		savePage("",page,pageRequest);
	}
	
	/**
	 * 用于一个页面有多个extremeTable是使用
	 * @param tableId 等于extremeTable的tableId属性
	 */
	public void savePage(String tableId,Page page,PageRequest pageRequest){
		Assert.notNull(tableId,"tableId must be not null");
		Assert.notNull(page,"page must be not null");
		
		getRequest().setAttribute(tableId+"page", page);
		getRequest().setAttribute(tableId+"totalRows", new Integer(page.getTotalCount()));
		getRequest().setAttribute(tableId+"pageRequest", pageRequest);
	}
	
	public PageRequest newPageRequest(String defaultSortColumns){
		PageRequest<Map> pageRequest =  PageRequestFactory.newPageRequest(ServletActionContext.getRequest(), defaultSortColumns);
		//把当前客户信息写到查询中
		OperatorInfo operatorInfo=(OperatorInfo) this.getSession().getAttribute("operatorInfo");
		
		int pageSize=operatorInfo.getPageSizeValue();
		pageRequest.setPageSize(pageSize);//覆盖属性pagesize
		pageRequest.getFilters().put("operatorId", operatorInfo.getOperatorId());//userId
		//pageRequest.getFilters().put("orgId",operatorInfo.getOrgId());
		return pageRequest;//根据这里的pagerequest设置的page中的pagesize
    }
	
	public boolean isNullOrEmptyString(Object o) {
		return ObjectUtils.isNullOrEmptyString(o);
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public String getSessionUserId() {
		OperatorInfo operatorInfo = (OperatorInfo)this.getSession().getAttribute("operatorInfo");
		return operatorInfo != null?operatorInfo.getUserId():null;
	}
	public String getSessionOperatorId() {
		OperatorInfo operatorInfo = (OperatorInfo)this.getSession().getAttribute("operatorInfo");
		return operatorInfo != null?operatorInfo.getOperatorId():null;
	}
	
	/**
	 * 提示信息并且关闭窗口
	 * @param msg
	 * @throws IOException
	 */
	public void promtAndCloseWindow(String msg) throws IOException{
		this.getResponse().setContentType("text/html; charset=utf-8");
        PrintWriter out = this.getResponse().getWriter();
        out.println("<html><head>");
        out.println("<script>alert('"+msg+"');window.returnValue='操作成功';window.close();</script>");
        out.println("</head><body>");
        out.println("</body></html>");
        out.close();
	}
	
	/**
	 * 关闭窗口并返回值
	 * @param msg
	 * @throws IOException
	 */
	public void returnMsgAndCloseWindow(String msg) throws IOException{
		this.getResponse().setContentType("text/html; charset=utf-8");
        PrintWriter out = this.getResponse().getWriter();
        out.println("<html><head>");
        out.println("<script>window.returnValue='"+msg+"';window.close();</script>");
        out.println("</head><body>");
        out.println("</body></html>");
        out.close();
	}
	
	/**
	 * 提示信息，并返回查询列表
	 * @param msg
	 * @throws IOException
	 */
	public void promtAndQuery(String msg) throws IOException{
		this.getResponse().setContentType("text/html; charset=utf-8");
        PrintWriter out = this.getResponse().getWriter();
        out.println("<html><head>");
        out.println("<script>");
        if(!isNullOrEmptyString(msg)){
        	out.println("alert('"+msg+"');");
        }
        out.println("parent.document.forms[0].submit();");
        out.println("</script>");
        out.println("</head><body>");
        out.println("</body></html>");
        out.close();
	}
	
	/**
	 * html内容提交执行
	 * @param content
	 * @throws IOException
	 */
	public void rendHtml(String content) throws IOException {
		this.getResponse().setContentType("text/html; charset=utf-8");
        PrintWriter out = this.getResponse().getWriter();
        out.println("<html><head>");
        out.println("<script>");
        out.println(content);
        out.println("</script>");
        out.println("</head><body>");
        out.println("</body></html>");
        out.close();
	}	
	
	/**
	 * 提示信息返回查询
	 * @param msg
	 * @throws IOException
	 */
	public void forwardQuery(String msg) throws IOException {
		this.getResponse().setContentType("text/html; charset=utf-8");
        PrintWriter out = this.getResponse().getWriter();
        out.println("<html><head>");
        out.println("<script>");
        if(!isNullOrEmptyString(msg)){
        	out.println("alert('"+msg+"');");
        }
        out.println("if(parent.doQuery)parent.doQuery();");
        out.println("</script>");
        out.println("</head><body>");
        out.println("</body></html>");
        out.close();
	}
}

