package com.zhang.roughshop.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.model.dto.system.SysUserDto;
import com.zhang.model.entity.system.SysUser;
import com.zhang.model.vo.common.Result;
import com.zhang.model.vo.common.ResultCodeEnum;
import com.zhang.roughshop.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "用户条件分页查询")
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             @RequestBody SysUserDto sysUserDto) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum, pageSize, sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加角色")
    @PostMapping("/addSysUser")
    public Result addSysUser(@RequestBody SysUser sysUser) {
        sysUserService.addSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改用户")
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/deleteById/{sysUserId}")
    public Result deleteById(@PathVariable("sysUserId") Integer sysUserId){
        sysUserService.deleteById(sysUserId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
