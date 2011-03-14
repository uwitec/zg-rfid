package javacommon.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.org.rapid_framework.page.Page;

public class PageGuideTag extends TagSupport {
	private boolean hasToolbar = true;
	private String deleteUrl = "";
	private String addUrl = "";
	private String exportUrl = "";
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			String ctx = request.getContextPath();
			Page page = (Page)request.getAttribute("page");
			StringBuffer sb= new StringBuffer();
			sb.append("<table border=\"0\"  cellpadding=\"0\"  cellspacing=\"0\"  width=\"100%\" ><tr>");
			sb.append("<td class=\"statusBar\" id=\"_toolbar\">");
			/*if(hasToolbar) {
				sb.append("<input type=\"button\" attr=\"viewLink\" class=\"commonButton addButton\" value=\"新增\" onclick=\"batchAdd('"+ctx+addUrl+"',document.forms.ec)\" />");
				sb.append("<input type=\"button\" class=\"commonButton delButton\" value=\"删除\" onclick=\"batchDelete('"+ctx+deleteUrl+"','items',document.forms.ec)\"  />");
				sb.append("<input type=\"button\" class=\"commonButton expButton\" value=\"导出\" onclick=\"batchExport('"+ctx+exportUrl+"')\" />");
			}*/
			int totalPage = 0;
			if(page.getTotalCount() != 0) {
				totalPage = page.getTotalCount()/page.getThisPageLastElementNumber();
			}
			sb.append("共&nbsp;"+totalPage+"&nbsp;页/&nbsp;"+page.getTotalCount()+"&nbsp;条记录");
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;每页&nbsp;<select name=\"ec_rd\" onchange=\"javascript:document.forms.ec.ec_crd.value=this.options[this.selectedIndex].value;document.forms.ec.ec_p.value='1';document.forms.ec.submit()\" >");
			Properties prop = new Properties();
			prop.load(this.getClass().getResourceAsStream("/extremetable.properties"));
			int row = Integer.parseInt(prop.getProperty("table.rowsDisplayed"));
			int midrow = Integer.parseInt(prop.getProperty("table.medianRowsDisplayed"));
			int maxrow = Integer.parseInt(prop.getProperty("table.maxRowsDisplayed"));
			Set<Integer> keySet = new LinkedHashSet<Integer>();
			keySet.add(row);
			keySet.add(midrow);
			keySet.add(maxrow);
			for(Integer set:keySet){
				if(set==page.getPageSize()){
					sb.append("<option value=\""+set+"\"  selected=\"selected\">"+set+"</option>");
				}else{
					sb.append("<option value=\""+set+"\">"+set+"</option>");
				}
			}
			sb.append("</select>&nbsp;条</td>");
			sb.append("<td class=\"compactToolbar\" align=\"left\" >");
			sb.append("<div class=\"button\">");
			sb.append("<table border=\"0\"  cellpadding=\"1\"  cellspacing=\"2\" > <tr><td>");
			if(page.isFirstPage()){
				//sb.append("<img src=\""+ctx+"/widgets/extremecomponents/images/firstPageDisabled.gif\"  style=\"border:0\"  alt=\"第一页\" />");
				sb.append("<a href=\"javascript:\">");
				sb.append("<span disabled=\"disabled\">");
				sb.append("首页");
				sb.append("</span>");
				sb.append("</a>");
			}else{
				//sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='1';document.forms.ec.submit()\">");
				//sb.append("<img src=\""+ctx+"/widgets/extremecomponents/images/firstPageDisabled.gif\"  style=\"border:0\"  alt=\"第一页\" />");
				//sb.append("</a>");
				sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='1';document.forms.ec.submit()\">");
				sb.append("<span>");
				sb.append("首页");
				sb.append("</span>");
				sb.append("</a>");
				
			}
			sb.append("</td><td>");
			if(page.isHasPreviousPage()){
				//sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='"+page.getPreviousPageNumber()+"';document.forms.ec.submit()\">");
				//sb.append("<img src=\""+ctx+"/widgets/extremecomponents/images/prevPageDisabled.gif\"  style=\"border:0\"  alt=\"上一页\" />");
				//sb.append("</a>");
				sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='"+page.getPreviousPageNumber()+"';document.forms.ec.submit()\">");
				sb.append("<span>");
				sb.append("上一页");
				sb.append("</span>");
				sb.append("</a>");
			}else{
				//sb.append("<img src=\""+ctx+"/widgets/extremecomponents/images/prevPageDisabled.gif\"  style=\"border:0\"  alt=\"上一页\" />");
				sb.append("<a href=\"javascript:\">");
				sb.append("<span disabled=\"disabled\">");
				sb.append("上一页");
				sb.append("</span>");
				sb.append("</a>");
			}
			sb.append("</td><td>");
			if(page.isHasNextPage()){
				//sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='"+page.getNextPageNumber()+"';document.forms.ec.submit()\"><img src=\""+ctx+"/widgets/extremecomponents/images/nextPage.gif\"  style=\"border:0\"  title=\"下一页\"  alt=\"下一页\" /></a>");
				sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='"+page.getNextPageNumber()+"';document.forms.ec.submit()\">");
				sb.append("<span>");
				sb.append("下一页");
				sb.append("</span>");
				sb.append("</a>");
			}else{
				//sb.append("<img src=\""+ctx+"/widgets/extremecomponents/images/nextPage.gif\"  style=\"border:0\"  title=\"下一页\"  alt=\"下一页\" />");
				sb.append("<a href=\"javascript:\" disabled=\"disabled\">");
				sb.append("<span onclick=\"\">");
				sb.append("下一页");
				sb.append("</span>");
				sb.append("</a>");
			}
			sb.append("</td><td>");
			if(page.isLastPage()){
				//sb.append("<img src=\""+ctx+"/widgets/extremecomponents/images/lastPage.gif\"  style=\"border:0\"  title=\"最后页\"  alt=\"最后页\" />");
				sb.append("<a href=\"javascript:\">");
				sb.append("<span onclick=\"\" disabled=\"disabled\">");
				sb.append("尾页");
				sb.append("</span>");
				sb.append("</a>");
			}else{
				//sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='"+page.getLastPageNumber()+"';document.forms.ec.submit()\"><img src=\""+ctx+"/widgets/extremecomponents/images/lastPage.gif\"  style=\"border:0\"  title=\"最后页\"  alt=\"最后页\" /></a>");
				sb.append("<a href=\"javascript:document.forms.ec.ec_p.value='"+page.getLastPageNumber()+"';document.forms.ec.submit()\">");
				sb.append("<span>");
				sb.append("尾页");
				sb.append("</span>");
				sb.append("</a>");
			}
			sb.append("</td>");
			sb.append("<td>跳至 <select name=\"ec_page\" onchange=\"javascript:document.forms.ec.ec_p.value=this.options[this.selectedIndex].value;document.forms.ec.submit()\">");
			for(int i=1;i<=page.getLastPageNumber();i++){
				if(page.getThisPageNumber()==i){
					sb.append("<option selected=\"selected\" value=\""+i+"\">"+i+"</option>");
				}else{
					sb.append("<option value=\""+i+"\">"+i+"</option>");
				}
			}
			sb.append("</select>页");
			sb.append("</td></tr></table>");
			sb.append("</div>");
			sb.append("</td></tr></table>");
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.EVAL_PAGE;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getAddUrl() {
		return addUrl;
	}

	public void setAddUrl(String addUrl) {
		this.addUrl = addUrl;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

	public boolean isHasToolbar() {
		return hasToolbar;
	}

	public void setHasToolbar(boolean hasToolbar) {
		this.hasToolbar = hasToolbar;
	}

}
