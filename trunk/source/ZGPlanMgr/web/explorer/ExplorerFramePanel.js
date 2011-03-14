$importjs(INC_PATH.EXPLORER+"/ExplorerPanel.js");
$importjs(INC_PATH.EXPLORER+"/tree/NavTreePanel.js");
$importjs(INC_PATH.EXPLORER+"/ContextMenuPanel.js");
DRM.ExplorerFramePanel = function(config) {
	DRM.ExplorerPanel.superclass.constructor.call(this, Ext.apply(config, {
	}));
	DRM.NavTreePanel.superclass.constructor.call(this, Ext.apply(config, {
	}));
};

Ext.extend(DRM.ExplorerFramePanel, Ext.Panel, {
	layout	: "border",
	border	: false,
	frame	: false,
	id		: "explorer_frame",
	initComponent : function() {
		var scope = this;
		this.navTreePanel = new DRM.NavTreePanel({
			clickType	: this.navTreeClickType,
			region		: 'west',
			border		: false,
			width		: 200
		});
		this.navTreePanel.on("_click",function(orgArgs, extArgs){
			scope._navTreeNodeClick(orgArgs, extArgs);
		});
		this.navTreePanel.on("_contextmenu",function(orgArgs, extArgs){
			scope._navContextMenu(orgArgs, extArgs);
		});
		this.explorerPanel = new DRM.ExplorerPanel({
			region		: 'center',
			frame		: false,
			border		: false,
			hasResGrid	: this.hasResGrid,
			hasResObject: this.hasResObject,
			hasResView	: this.hasResView,
			hasResReport: this.hasResReport
		});
		this.contextMenuPanel = new DRM.ContextMenuPanel({framePanel:scope});
		this.items = [this.navTreePanel,this.explorerPanel];
		DRM.ExplorerFramePanel.superclass.initComponent.call(this);
	},
	_navTreeNodeClick: function(orgArgs, extArgs) {
		var config = {
			id		: orgArgs.node.attributes.cuid,
			closable: true
		};
		var tabId = "";
		var src	= "";
		var actionGroupId = orgArgs.node.attributes.actionGroupId;
		var bmClassId = orgArgs.node.attributes.bmClassId;
		var nameSpace = orgArgs.node.attributes.nodeValue.NAME_SPACE==null?'frame/sys':orgArgs.node.attributes.nodeValue.NAME_SPACE;
		if(actionGroupId.indexOf("ITEM-TYPE-") != -1){
			tabId = this.explorerPanel.RES_REPORT_TAB_ID;
			config.title = orgArgs.node.attributes.text+".列表";
			src = nameSpace+"/"+converClassName(bmClassId)+"/query.do";
		}else if(actionGroupId.indexOf("ITEM-MENU-") != -1) {//菜单
			tabId = orgArgs.node.attributes.nodeValue.TARGET_ID==null?this.explorerPanel.RES_OBJECT_TAB_ID:orgArgs.node.attributes.nodeValue.TARGET_ID;
			config.title = orgArgs.node.attributes.text;
			src = orgArgs.node.attributes.nodeValue.URL;
		}else if(actionGroupId.indexOf("ITEM-CRAMER") != -1) {
			return;
		}else if(actionGroupId.indexOf("ITEM-") != -1) {
			tabId = this.explorerPanel.RES_OBJECT_TAB_ID;
			config.title = orgArgs.node.attributes.text+".对象";
			src = nameSpace+"/"+converClassName(bmClassId)+"/show.do?id="+config.id;
		}else {
			return;
		}
		if(src==null){
			return;
		}
		var width = '100%';
		var height = this.explorerPanel.getHeight() - 58;
		config.html="<iframe id='"+config.id+"' src='"+src+"' width='"+width+"' height='"+height+"' frameborder='0'></iframe>";
		this.explorerPanel.addTabToRes(tabId,config);
	},
	_navContextMenu: function(orgArgs, extArgs) {
		var hideContext = orgArgs.node.attributes.hideContext;
    	if(hideContext) return;
		var bmClassId = orgArgs.node.attributes.bmClassId;
		var text = orgArgs.node.text;
		var type = orgArgs.node.attributes.nodeValue.ITEM_TYPE
		if(!type){
			var bmClassIdLabel = orgArgs.node.attributes.nodeValue.BM_CLASS_ID_LABEL;
			if(bmClassIdLabel){
				text = bmClassIdLabel;
			}
		}
		var param={
			e:orgArgs.e,
			cuid:orgArgs.node.attributes.cuid,
			bmClassId:orgArgs.node.attributes.bmClassId,
			text:text,
			type:type,
			attributes:orgArgs.node.attributes
		}
		this.contextMenuPanel.createMenu(bmClassId,param);
	}
});