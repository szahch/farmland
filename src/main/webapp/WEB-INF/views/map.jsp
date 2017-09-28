<!-- ArcGIS API for JavaScript 版本号3.21地图操作首页 -->

<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1,user-scalable=no" />

<title>操作地图界面</title>

<!-- CSS start-->
<link rel="stylesheet"
	href="http://192.168.2.101:8080/arcgis_js_api_v3.21/library/3.21/3.21/dijit/themes/tundra/tundra.css" />
<!-- 注意claro和esri放置顺序，切记 -->
<link rel="stylesheet"
	href="http://192.168.2.101:8080/arcgis_js_api_v3.21/library/3.21/3.21/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css"
	href="http://192.168.2.101:8080/arcgis_js_api_v3.21/library/3.21/3.21/esri/css/esri.css" />
<style>
#title {
	background-color: #325C84;
	width: 100%;
}
</style>
<link rel="stylesheet" href="/farmland/css/map/main.css">
<link rel="stylesheet" href="../css/operateMap.css">
<!-- CSS end -->

<!-- JavaScript start -->
<script
	src="http://192.168.2.101:8080/arcgis_js_api_v3.21/library/3.21/3.21/init.js"></script>
<!-- <script src="http://192.168.2.101:8080/farmland/js/jquery-3.2.1.min.js"></script> -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/map.js"></script>
<!-- JavaScript end -->

<style type="text/css">
a:link {
	/* color: #blue; */
	color: #66CCFF;
	text-decoration: none;
}

a:visited {
	color: #66CCFF;
	text-decoration: none;
}

a:hover {
	color: #FFFF33;
	text-decoration: none;
}

a:active {
	color: #0033FF;
	text-decoration: none;
}
</style>
</head>
<body>

	<div id="mainDiv" data-dojo-type="dijit/layout/BorderContainer"
		data-dojo-props="gutters:true, design:'headline'">

		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="region:'top'"
			style="height: 79px; padding-bottom: 5px;">
			<div id="title" style="height: 100%;">
				<div style="padding-top: 10px; padding-left: 10px; float: left;">
					<a href="/farmland/map/index"><img
						src="../js/map/assets/images/logo.png" alt="深圳市规划国土发展研究中心" /></a>
				</div>

				<div
					style="float: right; padding-top: 49px; padding-right: 10px; height: 20px">
					<div style="color: white; float: left;">${user.name }，欢迎您。</div>
					<a style="float: right;" href="/farmland/user/signOut">注销</a>
				</div>

			</div>
		</div>

		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="region:'center'">
			<div id="mapDiv"></div>
		</div>

		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="region:'left'"
			style="background-color: #acb386; width: 40px;">
			<div id="leftToolBar"></div>
		</div>
		<div id="layerControl" data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="region:'right', splitter:'true'"
			style="background-color: #acb386; width: 100px;"></div>
		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="region:'bottom', splitter:'true'"
			style="background-color: #b39b86; height: 100px;">${user.name}
		</div>

	</div>
	<!-- drag -->

	<!-- FIXME 不知道为嘛下面这句话不能删，我自己都忘了写了什么了 2017.9.25 -->
	<div id="aaa"></div>


	<!-- 拖动界面暂时保留，应该可以删除 -->
	<!--  <div id="drag" style="width: 50%; height: 400px;">
		<div class="title">
			<h2>属性</h2>
			<div>
				<a class="min" href="javascript:;" title="最小化"></a> <a class="max"
					href="javascript:;" title="最大化"></a> <a class="revert"
					href="javascript:;" title="还原"></a> <a class="close"
					href="javascript:;" title="关闭"></a>
			</div>
		</div>
		<div class="resizeL"></div>
		<div class="resizeT"></div>
		<div class="resizeR"></div>
		<div class="resizeB"></div>
		<div class="resizeLT"></div>
		<div class="resizeTR"></div>
		<div class="resizeBR"></div>
		<div class="resizeLB"></div>
		<div id="content" class="content">
			<div id="myTableNode" style="bottom: 0px; left: 0px;"></div>
			<div id="bot"></div>
		</div>
	</div>   -->

</body>
</html>