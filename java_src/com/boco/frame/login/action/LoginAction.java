package com.boco.frame.login.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.util.StringHelper;

import com.boco.frame.login.pojo.LoginForm;
import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.login.service.LoginBo;
import com.boco.frame.sys.base.model.FwSystemNotice;
import com.boco.frame.sys.base.service.FwSystemNoticeBo;
import com.boco.zg.bom.base.service.ZgTbomManager;

@SuppressWarnings("unchecked")
public class LoginAction extends BaseStruts2Action {
	
	private static final long serialVersionUID = -8246222693568282916L;

	protected static final String LOGIN_PAGE = "/login.jsp";
	
	protected static final String LOGINBYK_PAGE = "/loginByK.jsp";
	
	protected static final String LOGIN_SUCCESS = "/frame/default/mainframe/mainframe.jsp";

	private LoginBo loginBo;
	
	private LoginForm loginForm;
	
	private String loginType;
	
	private ZgTbomManager zgTbomManager;
	
	private FwSystemNoticeBo fwSystemNoticeBo;
	
	public String login() {
		
		if(loginForm == null) return LOGIN_PAGE;
		Map map = loginBo.login(loginForm);
		String loginType = this.getRequest().getParameter("loginType");
		this.getRequest().setAttribute("loginType",loginType);
		if(map.get("success").equals("true")) {
			OperatorInfo operatorInfo = (OperatorInfo)map.get("result");
			operatorInfo.setIp(this.getRequest().getRemoteAddr());
			this.getRequest().getSession().setAttribute("operatorInfo", operatorInfo);
			boolean isAdmin = false;
			if(operatorInfo.getUserId().equals("sysadmin")) {
				isAdmin = true;
			}
			this.getRequest().getSession().setAttribute("isAdmin", isAdmin);
			this.getRequest().getSession().setAttribute("_theme", loginForm.getTheme());
			this.getRequest().getSession().setAttribute("loginLgort", null);
			this.getRequest().setAttribute("operatorInfo", operatorInfo);
			
			//判断登陆人是否是领料员 如果是刚登陆界面的默认页面是领料页面
			String defaultUrl=loginBo.getDefaultUrl(operatorInfo.getOperatorId());
			String url="/frame/default/mainframe/body.jsp";
			if(!StringHelper.isEmpty(defaultUrl)){
				url=defaultUrl;
			}
			
			this.getRequest().getSession().setAttribute("defaultUrl", url);
			this.getRequest().getSession().setAttribute("isLLY", !StringHelper.isEmpty(defaultUrl));
			
			//查看是否有bom未设置装车信息 是则显示在公告条中
			boolean flag=zgTbomManager.findNullCarInfoBom();
			if(flag){
				getRequest().getSession().setAttribute("nullCarFlag", "1");
			}
			
			List<FwSystemNotice> sysNoticeList=fwSystemNoticeBo.findAll();
			getRequest().getSession().setAttribute("sysNoticeList", sysNoticeList);
			
			return LOGIN_SUCCESS;
		}else {
			String reason = (String)map.get("reason");
			this.getRequest().setAttribute("loginFlag", reason);
			return LOGIN_PAGE;
		}
	}
	
	public String loginOut() throws IOException{
		getSession().invalidate();
		return LOGIN_PAGE;
//		((Object) getSession()).Abandon();
//		httpse
	}
	
//	/**
//	 * 卡号登陆
//	 * @return
//	 */
//	public String loginByK() {
//		if(loginForm == null) return LOGINBYK_PAGE;
//		Map map = loginBo.login(loginForm);
//		if(map.get("success").equals("true")) {
//			OperatorInfo operatorInfo = (OperatorInfo)map.get("result");
//			operatorInfo.setIp(this.getRequest().getRemoteAddr());
//			this.getRequest().getSession().setAttribute("operatorInfo", operatorInfo);
//			boolean isAdmin = false;
//			if(operatorInfo.getUserId().equals("sysadmin")) {
//				isAdmin = true;
//			}
//			this.getRequest().getSession().setAttribute("isAdmin", isAdmin);
//			this.getRequest().getSession().setAttribute("_theme", loginForm.getTheme());
//			this.getRequest().getSession().setAttribute("loginLgort", loginForm.getLgort());
//			return LOGIN_SUCCESS;
//		}else {
//			String reason = (String)map.get("reason");
//			this.getRequest().setAttribute("loginFlag", reason);
//			return LOGINBYK_PAGE;
//		}
//	}
	
	public String changeTheme() {
		this.getRequest().getSession().setAttribute("_theme", loginForm.getTheme());
		return LOGIN_PAGE;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

	public void setLoginBo(LoginBo loginBo) {
		this.loginBo = loginBo;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public void setZgTbomManager(ZgTbomManager zgTbomManager) {
		this.zgTbomManager = zgTbomManager;
	}

	public FwSystemNoticeBo getFwSystemNoticeBo() {
		return fwSystemNoticeBo;
	}

	public void setFwSystemNoticeBo(FwSystemNoticeBo fwSystemNoticeBo) {
		this.fwSystemNoticeBo = fwSystemNoticeBo;
	}
}
