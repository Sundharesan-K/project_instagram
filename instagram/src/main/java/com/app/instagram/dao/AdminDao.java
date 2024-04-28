package com.app.instagram.dao;

import com.app.instagram.entity.Admin;

public interface AdminDao {

    String COLLECTION_NAME = "admin";

    Admin findByEmailId(String emailId);

    void saveAdmin(Admin createAdmin);

}
