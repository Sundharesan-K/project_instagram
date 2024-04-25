package com.app.instagram.service;

import com.app.instagram.dto.LikeDto;

public interface LikeService {

    void likeAndDisPost(LikeDto likeDto, String username) throws Exception;
}
