package com.zhang.roughshop.service;

import com.github.pagehelper.PageInfo;
import com.zhang.model.dto.system.LoginDto;
import com.zhang.model.dto.system.SysUserDto;
import com.zhang.model.entity.system.SysUser;
import com.zhang.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    void addSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer sysUserId);
}
