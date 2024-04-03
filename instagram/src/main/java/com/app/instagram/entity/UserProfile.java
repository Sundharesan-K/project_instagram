package com.app.instagram.entity;

import com.app.instagram.dto.Gender;
import com.app.instagram.dto.Status;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_profile")
public class UserProfile {
    @Id
    private String id;
    private String username;
    private String emailId;
    private String password;
    private String bio;
    private String location;
    private Gender gender;
    private List<Post> postList;
    private Integer postCount;
    private Status status;
    private List<String> followerList;
    private Integer followerCount;
    private List<String> followingList;
    private Integer followingCount;
    private boolean is_active;
    private LocalDateTime create_ts;
    private LocalDateTime updated_ts;
}
