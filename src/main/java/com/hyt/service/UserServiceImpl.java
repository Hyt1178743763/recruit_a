package com.hyt.service;

import com.hyt.mapper.UserMapper;
import com.hyt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl {


    @Autowired
    private UserMapper userMapper;


    /*public void register(String username, String password) {
        userMapper.save(username,password);
    }*/


    public User findUserName(String username){
        User user = userMapper.selectByName(username);
            return user;

    }

}
