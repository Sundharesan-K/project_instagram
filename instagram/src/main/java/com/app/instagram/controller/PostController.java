package com.app.instagram.controller;

import com.app.instagram.dto.APIResponse;
import com.app.instagram.dto.PostDTO;
import com.app.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/createPost/{userId}")
    public ResponseEntity<APIResponse> postCreated(@PathVariable String userId, @RequestBody PostDTO postDTO){
        APIResponse response = new APIResponse();
        try {
            PostDTO post = postService.createPost(userId, postDTO);
            response.setMessage("Success");
            response.setData(post);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
