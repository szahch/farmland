/**
 * 图层控制器 适用于ArcGIS API for JavaScript v3.21
 * 
 * 初始化方法 var lc = new LayerControl_v321({ map:[esri/map], id:[the id for div]});
 * 
 */
define(
		[ "dojo/_base/declare", "dijit/form/CheckBox", "dojo/on",
				"dojo/_base/lang", "dojo/query" ],
		function(declare, CheckBox, on, lang, query) {

			return declare(
					"LayerControl_v321",
					[],
					{
						_extend : null,

						// 还需要加载的图层的数量，用于控制是否还需要进行初始化操作
						layerNumNeedtoLoad : Number.MAX_VALUE,

						constructor : function(param) {
							this.param = {};

							this._extend(param, param);

							this.param = param;

							this.map = this.param.map;

							this.id = this.param.id;

							if (this.map === null || this.id === null) {
								console.error("Map or Id is Null.");
								throw "Map or Id is Null.";
							}

							this.initLayout();
						},

						_extend : function(dest, src) {
							for (key in src) {
								if (src.hasOwnProperty(key)) {
									dest[key] = src[key];
								}
							}
						},

						/**
						 * 初始化图层
						 */
						initLayout : function() {

							this.doc = document.getElementById(this.id);

							this.ui = "";

							if (this.layerNumNeedtoLoad === 0) {
								return;
							}

							this.layerNumNeedtoLoad = this.map.layerIds.length;

							console.debug(" LayerControl_v3.21::initLayout."
									+ "layerNumNeedtoLoad="
									+ this.layerNumNeedtoLoad + ", layerIds="
									+ this.map.layerIds[0]
									+ ", graphicsLayerIds="
									+ this.map.graphicsLayerIds[0]);

							for (var i = 0; i < this.map.graphicsLayerIds.length; i++) {
								var layer = this.map
										.getLayer(this.map.graphicsLayerIds[i]);
								console.debug(layer);

								this.addToTOC(layer);
							}

							for (var i = 0; i < this.map.layerIds.length; i++) {
								var layer = this.map
										.getLayer(this.map.layerIds[i]);
								this.addToTOC(layer);
							}
						},

						/**
						 * 将图层数据加入图层控制器
						 */
						addToTOC : function(layer) {
							console.log(" LayerControl_v3.21::addToTOC::"
									+ "layerId = " + layer.id + " loaded = "
									+ layer.loaded);
							if (layer.loaded) {
								this.buildLayerList(layer);
							} else {
								layer.on("load", lang
										.hitch(this, "layerLoaded"));
							}
						},

						layerLoaded : function(evt) {
							this.buildLayerList(evt.layer);
						},

						buildLayerList : function(layer) {
							console.debug(" LayerControl_v3.21::buildLayerList="+layer.id);
							
							
							var currentLayer = layer;
							var currentHTML = "";
							var expandImageUrl = require
									.toUrl("/Farmland/js/map/assets/images/expand.bmp");
							console.log(" LayerControl_v3.21::buildLayerList::"
									+ "expandImageUrl = " + expandImageUrl);
							var blankImageUrl = require
									.toUrl("/Farmland/js/map/assets/images/white.bmp");
							console.log(" LayerControl_v3.21::buildLayerList::"
									+ "blankImageUrl = " + blankImageUrl);

							currentHTML += "<img src='" + expandImageUrl
									+ "' id='" + currentLayer.id + "Icon' />";
							currentHTML += "<input type='checkbox' data-dojo-type='dijit/form/CheckBox' class='TOC_Root' "
									+ (currentLayer.visible ? " CHECKED " : "")
									+ " id='" + currentLayer.id + "'/>";
							currentHTML += "<label ' for='" + currentLayer.id
									+ "'>" + currentLayer.id + "</label><br>";
							console.log(" LayerControl_v3.21::buildLayerList::"
									+ "currentHTML1 = \n" + currentHTML);

							var subLayers = currentLayer.layerInfos;
							var isDynamicLayer = true;
							if (layer.declaredClass === "esri.layers.ArcGISTiledMapServiceLayer") {
								isDynamicLayer = false;
							}
							var imgSrcHtml = "<img src='" + blankImageUrl
									+ "'/>";
							currentHTML += "<div id='"
									+ currentLayer.id
									+ "Layers' style='display:none;margin-right: 10px;'>";
							for (var i = 0; i < subLayers.length; i++) {
								var currentSubLayer = subLayers[i];
								console.log("TocWidget::"
										+ currentSubLayer.name);
								currentHTML += imgSrcHtml + imgSrcHtml;
								if (isDynamicLayer === true) {
									currentHTML += "<input type='checkbox' class='"
											+ currentLayer.id
											+ "TOC' "
											+ (currentSubLayer.defaultVisibility ? " CHECKED "
													: "")
											+ " id='"
											+ currentSubLayer.id + "'/>";
								}
								currentHTML += "<label for='"
										+ currentSubLayer.id + "'>"
										+ currentSubLayer.name + "</label><br>"
							}
							currentHTML += "</table></div>";
							console.log(" LayerControl_v3.21::buildLayerList::"
									+ "currentHTML2 = \n" + currentHTML);

							this.ui = currentHTML + this.ui;
							this.showUI();

							this.layerNumNeedtoLoad--;
							// 只有当所有图层都加载完毕后，才能进行事件连接操作
							if (this.layerNumNeedtoLoad === 0) {
								this.connectEvent();
							}
						},

						// 连接到事件，意思就是注册事件
						connectEvent : function() {
							console.log(" LayerControl_v3.21::connectEvent.");
							// 加>表示选择直接子元素
							var imageChildren = query("#toc > img");
							console.log(" LayerControl_v3.21::connectEvent."
									+ "imageChildren = " + imageChildren.length
									+ imageChildren[0].id);

							for (var i = 0; i < imageChildren.length; i++) {
								on(imageChildren[i], "click", lang.hitch(this,
										"toggleLayer"));
							}

							var inputChildren = dojo.query("#toc > input");
							for (var i = 0; i < inputChildren.length; i++) {
								on(inputChildren[i], "click", lang.hitch(this,
										"toggleService"));
							}
						},

						/**
						 * 展开图层列表
						 */
						toggleLayer : function(evt) {

							var id = evt.currentTarget.id;
							id = id.substring(0, id.length - 4);
							console.log(id);
							var layerDiv = document.getElementById(id
									+ 'Layers');
							var icon = document.getElementById(id + 'Icon');
							if (layerDiv.style.display == 'block') {
								icon.src = require
										.toUrl("/Farmland/js/map/assets/images/expand.bmp");
								layerDiv.style.display = 'none';
							} else {
								icon.src = require
										.toUrl("/Farmland/js/map/assets/images/close.bmp");
								layerDiv.style.display = 'block';
							}
						},

						toggleService : function(evt) {
							var layerID = evt.currentTarget.id;
							var layer = this.map.getLayer(layerID);
							if (layer.visible) {
								layer.hide();
							} else {
								layer.show();
							}
						},

						showUI : function() {
							this.doc.innerHTML = "<div id='toc' style='overflow:hidden; word-break:keep-all;white-space:nowrap; text-overflow:ellipsis;'>"
									+ this.ui + "</div>";
							console.log(" LayerControl_v3.21::start.");
						}
					})
		});
