package com.app.instagram.dao;

import com.app.instagram.entity.LikeAndDisLike;

public interface LikeDao {

    String COLLECTION_NAME = "like&DisLike";

    LikeAndDisLike findByPostIdAndUsername(String postId, String username);

    void save(LikeAndDisLike likeAndDisLike);

}
