package com.zhang.roughshop.mapper;

import com.zhang.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    SysUser selectUserInfoByUsername(String userName);
}
