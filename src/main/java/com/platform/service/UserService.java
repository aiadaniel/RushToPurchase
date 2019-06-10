package com.platform.service;

import com.platform.dao.UserMapper;
import com.platform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(int uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

}
