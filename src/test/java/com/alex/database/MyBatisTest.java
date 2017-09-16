package com.alex.database;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.szahch.dao.UserDao;
import com.szahch.pojo.User;
import com.szahch.utils.SQLSessionFactoryUtils;

public class MyBatisTest {

	public static void main(String str[]) {
		// test1();

		//test2();

		 test3();
	}

	private static void test3() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserDao dao = ctx.getBean(UserDao.class);
		User user = dao.queryById(1);
		System.out.println(user.getName());
	}

	private static void test1() {

		final String resource = "mybatis-config.xml";

		new Thread(new Runnable() {

			public void run() {

				try {

					Reader reader = Resources.getResourceAsReader(resource);

					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

					System.out.println("Done");

					SqlSession session = sqlSessionFactory.openSession();

					UserDao dao = session.getMapper(UserDao.class);
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

	private static void test2() {

		SqlSession session = SQLSessionFactoryUtils.openSession();

		UserDao dao = session.getMapper(UserDao.class);

		User usr = dao.queryById(1);

		session.commit();

		System.out.println(usr.getName());

	}

}
