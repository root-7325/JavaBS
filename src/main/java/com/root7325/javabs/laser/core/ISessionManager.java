package com.root7325.javabs.laser.core;

import java.util.Collection;
import java.util.Optional;

/**
 * @author root7325 on 22.06.2025
 */
public interface ISessionManager {
    Collection<LaserSession> getSessions();
    int getSessionsAmount();
    void addSession(LaserSession session);
    void removeSession(long id);
    void removeSession(LaserSession session);
    Optional<LaserSession> getSession(long id);
}