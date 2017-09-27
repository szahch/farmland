/**
 * 地图操作界面JavaScript文件融合
 */

console.log("已经连接到js");
var toc = null;
var map;
var pathName = window.document.location.pathname;
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
console.log(pathName);
console.log(projectName);

require(
		[ "dojo/parser", "dojo/ready", "esri/map",
				"esri/layers/ArcGISDynamicMapServiceLayer",
				"esri/layers/FeatureLayer", "esri/dijit/AttributeInspector",
				"esri/InfoTemplate", "esri/renderers/SimpleRenderer",
				"esri/Color", "esri/symbols/SimpleFillSymbol",
				"esri/symbols/SimpleLineSymbol",
				"esri/symbols/SimpleMarkerSymbol", "esri/geometry/Extent",
				"esri/SpatialReference", "dojo/dom-construct",
				"esri/tasks/query", projectName + "/js/map/TocWidget_v3.21.js",
				"../js/map/LayerControl_v3.21.js",
				"../js/map/LeftToolBar_v3.21.js",
				projectName + "/js/map/PropertyTable_v3.21.js",
				"esri/dijit/FeatureTable", "dijit/layout/BorderContainer",
				"dijit/layout/ContentPane", "dijit/layout/AccordionContainer",
				"dojo/domReady!" ],
		function(parser, ready, Map, ArcGISDynamicMapServiceLayer,
				FeatureLayer, AttributeInspector, InfoTemplate, SimpleRenderer,
				Color, SimpleFillSymbol, SimpleLineSymbol, SimpleMarkerSymbol,
				Extent, SpatialReference, domConstruct, Query, TocWidget,
				LayerControl_v321, LeftToolBar_v321, PropertyTable_v321,
				FeatureTable) {

			console
					.debug(location.pathname.replace(/\/[^/]+$/, "")
							+ "/js/map");

			// var
			// projectName=location.pathName.substring(0,location.pathName.substr(1).indexOf('/')+1);

			parser.parse();

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
								map.hideZoomSlider();
								// map.setMapCursor("url(/Farmland/js/map/assets/images/lefttoolbar/enlarge_30px.cur),auto");

								var infoTemplate = new InfoTemplate(
										"Attributes", "${*}");
								featureLayer = new FeatureLayer(
										"http://192.168.2.144:6080/arcgis/rest/services/Learning/Polygon2/MapServer/0",
										{
											mode : FeatureLayer.MODE_ONDEMAND,
											outFields : [ "*" ],
											id : "fLayer2",
											visible : true,
											infoTemplate : infoTemplate
										});
								/*
								 * 
								 * featureLayer = new FeatureLayer(
								 * "https://services.arcgis.com/V6ZHFr6zdgNZuVG0/arcgis/rest/services/california_census_blocks/FeatureServer/0", {
								 * mode : FeatureLayer.MODE_ONDEMAND, outFields : [
								 * "*" ], visible : true, id : "fLayer2" });
								 * 
								 */

								var layerInfos = [ {
									'featureLayer' : featureLayer,
									'showAttachments' : false,
									'isEditable' : true,
									'fieldInfos' : [ {
										'fieldName' : 'ID',
										'isEditable' : false,
										'label' : 'ID:'
									}, {
										'fieldName' : '调查人',
										'isEditable' : true,
										'tooltip' : 'Winpercentage',
										'label' : '调查:'
									} ]
								} ];

								var attInspector = new AttributeInspector({
									layerInfos : layerInfos
								}, domConstruct.create("divaaa"));

								var sls = new SimpleLineSymbol(
										SimpleLineSymbol.STYLE_SOLID,
										new Color([ 255, 0, 0 ]), 3);
								var renderer = new SimpleRenderer(sls);

								featureLayer.setRenderer(renderer);
								map.addLayer(featureLayer);

								map.infoWindow.setContent(attInspector.domNode);
								map.infoWindow.resize(150, 105);

								var a = new PropertyTable_v321({
									id : "aaa",
									map : map,
									featureLayer : featureLayer
								});

								try {
									// 地图控制器
									var ltb = new LeftToolBar_v321({
										id : "leftToolBar",
										map : map
									});

									ltb
											.setShowOrHideFeatureTable(function(
													str) {

												console.log(str);
												if (str === "show") {
													a.showFeatureTable();
												} else {
													a.hideFeatureTable();
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