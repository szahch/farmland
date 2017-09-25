package com.szahch.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 网站初始化
 * 
 * @author AlexZHOU 2017.9.19
 * 
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private final static Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);

	/**
	 * Spring IOC 容器配置
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info(logger.getName().getClass() + "::Spring IOC 容器配置");
		return new Class<?>[] { RootConfig.class };
	}

	/**
	 * DispatcherServlet的URI映射关系配置
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.info(logger.getName().getClass() + "::DispatcherServlet的URI映射关系配置");
		return new Class<?>[] { WebConfig.class };
	}

	/**
	 * DispatcherServlet拦截内容
	 */
	@Override
	protected String[] getServletMappings() {
		logger.info(logger.getName().getClass() + "::DispatcherServlet拦截内容");
		
		return new String[] { "/" };
	}
	
	
}
