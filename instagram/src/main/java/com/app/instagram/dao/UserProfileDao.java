package com.app.instagram.dao;

import com.app.instagram.entity.UserProfile;

public interface UserProfileDao {

    String COLLECTION_NAME = "user_profile";

    void save(UserProfile userProfile);

    UserProfile findById(String id);
}
