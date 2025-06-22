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
public class SessionManager implements ISessionManager {
    private final ConcurrentHashMap<Integer, LaserSession> activeSessions = new ConcurrentHashMap<>();

    public Collection<LaserSession> getSessions() {
        return Collections.unmodifiableCollection(activeSessions.values());
    }

    public int getSessionsAmount() { return activeSessions.size(); }

    public void addSession(LaserSession session) {
        if (session == null || session.getPlayer() == null) {
            throw new IllegalArgumentException("Session or player can't be null!");
        }

        int id = session.getPlayer().getId();
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
        if (session != null && session.getPlayer() != null) {
            removeSession(session.getPlayer().getId());
        }
    }

    public Optional<LaserSession> getSession(int id) {
        return Optional.ofNullable(activeSessions.get(id));
    }
}
