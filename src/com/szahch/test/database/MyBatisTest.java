package com.szahch.test.database;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.szahch.data.model.User;
import com.szahch.data.model.UserMapper;

public class MyBatisTest {

	public static void main(String str[]) {

		String resource = "com/szahch/data/mybatis-config.xml";

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {

					Reader reader = Resources.getResourceAsReader(resource);

					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

					System.out.println("Done");

					SqlSession session = sqlSessionFactory.openSession();

					UserMapper dao = session.getMapper(UserMapper.class);
					// dao.queryById(1);
					 User usr = dao.queryById(1);

					session.commit();

					System.out.println(usr.getName());

				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}).start();

	}
}
