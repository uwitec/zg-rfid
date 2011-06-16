package com.boco.frame.login.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.util.PageRequestFactory;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.pojo.LoginForm;
import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwEmployeeOrganazation;
import com.boco.frame.sys.base.service.FwEmployeeBo;
import com.boco.frame.sys.base.service.FwEmployeeOrganazationBo;
import com.boco.frame.sys.base.service.FwOperatorBo;
import com.boco.zg.plan.service.ZgTcarplanExBo;
import com.boco.zg.util.Constants;
import com.boco.zg.virtualorg.base.model.ZgTvirtualorg;
import com.boco.zg.virtualorg.base.model.ZgTvirtualorgEx;
import com.boco.zg.virtualorg.base.service.ZgTvirtualorgBo;

@SuppressWarnings("unchecked")
@Component
public class LoginBo {

	private FwEmployeeBo fwEmployeeBo;
	private FwEmployeeOrganazationBo fwEmployeeOrganazationBo;
	private ZgTvirtualorgBo zgTvirtualorgBo;
	private ZgTcarplanExBo zgTcarplanExBo;
	
	
	public Map login(LoginForm loginForm) {
		Map resultMap = new HashMap();
		FwEmployee fwEmployee = new FwEmployee();
		fwEmployee.setUserId(loginForm.getUserId());
		fwEmployee = fwEmployeeBo.findByPropertyUnique(fwEmployee);
		if(fwEmployee != null) {
			if(!loginForm.getPassword().equals(fwEmployee.getPassword())) {
				resultMap.put("success", "false"); 
				resultMap.put("reason", "1"); 
			}else {
				OperatorInfo operatorInfo = new OperatorInfo();
				operatorInfo.setOperatorId(fwEmployee.getCuid());
				operatorInfo.setUserId(fwEmployee.getUserId());
				
				operatorInfo.setPageSizeValue(getPageSize(fwEmployee));
				
				operatorInfo.setLoginTime(new Date());
				operatorInfo.setUserName(fwEmployee.getLabelCn());
				resultMap.put("success", "true"); 
				resultMap.put("result", operatorInfo);
			}
		}else {
			resultMap.put("success", "false"); 
			resultMap.put("reason", "2"); 
		}
		return resultMap;
	}

	/**
	 * 获取查询列表条数
	 * @param fwEmployee
	 * @return
	 */
	private int getPageSize(FwEmployee fwEmployee) {
		//查看是否有pagesize个性设置，没有则初始化为10但不插入此值到pageparam到库表；有则从库表中查询
		List<Map> paramList=fwEmployeeBo.findFromPageParam(fwEmployee.getCuid());
		if(paramList.size()==0||paramList==null){
			return PageRequestFactory.DEFAULT_PAGE_SIZE;//没有初始化为10 
		}else{
			Map map=paramList.get(0);
			String  pageSizeStr=map.get("PAGESIZE").toString();
			return Integer.parseInt(pageSizeStr);
		}
	}

	public void setFwEmployeeBo(FwEmployeeBo fwEmployeeBo) {	
		this.fwEmployeeBo = fwEmployeeBo;
	}


	public void setFwEmployeeOrganazationBo(
			FwEmployeeOrganazationBo fwEmployeeOrganazationBo) {
		this.fwEmployeeOrganazationBo = fwEmployeeOrganazationBo;
	}

	public String getDefaultUrl(String operatorId) {
		String url="";
		List<ZgTvirtualorgEx>  list = zgTvirtualorgBo.getVirtualOrgByOperatorId(operatorId);
		if(list.size()>0){
			String type=list.get(0).getType();
			if("ALL".equals(type)){
				type=Constants.sorft.ABE.value();
			}
//			if(Constants.sorft.ABE.value().equals(type)){
				List<Map> carPlanList=zgTcarplanExBo.getCarPlanByUserId1(operatorId,type);
				String arbpl="";
				if(carPlanList.size()>0){
					arbpl=carPlanList.get(0).get("ARBPL")==null?"":carPlanList.get(0).get("ARBPL").toString();
				}
				url ="zg/plan/ZgTcarplan/carplan_frame.jsp?type=2&orderPlanType="+type+"&onload=onload&defaulArbpl="+arbpl;;
//			}else {
//				url ="zg/plan/ZgTcarplan/query1.do?type=2&orderPlanType="+type+"&onload=onload";
//			}
		}
		return url;
	}

	public void setZgTvirtualorgBo(ZgTvirtualorgBo zgTvirtualorgBo) {
		this.zgTvirtualorgBo = zgTvirtualorgBo;
	}

	public void setZgTcarplanExBo(ZgTcarplanExBo zgTcarplanExBo) {
		this.zgTcarplanExBo = zgTcarplanExBo;
	}

}
