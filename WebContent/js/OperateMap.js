/**
 * 地图操作界面JavaScript文件融合
 */

console.log("已经连接到js");
var toc = null;
var map;
require(
		[ "dojo/parser", "dojo/ready", "esri/map",
				"esri/layers/ArcGISDynamicMapServiceLayer",
				"esri/layers/FeatureLayer", "esri/dijit/AttributeInspector",
				"esri/InfoTemplate", "esri/renderers/SimpleRenderer",
				"esri/Color", "esri/symbols/SimpleFillSymbol",
				"esri/symbols/SimpleLineSymbol",
				"esri/symbols/SimpleMarkerSymbol", "esri/geometry/Extent",
				"esri/SpatialReference", "dojo/dom-construct",
				"esri/tasks/query", "/Farmland/js/map/TocWidget_v3.21.js",
				"../js/map/LayerControl_v3.21.js",
				"../js/map/LeftToolBar_v3.21.js", "esri/dijit/FeatureTable",
				"dijit/layout/BorderContainer", "dijit/layout/ContentPane",
				"dijit/layout/AccordionContainer", "dojo/domReady!" ],
		function(parser, ready, Map, ArcGISDynamicMapServiceLayer,
				FeatureLayer, AttributeInspector, InfoTemplate, SimpleRenderer,
				Color, SimpleFillSymbol, SimpleLineSymbol, SimpleMarkerSymbol,
				Extent, SpatialReference, domConstruct, Query, TocWidget,
				LayerControl_v321, LeftToolBar_v321, FeatureTable) {

			console
					.debug(location.pathname.replace(/\/[^/]+$/, "")
							+ "/js/map");

			parser.parse();
			
			
//			ready(function() {
//
//				// Create the feature layer
//				var myFeatureLayer = new FeatureLayer(
//						"https://services.arcgis.com/V6ZHFr6zdgNZuVG0/arcgis/rest/services/california_census_blocks/FeatureServer/0",
//						{
//							mode : FeatureLayer.MODE_ONDEMAND,
//							outFields : [ "NAME", "GEOID", "MTFCC", "ALAND",
//									"AWATER" ],
//							visible : true,
//							id : "fLayer"
//						});
//
//				myTable = new FeatureTable({
//					featureLayer : myFeatureLayer,
//					showGridMenu : false,
//					hiddenFields : [ "FID", "C_Seq", "Street" ]
//				// field that end-user can show, but is hidden on startup
//				}, "myTableNode");
//
//				myTable.startup();
//			});
			
			

			var extent1 = new esri.geometry.Extent({
				"xmin" : 3.8486722337572604E7,
				"ymin" : 2480415.1499582035,
				"xmax" : 3.858183009056279E7,
				"ymax" : 2527370.5749582024,
				"spatialReference" : {
					"wkid" : 2362
				}
			});
			map = new Map("mapDiv", {
				// basemap : "dark-gray",
				logo : false,
				extent : extent1
			});

			var layer = new esri.layers.ArcGISDynamicMapServiceLayer(
					"http://192.168.2.144:6080/arcgis/rest/services/Learning/GeoImage/MapServer",
					{
						id : '深圳三调影像'
					});

			map.addLayer(layer);

			var selectQuery = new Query();

			var featureLayer;

			map
					.on(
							"load",
							function() {
								// map.hideZoomSlider();
								// map.setMapCursor("url(/Farmland/js/map/assets/images/lefttoolbar/enlarge_30px.cur),auto");

								// var infoTemplate = new InfoTemplate(
								// "Attributes", "${*}");
								featureLayer = new FeatureLayer(
										"http://192.168.2.144:6080/arcgis/rest/services/Learning/Polygon3/MapServer/0",
										{
											mode : FeatureLayer.MODE_ONDEMAND,
											outFields : [ "*" ],
											id : "fLayer2",
											visible : true
										// ,infoTemplate : infoTemplate
										});
								/*
								 * 
								 * featureLayer = new FeatureLayer(
								 * "https://services.arcgis.com/V6ZHFr6zdgNZuVG0/arcgis/rest/services/california_census_blocks/FeatureServer/0", {
								 * mode : FeatureLayer.MODE_ONDEMAND, outFields : [
								 * "*" ], visible : true, id : "fLayer2" });
								 * 
								 */
								/*
								 * var layerInfos = [ { 'featureLayer' :
								 * featureLayer, 'showAttachments' : false,
								 * 'isEditable' : true, 'fieldInfos' : [ {
								 * 'fieldName' : 'ID', 'isEditable' : false,
								 * 'label' : 'ID:' }, { 'fieldName' : '调查人',
								 * 'isEditable' : true, 'tooltip' : 'Win
								 * percentage', 'label' : '调查:' } ] } ];
								 * 
								 * var attInspector = new AttributeInspector({
								 * layerInfos : layerInfos },
								 * domConstruct.create("divaaa"));
								 */

								var sls = new SimpleLineSymbol(
										SimpleLineSymbol.STYLE_SOLID,
										new Color([ 255, 0, 0 ]), 3);
								var renderer = new SimpleRenderer(sls);

								featureLayer.setRenderer(renderer);
								map.addLayer(featureLayer);

								/*
								 * map.infoWindow
								 * .setContent(attInspector.domNode);
								 * map.infoWindow.resize(150, 105);
								 */

								try {
									var mFeatureLayerTable = new FeatureTable(
											{
												featureLayer : (featureLayer),
												map : (this.map),
												editable : true,
												syncSelection : true,
												dateOptions : {
													datePattern : 'M/d/y',
													timeEnabled : true,
													timePattern : 'H:mm',
												},
												fieldInfos : [
														{
															nanme : 'TBBH',
															alias : '图斑编号',
															editable : false
														},
														{
															nanme : 'XMC',
															alias : 'XMC',
															format : {
																template : "${value} XMC"
															}
														}, {
															nanme : '调查人',
															alias : '调查人',
														} ],
												menuFunctions : [ {
													label : "Show All Emergency Vehicles",
													callback : function(evt) {
														console.log(
																" -- evt: ",
																evt);
														this.myFeatureLayer
																.setDefinitionExpression("1=1");
														mFeatureLayerTable
																.refresh();
													}
												} ]
											}, 'myTableNode');

									console
											.debug(" LeftToolBar_v3.21::showOrHideFeatureTable. mFeatureLayerTable="
													+ mFeatureLayerTable);

									mFeatureLayerTable.startup();

									mFeatureLayerTable.on("refresh", function(
											evt) {
										console.log("refresh event - ", evt);
									});
								} catch (err) {
									console.log(" showOrHideFeatureTable::"
											+ err);
								}

								try {
									// 地图控制器
									var ltb = new LeftToolBar_v321({
										id : "leftToolBar",
										map : map
									});
									
									ltb.setShowOrHideFeatureTable(function(str){
										var oDrag = document.getElementById("drag");
										console.log(str);
										if (str === "show") {
											oDrag.style.display='block';  
										}else{
											oDrag.style.display='none';  
										}
										
									});

									// 加载图层控制器
									var lc = new LayerControl_v321({
										id : "layerControl",
										map : map
									});
									lc.setFeatureLayer(featureLayer);
								} catch (err) {
									console.log(" LayerControlJsTest::" + err);
								}

								/*
								 * var myTable = new FeatureTable({ featureLayer :
								 * featureLayer, showGridMenu : true,
								 * hiddenFields : [ "FID", "C_Seq", "Street" ] //
								 * field that end-user can show, but is hidden
								 * on startup }, "myTableNode");
								 */
								myTable.startup();

							});

			/*
			 * map .on( "click", function(evt) {
			 * 
			 * console.log(" map onclick::" + "saa"); selectQuery.geometry =
			 * evt.mapPoint; selectQuery.distance = 50; selectQuery.units =
			 * "miles" selectQuery.returnGeometry = true; featureLayer
			 * .selectFeatures( selectQuery, FeatureLayer.SELECTION_NEW,
			 * function(features) { console .log(" map onclick::" +
			 * features.length); if (features.length > 0) { /* map.infoWindow
			 * .setTitle(featureLayer .getLayer().name);
			 */

			/*
			 * evt.feature; map.infoWindow .show( evt.screenPoint, map
			 * .getInfoWindowAnchor(evt.screenPoint)); } else { map.infoWindow
			 * .hide(); } }); });
			 */
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
