package com.app.instagram.controller;

import static com.app.instagram.constant.APIEndPoint.API_USER;
import static com.app.instagram.constant.APIEndPoint.CREATE_USER;
import static com.app.instagram.constant.APIEndPoint.LOGIN_USER;
import static com.app.instagram.constant.APIEndPoint.SET_UP_PROFILE;
import static com.app.instagram.constant.APIEndPoint.STATUS_UPDATE;

import com.app.instagram.dto.APIResponse;
import com.app.instagram.dto.UserDTO;
import com.app.instagram.entity.User;
import com.app.instagram.service.UserProfileService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(CREATE_USER)
    public ResponseEntity<APIResponse> userCreate(@RequestBody User user) {
        APIResponse response = new APIResponse();
        try {
            UserDTO userDTO = userProfileService.userCreate(user);
            response.setData(userDTO);
            response.setMessage("SUCCESS");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(LOGIN_USER)
    public ResponseEntity<APIResponse> userLogin(@RequestBody User user) {
        APIResponse response = new APIResponse();
        try {
            String message = userProfileService.userLogin(user);
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(SET_UP_PROFILE)
    public ResponseEntity<APIResponse> setUpProfile(@PathVariable String id,
        @RequestBody Map<Object, String> request) {
        APIResponse response = new APIResponse();
        try {
            String message = userProfileService.setUp(id, request);
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(STATUS_UPDATE)
    public ResponseEntity<APIResponse> statusChange(@PathVariable String id) {
        APIResponse response = new APIResponse();
        try {
            String message = userProfileService.changeStatus(id);
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
