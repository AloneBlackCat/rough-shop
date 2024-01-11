package com.zhang.roughshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhang.model.dto.system.LoginDto;
import com.zhang.model.entity.system.SysUser;
import com.zhang.model.vo.common.ResultCodeEnum;
import com.zhang.model.vo.system.LoginVo;
import com.zhang.roughshop.common.exception.RoughException;
import com.zhang.roughshop.mapper.SysUserMapper;
import com.zhang.roughshop.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 1. 获取提交用户名
        String userName = loginDto.getUserName();
        // 2. 根据用户名查询数据库表sys_user表
        SysUser sysUser = sysUserMapper.selectUserInfoByUsername(userName);
        // 3. 查询是否有对应数据
        if (sysUser == null) {
            // throw new RuntimeException("用户名不存在");
            throw new RoughException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 4. 获取到用户信息
        // 5. 对输入的密码进行加密,对比密码是否一致
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        String database_password = sysUser.getPassword();
        // 比较
        if (!input_password.equals(database_password)) {
            // throw new RuntimeException("密码不正确");
            throw new RoughException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 6. 登录成功,生成用户唯一token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 7. 把token放在redis中
        // key : token value : sysUser
        redisTemplate.opsForValue()
                .set("user:login" + token,
                        JSON.toJSONString(sysUser),
                        7,
                        TimeUnit.DAYS);
        // 8. 返回loginVo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }
}
