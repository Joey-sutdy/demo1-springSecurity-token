package com.convertlab.service;

import com.convertlab.entity.User;

/**
 * Created by DELL on 2021/3/2.
 */
public interface UserInfoService {

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User getUserInfoByUserName(String name);
}
