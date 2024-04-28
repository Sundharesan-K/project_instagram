package com.app.instagram.service.impl;

import static com.app.instagram.constant.MessageConstants.EXISTS_MESSAGE;
import static com.app.instagram.constant.MessageConstants.INCORRECT_ADMIN;
import static com.app.instagram.constant.MessageConstants.INCORRECT_PASSWORD;
import static com.app.instagram.constant.MessageConstants.LOGIN_SUCCESS;
import static com.app.instagram.constant.MessageConstants.SUCCESS;

import com.app.instagram.dao.AdminDao;
import com.app.instagram.dto.AdminDto;
import com.app.instagram.entity.Admin;
import com.app.instagram.service.AdminService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final PasswordEncoder encoder;

    @Override
    public String createAdmin(AdminDto adminDto) throws Exception {
        try {
            Admin adminByEmailId = adminDao.findByEmailId(adminDto.getEmailId());
            if (Objects.isNull(adminByEmailId)) {
                Admin createAdmin = new Admin(adminDto.getUsername(),
                    adminDto.getEmailId(), encoder.encode(adminDto.getPassword()));
                adminDao.saveAdmin(createAdmin);
                return SUCCESS;
            } else {
                throw new Exception(EXISTS_MESSAGE);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String loginAdmin(AdminDto adminDto) throws Exception {
        Admin admin = adminDao.findByEmailId(adminDto.getEmailId());
        try {
            if (Objects.nonNull(admin)) {
                if (encoder.matches(adminDto.getPassword(), admin.getPassword())) {
                    return LOGIN_SUCCESS;
                } else {
                    throw new Exception(INCORRECT_PASSWORD);
                }
            } else {
                throw new Exception(INCORRECT_ADMIN);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
