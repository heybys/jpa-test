package com.heybys.optimusamicus.common.config;

import static org.hibernate.cfg.AvailableSettings.DEFAULT_BATCH_FETCH_SIZE;
import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.IMPLICIT_NAMING_STRATEGY;
import static org.hibernate.cfg.AvailableSettings.PHYSICAL_NAMING_STRATEGY;
import static org.hibernate.cfg.AvailableSettings.STATEMENT_BATCH_SIZE;
import static org.hibernate.cfg.AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS;

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

  public LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean() {

    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(database);
    vendorAdapter.setDatabasePlatform(databasePlatform);
    vendorAdapter.setGenerateDdl(generateDdl);
    vendorAdapter.setShowSql(showSql);
    em.setJpaVendorAdapter(vendorAdapter);

    Properties properties = new Properties();
    properties.put(FORMAT_SQL, formatSql);
    properties.put(DEFAULT_BATCH_FETCH_SIZE, defaultBatchFetchSize);
    properties.put(STATEMENT_BATCH_SIZE, jdbcBatchSize);
    properties.put(IMPLICIT_NAMING_STRATEGY, namingImplicitStrategy);
    properties.put(PHYSICAL_NAMING_STRATEGY, namingPhysicalStrategy);
    properties.put(HBM2DDL_AUTO, ddlAuto);
    properties.put(USE_NEW_ID_GENERATOR_MAPPINGS, useNewIdGeneratorMappings);
    // properties.put(ORDER_INSERTS, orderInserts);
    // properties.put(ORDER_UPDATES, orderUpdates);
    // properties.put(FLUSH_MODE, FlushModeType.COMMIT.name());
    em.setJpaProperties(properties);

    // em.afterPropertiesSet();

    return em;
  }
}
