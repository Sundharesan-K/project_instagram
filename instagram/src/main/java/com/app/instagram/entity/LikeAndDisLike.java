package com.app.instagram.entity;

import static com.app.instagram.dao.LikeDao.COLLECTION_NAME;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = COLLECTION_NAME)
public class LikeAndDisLike {

    @Id
    private String id;
    private String postId;
    private LikeType likeType;
    private String username;
    private LocalDateTime create_ts;
}
