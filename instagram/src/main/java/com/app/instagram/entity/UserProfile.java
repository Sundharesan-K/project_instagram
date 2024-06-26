package com.app.instagram.entity;

import static com.app.instagram.dao.UserProfileDao.COLLECTION_NAME;

import com.app.instagram.dto.Gender;
import com.app.instagram.dto.Status;
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
public class UserProfile {

    @Id
    private String id;
    private String username;
    private String emailId;
    private String password;
    private String bio = null;
    private String location = null;
    private Gender gender = null;
    private List<Post> postList = Collections.EMPTY_LIST;
    private Integer postCount = 0;
    private Status status = Status.PUBLIC;
    private List<String> followerList = Collections.EMPTY_LIST;
    private Integer followerCount = 0;
    private List<String> followingList = Collections.EMPTY_LIST;
    private Integer followingCount = 0;
    private boolean is_active = false;
    private LocalDateTime create_ts;
    private LocalDateTime updated_ts;

    public void setPost(Post post) {
        postList.add(post);
        postCount = postList.size();
    }
}
