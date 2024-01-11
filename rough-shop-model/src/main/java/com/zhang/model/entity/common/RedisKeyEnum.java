package com.zhang.model.entity.common;

import lombok.Getter;

@Getter
public enum RedisKeyEnum {

    // 用户验证码key
    USER_VALIDATE("user:validate"),
    // 用户登录信息key
    USER_LOGIN("user:login");


    private final String key ;

    private RedisKeyEnum(String key){
        this.key = key;
    }
}
