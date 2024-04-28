package com.app.instagram.entity;

import static com.app.instagram.dao.AdminDao.COLLECTION_NAME;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = COLLECTION_NAME)
public class Admin {

    @Id
    private String id;
    private String username;
    private String emailId;
    private String password;
    private LocalDateTime create_ts;

    public Admin(String username, String emailId, String password) {
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.create_ts = LocalDateTime.now();
    }
}
