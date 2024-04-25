package com.app.instagram.dto;

import com.app.instagram.entity.LikeType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDto {

    private LikeType likeType;
    private String postId;
}
