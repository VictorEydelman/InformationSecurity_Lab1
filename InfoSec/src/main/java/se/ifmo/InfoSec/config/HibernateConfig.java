package se.ifmo.InfoSec.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class HibernateConfig {
    @Bean
    public SessionFactory sessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
