/**
 * 页面左边工具栏 适用于ArcGIS API for JavaScript v3.21
 * 
 * 初始化方法 var lc = new LeftToolBar_v321({ map:[esri/map], id:[the id for div]});
 * 
 */

define(
		[ "dojo/_base/declare", "dojo/on", "dojo/dom", "dojo/query",
				"dojo/mouse", "dojo/_base/lang", "esri/toolbars/navigation",
				"esri/geometry/Extent", "esri/dijit/FeatureTable" ],
		function(declare, on, dom, query, mouse, lang, Navigation, Extent,
				FeatureTable) {
			return declare(
					"LeftToolBar_v321",
					[],
					{
						_extend : null,

						// 资源地址
						assetsAddress : "../js/map/assets/",

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

							this.fullExtent = this.param.fullExtent;

							if (this.map === null || this.id === null) {
								console.error("Map or Id is Null.");
								throw "Map or Id is Null.";
							}

							this.initLayout();
						},

						setFullExtent : function(extent) {
							this.fullExtent = extent;
						},

						initLayout : function() {
							this.doc = document.getElementById(this.id);

							console.log(" LeftToolBar_v3.21::start." + this.id
									+ " doc:" + this.doc.innerHTML);

							this.ui = "";

							this.simpleSelect = "";

							this.selectedImgUrl = this.assetsAddress
									+ "images/lefttoolbar/selected_30px.png";

							this.selectingImgUrl = this.assetsAddress
									+ "images/lefttoolbar/selecting_30px.png";

							this.simpleSelectTag = "SimpleSelect";

							this.simpleClickTag = "Simple_Click";

							this.multiSelectTag = "Multi_Select";

							this.initBtn();

							console.log(" LeftToolBar_v3.21::start." + this.ui);

							this.doc.innerHTML = this.ui;

							this.registerEvents();

							this.singleSelect("arrow");
						},

						/**
						 * 初始化按钮
						 */
						initBtn : function() {

							this.btnAbsolutePositionLeft = 5;

							this.firstBtnAbsolutePositionTop = 5;

							this.btnAbsolutePositionTopIncrease = 35;

							this.btnAbsolutePositionTop = this.firstBtnAbsolutePositionTop
									- this.btnAbsolutePositionTopIncrease;

							this.arrowButton();

							this.enlargeButton();

							this.narrowButton();

							this.fullExtentButton();

							// this.showFeatureTableButton();
						},

						/**
						 * 注册事件
						 */
						registerEvents : function() {

							var imageChildren = query("#" + this.id + " > div");
							console.debug(" LeftToolBar_v3.21::connectEvent."
									+ "imageChildren = " + imageChildren.length
									+ imageChildren[0].id);

							for (var i = 0; i < imageChildren.length; i++) {
								on(imageChildren[i], "mousedown", lang.hitch(
										this, "clickEvents"));
								on(imageChildren[i], mouse.enter, lang.hitch(
										this, "enterEvents"));
								on(imageChildren[i], mouse.leave, lang.hitch(
										this, "leaveEvents"));
							}
						},

						/**
						 * 按钮点击监听
						 */
						clickEvents : function(evt) {
							var divId = evt.currentTarget.id;
							var clickType = divId.substring(12, 24);
							var divTag = divId.substring(25, divId.length - 4);
							var selectedImg = divId.substring(0,
									divId.length - 3)
									+ "SelectedImg";

							// 控制台输出
							console
									.debug(" LeftToolBar_v3.21::clickEvents. clickType="
											+ clickType
											+ ", divTag="
											+ divTag
											+ ", selectedImg=" + selectedImg);

							switch (clickType) {
							// 单选按钮响应
							case this.simpleSelectTag:
								this.singleSelect(divTag);
								return;
								break;

							// 单击按钮响应
							case this.simpleClickTag:
								dom.byId(selectedImg).style.visibility = "visible";
								// 鼠标弹起时取消选中
								$("#" + divId)
										.on(
												"mouseup",
												function() {
													dom.byId(selectedImg).style.visibility = "hidden";
												});
								this.singleClick(divTag);
								return;
								break;
							case this.multiSelectTag:
								var img = dom.byId(selectedImg);
								if (img.style.visibility === "hidden") {
									img.style.visibility = "visible";
									this.multiSelect(divTag, "selected");
								} else {
									img.style.visibility = "hidden";
									this.multiSelect(divTag, "cancel");
								}
								break;
							}
						},

						/**
						 * 单选按钮
						 */
						singleSelect : function(tag) {

							if (tag === this.simpleSelect) {
								return;
							}

							// 清除所有已经选择
							var simpleSelectdiv = query("#" + this.id
									+ " > div");

							console.debug(simpleSelectdiv[1].id);
							for (var i = 0; i < simpleSelectdiv.length; i++) {

								if (simpleSelectdiv[i].id.substring(12, 24) === this.simpleSelectTag) {
									dom
											.byId((simpleSelectdiv[i].id
													.substring(
															0,
															simpleSelectdiv[i].id.length - 3) + "SelectedImg")).style.visibility = "hidden";
								}
							}

							(dom.byId("LeftToolBar_" + this.simpleSelectTag
									+ "_" + tag + "_SelectedImg")).style.visibility = "visible";

							switch (tag) {
							case "arrow":
								if (this.navToolbar == null) {
									this.navToolbar = new Navigation(this.map);
								}
								this.navToolbar.activate(Navigation.PAN);
								break;
							case "enlarge":
								if (this.navToolbar == null) {
									this.navToolbar = new Navigation(this.map);
								}
								this.navToolbar.activate(Navigation.ZOOM_IN);
								break;
							case "narrow":
								if (this.navToolbar == null) {
									this.navToolbar = new Navigation(this.map);
								}
								this.navToolbar.activate(Navigation.ZOOM_OUT);

								break;
							}

							this.simpleSelect = tag;
						},

						/**
						 * 单击按钮
						 */
						singleClick : function(tag) {
							switch (tag) {
							case "fullExtent":
								if (this.fullExtent == null) {
									this.fullExtent = new esri.geometry.Extent(
											{
												// TODO 深圳三调地址
												"xmin" : 3.8486722337572604E7,
												"ymin" : 2480415.1499582035,
												"xmax" : 3.858183009056279E7,
												"ymax" : 2527370.5749582024,
												"spatialReference" : {
													"wkid" : 2362
												}
											});
								}

								this.map.setExtent(this.fullExtent);
								break;
							}

						},

						/**
						 * 多选按钮
						 */
						multiSelect : function(tag, str) {
							switch (tag) {
							case "showFeatureTable":
								this.showOrHideFeatureTable(str);
								break;
							}
						},

						setFeatureLayer : function(featureLayer) {
							this.myFeatureLayer = featureLayer;
						},

						// 显示特征表与否
						showOrHideFeatureTable : function(str) {

							console
									.debug(" LeftToolBar_v3.21::showOrHideFeatureTable. mFeatureLayerTable="
											+ this.mFeatureLayerTable);
							console
									.debug(" LeftToolBar_v3.21::showOrHideFeatureTable. str="
											+ str);
							if (str === "selected") {

								if (this.mFeatureLayerTable == null) {
									try {
									this.mFeatureLayerTable = new FeatureTable(
											{
												featureLayer : (this.myFeatureLayer),
												map : (this.map),
												edutable : true,
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
														this.mFeatureLayerTable
																.refresh();
													}
												} ]
											}, 'featureTable');
									
									console
											.debug(" LeftToolBar_v3.21::showOrHideFeatureTable. mFeatureLayerTable="
													+ this.mFeatureLayerTable);
									
									this.mFeatureLayerTable.startup();
									}
									catch (err) {
										console.log(" showOrHideFeatureTable::"
												+ err);
									}
									console
											.debug(" LeftToolBar_v3.21::showOrHideFeatureTable. mFeatureLayerTable="
													+ this.mFeatureLayerTable);

								}

							} else {

							}
						},

						/**
						 * 鼠标进入按钮
						 */
						enterEvents : function(evt) {
							var imgId = evt.currentTarget.id;
							imgId = imgId.substring(0, imgId.length - 3)
									+ "SelectingImg";
							console
									.debug(" LeftToolBar_v3.21::enterEvents. imgId="
											+ imgId);
							var img = dom.byId(imgId);
							if (img.style.visibility === "hidden") {
								img.style.visibility = "visible";
								// img.src =
								// require.toUrl(this.selectingImgUrl);
							} else {
								console
										.debug(" LeftToolBar_v3.21::enterEvents. imgId="
												+ imgId + " is visible");
							}
						},

						/**
						 * 鼠标离开按钮
						 */
						leaveEvents : function(evt) {
							var divId = evt.currentTarget.id;
							if (divId === this.simpleSelect) {
								return;
							}
							var imgId = divId.substring(0, divId.length - 3)
									+ "SelectingImg";
							var img = dom.byId(imgId);
							if (img.style.visibility === "visible") {
								img.style.visibility = "hidden";
							}
						},

						arrowButton : function() {
							var narrowImgUrl = this.assetsAddress
									+ "images/lefttoolbar/arrow_30px.png";

							this.initSingleSelctedButton(narrowImgUrl, "arrow");
						},

						/**
						 * 放大按钮
						 */
						enlargeButton : function() {
							var enlargeImgUrl = this.assetsAddress
									+ "images/lefttoolbar/enlarge_30px.png";

							this.initSingleSelctedButton(enlargeImgUrl,
									"enlarge");
						},

						narrowButton : function() {
							var narrowImgUrl = this.assetsAddress
									+ "images/lefttoolbar/narrow_30px.png";

							this
									.initSingleSelctedButton(narrowImgUrl,
											"narrow");
						},

						// 显示全图
						fullExtentButton : function() {
							var fullExtentImgUrl = this.assetsAddress
									+ "images/lefttoolbar/full_extent_30px.png";
							this.initSingleClickButton(fullExtentImgUrl,
									"fullExtent");
						},

						/**
						 * 是否显示特征表
						 */
						showFeatureTableButton : function() {
							var featureTableImgUrl = this.assetsAddress
									+ "images/lefttoolbar/feature_table_30px.png";
							this.initMultiSelectButton(featureTableImgUrl,
									"showFeatureTable");
						},

						/**
						 * 只能选一个功能的按钮
						 */
						initSingleSelctedButton : function(imgUrl, tag) {

							tag = "LeftToolBar_" + this.simpleSelectTag + "_"
									+ tag + "_";

							this.btnAbsolutePositionTop += this.btnAbsolutePositionTopIncrease;

							var currentHtml = "<div id=\"" + tag
									+ "Div\" style=\"position: relative;\" "
									+ " ondragstart=\"return false;\" >";
							// 鼠标移动到图标上响应动画
							currentHtml += "<img id=\""
									+ tag
									+ "SelectingImg\" src=\""
									+ this.selectingImgUrl
									+ "\" style=\" visibility:hidden; position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 1; \"/>";
							// 鼠标点击响应动画
							currentHtml += "<img id=\""
									+ tag
									+ "SelectedImg\" src=\""
									+ this.selectedImgUrl
									+ "\" style=\" visibility:hidden; position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 2; \"/>";
							// 按钮图标
							currentHtml += "<img src=\"" + imgUrl
									+ "\" style=\" position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 3; \" />";
							currentHtml += "</div>";

							this.ui += currentHtml;
						},

						/**
						 * 单击按钮
						 */
						initSingleClickButton : function(imgUrl, tag) {
							tag = "LeftToolBar_" + this.simpleClickTag + "_"
									+ tag + "_";

							this.btnAbsolutePositionTop += this.btnAbsolutePositionTopIncrease;

							var currentHtml = "<div id=\"" + tag
									+ "Div\" style=\"position: relative;\" "
									+ " ondragstart=\"return false;\" >";
							// 鼠标移动到图标上响应动画
							currentHtml += "<img id=\""
									+ tag
									+ "SelectingImg\" src=\""
									+ this.selectingImgUrl
									+ "\" style=\" visibility:hidden; position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 1; \"/>";
							// 鼠标点击响应动画
							currentHtml += "<img id=\""
									+ tag
									+ "SelectedImg\" src=\""
									+ this.selectedImgUrl
									+ "\" style=\" visibility:hidden; position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 2; \"/>";
							// 按钮图标
							currentHtml += "<img src=\"" + imgUrl
									+ "\" style=\" position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 3; \" />";
							currentHtml += "</div>";

							this.ui += currentHtml;
						},

						/**
						 * 多选功能的按钮
						 */
						initMultiSelectButton : function(imgUrl, tag) {

							tag = "LeftToolBar_" + this.multiSelectTag + "_"
									+ tag + "_";

							this.btnAbsolutePositionTop += this.btnAbsolutePositionTopIncrease;

							var currentHtml = "<div id=\"" + tag
									+ "Div\" style=\"position: relative;\" "
									+ " ondragstart=\"return false;\" >";
							// 鼠标移动到图标上响应动画
							currentHtml += "<img id=\""
									+ tag
									+ "SelectingImg\" src=\""
									+ this.selectingImgUrl
									+ "\" style=\" visibility:hidden; position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 1; \"/>";
							// 鼠标点击响应动画
							currentHtml += "<img id=\""
									+ tag
									+ "SelectedImg\" src=\""
									+ this.selectedImgUrl
									+ "\" style=\" visibility:hidden; position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 2; \"/>";
							// 按钮图标
							currentHtml += "<img src=\"" + imgUrl
									+ "\" style=\" position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 3; \" />";
							currentHtml += "</div>";

							this.ui += currentHtml;
						}

					});
		});
