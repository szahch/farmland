/**
 * 页面左边工具栏 适用于ArcGIS API for JavaScript v3.21
 * 
 * 初始化方法 var lc = new LeftToolBar_v321({ map:[esri/map], id:[the id for div]});
 * 
 */

define(
		[ "dojo/_base/declare", "dojo/on", "dojo/dom", "dojo/query",
				"dojo/mouse", "dojo/_base/lang", "esri/toolbars/navigation" ],
		function(declare, on, dom, query, mouse, lang, Navigation) {
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

							if (this.map === null || this.id === null) {
								console.error("Map or Id is Null.");
								throw "Map or Id is Null.";
							}

							this.initLayout();
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

							this.initBtn();

							console.log(" LeftToolBar_v3.21::start." + this.ui);

							this.doc.innerHTML = this.ui;

							this.registerEvents();

						},

						mapMouseDown : function() {

							var select = this.simpleSelect;

							select = select.substring(12, select.length - 4);

							console.log(" LeftToolBar_v3.21::mapMouseDown."
									+ select);							
							
							switch (select) {
							case "arrow":
								if (this.navToolbar == null) {
									this.navToolbar = new Navigation(this.map);
								}
								console.log(" LeftToolBar_v3.21::mapMouseDown.111"
										+ select);	
								this.navToolbar.activate(Navigation.PAN);
								break;
							case "enlarge":
								if (this.navToolbar == null) {
									this.navToolbar = new Navigation(this.map);
								}
								console.log(" LeftToolBar_v3.21::mapMouseDown.111"
										+ select);	
								this.navToolbar.activate(Navigation.ZOOM_IN);
								break;
							case "narrow":
								if (this.navToolbar == null) {
									this.navToolbar = new Navigation(this.map);
								}
								console.log(" LeftToolBar_v3.21::mapMouseDown.2222"
										+ select);	
								this.navToolbar.activate(Navigation.ZOOM_OUT);
								break;
							}
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

						},

						/**
						 * 注册事件
						 */
						registerEvents : function() {

							var imageChildren = query("#" + this.id + " > div");
							console.log(" LeftToolBar_v3.21::connectEvent."
									+ "imageChildren = " + imageChildren.length
									+ imageChildren[0].id);

							for (var i = 0; i < imageChildren.length; i++) {
								on(imageChildren[i], "click", lang.hitch(this,
										"clickEvents"));
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

							if (divId === this.simpleSelect) {
								return;
							}

							if (this.simpleSelect !== "") {

								var historyDiv = this.simpleSelect;

								var historySelectedImg = dom.byId(historyDiv
										.substring(0, historyDiv.length - 3)
										+ "ActiveImg");

								if (historySelectedImg.style.visibility === "visible") {
									historySelectedImg.style.visibility = "hidden";
								}
							}

							this.simpleSelect = divId;

							var imgId = divId.substring(0, divId.length - 3)
									+ "ActiveImg";
							console
									.log(" LeftToolBar_v3.21::clickEvents. imgId="
											+ imgId);
							var img = dom.byId(imgId);
							if (img.style.visibility === "hidden") {
								img.style.visibility = "visible";
							}
							img.src = require.toUrl(this.selectedImgUrl);
							
							this.mapMouseDown();
						},

						/**
						 * 鼠标进入按钮
						 */
						enterEvents : function(evt) {
							var imgId = evt.currentTarget.id;
							imgId = imgId.substring(0, imgId.length - 3)
									+ "ActiveImg";
							console
									.log(" LeftToolBar_v3.21::enterEvents. imgId="
											+ imgId);
							var img = dom.byId(imgId);
							if (img.style.visibility === "hidden") {
								img.style.visibility = "visible";
								img.src = require.toUrl(this.selectingImgUrl);
							} else {
								console
										.log(" LeftToolBar_v3.21::enterEvents. imgId="
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
									+ "ActiveImg";
							var img = dom.byId(imgId);
							if (img.style.visibility === "visible") {
								img.style.visibility = "hidden";
							}
						},
						
						arrowButton : function() {
							var narrowImgUrl = this.assetsAddress
									+ "images/lefttoolbar/arrow_30px.png";

							this
									.initSingleSelctedButton(narrowImgUrl,
											"arrow");

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

						/**
						 * 只能选一个功能的按钮
						 */
						initSingleSelctedButton : function(imgUrl, tag) {

							tag = "LeftToolBar_" + tag + "_";

							this.btnAbsolutePositionTop += this.btnAbsolutePositionTopIncrease;

							var currentHtml = "<div id=\"" + tag
									+ "Div\" style=\"position: relative;\" "
									+ " ondragstart=\"return false;\" >";
							currentHtml += "<img id=\""
									+ tag
									+ "ActiveImg\" src=\""
									+ this.selectingImgUrl
									+ "\" style=\" visibility:hidden; position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 1; \"/>";
							currentHtml += "<img src=\"" + imgUrl
									+ "\" style=\" position: absolute; left: "
									+ this.btnAbsolutePositionLeft
									+ "px; top: " + this.btnAbsolutePositionTop
									+ "px; z-index: 2; \" />";
							currentHtml += "</div>";

							this.ui += currentHtml;
						}
					});
		});
