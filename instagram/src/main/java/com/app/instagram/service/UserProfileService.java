package com.app.instagram.service;

import com.app.instagram.dto.UserDTO;
import com.app.instagram.entity.User;
import java.util.Map;

public interface UserProfileService {

    UserDTO userCreate(User user) throws Exception;

    String userLogin(User user) throws Exception;

    String setUp(String id, Map<Object, String> request) throws Exception;

    String changeStatus(String id) throws Exception;
}
