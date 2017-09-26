package com.szahch.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
// import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

// import redis.clients.jedis.JedisPoolConfig;

/***
 * 这个扫描类我也还不是理解得非常透彻，不要问我任何问题。 总之是Spring Ioc处理数据库连接的。
 * 
 * @author AlexZHOU 2017.9.21
 *
 */
@Configuration
// 定义Spring 扫描的包
@ComponentScan(value = "com.*", includeFilters = { @Filter(type = FilterType.ANNOTATION, value = { Service.class }) })
// 使用事务驱动管理器
@EnableTransactionManagement
// 实现接口TransactionManagementConfigurer，这样可以配置注解驱动事务
public class RootConfig implements TransactionManagementConfigurer {

	private DataSource dataSource = null;

	/**
	 * 配置数据库.
	 * 
	 * @return 数据连接池
	 */
	@Bean(name = "dataSource")
	public DataSource initDataSource() {
		if (dataSource != null) {
			return dataSource;
		}
		Properties props = new Properties();
		props.setProperty("driverClassName", "oracle.jdbc.driver.OracleDriver");
		props.setProperty("url", "jdbc:oracle:thin:@192.168.2.144:1521:arcgis");
		props.setProperty("username", "system");
		props.setProperty("password", "arcgis");
		props.setProperty("maxActive", "255");
		props.setProperty("maxIdle", "5");
		props.setProperty("maxWait", "30000");
		try {
			dataSource = BasicDataSourceFactory.createDataSource(props);
			System.out.println("dataSource succeed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("dataSource");
		return dataSource;
	}

	/***
	 * 配置SqlSessionFactoryBean
	 * 
	 * @return SqlSessionFactoryBean
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean initSqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(initDataSource());
		// 配置MyBatis配置文件
		Resource resource = new ClassPathResource("mybatis/mybatis-config.xml");
		sqlSessionFactory.setConfigLocation(resource);
		System.out.println("sqlSessionFactory");
		return sqlSessionFactory;
	}

	/***
	 * 通过自动扫描，发现MyBatis Mapper接口
	 * 
	 * @return Mapper扫描器
	 */
	@Bean
	public MapperScannerConfigurer initMapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.*");
		msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		msc.setAnnotationClass(Repository.class);
		return msc;
	}

	// public PlatformTransactionManager annotationDrivenTransactionManager() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	/**
	 * 实现接口方法，注册注解事务，当@Transactional 使用的时候产生数据库事务
	 */
	@Bean(name = "annotationDrivenTransactionManager")
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(initDataSource());
		return transactionManager;
	}
	
	
	

}