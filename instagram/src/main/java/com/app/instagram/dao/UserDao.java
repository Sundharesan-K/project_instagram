package com.app.instagram.dao;

import com.app.instagram.entity.User;

public interface UserDao {

    User findByUser(String emailId);

    void save(User user);

}
