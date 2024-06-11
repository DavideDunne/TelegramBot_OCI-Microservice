package com.springboot.MyTodoList.config;


import oracle.jdbc.pool.OracleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
///*
//    This class grabs the appropriate values for OracleDataSource,
//    The method that uses env, grabs it from the environment variables set
//    in the docker container. The method that uses dbSettings is for local testing
//    @author: peter.song@oracle.com
// */
//
//
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class OracleConfiguration {
    Logger logger = LoggerFactory.getLogger(DbSettings.class);
    @Autowired
    private DbSettings dbSettings;
    @Autowired
    private Environment env;

    // start of local development lines with application.properties
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;
    // end of local development lines with application.properties

    @Bean
    public DataSource dataSource() throws SQLException{
        OracleDataSource ds = new OracleDataSource();
        // Start Kubernetes deployment
//        ds.setDriverType(env.getProperty("driver_class_name"));
//        logger.info("Using Driver " + env.getProperty("driver_class_name"));
//        ds.setURL(env.getProperty("db_url"));
//        logger.info("Using URL: " + env.getProperty("db_url"));
//        ds.setUser(env.getProperty("db_user"));
//        logger.info("Using Username " + env.getProperty("db_user"));
//        ds.setPassword(env.getProperty("dbpassword"));
        // End Kubernetes deployment

//        Start of lines for local testing with application.yaml
//        ds.setDriverType(dbSettings.getDriver_class_name());
//        logger.info("Using Driver " + dbSettings.getDriver_class_name());
//        ds.setURL(dbSettings.getUrl());
//        logger.info("Using URL: " + dbSettings.getUrl());
//        ds.setUser(dbSettings.getUsername());
//        logger.info("Using Username: " + dbSettings.getUsername());
//        ds.setPassword(dbSettings.getPassword());
//        End of lines for local testing with application.yaml

//        Start of lines for local testing with application.properties
        ds.setDriverType(driverClassName);
        logger.info("Using Driver " + driverClassName);
        ds.setURL(dbUrl);
        logger.info("Using URL: " + dbUrl);
        ds.setUser(dbUser);
        logger.info("Using Username " + dbUser);
        ds.setPassword(dbPassword);
//        End of lines for local testing with application.properties
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.springboot.MyTodoList.config");
        try {
                factory.setDataSource(dataSource());
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return factory;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
