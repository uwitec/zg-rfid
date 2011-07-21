(function($) {
	function load(settings, root, child, container) {
		$.getJSON(settings.url, {root: root}, function(response) {
			function createNode(parent) {
				var current = $("<li/>").attr("id", this.id || "")
				.attr("tree", this.tree|| "")
				.html("<span>" + this.text + "</span>").appendTo(parent);
				if (this.classes) {
					current.children("span").addClass(this.classes);
				}
				if (this.expanded) {
					current.addClass("open");
				}
				if (this.hasChildren || this.children && this.children.length) {
					var branch = $("<ul/>").appendTo(current);
					if (this.hasChildren) {
						current.addClass("hasChildren");
						createNode.call({
							text:"placeholder",
							id:"placeholder",
							children:[]
						}, branch);
					}
					if (this.children && this.children.length) {
						$.each(this.children, createNode, [branch])
					}
				}
			}
			$.each(response, createNode, [child]);
	        $(container).treeview({add: child});
	    });
	}
	
	var proxied = $.fn.treeview;
	$.fn.treeview = function(settings) {
		if (!settings.url) {
			return proxied.apply(this, arguments);
		}
		var container = this;
		load(settings, "source", this, container);
		var userToggle = settings.toggle;
		return proxied.call(this, $.extend({}, settings, {
			collapsed: true,
			toggle: function() {
				var $this = $(this);
				if ($this.hasClass("hasChildren")) {
					var childList = $this.removeClass("hasChildren").find("ul");
					childList.empty();
					load(settings, this.id, childList, container);
				}
				if (userToggle) {
					userToggle.apply(this, arguments);
				}
			}
		}));
	};
	
	$.widget("irms.resourcetree", {
		options: {
			width			: 150,
			selectHeight	: 25,
			maxHeight		: 200,
			url				: "frame/sys/SqlTree/resTree.do"
		},
		_create: function() {
			var self = this;
			self.selectDiv = $("<div></div>")
				.appendTo( self.element )
				.height(self.options.selectHeight);
			self.selectedText = $("<input></input>").appendTo(self.selectDiv)
				.addClass("ui-widget ui-widget-content ui-corner-left")
				.css({
					backgroundColor : "transparent"
				})
				.width(self.options.width).height(self.options.selectHeight-1);
			self.treeNav = $("<div></div>").appendTo( self.element )
				.treeview({
					url: "frame/sys/SqlTree/typeTree.do"
				})
				.width(self.options.width).height(self.options.maxHeight)
				.addClass("ui-widget ui-widget-content")
				.css({
					backgroundColor : "transparent"
				}).hide()
				.bind("add",function(event, branches){
					$(branches).find("li").bind("click",function(event){
						var param = $(this).attr("tree");
						var id = this.id;
						var value = $($(this).find("span")[0]).text();
						self._setSelected(value);
						self._hideTreeNav();
						self._addResTree(id,param);
						event.stopPropagation();
					});
				});
			self.treeNavIsShow = false;
			self.selectBtn = $("<button>&nbsp;</button>").insertAfter(self.selectedText)
				.button({
					icons	: {
						primary: "ui-icon-triangle-1-s"
					},
					text	: false
				}).removeClass("ui-corner-all")
				.addClass("ui-button-icon")
				.position({
					my		: "left top",
					at		: "right top",
					of		: self.selectedText
				}).click(function() {
					if(!self.treeNavIsShow) {
						self._showTreeNav();
					}else {
						self._hideTreeNav();
					}
				});
			self.resTree = $("<div></div>").appendTo( self.element ).css("padding", 0)
				.css({
					border		: '1px solid #ccc',
					borderWidth	: '0px 1px 1px 1px'
				})
				.height($(document).height()-self.options.selectHeight-5)
				.width(self.options.width);
			
		},
		_init: function() {
		},
		_showTreeNav: function() {
			this.treeNav.show();
			this.treeNav.focus();
			$(this.selectBtn).position({
				my		: "left top",
				at		: "right top",
				of		: this.selectedText
			});
			this.treeNavIsShow = true;
		},
		addResTreeNode: function(root,url) {
			var settings = {url:url};
			load(settings, root, this, "div[id*='restree_']");
		},
		_hideTreeNav: function() {
			this.treeNav.hide();
			$(this.selectBtn).position({
				my		: "left top",
				at		: "right top",
				of		: this.selectedText
			});
			this.treeNavIsShow = false;
		},
		_getTreeNav: function() {
			return this.treeNav;
		},
		_getSelectBtn: function() {
			return this.selectBtn;
		},
		_setSelected: function(val) {
			$(this.selectedText).val(val);
		},
		_addResTree: function(id,param) {
			$(this.resTree).find("div").hide();
			if(!document.getElementById("restree_"+id)) {
				$("<div id='restree_"+id+"'></div>").appendTo(this.resTree).treeview({
					url		: "frame/sys/SqlTree/resTree.do?param="+param
				});
			}else {
				$("#restree_"+id).show();
			}
		},
		destroy: function() {
			$.Widget.prototype.destroy.apply(this, arguments);
		}
	});
	$.extend( $.irms.resourcetree, {
		version: "1.0"
	});
})( jQuery );