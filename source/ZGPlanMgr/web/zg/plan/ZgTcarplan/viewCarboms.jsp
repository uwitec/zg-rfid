<%@ page contentType="text/html;charset=UTF-8" language="java"%>
		<table style="FONT-SIZE: x-small; BORDER-COLLAPSE: collapse"
			borderColor=#111111 cellSpacing=0 cellPadding=2 width="100%" border=1>
			<tr>
				<td height="20">
					物料编号
				</td>
				<td height="20">
					订单编号
				</td>
				<td height="20">
					需求数量
				</td>
				<td height="20">
					计划领取数量
				</td>
				<td height="20">
					排序日期
				</td>
				<td height="20">
					销售订单号
				</td>
				<td height="20">
					生产厂
				</td>
				<td height="20">
					生产线
				</td>
				<td height="20">
					物料描述
				</td>
				<td height="20">
					度量单位
				</td>
			</tr>
			
			<c:forEach items="${orderboms}" var="orderbom">
				<c:set var="length" value="${fn:length(orderbom.value)}"/>
				<tr>
			    <td rowspan="${length}">${orderbom.key}</td>
			    <c:forEach items="${orderbom.value}" var="obj" varStatus="n">
			    
			    <c:choose>
			    	<c:when test="${n.index==0}">
				    		<td height="20">
								${obj["AUFNR"]}
							</td>
							<td height="20">
								${obj["CAR_NUM"]-obj["COMPLETE_NUM"]}
							</td>
							<td height="20">
								${obj['PLAN_NUM']}
							</td>
							<td height="20">
								${obj["PXDAT"]}
							</td>
							<td height="20">
								${obj["KDAUF"]}
							</td>
							<td height="20">${obj["PLANT"]}</td>
							<td height="20">${obj["ARBPL"]}</td>
							<td height="20">${obj["MAKTX1"]}</td>
							<td height="20">${obj["MSEHL1"]}</td>
						  </tr>
			    	</c:when>
			    	<c:otherwise>
			    		<tr>
						    <td height="20">
								${obj["AUFNR"]}
							</td>
							<td height="20">
								${obj["CAR_NUM"]-obj["COMPLETE_NUM"]}
							</td>
							<td height="20">
								${obj['PLAN_NUM']}
							</td>
							<td height="20">
								${obj["PXDAT"]}
							</td>
							<td height="20">
								${obj["KDAUF"]}
							</td>
							<td height="20">${obj["PLANT"]}</td>
							<td height="20">${obj["ARBPL"]}</td>
							<td height="20">${obj["MAKTX1"]}</td>
							<td height="20">${obj["MSEHL1"]}</td>
						</tr>
			    	</c:otherwise>
			    	</c:choose>
			    </c:forEach>
			</c:forEach>
		</table>
	