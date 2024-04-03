package com.app.instagram.dao.impl;

import com.app.instagram.dao.AdminDao;
import com.app.instagram.dto.AdminDto;
import com.app.instagram.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {
    private final MongoTemplate mongoTemplate;

    @Override
    public Admin findByEmailId(String emailId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("emailId").is(emailId));
        return mongoTemplate.findOne(query, Admin.class);
    }

    @Override
    public void saveAdmin(Admin createAdmin) {
        mongoTemplate.save(createAdmin);
    }

}
