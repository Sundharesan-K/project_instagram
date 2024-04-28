package com.app.instagram.entity;

import static com.app.instagram.dao.PostDao.COLLECTION_NAME;

import com.app.instagram.dto.CommentDTO;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = COLLECTION_NAME)
public class Post {

    @Id
    private String id;
    private String userId;
    private String postType;
    private List<String> likedUsername;
    private List<String> disLikedUsername;
    private Integer likeCount = 0;
    private Integer disLikeCount = 0;
    private List<CommentDTO> comments = Collections.EMPTY_LIST;
    private Integer commentCount = 0;
    private String caption;
    private LocalDateTime create_ts;
    private LocalDateTime update_ts;

}
