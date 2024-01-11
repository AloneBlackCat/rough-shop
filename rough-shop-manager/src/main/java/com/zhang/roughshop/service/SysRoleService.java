package com.zhang.roughshop.service;

import com.github.pagehelper.PageInfo;
import com.zhang.model.dto.system.SysRoleDto;
import com.zhang.model.entity.system.SysRole;

public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);
}
