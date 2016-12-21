package com.zhu.mongo;

import com.zhu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhu on 2016/11/27.
 */
@Component
public class UserRepository   {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> findAll(){

        return mongoTemplate.findAll(User.class);
    }


}
