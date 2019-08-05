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


    public boolean findUserName(User user1){
        User user = userMapper.selectByName(user1.getUsername());
        if (user1!=null&&user1.getPassword()==user.getPassword()){
            return true;
        }else {
            return false;
        }

    }

}
