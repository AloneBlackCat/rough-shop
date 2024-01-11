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
}
