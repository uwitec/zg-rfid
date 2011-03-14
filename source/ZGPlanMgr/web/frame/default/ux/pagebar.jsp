<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="cn.org.rapid_framework.page.Page" %>
<%@page import="java.util.Properties" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.LinkedHashSet" %>
<%@page import="javacommon.base.BaseStruts2Action"%>

<%
	Page p = (Page)request.getAttribute("page");
	int totalPage = 0;
	if(p.getTotalCount() != 0) {
		totalPage = p.getTotalCount()/p.getPageSize();
		if(p.getTotalCount()%p.getPageSize() > 0) {
			totalPage = totalPage+1;
		}
	}
	Properties prop = new Properties();
	prop.load(this.getClass().getResourceAsStream("/extremetable.properties"));
	int row = Integer.parseInt(prop.getProperty("table.rowsDisplayed"));
	int midrow = Integer.parseInt(prop.getProperty("table.medianRowsDisplayed"));
	int maxrow = Integer.parseInt(prop.getProperty("table.maxRowsDisplayed"));
	Set<Integer> keySet = new LinkedHashSet<Integer>();
	keySet.add(row);
	keySet.add(midrow);
	keySet.add(maxrow);//10 15 20
	
%>
<script src="${ctx}/dwr/interface/OperatorFeatureSetDwrAction.js" type=""></script>
<script type="text/javascript">
	//个性保存pagesize
	function savePageSize(pageSizeValue){
		DWREngine.setAsync(false);
		OperatorFeatureSetDwrAction.savePageSizeValue(pageSizeValue);
	}
</script>
<table class="pagebar">
	<tr>
		<td>
			共&nbsp;<%=totalPage%>&nbsp;页/&nbsp;<%=p.getTotalCount()%>&nbsp;条记录
			&nbsp;&nbsp;&nbsp;&nbsp;每页&nbsp;
			<select name="ec_rd"
				onchange="javascript:document.forms.ec.ec_crd.value=this.options[this.selectedIndex].value;savePageSize(this.options[this.selectedIndex].value);document.forms.ec.ec_p.value='1';document.forms.ec.submit()">
				<%	for(Integer set:keySet){
						if(set==p.getPageSize()){
				%>
				<option value="<%=set %>" selected="selected"><%=set %></option>
				<%		}else{ %>
				<option value="<%=set %>"><%=set %></option>
				<%		}
					}
				%>
			</select>
			&nbsp;条
		</td>
		<td width="350px">
			<%if(p.isFirstPage()) {%>
			<a href="javascript:" disabled="disabled"><span>1</span></a>
			<%}else {%>
			<a href="javascript:javascript:document.forms.ec.ec_p.value='1';document.forms.ec.submit()"><span>1</span></a>
			<%}%>
			<%
				int begin = p.getThisPageNumber() -4;
				if(begin < 2) {
					begin = 2;
				}
				int end = p.getThisPageNumber()+4+(3-p.getThisPageNumber()+begin);
				if(end >= p.getLastPageNumber()) {
					end = p.getLastPageNumber()-1;
				}
				for(int i=begin;i<=end;i++){%>
				<%
				if(i==begin&&p.getThisPageNumber()>=7){
			 %>
			 ...
			 <%} %>
				<%
					if(p.getThisPageNumber()==i){
			%>
			<a href="javascript:document.forms.ec.ec_p.value='<%=i%>';document.forms.ec.submit()" disabled="disabled"><span><%=i%></span></a>
			<%		}else {%>
			<a href="javascript:document.forms.ec.ec_p.value='<%=i%>';document.forms.ec.submit()"><span><%=i%></span></a>
			<%
				if(i==end&&p.getLastPageNumber()>p.getThisPageNumber()+4){
			 %>
			 ...
			 <%} %>
			<%		}
				}
			%>
			<%	if(p.getLastPageNumber() != 1) {
					if(p.isLastPage() && p.getLastPageNumber() != 1){ %>
			<a href="javascript:" disabled="disabled"><span><%=p.getLastPageNumber()%></span></a>
			<%		}else {%>
			<a href="javascript:document.forms.ec.ec_p.value='<%=p.getLastPageNumber()%>';document.forms.ec.submit()"><span><%=p.getLastPageNumber()%></span></a>
			<%		}
				}
			%>
		</td>
		<td width="100px">
			跳至
			<select name="ec_page"
				onchange="javascript:document.forms.ec.ec_p.value=this.options[this.selectedIndex].value;document.forms.ec.submit()">
				<%	for(int i=1;i<=p.getLastPageNumber();i++){
						if(p.getThisPageNumber()==i){
				%>
				<option selected="selected" value="<%=i %>"><%=i %></option>
				<%		}else{ %>
				<option value="<%=i %>"><%=i %></option>
				<%		}
					}
				%>
			</select>
			页
		</td>
		</td>
	</tr>
</table>