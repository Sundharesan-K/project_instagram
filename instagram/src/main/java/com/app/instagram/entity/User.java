package com.app.instagram.entity;

import static com.app.instagram.dao.UserDao.COLLECTION_NAME;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = COLLECTION_NAME)
public class User {

    @Id
    private String id;
    private String username;
    private String emailId;
    private String password;
    private LocalDateTime create_ts;
}
