package com.convertlab.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.convertlab.entity.User;
import com.convertlab.mapper.UserMapper;
import com.convertlab.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * created by limei on 2021/3/2
 * 说明:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoByUserName(String name) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("user_name", name);
        User user = userMapper.selectOne(qw);
        return user;
    }
}
