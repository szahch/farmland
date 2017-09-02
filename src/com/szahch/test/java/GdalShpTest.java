package com.szahch.test.java;

import org.gdal.ogr.*;
import org.gdal.ogr.Driver;
import org.gdal.gdal.*;

public class GdalShpTest {
	  
	public static void main(String[] args) {
		
		
		// 注册所有的驱动
		ogr.RegisterAll();
		// 为了支持中文路径，请添加下面这句代码
		gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
		// 为了使属性表字段支持中文，请添加下面这句
		gdal.SetConfigOption("SHAPE_ENCODING", "");

		String strVectorFile = "D:\\Disk_Old_2017_7_31\\01龙岗区三调试点\\国家不一致图斑内业整理后_20170711\\国家图斑整理后.shp";
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
		//System.out.println(dv.GetMetadataDomainList());
		
		String str =strVectorFile.substring(0, strVectorFile.length()-4)+".json";
		
		System.out.println(str);
		
		dv.CopyDataSource(ds, str);
		
		
		System.out.println(str);
		System.out.println(ds.GetDescription());
		System.out.println("转换成功！");
	}
}