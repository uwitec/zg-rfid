DRM.NavTreePanel = function(config) {
	DRM.NavTreePanel.superclass.constructor.call(this, Ext.apply(config, {
		treeTemplateId	: config.treeTemplateId,
		boName			: config.boName,
		clickType		: typeof(config.clickType) == 'undefined'? 'click' : config.clickType,
		cardSize		: 3
	}));
};

Ext.extend(DRM.NavTreePanel, Ext.Panel, {
	layout	: "border",
	border	: false,
	frame	: false,
	id		: "res_browser_tree",
	initComponent : function() {
		this.manualPanel = new DRM.NTManualPanel({
			region		: "north",
			height		: 36,
			border		: false,
			frame		: true,
			bodyBorder	: true,
			layout		: 'column',
			boName		: this.boName,
			selectFirst	: true
		});
		this.cardPanel = new Ext.Panel({
			region		: "center",
			layout		: "card",
			activeItem	: 0,
			frame		: false,
			border		: true,
			items		: [{}]

		});
		this.cardStack = [];
		this.items = [this.manualPanel, this.cardPanel];
		DRM.NavTreePanel.superclass.initComponent.call(this);
		
		this.addEvents("_contextmenu");// 右键事件
		this.addEvents("_click");

		// 模板更改的响应
		this.manualPanel.on("templateChanged",function(templateId, treeObject) {
			var needInit = this._initTreePanel(templateId, treeObject);
			if (needInit) {
				this.treePanel.initTree(templateId, treeObject);
			}
		}, this);

		// 2.定义事件
		this.manualPanel.on("resultshow", function(templateId) {
			this.getLayout().setActiveItem(templateId);
		});
		this.manualPanel.on("refresh", function(templateId, treeObject) {
			this.manualPanel.nodeRefresh.disable();
			var node = this.treePanel.getSelectionModel()
					.getSelectedNode();
			// ---处理未选中节点就点击刷新按钮时的情况
			if (!node) {
				Ext.getCmp("REFRESHBUTTON").enable();
				return;
			}

			if (node.childNodes != null) {
				for (var i = node.childNodes.length; i > 0; i--) {
					node.firstChild.remove();
				}
			}
			if (node.attributes.id.indexOf('root') != -1
					|| node.attributes.id.indexOf('ROOT') != -1) {
				this.treePanel.initTree(templateId, treeObject);
			} else {
				this.treePanel.insertChildren(node);
			}
			// 处理节点不展开点刷新按钮问题
			if (!node.leaf) {
				node.childLoaded = true;
			}
			node.attributes.leaf = false;// 处理此节点下无子节点时，新增子节点后的刷新问题
			node.leaf = false;

			node.expand();

		}, this);
	},
	_resizeTreePanel : function(templateId) {
		var contains = false;
		for (var i = 0; i < this.cardStack.length; i++) {
			if (this.cardStack[i] == templateId) {
				contains = true;
				this.cardStack.splice(i, 1);
				this.cardStack.unshift(templateId);
				return;
			}
		}
		if (this.cardStack.length >= this.cardSize) {
			if (!contains) {
				var toRemove = this.cardStack.pop();
				this.cardStack.unshift(templateId);
				this.cardPanel.remove(toRemove);
			}
		} else {
			this.cardStack.unshift(templateId);
		}
	},
	_initTreePanel : function(templateId, treeObject) {
		var needInit = false;
		var treePanel = this.cardPanel.items.get(templateId);
		this._resizeTreePanel(templateId);
		if (treePanel == null || typeof treePanel == "undefined") {
			needInit = true;
			treePanel = new DRM.NTTreePanel({
				id			: templateId,
				templateId	: templateId,
				treeObject	: treeObject
			});
			this.cardPanel.items.add(treePanel);
		}
		this.cardPanel.getLayout().setActiveItem(templateId);
		this.treePanel = null;;
		this.treePanel = treePanel;
		this.treePanel.on("contextmenu", function(node, event) {
			try {
				this.treePanel.getSelectionModel().clearSelections();
			} catch (e) {
			}
			try {
				node.select();
			} catch (e) {
			}
			var groupId = node.attributes.actionGroupId;
			if (groupId != null && typeof groupId != "undefined") {
				var orgArgs = {
					node	: node,
					e		: event
				};
				var extArgs = {
					widgetId	: 'res_browser_tree',
					groupId		: groupId
				};
				this.fireEvent('_contextmenu', orgArgs, extArgs);
			}
		}, this);
		this.treePanel.on(this.clickType, function(node, event) {
			var nodeAttributes = node.attributes;
			if (nodeAttributes != null && typeof nodeAttributes != "undefined") {
				var bmClassId = nodeAttributes.bmClassId;
				var cuid = nodeAttributes.cuid;
				if (bmClassId != null && typeof bmClassId != "undefined"
						&& cuid != null && typeof cuid != "undefined"
						&& cuid.length > 0) {
					var orgArgs = {
						node	: node,
						e		: event
					};
					var extArgs = {
						widgetId : "res_browser_tree"
					};
					this.fireEvent('_click', orgArgs, extArgs);
				}
			}
		}, this);
		return needInit;
	}
});

DRM.NavTreePanel.convertJsonToExtNode = function(data) {
	var node = new Ext.tree.TreeNode(data);
	var childNodes = data.childNodes;
	if (childNodes != null && typeof childNodes != "undefined") {
		for (var i = 0; i < childNodes.length; i++) {
			node.appendChild(this.convertJsonToExtNode(childNodes[i]));
		}
	}
	return node;
}

/**
 * SQL树控件，参数:
 * templateId sql树模板的id（T_SYS_TREE_TEMPLATE表的CUID）;
 * templateName 树顶级节点的名称，如果treeObject有效，则优先显示treeObject中的templateName
 * treeObject 树顶级节点参数对象，用于定义模板ID，顶级节点名称属性
 * isInit 是否初始化的时候创建数据，如果控件是通过类型树点击触发，则默认为false
 * @param {}
 *            config
 */
DRM.NTTreePanel = function(config) {
	DRM.NTTreePanel.superclass.constructor.call(this, Ext.apply(config, {
		templateId	: config.templateId,
		templateName: typeof(config.templateName)=='undefined'?'导航树' : config.templateName,
		treeObject	: typeof(config.treeObject) == 'undefined'? null : config.treeObject,
		isInit		: typeof(config.isInit) == 'undefined'? false : config.isInit,
		showChecked : typeof(config.showChecked) == 'undefined'? false : config.showChecked,
		checkIds    : typeof(config.checkIds) == 'undefined'? '' : config.checkIds
	}));
};

Ext.extend(DRM.NTTreePanel, Ext.tree.TreePanel, {
	autoScroll	: true,
	loadMask	: {
		msg : '加载中...'
	},
	
	initComponent : function() {
		this.root = new Ext.tree.TreeNode({
			text : "导航树根结点",
			id : this.templateId + "-root",
			expandable : true,
			expanded : true
		});
		DRM.NTTreePanel.superclass.initComponent.call(this);
		this.addEvents("_click");
		this.addEvents("_getCheckedNodes");
		this.on('beforeexpandnode', function(node) {
			if (node.getDepth() > 0) {
				if (!node.childLoaded) {
					this.insertChildren(node);
					node.childLoaded = true;
				}
			}
		}, this);
		this.on("dblclick", function(node, event) {
			var nodeAttributes = node.attributes;
			if (nodeAttributes != null && typeof nodeAttributes != "undefined") {
				var bmClassId = nodeAttributes.bmClassId;
				var cuid = nodeAttributes.cuid;
				if (bmClassId != null && typeof bmClassId != "undefined"
						&& cuid != null && typeof cuid != "undefined"
						&& cuid.length > 0) {
					var orgArgs = {
						node : node,
						e : event
					};
					var extArgs = {
						widgetId : "res_browser_tree"
					};
					this.fireEvent('_click', orgArgs, extArgs);
				}
			}
		}, this);
		if(this.isInit){
			this.initTree(this.templateId, this.treeObject);
		};
		this.expandAll();
	},
	_getCheckedNodes:function(tree){
		selNodes = this.getChecked();
		this.fireEvent('_getCheckedNodes',selNodes);
	},
	
	initTree : function(templateId, treeObject) {
		var panel = this;
		if(treeObject==null){
			treeObject = {
				relatedTreeTemplateCuid : 	templateId,
				boName					:	'sqlTreeBo',
				templateName			:	this.templateName
			}
		}
		var param = {
			tree : treeObject
		};
		ExplorerTreeAction.getTreeRootJson(param, function(data) {
			var root = data.root;
			panel.root.setText(root.text);
			panel.root.iconCls = root.iconCls;
			panel.root.icon = root.icon;
			panel.root.expandable = root.expandable;
			panel.root.attributes.treeTemplateId = root.treeTemplateId;
			panel.root.attributes.nodeTemplateId = root.nodeTemplateId;
			panel.root.attributes.nodeTemplateIndex = root.nodeTemplateIndex;
			panel.root.attributes.actionGroupId = root.actionGroupId;

			for (var i = 0; i < root.childNodes.length; i++) {
				var child = panel
						.convertJsonToExtNode(root.childNodes[i]);
				panel.root.appendChild(child);
			}
			try{
				Ext.getCmp("REFRESHBUTTON").enable();
			}catch(e){}
		})
	},
	insertChildren : function(node) {
		var panel = this;
		panel.getEl().mask('加载中...', 'x-mask-loading');
		if (node.tree) {
			if (!node.leaf) {
				var obj = node.attributes;
				ExplorerTreeAction.getTreeNodeJson(obj, function(data) {
					for (var i = 0; i < data.length; i++) {
						node.appendChild(panel
								.convertJsonToExtNode(data[i]));
					}
					try{
						Ext.getCmp("REFRESHBUTTON").enable();
					}catch(e){}
					panel.getEl().unmask();
				});
			}
		}
	},
	getNodeToRootRoute : function(node) {
		var route = new Array();
		route.push(node.attributes);
		if (node.parentNode) {
			route = route.concat(this.getNodeToRootRoute(node.parentNode));
		}
		return route;
	},
	convertJsonToExtNode : function(data) {
		if(this.showChecked){
			data.checked = false;
		}
		if(this.checkIds.indexOf(data.cuid)!=-1){
			data.checked = true;
		}
		var node = new Ext.tree.TreeNode(data);
		node.tree = data.tree;
		var childNodes = data.childNodes;
		if (childNodes != null && typeof childNodes != "undefined") {
			for (var i = 0; i < childNodes.length; i++) {
				node.appendChild(this.convertJsonToExtNode(childNodes[i]));
			}
		}
		if (node.attributes.cuid == 'moreTreeNode')
			this._addMoreNodeEvent(node, this);
		return node;
	},

	// 为‘更多’节点，添加点击事件
	_addMoreNodeEvent : function(moreNode, panel) {
		moreNode.on('beforeclick', function(node, e) {
			node.unselect();
			e.stopEvent();// 停止CLICK事件（防止其他监听此事件的方法启动）
			this._insertBeforeChildren(node);// 选中的那个更多节点
		}, panel)
	},

	// 点击更多添加数据到‘更多’节点之前的处理方法
	_insertBeforeChildren : function(moreNode) {
		var panel = this;
		var node = moreNode.parentNode;
		panel.getEl().mask('加载中...', 'x-mask-loading');

		var obj = moreNode.attributes;
		ExplorerTreeAction.getTreeNodeJson(obj, function(data) {
			for (var i = 0; i < data.length; i++) {
				node.insertBefore(panel.convertJsonToExtNode(data[i]),
						moreNode);
			}
			moreNode.remove();
			panel.getEl().unmask();
		});
	}
});
/**
 * 
 * @param {}
 *            config
 */
DRM.NTManualPanel = function(config) {
	DRM.NTManualPanel.superclass.constructor.call(this, Ext.apply(config, {
		boName		: config.boName,
		selectFirst	: config.selectFirst
	}));
};
Ext.extend(DRM.NTManualPanel, Ext.Panel, {
	initComponent : function() {
		this.addEvents("templateChanged");
		this.addEvents("refresh");
		this.templateSelector = new Ext.form.ComboBox({
			store : new Ext.data.ArrayStore({
				fields : [],
				data : [[]]
			}),
			fieldLabel : "",
			hideLabel : true,
			emptyText : "请选择导航树类型",
			displayField : "templateName",
			valueField : "templateId",
			mode : "local",
			triggerAction : "all",
			anchor : "100%",
			clickedNode : null,
			//readOnly : true,
			editable : false,
			typeAhead: true,
			//maxHeight : 2000,
			//minHeight : 400,
			width : 200,
			scope : this,
			tpl : "<tpl for='.'><div style='height:250px'><div id='"
					+ this.id + "-templatetree'></div></div></tpl>"
		});
		this.templateSelector.on('expand', this._privateExpand, this);
		this._initTree();

		this.nodeRefresh = new Ext.Button({
			iconCls : "x-tbar-loading",
			id : "REFRESHBUTTON",
			handler : function() {
				var templateId = this.templateSelector.getValue();
				var treeObject = this.templateSelector.valueMap[this.templateSelector
						.getValue()];
				this.fireEvent("refresh", templateId, treeObject);
			},
			scope : this
		});

		var selectorPanel = new Ext.Panel({
			layout		: 'fit',
			items		: [this.templateSelector],
			columnWidth	: .8
		});

		var refreshPanel = new Ext.Panel({
			items		: [this.nodeRefresh],
			columnWidth	: .2
		});
		this.items = [selectorPanel, refreshPanel];

		this.on('afterlayout', this.initTemplateStore, this);
		
		DRM.NTManualPanel.superclass.initComponent.call(this);

		this.tree.on("click", function(node) {
			if (!isNotNull(node.attributes.attributes.relatedTreeTemplateCuid))
				return;
			this.templateSelector.setValue(node.attributes.id);
			this.templateSelector.setRawValue(node.text);
			this.templateSelector.collapse();
			this.templateSelector.originalValue = node.attributes.id;
			var treeObject = node.attributes.attributes;
			this.fireEvent("templateChanged",node.attributes.id, treeObject);
		}, this);

	},
	initTemplateStore : function() {
		this.removeListener('afterlayout', this.initTemplateStore, this);
		var scope = this;
		ExplorerTreeAction.getAllTreeList(scope.boName, function(data) {
			scope._makeTree(data);
		});
	},

	getSelectedTemplateId : function() {
		return this.templateSelector.getValue();
	},
	expandAll:function(){
		alert(12);
		this.tree.expandAll();
	},
	
	_initTree : function() {
		// 建根节点
		this.root = new Ext.tree.TreeNode({
			text		: '类型',
			expanded	: true,
			draggable	: false
		});

		// 建树panel
		this.tree = new Ext.tree.TreePanel({
			animate		: true,// 动画效果
			enableDD	: true,
			rootVisible	: false,
			border		: false
		});
		this.tree.setRootNode(this.root);
	},
	// 通过数组创建树
	_makeTree : function(lst) {
		var scope = this;
		var map = [];// 用节点的parentTypeCuid <--> node
		// -1. 清空ROOT上的所有孩子
		if (this.nodes && this.nodes.length > 0) {
			for (var g = 0; g < this.nodes.length; g++) {
				this.root.removeChild(this.nodes[g]);
			}
		}

		this.nodes = new Array();

		var tmpMap = {};
		// 1. 分出一级根节点和多级根节点
		for (var i = 0; i < lst.length; i++) {
			var temp = lst[i];

			var type = this._createTreeNode(temp);

			if (temp.relatedParentCuid == null) {
				this.root.appendChild(type);
				this.nodes.push(type)
			} else {
				tmpMap[lst[i].templateId] = lst[i];
				if (typeof(map[temp.relatedParentCuid]) != 'undefined') {
					map[temp.relatedParentCuid].push(type);
				} else {
					var array = new Array();
					array.push(type);
					map[temp.relatedParentCuid] = array;
				}
			}
		}

		scope.templateSelector.valueMap = tmpMap;

		// 2. 循环父节点
		for (var g = 0; g < this.nodes.length; g++) {
			this._insertNode(this.nodes[g], map);
		}

		
		if (lst.length > 0) {
			for (var g = 0; g < lst.length; g++) {
				if (lst[g].relatedTreeTemplateCuid != null) {
					this.templateSelector.setValue(lst[g].templateId);
					scope.templateSelector.setRawValue(lst[g].templateName);
					scope.fireEvent("templateChanged", lst[g].templateId,
							lst[g]);
					break;
				}
			}
		}
		
	},
	_createTreeNode : function(m) {// 创建treenode
		var type = new Ext.tree.TreeNode({
			draggable : false,
			attributes : m,
			text : m.templateName,
			id : m.templateId
			});
		return type;
	},
	_insertNode : function(node, map) {
		if (!node.attributes.attributes
				|| map[node.attributes.attributes.templateId] == null) {
			return;
		} else {
			var lis = map[node.attributes.attributes.templateId];
			for (var i = 0; i < lis.length; i++) {
				var type = lis[i];
				node.appendChild(type);
				this._insertNode(type, map);
			}
		}
	},
	_privateExpand : function() {
		this.tree.render(this.id + "-templatetree");
		this.tree.expandAll();
	}
});