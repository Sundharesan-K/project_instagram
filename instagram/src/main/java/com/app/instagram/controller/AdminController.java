package com.app.instagram.controller;

import com.app.instagram.dto.APIResponse;
import com.app.instagram.dto.AdminDto;
import com.app.instagram.jwt.JwtService;
import com.app.instagram.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final JwtService jwtService;

    @PostMapping("/createAdmin")
    public ResponseEntity<APIResponse> createAdmin(@RequestBody AdminDto adminDto)  {
        APIResponse response = new APIResponse();
        try {
            String message = adminService.createAdmin(adminDto);
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
    @PostMapping("/loginAdmin")
    public ResponseEntity<APIResponse> loginAdmin(@RequestBody AdminDto adminDto){
        APIResponse response = new APIResponse();
        try {
            String message = adminService.loginAdmin(adminDto);
            response.setMessage(message);
            response.setData(jwtService.generateToken(adminDto,10 * 60));
            HttpHeaders headers = new HttpHeaders();
            headers.set("jwttoken",jwtService.generateToken(adminDto,10 * 60));
            return new ResponseEntity<>(response,headers,HttpStatus.OK);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
    @PostMapping("/addAdmin")
    public ResponseEntity<APIResponse> addAdmin(@RequestHeader("authorization") String auth, @RequestBody AdminDto adminDto){
        APIResponse response = new APIResponse();
        if (jwtService.validateAdminToken(auth)) {
            try {
                adminService.createAdmin(adminDto);
                response.setMessage("Admin created successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                response.setMessage(e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
