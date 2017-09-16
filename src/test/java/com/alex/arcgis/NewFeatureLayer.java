package com.alex.arcgis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.gdal.gdal.gdal;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.Driver;
import org.gdal.ogr.ogr;
import org.junit.Test;

public class NewFeatureLayer {

	@Test
	public void loadShpFileToJson() {
		// 注册所有的驱动
		ogr.RegisterAll();
		// 为了支持中文路径，请添加下面这句代码
		gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
		// 为了使属性表字段支持中文，请添加下面这句
		gdal.SetConfigOption("SHAPE_ENCODING", "");

		String strVectorFile = "F://Alex//Desktop//爱华文件//一莉//测试3//测试3.shp";

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

		System.out.println("转换成功！");

		System.out.println(readToString(str));
	}

	public String readToString(String fileName) {
		String encoding = "gbk";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void uploadToArcGISServer() {
		// FIXME 数据不知如何创建信的图层
		String src = readToString("F://Alex//Desktop//爱华文件//一莉//测试//测试.json");
	}

}
