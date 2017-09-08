package com.szahch.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SQLSessionFactoryUtils {

	private static SqlSessionFactory mSqlSessionFactory = null;

	private final static Class<SQLSessionFactoryUtils> LOCK = SQLSessionFactoryUtils.class;

	private static void initSqlSessionFactory() {

		synchronized (LOCK) {

			String mybatisResource = "mybatis-config.xml";

			String jdbcResource = "jdbc.properties";

			InputStream inputStream = null;

			InputStream in = null;

			try {
				in = Resources.getResourceAsStream(jdbcResource);

				Properties props = new Properties();

				props.load(in);

				String username = props.getProperty("database.username");

				String password = props.getProperty("database.password");

				props.put("database.username", username);

				props.put("database.password", password);

				inputStream = Resources.getResourceAsStream(mybatisResource);

				mSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, props);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				try {

					if (inputStream != null) {
						inputStream.close();
					}

					if (in != null) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static SqlSession openSession() {
		if (mSqlSessionFactory == null) {
			initSqlSessionFactory();
		}
		return mSqlSessionFactory.openSession();
	}

}
