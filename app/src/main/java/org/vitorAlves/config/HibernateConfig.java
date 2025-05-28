package org.vitorAlves.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.vitorAlves.infra.persistence.BookEntity;

import javax.persistence.EntityManagerFactory;


public class HibernateConfig {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        Dotenv dotenv = Dotenv.load();
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(BookEntity.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "none");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        entityManagerFactory = configuration.buildSessionFactory(builder.build());
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
