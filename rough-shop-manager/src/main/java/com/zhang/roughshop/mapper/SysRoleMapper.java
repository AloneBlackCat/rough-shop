package com.zhang.roughshop.mapper;

import com.zhang.model.dto.system.SysRoleDto;
import com.zhang.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void addRole(SysRole sysRole);

    void updateRole(SysRole sysRole);

    void delRole(Long roleId);
}
