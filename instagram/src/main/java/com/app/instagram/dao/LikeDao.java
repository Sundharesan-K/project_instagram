package com.app.instagram.dao;

import com.app.instagram.entity.LikeAndDisLike;
import com.app.instagram.entity.User;

public interface LikeDao {

    LikeAndDisLike findByPostIdAndUsername(String postId, String username);

    void save(LikeAndDisLike likeAndDisLike);

}
