package com.example.project.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring configuration class for JPA entities.
 *
 * @author mlglenn.
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:/common.properties")
})
@Profile({"local", "dev", "qatest", "staging", "production"})
public class CommonDataConfiguration {

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Autowired
    private DataSource jpaDatasource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

        emfb.setDataSource(jpaDatasource);
        emfb.setPackagesToScan("com.example.project.common.entity");
        emfb.setJpaDialect(new EclipseLinkJpaDialect());

        AbstractJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(Boolean.valueOf(showSql));
        jpaVendorAdapter.setGenerateDdl(false);

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("eclipselink.weaving", "false");
        jpaProperties.setProperty("eclipselink.logging.level", "SEVERE");
        jpaProperties.setProperty("eclipselink.persistence-context.flush-mode", "AUTO");

        emfb.setJpaProperties(jpaProperties);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.afterPropertiesSet();

        return emfb;
    }
}
