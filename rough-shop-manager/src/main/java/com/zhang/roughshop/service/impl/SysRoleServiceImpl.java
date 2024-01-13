package com.zhang.roughshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.model.dto.system.SysRoleDto;
import com.zhang.model.entity.system.SysRole;
import com.zhang.roughshop.mapper.SysRoleMapper;
import com.zhang.roughshop.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        // 设置分页参数
        PageHelper.startPage(current,limit);
        // 根据条件查询数据
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        // 封装pageInfo对象
        return new PageInfo<>(list);
    }

    @Override
    public void addRole(SysRole sysRole) {
        sysRoleMapper.addRole(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {
        sysRoleMapper.updateRole(sysRole);
    }

    @Override
    public void delRole(Long roleId) {
        sysRoleMapper.delRole(roleId);
    }
}
