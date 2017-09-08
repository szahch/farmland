package com.szahch.test.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Orcal连接测试代码
 * 
 * @author Alex
 * @version 1.0
 */
public class OrcalConnectionTest {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			// 加载Oracle驱动程序
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("开始尝试连接数据库");
			// 127.0.0.1是本机地址，arcgis是Oracle的数据库名
			String url = "jdbc:oracle:thin:@192.168.2.144:1521:arcgis";
			String user = "system";
			String password = "arcgis";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("连接成功！");
			// 预编译语句，“？”代表参数
			String sql = "select * from TABLE_FARMLAND_USER where USER_ID=?";
			// 实例化预编译语句
			pre = con.prepareStatement(sql);
			// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			pre.setInt(1, 1);
			// 执行查询，注意括号中不需要再加参数
			rs = pre.executeQuery();
			// 当结果集不为空时
			while (rs.next()) {
				System.out.println("USER_NAME:" + rs.getString("USER_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
