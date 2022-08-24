package com.heybys.optimusamicus.common.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class CommonConfigFactory {

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

  @Value("${spring.jpa.hibernate.naming.implicit-strategy}")
  private String namingImplicitStrategy;

  @Value("${spring.jpa.hibernate.naming.physical-strategy}")
  private String namingPhysicalStrategy;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String ddlAuto;

  @Value("${spring.jpa.hibernate.use-new-id-generator-mappings}")
  private String useNewIdGeneratorMappings;

  /**
   * Create EntityManagerFactory with Hibernate Properties.
   *
   * @see <a
   *     href="https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html">
   *     Hibernate ORM User Guide</a>
   */
  public LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean() {

    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

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
    // properties.put("hibernate.order_inserts", orderInserts);
    // properties.put("hibernate.order_updates", orderUpdates);
    properties.put("hibernate.implicit_naming_strategy", namingImplicitStrategy);
    properties.put("hibernate.physical_naming_strategy", namingPhysicalStrategy);
    properties.put("hibernate.hbm2ddl.auto", ddlAuto);
    properties.put("hibernate.id.new_generator_mappings", useNewIdGeneratorMappings);
    em.setJpaProperties(properties);

    return em;
  }
}
