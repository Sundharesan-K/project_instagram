package com.app.instagram.dao.impl;

import com.app.instagram.dao.UserProfileDao;
import com.app.instagram.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserProfileDaoImpl implements UserProfileDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(UserProfile userProfile) {
        mongoTemplate.save(userProfile);
    }

    @Override
    public UserProfile findById(String id) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findById(id, UserProfile.class);
    }
}
