package com.app.instagram.service.impl;

import com.app.instagram.dao.UserDao;
import com.app.instagram.dao.UserProfileDao;
import com.app.instagram.dto.Gender;
import com.app.instagram.dto.UserDTO;
import com.app.instagram.entity.User;
import com.app.instagram.entity.UserProfile;
import com.app.instagram.service.UserProfileService;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final UserProfileDao userProfileDao;

    @Override
    public UserDTO userCreate(User user) throws Exception {
        User byUser = userDao.findByUser(user.getEmailId());
        try {
            if (Objects.isNull(byUser)) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername(user.getUsername());
                userDTO.setEmailId(user.getEmailId());
                user.setPassword(encoder.encode(user.getPassword()));
                user.setCreate_ts(LocalDateTime.now());
                userDao.save(user);
                return userDTO;
            } else {
                throw new Exception("Already Exists");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String userLogin(User user) throws Exception {
        User userByEmailId = userDao.findByUser(user.getEmailId());
        UserProfile userProfile = new UserProfile();
        try {
            if (Objects.nonNull(userByEmailId)) {
                if (encoder.matches(user.getPassword(), userByEmailId.getPassword())) {
                    createUserProfile(user, userProfile, userByEmailId);
                    return "Logged in success";
                } else {
                    throw new Exception("Password is incorrect");
                }
            } else {
                throw new Exception("User emailId is incorrect");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void createUserProfile(User user, UserProfile userProfile, User userByEmailId) {
        userProfile.setUsername(user.getUsername());
        userProfile.setEmailId(user.getEmailId());
        userProfile.setPassword(userByEmailId.getPassword());
        userProfile.is_active();
        userProfile.setCreate_ts(LocalDateTime.now());
        userProfile.setUpdated_ts(LocalDateTime.now());
        userProfileDao.save(userProfile);
    }

    @Override
    public String setUp(String id, Map<Object, String> request) throws Exception {
        UserProfile userProfile = userProfileDao.findById(id);
        try {
            if (Objects.nonNull(userProfile)){
                userProfile.setBio(request.get("bio"));
                userProfile.setLocation(request.get("location"));
                userProfile.setGender(Gender.valueOf(request.get("gender")));
                userProfile.set_active(true);
                userProfile.setUpdated_ts(LocalDateTime.now());
                userProfileDao.save(userProfile);
                return "Successfully updated in profile";
            }else {
                throw new Exception("Username is incorrect");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
