package com.app.instagram.dao;

import com.app.instagram.dto.AdminDto;
import com.app.instagram.entity.Admin;

public interface AdminDao {

    Admin findByEmailId(String emailId);

    void saveAdmin(Admin createAdmin);

}
