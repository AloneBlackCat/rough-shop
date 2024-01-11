package com.zhang.roughshop.service;

import com.zhang.model.dto.system.LoginDto;
import com.zhang.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);
}
