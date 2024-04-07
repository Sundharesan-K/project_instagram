package com.app.instagram.dao;

import com.app.instagram.entity.UserProfile;

public interface UserProfileDao {

    void save(UserProfile userProfile);

    UserProfile findById(String id);
}
