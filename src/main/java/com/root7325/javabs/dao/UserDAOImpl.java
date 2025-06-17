package com.root7325.javabs.dao;

import com.root7325.javabs.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.UUID;

/**
 * @author root7325 on 17.06.2025
 */
@Slf4j
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    @Override
    public Account getAccount(int id) {
        return null;
    }

    @Override
    public Account getAccount(int id, String token) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Account a WHERE a.id = :id AND a.token = :token";
            Account account = session.createQuery(hql, Account.class)
                    .setParameter("id", id)
                    .setParameter("token", token)
                    .uniqueResult();

            if (account != null) {
                return account;
            } else {
                log.warn("Account with id {} and token {} wasn't found!", id, token);
            }
        } catch (Exception ex) {
            log.error("Failed to load player.", ex);
        }
        return null;
    }

    @Override
    public Account createAccount() {
        try (Session session = sessionFactory.openSession()) {
            Account account = new Account();
            session.persist(account);

            log.debug("Created new account with id {}", account.getId());
            return account;
        } catch (Exception ex) {
            log.error("Failed to create new account.", ex);
        }
        return null;
    }
}
