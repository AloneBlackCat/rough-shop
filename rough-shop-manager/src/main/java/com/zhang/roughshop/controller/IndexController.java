package com.zhang.roughshop.controller;

import com.zhang.model.dto.system.LoginDto;
import com.zhang.model.entity.system.SysUser;
import com.zhang.model.vo.common.Result;
import com.zhang.model.vo.common.ResultCodeEnum;
import com.zhang.model.vo.system.LoginVo;
import com.zhang.model.vo.system.ValidateCodeVo;
import com.zhang.roughshop.service.SysUserService;
import com.zhang.roughshop.service.ValidateCodeService;
import com.zhang.roughshop.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;

    @Operation(summary = "生成验证码")
    @GetMapping("/generateValidateCode")
    public Result generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }

    // 用户登录
    @Operation(summary = "登录方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        // 返回用户信息
        return Result.build(AuthContextUtil.get(),ResultCodeEnum.SUCCESS);
    }

   /** @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token) {
        // 从请求头中获取token
        // 根据redis查询用户信息
        SysUser sysUser = sysUserService.getUserInfo(token);
        // 返回用户信息
        return Result.build(sysUser,ResultCodeEnum.SUCCESS);
    }*/

    @Operation(summary = "退出登录")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
