package com.app.instagram.service;

import com.app.instagram.dto.PostDTO;

public interface PostService {

    PostDTO createPost(String userId, PostDTO postDTO) throws Exception;
}
