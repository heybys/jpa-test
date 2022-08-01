package com.heybys.jpatest.sub.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.heybys.jpatest.sub.repository",
    entityManagerFactoryRef = "subEntityManagerFactory",
    transactionManagerRef = "subTransactionManager"
)
public class SubDataSourceConfig {

    @Value("${spring.jpa.database}")
    private Database database;

    @Value("${spring.jpa.database-platform}")
    private String databasePlatform;

    @Value("${spring.jpa.show-sql}")
    private Boolean showSql;

    @Value("${spring.jpa.generate-ddl}")
    private Boolean generateDdl;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private String jdbcBatchSize;

    @Value("${spring.jpa.hibernate.naming.physical-strategy}")
    private String namingPhysicalStrategy;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.hibernate.use-new-id-generator-mappings}")
    private String useNewIdGeneratorMappings;

    @Bean("subHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.sub")
    public HikariConfig subHikariConfig() {
        return new HikariConfig();
    }

    @Bean("subDataSource")
    public HikariDataSource subDataSource() {
        return new HikariDataSource(subHikariConfig());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean subEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.subDataSource());
        em.setPersistenceUnitName("subEntityManager");
        em.setPackagesToScan(new String[]{"com.heybys.jpatest.sub.entity"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(database);
        vendorAdapter.setDatabasePlatform(databasePlatform);
        vendorAdapter.setGenerateDdl(generateDdl);
        vendorAdapter.setShowSql(showSql);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.jdbc.batch_size", jdbcBatchSize);
        properties.setProperty("hibernate.naming.physical-strategy", namingPhysicalStrategy);
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty("hibernate.id.new_generator_mappings",
            useNewIdGeneratorMappings);
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager subTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(subEntityManagerFactory().getObject());

        return transactionManager;
    }
}
