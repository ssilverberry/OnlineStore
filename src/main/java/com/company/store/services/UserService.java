package com.company.store.services;

import com.company.store.entities.User;
import com.company.store.entities.UserRoles;

import java.util.Collection;

public interface UserService {

    User getUser(String email, String password);

    boolean match(String email, String password);

    Collection<User> getAll ();

    UserRoles checkUserType(User user);
}
