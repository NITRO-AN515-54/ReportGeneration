package com.mastercard.databaseConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author e140856
 * @description class for Database Configuration
 *
 */
//@Profile({"local","dev","stage","prod","ksc"})
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "primaryEntityManagerFactory",
                       transactionManagerRef = "primaryTransactionManager",
                       basePackages = {"com.mastercard.repository"})
public class DatabaseConfig {

    @Autowired
    Environment env;

    /**
     * @description: SQLServerDataSource Bean Configured for application data
     *               tables
     * @param:
     * @return:oracleDataSource
     */

    @Bean
    @Qualifier("dsApplicationDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource SQLserverDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("dsApplicationDataSource") DataSource primaryDataSource) {
        return builder
                .dataSource(primaryDataSource)
                .packages("com.nitro.springboot.model")
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}
