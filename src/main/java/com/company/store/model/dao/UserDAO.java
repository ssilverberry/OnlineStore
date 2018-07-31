package com.company.store.model.dao;


import com.company.store.model.entities.User;

import java.util.Collection;

public interface UserDAO {

    Collection<User> getAllUsers();
    User getById(int user_id);
    User getByCredentials(String email, String password);

    boolean saveUser(User user);
    boolean removeUser (int user_id);
}
