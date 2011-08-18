/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTcarbomEx extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_CARBOM";
	public static final String BM_CLASS_ID = "ZG_T_CARBOM";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_ORDER_BOM_ID = "orderBomId";
	public static final String ALIAS_ORDER_ID = "orderId";
	public static final String ALIAS_PLAN_NUM = "planNum";
	public static final String ALIAS_REAL_NUM = "realNum";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_CAR_PLAN_ID = "carPlanId";
	public static final String ALIAS_ORDER_PLANBOM_ID = "orderPlanbomId";
	//date formats
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String orderBomId;
	private java.lang.String orderId;
	private java.lang.Double planNum;
	private java.lang.Double realNum;
	private java.lang.String labelCn;
	private java.lang.String carPlanId;
	private java.lang.String carId;
	private java.lang.String orderPlanbomId;
	private Double oldRealNum;
	private Double oldPlanNum;
	private String planType;
	private String lgort;
	private Double supCarNum;
	private String storageUserId;
	private String taskBomId;
	private String orderTaskId;
	private String idnrk;
	
	private  List<ZgTcarbomSuppliers> supList;

	

	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getOrderBomId() {
		return this.orderBomId;
	}
	
	public void setOrderBomId(java.lang.String value) {
		this.orderBomId = value;
	}
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String value) {
		this.orderId = value;
	}
	/**
	 * @return the planNum
	 */
	public java.lang.Double getPlanNum() {
		return planNum;
	}

	/**
	 * @param planNum the planNum to set
	 */
	public void setPlanNum(java.lang.Double planNum) {
		this.planNum = planNum;
	}

	/**
	 * @return the realNum
	 */
	public java.lang.Double getRealNum() {
		return realNum;
	}

	/**
	 * @param realNum the realNum to set
	 */
	public void setRealNum(java.lang.Double realNum) {
		this.realNum = realNum;
	}

	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.String getCarPlanId() {
		return this.carPlanId;
	}
	
	public void setCarPlanId(java.lang.String value) {
		this.carPlanId = value;
	}
	public java.lang.String getOrderPlanbomId() {
		return this.orderPlanbomId;
	}
	
	public void setOrderPlanbomId(java.lang.String value) {
		this.orderPlanbomId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("OrderBomId",getOrderBomId())
			.append("OrderId",getOrderId())
			.append("PlanNum",getPlanNum())
			.append("RealNum",getRealNum())
			.append("LabelCn",getLabelCn())
			.append("CarPlanId",getCarPlanId())
			.append("OrderPlanbomId",getOrderPlanbomId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getOrderBomId())
			.append(getOrderId())
			.append(getPlanNum())
			.append(getRealNum())
			.append(getLabelCn())
			.append(getCarPlanId())
			.append(getOrderPlanbomId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTcarbomEx == false) return false;
		if(this == obj) return true;
		ZgTcarbomEx other = (ZgTcarbomEx)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getOrderBomId(),other.getOrderBomId())
			.append(getOrderId(),other.getOrderId())
			.append(getPlanNum(),other.getPlanNum())
			.append(getRealNum(),other.getRealNum())
			.append(getLabelCn(),other.getLabelCn())
			.append(getCarPlanId(),other.getCarPlanId())
			.append(getOrderPlanbomId(),other.getOrderPlanbomId())
			.isEquals();
	}


	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}


	public java.lang.String getCarId() {
		return carId;
	}

	public void setCarId(java.lang.String carId) {
		this.carId = carId;
	}

	public String getLgort() {
		return lgort;
	}

	public void setLgort(String lgort) {
		this.lgort = lgort;
	}


	public void add(int index, ZgTcarbomSuppliers element) {
		supList.add(index, element);
	}

	public boolean add(ZgTcarbomSuppliers o) {
		return supList.add(o);
	}

	public boolean addAll(Collection<? extends ZgTcarbomSuppliers> c) {
		return supList.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends ZgTcarbomSuppliers> c) {
		return supList.addAll(index, c);
	}

	public void clear() {
		supList.clear();
	}

	public boolean contains(Object o) {
		return supList.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return supList.containsAll(c);
	}

	public ZgTcarbomSuppliers get(int index) {
		return supList.get(index);
	}

	public int indexOf(Object o) {
		return supList.indexOf(o);
	}

	public boolean isEmpty() {
		return supList.isEmpty();
	}

	public Iterator<ZgTcarbomSuppliers> iterator() {
		return supList.iterator();
	}

	public int lastIndexOf(Object o) {
		return supList.lastIndexOf(o);
	}

	public ListIterator<ZgTcarbomSuppliers> listIterator() {
		return supList.listIterator();
	}

	public ListIterator<ZgTcarbomSuppliers> listIterator(int index) {
		return supList.listIterator(index);
	}

	public ZgTcarbomSuppliers remove(int index) {
		return supList.remove(index);
	}

	public boolean remove(Object o) {
		return supList.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return supList.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return supList.retainAll(c);
	}

	public ZgTcarbomSuppliers set(int index, ZgTcarbomSuppliers element) {
		return supList.set(index, element);
	}

	public int size() {
		return supList.size();
	}

	public List<ZgTcarbomSuppliers> subList(int fromIndex, int toIndex) {
		return supList.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return supList.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return supList.toArray(a);
	}

	public List<ZgTcarbomSuppliers> getSupList() {
		return supList;
	}

	public void setSupList(List<ZgTcarbomSuppliers> supList) {
		this.supList = supList;
	}


	/**
	 * @return the oldRealNum
	 */
	public Double getOldRealNum() {
		return oldRealNum;
	}

	/**
	 * @param oldRealNum the oldRealNum to set
	 */
	public void setOldRealNum(Double oldRealNum) {
		this.oldRealNum = oldRealNum;
	}

	/**
	 * @return the oldPlanNum
	 */
	public Double getOldPlanNum() {
		return oldPlanNum;
	}

	/**
	 * @param oldPlanNum the oldPlanNum to set
	 */
	public void setOldPlanNum(Double oldPlanNum) {
		this.oldPlanNum = oldPlanNum;
	}

	/**
	 * @return the supCarNum
	 */
	public Double getSupCarNum() {
		return supCarNum;
	}

	/**
	 * @param supCarNum the supCarNum to set
	 */
	public void setSupCarNum(Double supCarNum) {
		this.supCarNum = supCarNum;
	}

	public String getStorageUserId() {
		return storageUserId;
	}

	public void setStorageUserId(String storageUserId) {
		this.storageUserId = storageUserId;
	}

	/**
	 * @return the taskBomId
	 */
	public String getTaskBomId() {
		return taskBomId;
	}

	/**
	 * @param taskBomId the taskBomId to set
	 */
	public void setTaskBomId(String taskBomId) {
		this.taskBomId = taskBomId;
	}

	/**
	 * @return the orderTaskId
	 */
	public String getOrderTaskId() {
		return orderTaskId;
	}

	/**
	 * @param orderTaskId the orderTaskId to set
	 */
	public void setOrderTaskId(String orderTaskId) {
		this.orderTaskId = orderTaskId;
	}

	/**
	 * @return the idnrk
	 */
	public String getIdnrk() {
		return idnrk;
	}

	/**
	 * @param idnrk the idnrk to set
	 */
	public void setIdnrk(String idnrk) {
		this.idnrk = idnrk;
	}
}
