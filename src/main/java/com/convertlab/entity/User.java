package com.convertlab.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * created by limei on 2021/3/2
 * 说明:用户表实体
 */
@Data
@ApiModel(value="用户", description="")
@TableName("user")
public class User {
    //id
    private Integer id;
    //用户名
    private  String userName;
    //密码
    private String password;
    //角色
    private String jobRole;


    public User() {

    }

    public User(User user) {
        this.id = user.id;
        this.userName = user.userName;
        this.password = user.password;
        this.jobRole = user.jobRole;
    }
}
