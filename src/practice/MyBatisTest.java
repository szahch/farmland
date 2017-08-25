package practice;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisTest {

	public static void main(String str[]) {

		String resource = "com/szahch/data/mybatis-config.xml";

		try {

			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			
			
			System.out.println("Done");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
