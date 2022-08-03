package com.heybys.optimusamicus.user.config;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    basePackages = "com.heybys.optimusamicus.user.repository",
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userTransactionManager"
)
public class UserDataSourceConfig {

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
    private Boolean useNewIdGeneratorMappings;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.user")
    public HikariConfig userHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    public HikariDataSource userDataSource() {
        return new HikariDataSource(userHikariConfig());
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
        DataSource userDataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource);
        em.setPersistenceUnitName("userEntityManager");
        em.setPackagesToScan(new String[]{"com.heybys.optimusamicus.user.entity"});

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
        properties.put("hibernate.batch_versioned_data", true);
        properties.put("hibernate.naming.physical-strategy", namingPhysicalStrategy);
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        properties.put("hibernate.id.new_generator_mappings", useNewIdGeneratorMappings);
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager userTransactionManager(
        EntityManagerFactory userEntityManagerFactory) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManagerFactory);

        return transactionManager;
    }

    @Bean
    public JdbcEventListener myListener() {
        return new JdbcEventListener() {
            @Override
            public void onAfterGetConnection(ConnectionInformation connectionInformation,
                SQLException e) {
                System.out.println("connection started ====================");
            }

            @Override
            public void onAfterConnectionClose(ConnectionInformation connectionInformation,
                SQLException e) {
                System.out.println("connection closed ======================");
            }
        };
    }
}
