package com.root7325.javabs.laser.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();
    private final ConcurrentHashMap<Integer, LaserSession> activeSessions = new ConcurrentHashMap<>();

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public Collection<LaserSession> getSessions() {
        return Collections.unmodifiableCollection(activeSessions.values());
    }

    public int getSessionsAmount() { return activeSessions.size(); }

    public void addSession(LaserSession session) {
        if (session == null || session.getAccount() == null) {
            throw new IllegalArgumentException("Session or account can't be null!");
        }

        int id = session.getAccount().getId();
        Optional<LaserSession> possibleSession = getSession(id);

        if (possibleSession.isPresent()) {
            log.warn("{} already online! Removing old session.", id);
            removeSession(id);
        }

        activeSessions.put(id, session);
    }

    public void removeSession(int id) {
        activeSessions.remove(id);
    }

    public void removeSession(LaserSession session) {
        if (session != null && session.getAccount() != null) {
            removeSession(session.getAccount().getId());
        }
    }

    public Optional<LaserSession> getSession(int id) {
        return Optional.ofNullable(activeSessions.get(id));
    }
}
