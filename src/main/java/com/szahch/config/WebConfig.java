package com.szahch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 系统配置
 * 
 * @author AlexZHOU 2017.9.19
 *
 */
@Configuration
// 定义扫描的包，加载控制器
@ComponentScan("com.*")
// 启用Spring Web MVC
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 创建视图解析器
	 * 
	 * @return 视图解析器
	 */
	@Bean(name = "viewResolver")
	public ViewResolver initViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// 配置静态资源
		configurer.enable();
	}

}
