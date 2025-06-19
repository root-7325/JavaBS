package com.root7325.javabs.dao;

import com.root7325.javabs.entity.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@RequiredArgsConstructor
public class PlayerDAOImpl implements PlayerDAO {
    private final SessionFactory sessionFactory;

    @Override
    public Player getPlayer(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Player getPlayer(int id, String token) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Player a WHERE a.id = :id AND a.token = :token";
            Player player = session.createQuery(hql, Player.class)
                    .setParameter("id", id)
                    .setParameter("token", token)
                    .uniqueResult();

            if (player != null) {
                return player;
            } else {
                log.warn("Player with id {} and token {} wasn't found!", id, token);
            }
        } catch (Exception ex) {
            log.error("Failed to load player.", ex);
        }
        return null;
    }

    @Override
    public Player createPlayer() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Player player = new Player();
            session.persist(player);
            transaction.commit();

            log.debug("Created new player with id {}", player.getId());
            return player;
        } catch (Exception ex) {
            log.error("Failed to create new player.", ex);
        }
        return null;
    }
}
