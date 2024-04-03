package com.app.instagram.service;

import com.app.instagram.dto.AdminDto;

public interface AdminService {

    String createAdmin(AdminDto admin) throws Exception;

    String loginAdmin(AdminDto adminDto) throws Exception;
}
