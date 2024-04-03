package com.app.instagram.service.impl;

import com.app.instagram.dao.AdminDao;
import com.app.instagram.dto.AdminDto;
import com.app.instagram.entity.Admin;
import com.app.instagram.repository.AdminRepo;
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
    private final AdminRepo adminRepo;
    private final PasswordEncoder encoder;
    @Override
    public String createAdmin(AdminDto adminDto) throws Exception {
        try {
            Admin adminByEmailId = adminDao.findByEmailId(adminDto.getEmailId());
            if (Objects.isNull(adminByEmailId)){
                Admin createAdmin = new Admin(adminDto.getUsername(),
                    adminDto.getEmailId(),encoder.encode(adminDto.getPassword()));
                adminDao.saveAdmin(createAdmin);
                return "Successful created adminDto";
            }else {
                throw new Exception("Already exists");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String loginAdmin(AdminDto adminDto) throws Exception {
        Admin admin = adminDao.findByEmailId(adminDto.getEmailId());
        try {
            if (Objects.nonNull(admin)){
                if (encoder.matches(adminDto.getPassword(),admin.getPassword())){
                    return "Logged in success";
                }else {
                    throw new Exception("Password is incorrect");
                }
            }else {
                throw new Exception("Admin emailId incorrect");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
