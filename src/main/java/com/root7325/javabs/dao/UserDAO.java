package com.root7325.javabs.dao;

import com.root7325.javabs.entity.Account;

import java.util.UUID;

/**
 * @author root7325 on 17.06.2025
 */
public interface UserDAO {
    Account getAccount(int id);
    Account getAccount(int id, String token);
    Account createAccount();
}