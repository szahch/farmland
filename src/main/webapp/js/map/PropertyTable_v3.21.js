/**
 * 页面左边工具栏 适用于ArcGIS API for JavaScript v3.21
 * 
 */

var mFeatureLayerTable;

define(
		[ "dojo/_base/declare", "dojo/dom", "esri/dijit/FeatureTable",
				"dojo/text!./templates/PropertyTable.html" ],
		function(declare, dom, FeatureTable, template) {
			return declare(
					"PropertyTable_v321",
					[],
					{

						_extend : null,

						_extend : function(dest, src) {
							for (key in src) {
								if (src.hasOwnProperty(key)) {
									dest[key] = src[key];
								}
							}
						},

						constructor : function(param) {

							this.param = {};

							this._extend(param, param);

							this.param = param;

							this.map = this.param.map;

							this.id = this.param.id;

							this.featureLayer = this.param.featureLayer;

							dom.byId(this.id).innerHTML = template;

							this.initTable();

							dom.byId(this.id).style.display = 'none';
						},

						initTable : function() {
							try {
								mFeatureLayerTable = new FeatureTable(
										{
											featureLayer : (this.featureLayer),
											map : (this.map),
											editable : true,
											syncSelection : true,
											dateOptions : {
												datePattern : 'M/d/y',
												timeEnabled : true,
												timePattern : 'H:mm',
											},
											fieldInfos : [ {
												nanme : 'TBBH',
												alias : '图斑编号',
												editable : false
											}, {
												nanme : 'XMC',
												alias : 'XMC',
												format : {
													template : "${value} XMC"
												}
											}, {
												nanme : '调查人',
												alias : '调查人',
												editable : true
											} ],
											menuFunctions : [ {
												label : "Show All Emergency Vehicles",
												callback : function(evt) {
													console.log(" -- evt: ",
															evt);
													this.myFeatureLayer
															.setDefinitionExpression("1=1");
													this.mFeatureLayerTable
															.refresh();
												}
											} ]
										}, 'myTableNode');

								console
										.debug(" LeftToolBar_v3.21::showOrHideFeatureTable. mFeatureLayerTable="
												+ this.mFeatureLayerTable);

								mFeatureLayerTable.startup();
								mFeatureLayerTable.resize();
								mFeatureLayerTable.on("refresh", function(evt) {
									console.log("refresh event - ", evt);
								});
							} catch (err) {
								console.log(" showOrHideFeatureTable::" + err);
							}
						},

						showFeatureTable : function() {
							dom.byId(this.id).style.display = 'block';
						},

						hideFeatureTable : function() {
							dom.byId(this.id).style.display = 'none';
						}

					});
		});

/*-------------------------- +
 获取id, class, tagName
 +-------------------------- */
var get = {
	byId : function(id) {
		return typeof id === "string" ? document.getElementById(id) : id
	},
	byClass : function(sClass, oParent) {
		var aClass = [];
		var reClass = new RegExp("(^| )" + sClass + "( |$)");
		var aElem = this.byTagName("*", oParent);
		for (var i = 0; i < aElem.length; i++)
			reClass.test(aElem[i].className) && aClass.push(aElem[i]);
		return aClass
	},
	byTagName : function(elem, obj) {
		return (obj || document).getElementsByTagName(elem)
	}
};
var dragMinWidth = 300;
var dragMinHeight = 200;
/*-------------------------- +
 拖拽函数
 +-------------------------- */
function drag(oDrag, handle) {
	var disX = dixY = 0;
	var oMin = get.byClass("min", oDrag)[0];
	var oMax = get.byClass("max", oDrag)[0];
	var oRevert = get.byClass("revert", oDrag)[0];
	var oClose = get.byClass("close", oDrag)[0];

	handle = handle || oDrag;
	handle.style.cursor = "move";
	handle.onmousedown = function(event) {
		var event = event || window.event;
		disX = event.clientX - oDrag.offsetLeft;
		disY = event.clientY - oDrag.offsetTop;

		document.onmousemove = function(event) {
			var event = event || window.event;
			var iL = event.clientX - disX;
			var iT = event.clientY - disY;
			var maxL = document.documentElement.clientWidth - oDrag.offsetWidth;
			var maxT = document.documentElement.clientHeight
					- oDrag.offsetHeight;

			iL <= 0 && (iL = 0);
			iT <= 0 && (iT = 0);
			iL >= maxL && (iL = maxL);
			iT >= maxT && (iT = maxT);

			oDrag.style.left = iL + "px";
			oDrag.style.top = iT + "px";

			return false
		};

		document.onmouseup = function() {
			document.onmousemove = null;
			document.onmouseup = null;
			this.releaseCapture && this.releaseCapture()
		};
		this.setCapture && this.setCapture();
		return false
	};
	// 最大化按钮
	oMax.onclick = function() {
		oDrag.style.top = oDrag.style.left = 0;
		oDrag.style.width = document.documentElement.clientWidth - 2 + "px";
		oDrag.style.height = document.documentElement.clientHeight - 2 + "px";
		this.style.display = "none";
		oRevert.style.display = "block";
	};
	// 还原按钮
	oRevert.onclick = function() {
		oDrag.style.width = dragMinWidth + "px";
		oDrag.style.height = dragMinHeight + "px";
		oDrag.style.left = (document.documentElement.clientWidth - oDrag.offsetWidth)
				/ 2 + "px";
		oDrag.style.top = (document.documentElement.clientHeight - oDrag.offsetHeight)
				/ 2 + "px";
		this.style.display = "none";
		oMax.style.display = "block";
	};
	// 最小化按钮
	oMin.onclick = oClose.onclick = function() {
		oDrag.style.display = "none";
		var oA = document.createElement("a");
		oA.className = "open";
		oA.href = "javascript:;";
		oA.title = "还原";
		document.body.appendChild(oA);
		oA.onclick = function() {
			oDrag.style.display = "block";
			document.body.removeChild(this);
			this.onclick = null;
		};
	};
	// 阻止冒泡
	oMin.onmousedown = oMax.onmousedown = oClose.onmousedown = function(event) {
		this.onfocus = function() {
			this.blur()
		};
		(event || window.event).cancelBubble = true
	};
	// oContent.style.height = (oDrag.offsetHeight-100) + "px";

	console.log(oDrag.style.height);
	// console.log(oContent.style.height);
}
/*-------------------------- +
 改变大小函数
 +-------------------------- */
function resize(oParent, handle, isLeft, isTop, lockX, lockY) {
	handle.onmousedown = function(event) {
		var event = event || window.event;
		var disX = event.clientX - handle.offsetLeft;
		var disY = event.clientY - handle.offsetTop;
		var iParentTop = oParent.offsetTop;
		var iParentLeft = oParent.offsetLeft;
		var iParentWidth = oParent.offsetWidth;
		var iParentHeight = oParent.offsetHeight;

		document.onmousemove = function(event) {

			console.log("11111");

			var event = event || window.event;

			var iL = event.clientX - disX;
			var iT = event.clientY - disY;
			var maxW = document.documentElement.clientWidth
					- oParent.offsetLeft - 2;
			var maxH = document.documentElement.clientHeight
					- oParent.offsetTop - 2;
			var iW = isLeft ? iParentWidth - iL : handle.offsetWidth + iL;
			var iH = isTop ? iParentHeight - iT : handle.offsetHeight + iT;

			isLeft && (oParent.style.left = iParentLeft + iL + "px");
			isTop && (oParent.style.top = iParentTop + iT + "px");

			iW < dragMinWidth && (iW = dragMinWidth);
			iW > maxW && (iW = maxW);
			lockX || (oParent.style.width = iW + "px");

			iH < dragMinHeight && (iH = dragMinHeight);
			iH > maxH && (iH = maxH);
			lockY || (oParent.style.height = iH + "px");

			if ((isLeft && iW == dragMinWidth)
					|| (isTop && iH == dragMinHeight))
				document.onmousemove = null;

			var oDrag = document.getElementById("drag");
			var oContent = get.byClass("content", oDrag)[0];
			oContent.style.height = (parseInt(oDrag.offsetHeight) - 100) + "px";
			console.log(oDrag.style.height);
			console.log(oContent.style.height);
			mFeatureLayerTable.resize();

			return false;
		};
		document.onmouseup = function() {
			document.onmousemove = null;
			document.onmouseup = null;
		};
		return false;
	}
};
window.onload = window.onresize = function() {
	var oDrag = document.getElementById("drag");
	var oTitle = get.byClass("title", oDrag)[0];
	var oL = get.byClass("resizeL", oDrag)[0];
	var oT = get.byClass("resizeT", oDrag)[0];
	var oR = get.byClass("resizeR", oDrag)[0];
	var oB = get.byClass("resizeB", oDrag)[0];
	var oLT = get.byClass("resizeLT", oDrag)[0];
	var oTR = get.byClass("resizeTR", oDrag)[0];
	var oBR = get.byClass("resizeBR", oDrag)[0];
	var oLB = get.byClass("resizeLB", oDrag)[0];
	var oContent = get.byClass("content", oDrag)[0];

	drag(oDrag, oTitle);
	// 四角
	resize(oDrag, oLT, true, true, false, false);
	resize(oDrag, oTR, false, true, false, false);
	resize(oDrag, oBR, false, false, false, false);
	resize(oDrag, oLB, true, false, false, false);
	// 四边
	resize(oDrag, oL, true, false, false, true);
	resize(oDrag, oT, false, true, true, false);
	resize(oDrag, oR, false, false, false, true);
	resize(oDrag, oB, false, false, true, false);

	oDrag.style.left = (document.documentElement.clientWidth - oDrag.offsetWidth)
			/ 2 + "px";
	oDrag.style.top = (document.documentElement.clientHeight - oDrag.offsetHeight)
			/ 2 + "px";

	oContent.style.height = (oDrag.offsetHeight - 100) + "px";

	console.log(oDrag.style.height);
	console.log(oContent.style.height);
}
