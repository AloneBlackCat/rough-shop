package com.zhang.roughshop.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.model.dto.system.SysRoleDto;
import com.zhang.model.entity.system.SysRole;
import com.zhang.model.vo.common.Result;
import com.zhang.model.vo.common.ResultCodeEnum;
import com.zhang.roughshop.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Operation(summary = "角色列表")
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto){
        // pageHelper插件实现分页
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto,current,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加角色")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody SysRole sysRole) {
        sysRoleService.addRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改角色")
    @PutMapping("/updateRole")
    public Result updateRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/delById/{roleId}")
    public Result delRole( @PathVariable("roleId") Long roleId) {
        sysRoleService.delRole(roleId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
