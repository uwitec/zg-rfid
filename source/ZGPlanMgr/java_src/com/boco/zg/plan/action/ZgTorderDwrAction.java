package com.boco.zg.plan.action;

import java.util.List;
import java.util.Map;

import javacommon.base.BaseDwrAction;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;

public class ZgTorderDwrAction extends BaseDwrAction {
	
	private ZgTorderbomExBo getZgTorderbomExBo() {
		return (ZgTorderbomExBo)ApplicationContextHolder.getBean("zgTorderbomExBo");
	}
	
	private ZgTorderExBo getZgTorderExBo() {
		return (ZgTorderExBo)ApplicationContextHolder.getBean("zgTorderExBo");
	}
	
	public String submitOrder(HttpServletRequest request,String orderId,String[] sortfs) {
		for(int j = 0;j<sortfs.length;j++) {
			if(!StringUtils.isBlank(sortfs[j])) {
				String[] s = sortfs[j].split("_");
				getZgTorderbomExBo().updateOrderBomSortf(s[0],s[1]);
			}
		}	
		OperatorInfo operatorInfo = (OperatorInfo)this.getSessionOperatorInfo(request);
		this.getZgTorderExBo().submitOrder(orderId,operatorInfo);
		return "success";
	}
	
	/**判断成品能否下线的3个约束条件
	 * 1 本次下线数量 ≤  已排序数量  -  已下线数量
	 * 2 半成品库存数量满足：已投入使用+本次下线数量*单台用量<=出库数量
	 * 3 订单BOM表和领料计划BOM中abc abe abd的BOM数量相等
	 * 4 单台用量*本次下线数量 <=已领取数量-移单数量-已退料数量-已出库数量
	 * @param orderId  订单ID
	 * @param finishNumStr 本次成品下线数量字符串
	 * @return
	 */
	public String orderFinish(String orderId,String finishNumStr){
		boolean flag=true;
		flag=getZgTorderExBo().getMaxFinishNum(orderId,finishNumStr);//1、判断本次下线数量是否小于等于剩余排序数量
		if(flag==false){
			return "下线数量超过剩余排产数量,成品不能下线!";
		}
		
		/*String result2=getZgTorderExBo().isEnoughProductForOrderFinish(orderId,finishNumStr);//2 半成品库存数量满足：已投入使用+本次下线数量*单台用量<=出库数量
		if(!result2.equals("OK")){
			if(result2.equals("")){
				return "半成品物料没有入库,成品不能下线!";
			}
			return "半成品物料"+result2+" 出库数量不够,成品不能下线!";
		}*/
		
		
		List<Map> list= getZgTorderExBo().isCountInOrderBomAndOrderPlanBomEqual(orderId);//3、判断订单bom和领料bom数量是否相等，即所有订单bom均已生成领料计划
		if(list.size()>0){
			return "BOM物料" +(list.get(0)).get("IDNRK")+" 备料库存数量不够,成品不能下线!";
		}
		String result=getZgTorderExBo().isEnoughBomForOrderFinish(orderId, finishNumStr);//4、判断领料BOM数量是否足够下线
		if(!result.equals("OK")){
			return "BOM物料"+result+" 备料库存数量不够,成品不能下线!";
		}
		//成品下线：更新已出库数量和成品下线数量   
		getZgTorderExBo().doProcessPublish(orderId,finishNumStr);
		return null;
	}
	
}
