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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(HibernateProperties.class)
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecond",
        basePackages = {"com.sqlva.springboot.s"})
public class SecondConfig {

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource secondDataSource;
    @Autowired
    HibernateProperties hibernateProperties;

    @Autowired
    JpaProperties jpaProperties;

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecond(builder).getObject().createEntityManager();
    }


    /**
     * return LocalContainerEntityManagerFactoryBean 将基于 persistence.xml 文件创建 PersistenceUnitInfo类，并提供 dataSourceLookup 策略和 loadTimeWeaver。 因此它可以在JNDI之外的用户定义的数据源之上工作，并控制织入流程。
     */
    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecond(EntityManagerFactoryBuilder  builder){
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(secondDataSource)
                .properties(properties)
                .packages("com.sqlva.springboot.s") //设置实体类所在位置
                .persistenceUnit("SecondPersistenceUnit")
                .build();
    }

    @Bean(name = "transactionManagerSecond")
    public PlatformTransactionManager transactionManagerSecond(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecond(builder).getObject());
    }
}
