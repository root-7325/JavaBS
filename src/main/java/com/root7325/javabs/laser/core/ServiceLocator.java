package com.root7325.javabs.laser.core;

import com.root7325.javabs.dao.UserDAOImpl;
import com.root7325.javabs.utils.HibernateUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@Getter
public class ServiceLocator {
    private static final ServiceLocator INSTANCE = new ServiceLocator();
    private final UserDAOImpl userDAO;

    private ServiceLocator() {
        this.userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
    }

    public static ServiceLocator getInstance() {
        return INSTANCE;
    }
}
