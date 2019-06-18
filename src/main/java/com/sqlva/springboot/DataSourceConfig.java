package com.sqlva.springboot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {
    /**
     * 主数据源配置
     * @return
     */
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource") //存在多个实例配合使用
    @ConfigurationProperties(prefix="spring.datasource.primary")
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 第二数据源配置
     * @return
     */
    @Bean(name = "secondDataSource")
    @Qualifier("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    //自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    //JdbcTemplate支持
    @Bean(name = "primaryJdbcTemplate")
    @Primary
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    //JdbcTemplate支持
    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
