package com.zhang.roughshop.mapper;

import com.zhang.model.dto.system.SysUserDto;
import com.zhang.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    SysUser selectUserInfoByUsername(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    void addSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer sysUserId);
}
