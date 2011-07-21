DRM.ExplorerPanel = function(config) {
	DRM.ExplorerPanel.superclass.constructor.call(this, Ext.apply(config, {
		navTreeClickType	: config.navTreeClickType,
		hasResGrid			: config.hasResGrid == null?true:config.hasResGrid,
		hasResObject		: config.hasResObject == null?true:config.hasResObject,
		hasResView			: config.hasResView == null?true:config.hasResView,
		hasResReport		: config.hasResReport == null?true:config.hasResReport
	}));
};
Ext.extend(DRM.ExplorerPanel, Ext.Panel, {
	layout				: "border",
	border				: false,
	frame				: false,
	id					: "explorer_panel",
	RES_GRID_TAB_ID		: "res_gird",	//资源维护
	RES_VIEW_TAB_ID		: "res_view",	//资源视图
	RES_REPORT_TAB_ID	: "res_report",	//资源报表
	RES_OBJECT_TAB_ID	: "res_object",	//资源对象
	initComponent : function() {
		this.viewTabPanel = new Ext.TabPanel({
			activeTab	: 0,
			frame		: true,
			region		: "center",
			tabPosition	: "bottom",
	        defaults	: {autoHeight: true}
		});
		
		this._initViewTabPanelItems(); //初始化视图标签
		this.items = [this.viewTabPanel];
		DRM.ExplorerPanel.superclass.initComponent.call(this);
	},
	_initViewTabPanelItems: function() {
		var tabArray = new Array();
		if(this.hasResGrid == true)
			tabArray.push({id:this.RES_GRID_TAB_ID,title: "资源维护",xtype:"tabpanel"});
		if(this.hasResReport == true)
			tabArray.push({id:this.RES_REPORT_TAB_ID,title: "资源报表",xtype:"tabpanel"});
		if(this.hasResObject == true)
			tabArray.push({id:this.RES_OBJECT_TAB_ID,title: "资源对象",xtype:"tabpanel"});
		if(this.hasResView == true)
			tabArray.push({id:this.RES_VIEW_TAB_ID,title: "资源视图",xtype:"tabpanel"});
		for(var i = 0 ; i < tabArray.length; i++) {
			var obj = tabArray[i];
			this.viewTabPanel.add({id:obj.id,title: obj.title,xtype:obj.xtype});
			var config = {
				id		:obj.id+"_index",
				title	:obj.title+"首页",
				closable:false
			};
			this.addTabToRes(obj.id,config);
		}
		var tabPanel = this.viewTabPanel.getItem(this.RES_GRID_TAB_ID);
		this.viewTabPanel.activate(tabPanel);
	},
	addTabToRes: function(tabId,config) {
		var tabPanel = this.viewTabPanel.getItem(tabId);
		this.viewTabPanel.activate(tabPanel);
		if(tabPanel) {
			var childTab = tabPanel.getItem(config.id);
			if(!childTab) {
				childTab = tabPanel.add(config);
			}
			tabPanel.activate(childTab);
		}else
			Ext.example.msg("错误", "没有找到ID为"+tabId+"的标签页!");
	}
});