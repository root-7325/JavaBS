package com.root7325.javabs.laser.core;

import com.google.inject.Inject;
import com.root7325.javabs.dao.PlayerDAO;
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
    private final ConcurrentHashMap<Long, LaserSession> activeSessions = new ConcurrentHashMap<>();
    private final PlayerDAO playerDAO;

    @Inject
    public SessionManager(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public Collection<LaserSession> getSessions() {
        return Collections.unmodifiableCollection(activeSessions.values());
    }

    public int getSessionsAmount() { return activeSessions.size(); }

    public void addSession(LaserSession session) {
        if (session == null || session.getPlayer() == null) {
            throw new IllegalArgumentException("Session or player can't be null!");
        }

        long id = session.getPlayer().getId();
        Optional<LaserSession> possibleSession = getSession(id);

        if (possibleSession.isPresent()) {
            log.warn("{} already online! Removing old session.", id);
            removeSession(id);
        }

        activeSessions.put(id, session);
    }

    public void removeSession(long id) {
        activeSessions.remove(id);
    }

    public void removeSession(LaserSession session) {
        if (session != null && session.getPlayer() != null) {
            removeSession(session.getPlayer().getId());
            playerDAO.savePlayer(session.getPlayer());
        }
    }

    public Optional<LaserSession> getSession(long id) {
        return Optional.ofNullable(activeSessions.get(id));
    }
}
