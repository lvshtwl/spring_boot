package com.sqlva.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Spring-data-jpa支持
 * 新增对第一数据源的JPA配置，注意两处注释的地方，用于指定数据源对应的Entity实体和Repository定义位置，用@Primary区分主数据源。
 */
@Configuration
@EnableTransactionManagement // 声明开启事务
@EnableConfigurationProperties(HibernateProperties.class)
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages ={"com.sqlva.springboot.p"})
 //用来扫描和发现指定包及其子包中的Repository定义  .多处使用@EnableJpaRepositories，它们的basePackages集合不能有交集
public class PrimaryConfig {
    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    private HibernateProperties  hibernateProperties;

    @Autowired
    JpaProperties jpaProperties;
    private Map<String,Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }
    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }
    /**
     * return LocalContainerEntityManagerFactoryBean 将基于 persistence.xml 文件创建 PersistenceUnitInfo类，并提供 dataSourceLookup 策略和 loadTimeWeaver。 因此它可以在JNDI之外的用户定义的数据源之上工作，并控制织入流程。
     */
    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder  builder){
        return builder.dataSource(primaryDataSource)
                .properties(getVendorProperties())
                .packages("com.sqlva.springboot.p") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
