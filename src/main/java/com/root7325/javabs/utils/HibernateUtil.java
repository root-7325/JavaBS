package com.root7325.javabs.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author root7325 on 10.02.2025
 * <p>
 * Utility class for Hibernate stuff
 */
@Slf4j
public class HibernateUtil {
    /** Session factory instance. */
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration()
                    .configure("hibernate.cfg.xml");

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            log.error("Failed to create session factory: {}", e.getClass());
            throw new ExceptionInInitializerError(e);
        }
    }
}
