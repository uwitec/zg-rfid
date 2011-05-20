DRM.ContextMenuPanel = function(config) {
	DRM.ContextMenuPanel.superclass.constructor.call(this, Ext.apply(config, {
		framePanel	: config.framePanel,
		maxMenuNum	: 3
	}));
};

function add(framePanel,obj,node) {
	var height = framePanel.explorerPanel.getHeight() - 58;
	var bmClassId = node.bmClassId;
	var text = node.text;
	var className = converClassName(bmClassId);
	var config = {
			id		: bmClassId+"."+obj.labelCn,
			closable: true,
			title	: text+"."+obj.labelCn,
			html	: "<iframe src='"+ctx+"/frame/sys/"+className+"/create.do' width='100%' height='"+height+"' frameborder='0'></iframe>"
		};
	framePanel.explorerPanel.addTabToRes(framePanel.explorerPanel.RES_VIEW_TAB_ID,config);
}

function edit(framePanel,obj,node) {
	var height = framePanel.explorerPanel.getHeight() - 58;
	var bmClassId = node.bmClassId;
	var text = node.text;
	var className = converClassName(bmClassId);
	var config = {
			id		: bmClassId+"."+obj.labelCn,
			closable: true,
			title	: text+"."+obj.labelCn,
			html	: "<iframe src='"+ctx+"/frame/sys/"+className+"/edit.do?id="+node.cuid+"' width='100%' height='"+height+"' frameborder='0'></iframe>"
		};
	framePanel.explorerPanel.addTabToRes(framePanel.explorerPanel.RES_VIEW_TAB_ID,config);
}

function query(framePanel,obj,node) {
	var height = framePanel.explorerPanel.getHeight() - 58;
	var bmClassId = node.bmClassId;
	var text = node.text;
	var className = converClassName(bmClassId);
	var config = {
			id		: bmClassId,
			closable: true,
			title	: text+"."+obj.labelCn,
			html	: "<iframe src='"+ctx+"/frame/sys/"+className+"/query.do?s_equalBmClassIdQuery=true' width='100%' height='"+height+"' frameborder='0'></iframe>"
		};
	framePanel.explorerPanel.addTabToRes(framePanel.explorerPanel.RES_VIEW_TAB_ID,config);
}

/**
function test(framePanel) {
	var height = framePanel.explorerPanel.getHeight() - 58;
	var config = {
			closable: true,
			title	: "百度",
			html	: "<iframe src='http://www.baidu.com' width='100%' height='"+height+"' frameborder='0'></iframe>"
		};
	framePanel.explorerPanel.addTabToRes(framePanel.explorerPanel.RES_VIEW_TAB_ID,config);
}**/
Ext.extend(DRM.ContextMenuPanel, Ext.menu.Menu, {
	_menus	: [],
	initComponent : function() {
	},
	_initMenuItem: function(menu,bmClassId,orgArgs) {
		var scope = this;
		menu.removeAll(true);
		ContextMenuAction.findTsysContextmenu(orgArgs, function(data) {
			if(data.length == 0) {
				menu.destroy();
				return;
			}
			var prefix = "CONTEXTMENU_"+bmClassId.toUpperCase()+"_ITEM";
			var groupPrefix = "CONTEXTMENU_"+bmClassId.toUpperCase()+"_GROUP";
			var menuContent = [];
			for(var i = 0; i < data.length; i++) {
				var obj = data[i];
				if(obj.childs && obj.childs.length > 0) {
					var childsArray = new Array();
					for(var j = 0 ; j < obj.childs.length; j++) {
						var iconPath = isNotNull(obj.childs[j].iconPath)?ctx+"/"+obj.childs[j].iconPath:Ext.BLANK_IMAGE_URL;
						var handler = scope._getHandlerFunc(obj.childs[j],orgArgs);
						childsArray[j] = new Ext.menu.Item({
							id		: prefix+obj.childs[j].labelCn,
							text	: obj.childs[j].labelCn,
							icon	: iconPath,
							handler	: handler
						});
					}
					var group = menu.add({
						id	: groupPrefix+obj.labelCn,
						text: obj.labelCn,
						menu: new Ext.menu.Menu(
						)
					});
					group.menu.add(childsArray);
				}else {
					var handler = scope._getHandlerFunc(obj,orgArgs);
					var iconPath = isNotNull(obj.iconPath)?ctx+"/"+obj.iconPath:Ext.BLANK_IMAGE_URL;
					menu.add({
						id		: prefix+obj.labelCn,
						text	: obj.labelCn,
						icon	: iconPath,
						handler	: handler
					});
				}
			}
		})
	},
	createMenu: function(bmClassId,orgArgs) {
		var menu = this._getMenu(bmClassId);
		if(!menu) {
			menu = new Ext.menu.Menu({
				id	: "CONTEXTMENU_"+bmClassId
			});
			this._refreshMenuStack(menu);
			this._initMenuItem(menu,bmClassId,orgArgs);
		}
		this._showMenu(menu,orgArgs.e);
	},
	_refreshMenuStack: function(menu) {
		if(this._menus.length >= this.maxMenuNum) {
			var popMenu = this._menus.pop();
			popMenu.destroy();
		}
		this._menus.push(menu);
	},
	_getMenu: function(bmClassId) {
		return Ext.getCmp("CONTEXTMENU_"+bmClassId);
	},
	_showMenu: function(menu,e) {
		var x = e.getPageX() , y = e.getPageY();
		menu.showAt([x,y]);
	},
	_getHandlerFunc: function(obj,node) {
		var scope = this;
		var handler = obj.handler;
		if(isNotNull(handler)) {
			if(typeof(handler) != "object")
				eval("handler = "+handler);
			if(handler.url) {
				var title = node.text+"."+handler.title;
				if(!isNotNull(title)){
					title = node.text+"."+obj.labelCn;
				}
				return function() {
					scope._defaultUrlHandler(title,handler.url,obj.targetId);
				}
			}else if(handler.func) {
				eval("var funcType = typeof("+handler.func+")");
				if(funcType != 'function')
					eval("var func = function(){}");
				else
					eval("var func = function(){"+handler.func+"(scope.framePanel,obj,node)}");
				return func;
			}else {
				return function(){};
			}
		}
	},
	_defaultUrlHandler: function(title,url,target) {
		var width = '100%';
		var height = this.framePanel.explorerPanel.getHeight() - 58;
		if(url.indexOf("www.") == 0) {
			url = "http://"+url;
		}else if(url.indexOf("http://") == -1) {
			url = ctx + "/" + url;
		}
		var config = {
			id		: title,
			closable: true,
			title	: title,
			html	: "<iframe src='"+url+"' width='"+width+"' height='"+height+"' frameborder='0'></iframe>"
		};
		this.framePanel.explorerPanel.addTabToRes(target,config);
	}
});