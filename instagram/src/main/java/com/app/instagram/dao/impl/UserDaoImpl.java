package com.app.instagram.dao.impl;

import com.app.instagram.dao.UserDao;
import com.app.instagram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public User findByUser(String emailId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("emailId").is(emailId));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public void save(User user) {
        mongoTemplate.save(user);
    }
}
