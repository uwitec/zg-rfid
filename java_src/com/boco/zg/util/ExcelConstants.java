package com.boco.zg.util;

import java.util.Map;import java.util.LinkedHashMap;

/**
 * 常量类
 * @author wengq
 *
 */
@SuppressWarnings("unchecked")
public class ExcelConstants {
	
	public static Map ZG_T_ORDER_PLAN = new LinkedHashMap();
	public static Map ZG_T_CARPLAN = new LinkedHashMap();
	public static Map FW_EMPLOYEE = new LinkedHashMap();
	public static Map ZG_T_STORAGE = new LinkedHashMap();
	public static Map ZG_T_STORAGE_CANCLE = new LinkedHashMap();
	public static Map ZG_T_STORAGE_STAT = new LinkedHashMap();
	
	public static Map ZG_T_CARPLAN_STORAGE = new LinkedHashMap();
	public static Map ZG_T_CARPLAN_BATCH_STORAGE = new LinkedHashMap();
	static{
		ZG_T_ORDER_PLAN.put("排序日期", "pxdatString");
		ZG_T_ORDER_PLAN.put("订单编码", "aufnr");
		ZG_T_ORDER_PLAN.put("排产日期", "pcdatString");
		ZG_T_ORDER_PLAN.put("销售订单号", "kdauf");
		ZG_T_ORDER_PLAN.put("线体描述", "planType_enum");
		ZG_T_ORDER_PLAN.put("产品编码", "matnr");
		ZG_T_ORDER_PLAN.put("公司机型", "maktx1");
		ZG_T_ORDER_PLAN.put("生产数量", "psmng");
		ZG_T_ORDER_PLAN.put("生产厂", "plant");
		ZG_T_ORDER_PLAN.put("生产线", "arbpl");
		ZG_T_ORDER_PLAN.put("领料组", "departmentName");
		ZG_T_ORDER_PLAN.put("领料人", "userName");
		ZG_T_ORDER_PLAN.put("领料时间", "planDateString");
		ZG_T_ORDER_PLAN.put("开始时间", "planStartTime");
		ZG_T_ORDER_PLAN.put("结束时间", "planEndTime");
		ZG_T_ORDER_PLAN.put("状态", "state_enum");
		
		ZG_T_CARPLAN.put("单据编号", "cuid");
		ZG_T_CARPLAN.put("单据日期", "createDateString");
		ZG_T_CARPLAN.put("领料员编号", "carUser");
		ZG_T_CARPLAN.put("领料员", "carUser_related");
		ZG_T_CARPLAN.put("领料部门", "carUser_dept_Id_related");
		ZG_T_CARPLAN.put("仓库编号", "storageId");
		ZG_T_CARPLAN.put("仓库名称", "storageId_related");
		ZG_T_CARPLAN.put("单据状态", "carState_enum");
		
		FW_EMPLOYEE.put("用户ID", "userId");
		FW_EMPLOYEE.put("用户名称", "labelCn");
		FW_EMPLOYEE.put("密码", "password");
		FW_EMPLOYEE.put("入职时间", "effectTimeString");
		FW_EMPLOYEE.put("部门", "orgId_related");
		FW_EMPLOYEE.put("性别", "sex_enum");
		FW_EMPLOYEE.put("手机", "mobile");
		FW_EMPLOYEE.put("电话", "telephone");
		FW_EMPLOYEE.put("地址", "address");
		FW_EMPLOYEE.put("邮箱", "email");
		
		ZG_T_STORAGE.put("单据编号", "cuid");
		ZG_T_STORAGE.put("创建时间", "createDateString");
		ZG_T_STORAGE.put("订单号", "orderId_related");
		ZG_T_STORAGE.put("生产线", "arbpl_related");
		ZG_T_STORAGE.put("仓库名称", "lgort_related");
		ZG_T_STORAGE.put("部门名称", "deptId_related");
		ZG_T_STORAGE.put("录单人", "creatorId_related");
		ZG_T_STORAGE.put("备注", "zbz");
		ZG_T_STORAGE.put("单据状态", "state_enum");
		
		ZG_T_STORAGE_CANCLE.put("单据编号", "cuid");
		ZG_T_STORAGE_CANCLE.put("部门名称", "deptId_related");
		ZG_T_STORAGE_CANCLE.put("录单人", "creatorId_related");
		ZG_T_STORAGE_CANCLE.put("备注", "zbz");
		ZG_T_STORAGE_CANCLE.put("状态", "state_enum");
		ZG_T_STORAGE_CANCLE.put("单据日期", "createDateString");
		
		ZG_T_STORAGE_STAT.put("仓库编号", "lgort");
		ZG_T_STORAGE_STAT.put("仓库名称", "lgort_related");
		ZG_T_STORAGE_STAT.put("订单号", "orderId_related");
		ZG_T_STORAGE_STAT.put("生产线", "arbpl");
		ZG_T_STORAGE_STAT.put("半成品物料号", "matnr");
		ZG_T_STORAGE_STAT.put("半成品名称", "idnrk");
		ZG_T_STORAGE_STAT.put("单位", "msehl1");
		ZG_T_STORAGE_STAT.put("库存量", "num");
		
		ZG_T_CARPLAN_STORAGE.put("单据编号", "cuid");
		ZG_T_CARPLAN_STORAGE.put("单据日期", "createDateString");
		ZG_T_CARPLAN_STORAGE.put("领料人编号", "carUser");
		ZG_T_CARPLAN_STORAGE.put("领料人", "carUser_related");
		ZG_T_CARPLAN_STORAGE.put("领料组", "carUser_dept_Id_related");
		ZG_T_CARPLAN_STORAGE.put("线体描述", "sortf");
		ZG_T_CARPLAN_STORAGE.put("仓库名称", "storageId_related");
		ZG_T_CARPLAN_STORAGE.put("仓管员", "storageUserId_related");
		ZG_T_CARPLAN_STORAGE.put("单据状态", "carState_enum");
		
		ZG_T_CARPLAN_BATCH_STORAGE.put("单据编号", "cuid");
		ZG_T_CARPLAN_BATCH_STORAGE.put("单据日期", "createDateString");
		ZG_T_CARPLAN_BATCH_STORAGE.put("领料人编号", "carUser");
		ZG_T_CARPLAN_BATCH_STORAGE.put("领料人", "carUser_related");
		ZG_T_CARPLAN_BATCH_STORAGE.put("领料时间", "carDate");
		ZG_T_CARPLAN_BATCH_STORAGE.put("领料组", "carUser_dept_Id_related");
		ZG_T_CARPLAN_BATCH_STORAGE.put("仓库名称", "storageId_related");
		ZG_T_CARPLAN_BATCH_STORAGE.put("仓管员", "storageUserId_related");
		ZG_T_CARPLAN_BATCH_STORAGE.put("单据状态", "carState_enum");
	}
	
		
	public static Map getZG_T_ORDER_PLAN() {
		return ZG_T_ORDER_PLAN;
	}

	public static void setZG_T_ORDER_PLAN(Map zg_t_order_plan) {
		ZG_T_ORDER_PLAN = zg_t_order_plan;
	}

	public static Map getZG_T_CARPLAN() {
		return ZG_T_CARPLAN;
	}

	public static void setZG_T_CARPLAN(Map zg_t_carplan) {
		ZG_T_CARPLAN = zg_t_carplan;
	}

	public static Map getFW_EMPLOYEE() {
		return FW_EMPLOYEE;
	}

	public static void setFW_EMPLOYEE(Map fw_employee) {
		FW_EMPLOYEE = fw_employee;
	}

	public static Map getZG_T_STORAGE() {
		return ZG_T_STORAGE;
	}

	public static void setZG_T_STORAGE(Map zg_t_storage) {
		ZG_T_STORAGE = zg_t_storage;
	}

	public static Map getZG_T_STORAGE_CANCLE() {
		return ZG_T_STORAGE_CANCLE;
	}

	public static void setZG_T_STORAGE_CANCLE(Map zg_t_storage_cancle) {
		ZG_T_STORAGE_CANCLE = zg_t_storage_cancle;
	}

	public static Map getZG_T_STORAGE_STAT() {
		return ZG_T_STORAGE_STAT;
	}

	public static void setZG_T_STORAGE_STAT(Map zg_t_storage_stat) {
		ZG_T_STORAGE_STAT = zg_t_storage_stat;
	}

	public static Map getZG_T_CARPLAN_STORAGE() {
		return ZG_T_CARPLAN_STORAGE;
	}

	public static void setZG_T_CARPLAN_STORAGE(Map zg_t_carplan_storage) {
		ZG_T_CARPLAN_STORAGE = zg_t_carplan_storage;
	}

	public static Map getZG_T_CARPLAN_BATCH_STORAGE() {
		return ZG_T_CARPLAN_BATCH_STORAGE;
	}

	public static void setZG_T_CARPLAN_BATCH_STORAGE(Map zg_t_carplan_batch_storage) {
		ZG_T_CARPLAN_BATCH_STORAGE = zg_t_carplan_batch_storage;
	}
	
}
