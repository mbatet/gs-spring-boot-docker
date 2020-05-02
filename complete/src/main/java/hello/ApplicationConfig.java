package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
class ApplicationConfig {

    //https://github.com/spring-guides
    //https://docs.spring.io/spring-data/data-jpa/docs/1.5.x/reference/html/index.html
    //https://docs.spring.io/spring-data/data-jpa/docs/1.5.x/reference/html/jpa.repositories.html
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
    //https://spring.io/guides/gs/accessing-data-jpa/
    //https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
    //https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-persistence-simple

    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        //return builder.setType(EmbeddedDatabaseType.HSQL).build();
        return builder.setType(EmbeddedDatabaseType.H2).build();

        /*
        //També haguessim pogut fer:

         final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("dataSource.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("dataSource.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("dataSource.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("dataSource.password")));
        return dataSource;

         */

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        /*
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        * */




        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("hello.model");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}