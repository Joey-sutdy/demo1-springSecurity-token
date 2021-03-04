package com.convertlab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.convertlab.entity.Employee;
import com.convertlab.entity.User;
import org.springframework.stereotype.Repository;

/**
 * created by limei on 2021/3/2
 * 说明:
 */
@Repository
public interface  UserMapper extends BaseMapper<User> {

}
