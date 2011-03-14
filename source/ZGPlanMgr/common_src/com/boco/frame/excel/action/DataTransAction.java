package com.boco.frame.excel.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.excel.ExcuteHelper;


/**
 * BomCarAction类
 * @author xh
 * 说明：导入导出Excel的Action类
 */
public class DataTransAction extends BaseStruts2Action{
	
	
	protected static final String RESULT_JSP = "/zg/tempFile/importResult.jsp";
	
	/*
	 * 属性
	 * */
	
	private String fileName;
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	private String folderName;
	
	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	
	private Map<Integer, String> errorMap;
	
	public Map<Integer, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, String> errorMap) {
		this.errorMap = errorMap;
	}
	
	private File upload;
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	/*
	 * 方法
	 * 默认输入参数包括 template/in/out，示例如下：
	 * template=exportUser&in=name:张$age:16
	 * */
	public String export()
	{
		// 从spring中获取实例
		ExcuteHelper helper=(ExcuteHelper)ApplicationContextHolder.getBean("xlsExport");
		
		// 获得输入参数
		//String inString=getRequest().getParameter("in");
			
		HashMap<String, String>	inparams=new HashMap<String, String>();
		
		if (getRequest().getSession().getAttribute("ex_in")!=null)
		{
			inparams=(HashMap<String, String>)getRequest().getSession().getAttribute("ex_in");//createMapParam(inString);
		}
		//inparams.put("name","张");
		
		// 获得导出路径
		String exparams=getRequest().getSession().getServletContext().getRealPath("zg/tempFile/export/");
		
		try {
			//helper.run(getRequest().getParameter("template"),inparams,exparams);
			helper.run((String)getRequest().getSession().getAttribute("ex_template"), inparams, exparams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.folderName=helper.getResultObject().toString();
		
		this.fileName="download.xls";

		return SUCCESS;
	}
	
	/*
	 * 方法
	 * 默认输入参数包括 template/in/out，示例如下：
	 * template=exportUser
	 * */
	public String importData()
	{
		Map<Integer, String> result=new LinkedHashMap<Integer, String>();
		
		// 获取文件的路径
		String saveurl=getRequest().getSession().getServletContext().getRealPath("zg/tempFile/import/")+"\\" + upload.getName();
		try {
			
			
			
			// 上传文件到服务器目录
			java.io.InputStream is = new java.io.FileInputStream(upload);
			java.io.OutputStream os = new java.io.FileOutputStream(saveurl);
	        byte buffer[] = new byte[8192];
	        int count = 0;
	        while((count = is.read(buffer)) > 0)
	        {
	            os.write(buffer, 0, count);
	        }
	        os.close();
	        is.close();
			
			// 定义相关接口,进行数据导入
			ExcuteHelper helper=(ExcuteHelper)ApplicationContextHolder.getBean("xlsImport");
			
			// 暂时默认没有out参数
			helper.run(getRequest().getParameter("template"),saveurl,null);
			
			// 写入返回结果
			this.errorMap=(Map<Integer, String>)helper.getResultObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		getRequest().setAttribute("result", this.errorMap);
		

		
		
		//return "/zg/bomAndCar/bomCarImport.jsp";
		/*try {
			rendHtml("window.close()");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		return RESULT_JSP;
	}
	
	
	public InputStream getInputStream() throws Exception {
		InputStream is = null;
		try {
			is = new FileInputStream(folderName);
		} catch (RuntimeException e) {
			is = new ByteArrayInputStream("文件下载失败".getBytes());
			e.printStackTrace();
		}
		return is;
	}
	
	// 通过字符串（name:张$age:16） 获取参数
	protected HashMap<String, String> createMapParam(String str) {
		
		HashMap<String, String> resultMap=new HashMap<String, String>();
		
		if (str!=null)
		{
			String[] strList=str.split("#");
			
			for(int i=0;i<strList.length;i++)
			{
				if (!strList[i].equals(""))
				{
					String[] values=strList[i].split("&");
					String name;
					String value;
					if (values.length!=2)
					{
						name=values[0];
						value="";
					}
					else
					{
						name=values[0];
						
						// 当需要设定值为null的时候，需要传入“null”字符串
						if (values[1].equals("null"))
						{
							value=null;
						}
						else {
							value=values[1];
						}
					}
					
					resultMap.put(name, value);
					
					
				}
			}
		}
		
		return resultMap;
	}


}
