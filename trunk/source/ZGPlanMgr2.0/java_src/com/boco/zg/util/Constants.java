package com.boco.zg.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * @author wengq
 *
 */
public class Constants {
	public static String isManulFinished="0";//手工结单
	public static String isNotManulFinished="1";//非手工结单
	
	public static String QUALITY="1";
	public static String NOTQUALITY="-1";
	public static String NEEDPLANSORTF="ABC','ABD','ABE";
	public static String ORDERCHANGESORTF="ABCBACK','ABDBACK','ABEBACK";//工单变更引起退换料
	public static String MANULCHANGESORTF="CHANGE','BACK','RENEW";//人工退换料
	public static String ALLCHANGESORTF="CHANGE','BACK','RENEW','ABCBACK','ABDBACK','ABEBACK";//人工退换料
	public final static int ADD = 1;
	public final static int EDIT = 2;  //未做任何操作 /修改
	public final static int DELETE = 3;
	
	
	
	/*领料计划状态
	*/
	public enum OrderStatus {
		DEL("-1","删除"),
		NEW("0", "新建"),
		SAVE("4", "保存"),
		SUBMIT("8", "已提交"),
		FINISHED("9", "完成"),
		MANULFINISHED("10", "手工结单");
		
		private String value;

		private String name;

		private OrderStatus(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*
	 * 领料计划bom状态
	 */
	public  enum OrderPlanbomStatus{
		WAIT("0","待处理"),
		SAVE("4","待处理"),
		SUBMIT("4","待处理");
		
		private String value;
		private String name;
		
		private OrderPlanbomStatus(String value,String name){
			this.value = value;
			this.name = name;
		}
		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	/*领料计划状态
	*/
	public enum OrderPlanStatus {
		PAUSE("-2","暂停"),
		PLAN("-1","计划中"),//排序数据已经过来，但是bom标识还没有经过调整提交的
		NEW("0", "新建"),
		WAITAUDITING("1","待审核"),
		REJECTAUDITING("2","审核退回"),
		SAVE("4", "保存"),
		SUBMIT("8", "已提交/已领料完成（需要退料）"),
		FINISHED("9", "完成");
		
		private String value;

		private String name;

		private OrderPlanStatus(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*装车计划状态
	*/
	public enum CarPlanStatus {
		NEW("0", "新建"),
		WAITAUDITING("1","待审核"),
		REJECTAUDITING("2","审核退回"),
		SAVE("4", "保存"),
		SUBMIT("8", "已提交"),
		DONE("9", "完成");
		
		private String value;

		private String name;

		private CarPlanStatus(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	/*换料计划状态
	*/
	public enum ChangePlanStatus {
		SAVE("-8", "未提交"),
		REJECTAUDITING("-7","厂领导审核退回"),
		WAITAUDITING("-6", "待厂领导审核"),
		WAITAUDITINGC("-4", "待品质部审核"),
		DONE("0", "审批完成");
		
		private String value;

		private String name;

		private ChangePlanStatus(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	/*装车计划类型
	 * 因为装车计划与仓库领料是用同一张表，所以用类型来区分
	 * 1 装车计划
	 * 2 仓库领料
	*/
	public enum CarPlanType {
		CARPLAN("1", "装车计划"),
		STOREGETDATA("2", "仓库领料");
		
		private String value;

		private String name;

		private CarPlanType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*领料计划类型
	 *1:预装，2:预焊，3：批量,4：总装
	*/
	public enum OrderPlanType {
		YZTYPE("ABD", "预装"),
		YHTYPE("ABC", "预焊"),
		PLTYPE("3", "批量"),
		ZZTYPE("ABE", "总装"),
		RENEW("RENEW","补领料"),
		CHANGE("CHANGE","换料"),
		BACK("BACK","退料");

		
		private String value;

		private String name;

		private OrderPlanType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*
	 * 半成品入库状态
	*/
	public enum EBomStorageStatus {
		NEW("0", "需要入库"),
		DONE("1", "入库完成"),
		STORAGING("2", "正在入库"),
		CANCLE("3", "正在冲单");
		
		private String value;

		private String name;

		private EBomStorageStatus(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*
	 * 库存管理出毛主席
	*/
	public enum InOutStorageType {
		IN("1", "入库"),
		OUT("2", "出库");
		
		private String value;

		private String name;

		private InOutStorageType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	public enum ReasonStatus {
		PEAPLE("1","人为原因"),
		YUANCAILIAO("2","原材料"),
		LIUCANG("3","留仓待用"),
		BAOFEI("4","建议报废"),
		FANGONG("5","退回工厂返工"),
		OTHER("6","其他");
		
		private String value;

		private String name;

		private ReasonStatus(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	 public enum TypeStatus{
		    FACTORY("factory","工厂审核"),
			QUALITY("quality","品质部审核");
			
			private String value;

			private String name;

			private TypeStatus(String value, String name) {
				this.value = value;
				this.name = name;
			}

			public String value() {
				return this.value;
			}

			public String displayName() {
				return this.name;
			}
	 }
	/**接收数据*/
	public final static String INTERFACE_DATA_STAUTS_INIT="0";
	/**调用业务接口成功*/
	public final static String INTERFACE_DATA_STAUTS_SUCCESS="1";
	/**调用握手接口失败*/
	public final static String INTERFACE_DATA_STAUTS_FAILURE_INVOKE_ALIVE="2";
	/**调用业务接口时失败*/
	public final static String INTERFACE_DATA_STAUTS_FAILURE="3";
	/**业务数据处理异常*/
	public final static String INTERFACE_DATA_STAUTS_EXP="4";
	/**服务提供方*/
	public final static String SER_CALLER="JOC";
	
	public final static String SYN_BOM_SAP_METHOD="ZRFC_RFIDMSEG_TBSAP889";
	public static final String SYSTEMUSER = "system";
	
	public enum OverTimeType {
		DELATE_FORBID("0", "超时不可领料"),
		DELATE_UNFORBID("1", "超时可领料");
		
		private String value;
		private String name;

		private OverTimeType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*领料计划状态
	*/
	public enum Plant {
		C01("C01", "总装工厂"),
		C02("C02", "预装工厂"),
		C03("C03", "预焊工厂"),
		C04("C04", "总装工厂"),
		C05("C05", "预装工厂"),
		C06("C06", "预焊工厂");
		
		private String value;

		private String name;

		private Plant(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*领料计划状态
	*/
	public enum orgType {
		dept("1", "部门"),
		plant("2", "工厂"),
		arbpl("3", "生产线"),
		ll("4", "领料线");
		
		private String value;

		private String name;

		private orgType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*总装 预装 预焊
	*/
	public enum sorft {
		ABC("ABC", "预焊"),
		ABD("ABD", "预装"),
		ABE("ABE", "总装");
		
		private String value;

		private String name;

		private sorft(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*订单冻结
	*/
	public enum OrderFreezeState {
		normal("0", "正常状态"),
		ABC("1", "预焊冻结"),
		ABD("2", "预装冻结"),
		ABCABD("3", "预装预焊同时冻结"),
		FREEZE("4", "整单冻结");	
		private String value;

		private String name;

		private OrderFreezeState(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*订单变更接口订单状态
	 * WHEN 'I0076'.
        STATTAB-STATTXT = '删除标志'.
    WHEN 'I0043'.
        STATTAB-STATTXT = '冻结'.
    WHEN 'I0045'.
        STATTAB-STATTXT = '技术完成'.
	*/
	public enum InterFaceOrderState {
		Del("I0076", "删除标志"),
		Freeze("I0043", "冻结"),
		Finished("I0045", "技术完成");	
		private String value;

		private String name;

		private InterFaceOrderState(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*订单变更接口订单状态
	 * WHEN 'I0076'.
        STATTAB-STATTXT = '删除标志'.
    WHEN 'I0043'.
        STATTAB-STATTXT = '冻结'.
    WHEN 'I0045'.
        STATTAB-STATTXT = '技术完成'.
	*/
	public enum InterFaceSynBomSap {
		SUCCESS("101", "数据已成功写入！"),
		FAIL("009", "数据写入失败！");
		private String value;
		private String name;

		private InterFaceSynBomSap(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	public enum InterFaceMethod {
//		functionMap.add("ZSTFC_CONNECTION_RFID_01");
//		functionMap.add("ZSTFC_CONNECTION_RFID_02");
//		functionMap.add("ZSTFC_CONNECTION_RFID_03");
//		functionMap.add("ZSTFC_CONNECTION_RFID_04");
//		functionMap.add("ZSTFC_CONNECTION_RFID
		
		PX("ZSTFC_CONNECTION_RFID_01", "排序接口"),
		PC("ZSTFC_CONNECTION_RFID_02", "排产"),
		BATCH("ZSTFC_CONNECTION_RFID_03", "批量领料物料"),
		BG("ZSTFC_CONNECTION_RFID_04", "变更"),
		SYN("ZSTFC_CONNECTION_RFID_05", "领料BOM同步SAP");
		private String value;
		private String name;

		private InterFaceMethod(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	/*排序类型：
	 * 
	*/
	public enum PxType {
		PC("pc","排产"),
		PX("px", "排序"),
		BG("bg", "变更");
		
		private String value;

		private String name;

		private PxType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String value() {
			return this.value;
		}

		public String displayName() {
			return this.name;
		}
	}
	
	
	
	
}
