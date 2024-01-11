package com.zhang.roughshop.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.zhang.model.entity.common.RedisKeyEnum;
import com.zhang.model.entity.system.SysUser;
import com.zhang.model.vo.common.Result;
import com.zhang.model.vo.common.ResultCodeEnum;
import com.zhang.roughshop.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方式
        String method = request.getMethod();
        // 如果请求方式为options 预检请求,直接放行
        if (method.equals("OPTIONS")) {
            return true;
        }
        // 请求头中获取token
        String token = request.getHeader("token");
        // token为空,返回错误提示
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 不为空,查询redis
        String userInfoString = redisTemplate.opsForValue().get(RedisKeyEnum.USER_LOGIN + token);
        // 查询数据为空,返回错误提示
        if (StrUtil.isEmpty(userInfoString)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 不为空,将用户信息放到threadLocal中
        SysUser sysUser = JSON.parseObject(userInfoString, SysUser.class);
        AuthContextUtil.set(sysUser);
        // 更新redis过期时间
        redisTemplate.expire(RedisKeyEnum.USER_LOGIN + token,30, TimeUnit.MINUTES);
        // 放行
        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // threadLocal移除
        AuthContextUtil.remove();
    }
}
