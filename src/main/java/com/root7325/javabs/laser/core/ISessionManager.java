package com.root7325.javabs.laser.core;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for managing active sessions.
 *
 * @author root7325 on 22.06.2025
 */
public interface ISessionManager {

    /**
     * Returns all active sessions.
     *
     * @return a collection of all active sessions
     */
    Collection<LaserSession> getSessions();

    /**
     * Returns total number of active sessions.
     *
     * @return count of an active sessions
     */
    int getSessionsAmount();

    /**
     * Registers a new session.
     *
     * @param session session to register
     */
    void addSession(LaserSession session);

    /**
     * Removes a session by its ID.
     *
     * @param id ID of session to remove
     */
    void removeSession(long id);

    /**
     * Removes a specific session from manager.
     *
     * @param session session to remove
     */
    void removeSession(LaserSession session);

    /**
     * Retrieves a session by its ID.
     *
     * @param id ID of session to retrieve
     * @return an Optional containing session if found, empty otherwise
     */
    Optional<LaserSession> getSession(long id);
}