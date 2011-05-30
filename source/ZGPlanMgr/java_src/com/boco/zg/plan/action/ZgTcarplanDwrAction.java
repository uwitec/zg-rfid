
package com.boco.zg.plan.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseDwrAction;
import javacommon.util.StringHelper;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.service.ZgTcarbomExBo;
import com.boco.zg.plan.service.ZgTcarplanExBo;
import com.boco.zg.util.Constants;
import com.boco.zg.util.TimeFormatHelper;

@SuppressWarnings("unchecked")
public class ZgTcarplanDwrAction extends BaseDwrAction {

	private ZgTcarplanExBo getZgTcarplanExBo() {
		return (ZgTcarplanExBo) ApplicationContextHolder
				.getBean("zgTcarplanExBo");
	}
	
	private ZgTcarbomExBo getZgTcarbomExBo() {
		return (ZgTcarbomExBo) ApplicationContextHolder.getBean("zgTcarbomExBo");
	}

	/**
	 * 仓库领料提交
	 * @param carPlanId 领料单编号
	 * @param storageUserId 仓管员信息
	 * @return
	 * @throws IOException
	 */
	public boolean submitOneCarPlan(String carPlanId,String storageUserId) throws IOException {
		boolean flag = true;
		try {
			getZgTcarplanExBo().updateCarPlanStorageUserId(carPlanId, storageUserId);
			getZgTcarplanExBo().carPlanSubmit(carPlanId);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	static Map<String,String> orderBomMap=new LinkedHashMap<String,String>();
	static
	{
		orderBomMap.put("物料号","has");
		orderBomMap.put("订单编号","AUFNR");
		orderBomMap.put("需求数量","CAR_NUM");
		orderBomMap.put("计划领取数量","PLAN_NUM");
		orderBomMap.put("排序日期","PXDAT");
		orderBomMap.put("销售订单号","KDAUF");
		orderBomMap.put("生产厂","PLANT");
		orderBomMap.put("生产线","ARBPL");
		orderBomMap.put("物料描述","MAKTX1");
		orderBomMap.put("度量单位","MSEHL1");
	}
	
	
	public String ExportCarPlan(HttpServletRequest request, String carPlanId)
			throws Exception {
		Map<String,List<Map>> map = new HashMap<String,List<Map>>();
		List<Map> list = getZgTcarbomExBo().findBomByCarPlanId(carPlanId);
		for(Map bom : list) {
			String idnrk = IbatisDAOHelper.getStringValue(bom, "IDNRK");
			List<Map> boms = map.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<Map>();
			}
			boms.add(bom);
			map.put(idnrk, boms);
		}
		String fileName = TimeFormatHelper.getFormatDate(new Date(),TimeFormatHelper.TIME_FORMAT_B) + ".xls";
		String destFileName = getFilePath(request) + fileName;
		this.createExcel(map,destFileName);
		// 获取模板
		System.out.println("导出CarrierCardEffect.xls结束......");
		String destFilePath = getFileLink(request,fileName);
		return destFilePath;
	}
	
	private void createExcel(Map<String, List<Map>> map, String destFileName) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		this.addSheetHeader(sheet);
		Iterator ite = map.keySet().iterator();
		int rows = 0;
		while(ite.hasNext()){
			String idnrk = ite.next().toString();
			List<Map> list = map.get(idnrk);
			this.addExcelRowByIdnrk(idnrk,list,sheet,rows);
			if(list!=null && list.size()>0){
				rows += list.size();
			}
		}
		FileOutputStream fOut = new FileOutputStream(destFileName);
		workbook.write(fOut);
		fOut.flush();
		fOut.close();
		System.out.println("文件生成...");
	}
	
	/**
	 * 增加EXCEL的头
	 * @param sheet
	 */
	private void addSheetHeader(HSSFSheet sheet) {
		HSSFRow row = sheet.createRow((short)0);
		Iterator ite = orderBomMap.keySet().iterator();
		int i=0;
		while(ite.hasNext()){
			String context = ite.next().toString();
			HSSFCell cell = this.createCell(row,i);
			cell.setCellValue(context);
			i++;
		}
	}

	private void addExcelRowByIdnrk(String idnrk, List<Map> list,
			HSSFSheet sheet, int rows) {
		for(int i=1;i<=list.size();i++){
			HSSFRow row = sheet.createRow((short)rows+i);
			Map map = list.get(i-1);
			this.addRowValue(row,map,idnrk);
		}
		if(list!=null && list.size()>0){
			int endRow = rows+list.size();
			sheet.addMergedRegion(new Region(rows+1,(short)0,endRow,(short)0)); 
		}
	}

	
	
	private void addRowValue(HSSFRow row, Map map, String idnrk) {
		HSSFCell cell0 = row.createCell((short) 0);
		cell0.setCellValue(idnrk);
		Integer planNum = 0;
		if(map.get("CAR_NUM")!=null && !map.get("CAR_NUM").equals("") && map.get("COMPLETE_NUM")!=null && !map.get("COMPLETE_NUM").equals("") ){
			planNum = Integer.parseInt(map.get("CAR_NUM").toString())-Integer.parseInt(map.get("COMPLETE_NUM").toString());
		}
		HSSFCell cell2 = row.createCell((short) 2);
		cell2.setCellValue(planNum);
		Iterator ite = orderBomMap.keySet().iterator();
		int i=0;
		while(ite.hasNext()){
			String context = ite.next().toString();
			String field = orderBomMap.get(context);
			if(i==0 || i==2){
				i++;
				continue;
			}
			HSSFCell cell = this.createCell(row,i);
			cell.setCellValue(map.get(field)==null?"":map.get(field).toString());
			i++;
		}
	}

	private HSSFCell createCell(HSSFRow row, int index) {
		HSSFCell cell = row.createCell((short) index);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		return cell;
	}

	private String getFilePath(HttpServletRequest request) {
		String filePath = request.getSession().getServletContext()
		.getRealPath("/")+ "exportFile";
		this.createDir(filePath);
		String exportPath = filePath + File.separator;
		return exportPath;
	}
	
	private void createDir(String path) {
		File d = new File(path);
		if (!d.exists()) {
			d.mkdirs();
		}
	}
	
	private String getFileLink(HttpServletRequest request, String filename) {
		String contextPath = request.getContextPath() + "/" + "exportFile" + "/" + filename;
		return contextPath;
	}
	
	/**
	 * 根据领料人id获取该领料人的领料仓库列表
	 * @param userId
	 * @return
	 */
	public List<Map> getLgortListByUserId(String userId){
		return getZgTcarplanExBo().getLgortListByUserId(userId);
	}
	
	/**
	 *(乐观锁)预装，总装，预焊装车计划生成的时候检验
	 * 	判断该装车计划是否已经提交 主要是校验bom状态是否有改变
	 * @return
	 */
	public String checkForSubmitCarPlan(String objcetJOSNs,String carPlanId){
		
		
		ZgTcarplan zgTcarplan = (ZgTcarplan)getZgTcarplanExBo().getById(carPlanId);
		if(null!=zgTcarplan){
			if(Constants.CarPlanStatus.SUBMIT.value().equals(zgTcarplan.getCarState())&&Constants.CarPlanType.STOREGETDATA.value().equals(zgTcarplan.getType())){
				return "该装车计划已经被领取，请确认";
			}
		}
		
		
		JSONArray jsonArray=JSONArray.fromObject(objcetJOSNs);
		String orderPlanBomIds="";
		String aufnr="";
		String idnrk="";
		Long num=0l;
		
		List<Map> bomList=new ArrayList<Map>();
		for (int i = 0; i < jsonArray.size(); i++) {//因为浏览器双击，有时候会向后台发送两次请求，生成两条同样数据，防止有两个一样的bom记录，这里负责合并
			JSONObject jsonObj=jsonArray.getJSONObject(i);
			
			String orderPlanbomId = jsonObj.getString("orderPlanbomId");
			Long carPlanNum=Long.parseLong(jsonObj.getString("carPlanNum"));
			aufnr=jsonObj.getString("aufnr");
			idnrk=jsonObj.getString("idnrk");
			
			boolean flag=false;
			for(int j=0;j<bomList.size();j++){
				Map bomMap=bomList.get(j);
				if(bomMap.get("orderPlanbomId").toString().equals(orderPlanbomId)){//相同的bom合并
					carPlanNum=Long.parseLong(bomMap.get("carPlanNum").toString())+carPlanNum;
					bomMap.put("carPlanNum", carPlanNum);
					flag=true;
					break;
				}
			}
			
			if(!flag){
				Map bomMap=new HashMap();
				bomMap.put("orderPlanbomId", orderPlanbomId);
				bomMap.put("carPlanNum", carPlanNum);
				bomMap.put("aufnr", aufnr);
				bomMap.put("idnrk", idnrk);
				bomList.add(bomMap);
			}
			
			
			
		}
		
		String planboms="";
		for (int i = 0; i < bomList.size(); i++) {//检查数量是否超过需求数量
			JSONObject jsonObj=jsonArray.getJSONObject(i);
			Map bomMap=bomList.get(i);
			
			
			String orderPlanbomId = IbatisDAOHelper.getStringValue(bomMap,"orderPlanbomId");
			
			Long carPlanNum=Long.parseLong(IbatisDAOHelper.getStringValue(bomMap,"carPlanNum"));
			aufnr=IbatisDAOHelper.getStringValue(bomMap,"aufnr");
			idnrk=IbatisDAOHelper.getStringValue(bomMap,"idnrk");
			
			planboms=planboms+orderPlanbomId+"','";
			
			num = getZgTcarbomExBo().checkForBomCarNum(orderPlanbomId,carPlanNum);
			
			if(num<0){//领料数量超过需求数量
				break;
			}
		}
		
		if(num<0){
			return "订单号-"+aufnr+"  BOM-"+idnrk+"  组件领取数量超出总需求数量:"+(num*(-1))+"，请确认!"; 
		}
		
		if(planboms.length()>0){
			planboms=planboms.substring(0,planboms.length()-3);
		}
		
		return getZgTcarplanExBo().checkOrderLock(planboms);
		
		
	}
	
	/**
	 *(乐观锁)批量领料计划保存，提交时校验
	 *	1 判断表单是否已经提交
	 *  2 判断bom组件的计划的实际领取数量是否发生变化
	 * @return
	 */
	public boolean checkForSaveOrSubmitBatchStorage(String objcetJOSNs,String carPlanId){
		
		//1 判断表单是否已经提交
		//carPlanId
		ZgTcarplan zgTcarplan = (ZgTcarplan)getZgTcarplanExBo().getById(carPlanId);
		if(Constants.CarPlanStatus.SUBMIT.value().equals(zgTcarplan.getCarState())&&Constants.CarPlanType.STOREGETDATA.value().equals(zgTcarplan.getType())){
			return false;
		}
		
		// 2 判断bom组件的计划的实际领取数量是否发生变化
		JSONArray jsonArray=JSONArray.fromObject(objcetJOSNs);
		String cuids="";
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj=jsonArray.getJSONObject(i);
			cuids=cuids+jsonObj.getString("cuid")+"','";
		}
		
		if(cuids.length()>0){
			cuids=cuids.substring(0, cuids.length()-3);
		}
		
		List<Map> list =getZgTcarbomExBo().getCarBomList(cuids);
		boolean result=true;
		
		for (int i = 0; i < jsonArray.size(); i++) {
			boolean flag=true;
			
			JSONObject jsonObj=jsonArray.getJSONObject(i);
			String cuid=jsonObj.get("cuid").toString();
			String oldRealNum=jsonObj.getString("oldRealNum");
			for (Map map:list) {
				if(map.get("CUID").toString().equals(cuid)){
					if(!map.get("REAL_NUM").toString().equals(oldRealNum)){
						flag=false;
						result=false;
						break;
					}
				}
			}
			if(!flag) break;
		}
		return result;
		
	}
	
	/**
	 *(乐观锁)领料计划保存，提交时校验
	 *	1 判断表单是否已经提交
	 * 	2 判断领取数量是否大于需求数量
	 * @return
	 */
	public boolean checkForSaveOrSubmitStorage(String objcetJOSNs,String carPlanId){
		
		//1 判断表单是否已经提交
		//carPlanId
		ZgTcarplan zgTcarplan = (ZgTcarplan)getZgTcarplanExBo().getById(carPlanId);
		if(Constants.CarPlanStatus.SUBMIT.value().equals(zgTcarplan.getCarState())&&Constants.CarPlanType.STOREGETDATA.value().equals(zgTcarplan.getType())){
			return false;
		}
		
		//	2 判断领取数量是否大于需求数量
		JSONArray jsonArray=JSONArray.fromObject(objcetJOSNs);
		String orderPlanbomIds="";
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj=jsonArray.getJSONObject(i);
			orderPlanbomIds=orderPlanbomIds+jsonObj.getString("orderPlanbomId")+"','";
		}
		
		if(orderPlanbomIds.length()>0){
			orderPlanbomIds=orderPlanbomIds.substring(0, orderPlanbomIds.length()-3);
		}
		
		List<Map> list =getZgTcarbomExBo().getrPlanbomList(orderPlanbomIds);
		boolean result=true;
		
		for (int i = 0; i < jsonArray.size(); i++) {
			boolean flag=true;
			JSONObject jsonObj=jsonArray.getJSONObject(i);
			String orderPlanbomId=jsonObj.get("orderPlanbomId").toString();
			Long realNum=Long.parseLong(jsonObj.get("planNum").toString());
			for (Map map:list) {
				if(map.get("CUID").toString().equals(orderPlanbomId)){
					Long carNum=Long.parseLong(map.get("CAR_NUM").toString());
					Long completeNum=Long.parseLong(map.get("COMPLETE_NUM").toString());
					if(realNum+completeNum>carNum){
						flag=false;
						result=false;
						break;
					}
				}
			}
			if(!flag) break;
		}
		return true;
		
	}
	
	/**
	 *(乐观锁)领料计划保存，提交时校验
	 *	1 判断表单是否已经提交
	 * @return
	 */
	public boolean checkForSubmitCarplan1(String carPlanId){
		
		//1 判断表单是否已经提交
		//carPlanId
		ZgTcarplan zgTcarplan = (ZgTcarplan)getZgTcarplanExBo().getById(carPlanId);
		if(Constants.CarPlanStatus.SUBMIT.value().equals(zgTcarplan.getCarState())){
			return false;
		}
		return true;
	}
	
	/**
	 * 检查领料员是否有装车计划
	 * @param planType
	 * @param request
	 * @return
	 */
	public  boolean checkHasCarPlan(String planType,HttpServletRequest request) {
		return getZgTcarplanExBo().checkHasCarPlan(getSessionOperatorId(request),planType);
	}
	
	/**
	 * 生成装车计划时，如果是追加的话，判断前后的仓库是否一样
	 * @param lgort
	 * @planType
	 * @return
	 */
	public boolean checkForLgort(String lgort,String planType,HttpServletRequest request){
		return getZgTcarplanExBo().checkForLgort(lgort,planType,getSessionOperatorId(request));
	}
	
	/**
	 * 获取bom的锁定装车人
	 * @return
	 */
	public String getLockUser(String taskBomId){
		return getZgTcarplanExBo().getLockUser(taskBomId);
	}
	
	/**
	 * 获取员工装车计划编号，如果只有一个装车计划，则返回计划编号，否则返回空
	 * @param planType
	 * @param request
	 * @return
	 */
	public String getCarPlanByUserId(String planType,HttpServletRequest request){
		List<Map> carPlanList=getZgTcarplanExBo().getCarPlanByUserId(getSessionOperatorId(request),planType,"");
		return carPlanList.size()==1?IbatisDAOHelper.getStringValue(carPlanList.get(0), "CUID"):"";
	}
	
	
}
