package com.zhu.biz;

import com.zhu.entity.User;
import com.zhu.mongo.UserRepository;
import com.zhu.util.JsonUtil;
import com.zhu.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by zhu on 2016/11/27.
 */
@Service
public class UserBiz {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    public List<User> getUser(){

       return userRepository.findAll();
    }
}
