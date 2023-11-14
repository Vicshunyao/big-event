package org.kingsy.service;

import org.kingsy.pojo.User;

public interface UserService {
    User findByUserName( String username);

   void register(String username, String password);
}
