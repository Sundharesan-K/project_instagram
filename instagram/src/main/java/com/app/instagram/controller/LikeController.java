package com.app.instagram.controller;

import com.app.instagram.dto.APIResponse;
import com.app.instagram.dto.LikeDto;
import com.app.instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{username}")
    public ResponseEntity<APIResponse> likeAndDisLikePost(@PathVariable String username,@RequestBody LikeDto likeDto){
        APIResponse response = new APIResponse();
        try {
            likeService.likeAndDisPost(likeDto,username);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
