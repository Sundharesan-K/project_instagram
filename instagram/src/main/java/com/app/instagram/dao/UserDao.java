package com.app.instagram.dao;

import com.app.instagram.entity.User;
import com.app.instagram.entity.UserProfile;
import java.util.Optional;

public interface UserDao {

    String COLLECTION_NAME = "user";

    User findByUser(String emailId);

    void save(User user);

    Optional<User> findByUsername(String username);
}
