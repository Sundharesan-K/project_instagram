package com.app.instagram.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String userId;
    private String postType;
    private Integer likeCount;
    private Integer commentCount;
    private String caption;
    private LocalDateTime create_ts;
}
