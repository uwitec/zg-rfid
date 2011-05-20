package com.boco.zg.plan.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextLoader;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.request.RequestScope;

import javacommon.base.BaseDwrAction;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.storage.base.model.ZgTstorageCanclebom;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.storage.service.ZgTstorageCanclebomExBo;
import com.boco.zg.storage.service.ZgTstorageExBo;
import com.opensymphony.xwork2.inject.Context;

public class ZgTbomCancelDwrAction extends BaseDwrAction {

	private ZgTorderbomExBo getZgTorderbomExBo() {
		return (ZgTorderbomExBo)ApplicationContextHolder.getBean("zgTorderbomExBo");
	}
	
	
	private ZgTstorageCanclebomEx getZgTstorageCanclebomEx() {
		return (ZgTstorageCanclebomEx)ApplicationContextHolder.getBean("zgTstorageCanclebomEx");
	}
	private ZgTstorageCanclebomExBo getZgTstorageCanclebomExBo() {
		return (ZgTstorageCanclebomExBo)ApplicationContextHolder.getBean("zgTstorageCanclebomExBo");
	}
	
	
	/**
	 *新建入库冲单（保存）提交的时候检验
	 * 	判断所输入的数量是否超出现有需求总量
	 * @param session
	 * @return
	 */
	public boolean checkForGenerateCanelCreate(HttpSession session){
		List<ZgTstorageCanclebomEx> bomECancleList=(List<ZgTstorageCanclebomEx>)session.getAttribute("bomECancleList");
		
		boolean result = true;
		for(int i = 0;i < bomECancleList.size();i++){
			ZgTstorageCanclebomEx zgTstorageCanclebomEx = bomECancleList.get(i);
			Long num = zgTstorageCanclebomEx.getNum();
			String aufnr = zgTstorageCanclebomEx.getAufnr();
			String idnrk = zgTstorageCanclebomEx.getIdnrk();
			String lgort=zgTstorageCanclebomEx.getLgort();
			boolean flag = this.getZgTorderbomExBo().checkStateForGenerateBomNum(aufnr,idnrk,num,lgort);
			if(!flag){
				result = false;
				break;
			}
		}
		return result;
		
	}
	/**
	 * 	修改入库冲单（保存）提交的时候检验
	 *  校验表单状态是否为已提交，若不是，则调用同一个类下的方法进行数量的检验
	 *  若通过，返回TRUE
	 * @param session
	 * @return
	 */
	public String checkForGenerateOrderChanged(String cuid,HttpSession session){
		String result = "OK";
			boolean flag  = (this).getZgTstorageCanclebomExBo().checkStateForGenerateStrorageCancelState(cuid);
			if(flag){
				result = "该表单已经被提交，请确认!";
			}else{
				flag = this.checkForGenerateCanelCreate(session);
				if(!flag){
					result = "冲单数量不能大于可冲单数量";
				}
			}
		return result;
	}
	
}
