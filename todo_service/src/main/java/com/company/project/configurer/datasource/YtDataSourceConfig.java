package com.company.project.configurer.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.company.project.dao.yt", sqlSessionFactoryRef = "ytSqlSessionTemplate")
public class YtDataSourceConfig {

	@Value("${spring.datasource.yt.url}")
	private String url;
	@Value("${spring.datasource.yt.username}")
	private String user;
	@Value("${spring.datasource.yt.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;
	@Value("${spring.datasource.initial-size}")
	private int initialSize;
	@Value("${spring.datasource.max-active}")
	private int maxActive;
	@Value("${spring.datasource.min-idle}")
	private int minIdle;
	@Value("${spring.datasource.validation-query}")
	private String validationQuery;
	@Value("${spring.datasource.test-while-idle}")
	private boolean testWhileIdle;
	@Value("${spring.datasource.filters}")
	private String filters;

	/**
	 * 注入 datasource 数据源
	 * 
	 * @return
	 */
	@Bean(name = "ytDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.yt")
	public DataSource setDataSource() {
		// 使用druid 则这样注入 dataSource，不需要则直接 DataSourceBuilder.create().build()
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestWhileIdle(testWhileIdle);
		try {
			dataSource.setFilters(filters);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	/**
	 * 注入 事务，在 serviceImpl 的时候使用
	 * 
	 * @return
	 */
	@Bean(name = "ytTransationManager")
	public DataSourceTransactionManager setTransactionManager() {
		return new DataSourceTransactionManager(setDataSource());
	}

	/**
	 * 注入 sqlSession
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "ytSqlSessionTemplate")
	public SqlSessionFactory setSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(setDataSource());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return bean.getObject();
	}
}
