package com.heybys.optimusamicus.order.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
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
    basePackages = "com.heybys.optimusamicus.order.repository",
    entityManagerFactoryRef = "orderEntityManagerFactory",
    transactionManagerRef = "orderTransactionManager"
)
public class OrderDataSourceConfig {

    @Value("${spring.jpa.database}")
    private Database database;

    @Value("${spring.jpa.database-platform}")
    private String databasePlatform;

    @Value("${spring.jpa.show-sql}")
    private Boolean showSql;

    @Value("${spring.jpa.generate-ddl}")
    private Boolean generateDdl;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${spring.jpa.properties.hibernate.default_batch_fetch_size}")
    private Integer defaultBatchFetchSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private Integer jdbcBatchSize;

    @Value("${spring.jpa.properties.hibernate.order_inserts}")
    private Boolean orderInserts;

    @Value("${spring.jpa.properties.hibernate.order_updates}")
    private Boolean orderUpdates;

    @Value("${spring.jpa.hibernate.naming.physical-strategy}")
    private String namingPhysicalStrategy;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.hibernate.use-new-id-generator-mappings}")
    private String useNewIdGeneratorMappings;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.order")
    public HikariConfig orderHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public HikariDataSource orderDataSource() {
        return new HikariDataSource(orderHikariConfig());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(
        DataSource orderDataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(orderDataSource);
        em.setPersistenceUnitName("orderEntityManager");
        em.setPackagesToScan(new String[]{"com.heybys.optimusamicus.order.entity"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(database);
        vendorAdapter.setDatabasePlatform(databasePlatform);
        vendorAdapter.setGenerateDdl(generateDdl);
        vendorAdapter.setShowSql(showSql);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.default_batch_fetch_size", defaultBatchFetchSize);
        properties.put("hibernate.jdbc.batch_size", jdbcBatchSize);
        properties.put("hibernate.order_inserts", orderInserts);
        properties.put("hibernate.order_updates", orderUpdates);
        properties.put("hibernate.naming.physical-strategy", namingPhysicalStrategy);
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        properties.put("hibernate.id.new_generator_mappings",useNewIdGeneratorMappings);
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager orderTransactionManager(
        EntityManagerFactory orderEntityManagerFactory) {
        
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(orderEntityManagerFactory);

        return transactionManager;
    }
}
