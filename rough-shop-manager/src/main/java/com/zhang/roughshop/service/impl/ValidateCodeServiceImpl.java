package com.zhang.roughshop.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.zhang.model.entity.common.RedisKeyEnum;
import com.zhang.model.vo.system.ValidateCodeVo;
import com.zhang.roughshop.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 生成图片验证码
    @Override
    public ValidateCodeVo generateValidateCode() {
        // 通过工具生成图片
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 6);
        // 将验证码存储到redis
        String codeValue = circleCaptcha.getCode(); // 验证码值
        String imageBase64 = circleCaptcha.getImageBase64(); // 返回图片验证码
        // 设置过期时间
        String key = UUID.randomUUID().toString().replaceAll("-","");
        redisTemplate.opsForValue().set(RedisKeyEnum.USER_VALIDATE + key,
                codeValue,5, TimeUnit.MINUTES);
        // 返回validateCodeVo对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);

        return validateCodeVo;
    }
}
