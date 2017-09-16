package com.alex.arcgis;

import org.gdal.ogr.*;
import org.gdal.ogr.Driver;
import org.junit.Test;
import org.gdal.gdal.*;

public class GdalShpTest {
	@Test
	public void shp2Json() {

		// 注册所有的驱动
		ogr.RegisterAll();
		// 为了支持中文路径，请添加下面这句代码
		gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
		// 为了使属性表字段支持中文，请添加下面这句
		gdal.SetConfigOption("SHAPE_ENCODING", "");

		//String strVectorFile = "F:\\Alex\\Desktop\\爱华文件\\一莉\\省级行政区\\省级行政区.shp";

//		String strVectorFile ="F:\\Alex\\Desktop\\爱华文件\\一莉\\测试\\测试.shp";
		
		String strVectorFile ="F:\\Alex\\Desktop\\爱华文件\\南哥\\耕地成果\\GDJC2017\\GDJC2017.shp";
		// String strVectorFile ="F:\\Alex\\Desktop\\爱华文件\\一莉\\测试1\\测试1.shp";
		// String strVectorFile =
		// "//Users//apple//Downloads//GDJCWY2017-1//GDJCWY2017-1.shx";
		// 打开文件
		DataSource ds = ogr.Open(strVectorFile, 0);
		if (ds == null) {
			System.out.println("打开文件失败！");
			return;
		}
		System.out.println("打开文件成功！");
		Driver dv = ogr.GetDriverByName("GeoJSON");
		if (dv == null) {
			System.out.println("打开驱动失败！");
			return;
		}
		System.out.println("打开驱动成功！");
		// System.out.println(dv.GetMetadataDomainList());

		String str = strVectorFile.substring(0, strVectorFile.length() - 4) + ".json";

		System.out.println(str);

		dv.CopyDataSource(ds, str);

		System.out.println(str);
		System.out.println(ds.GetDescription());
		System.out.println("转换成功！");

	}
}