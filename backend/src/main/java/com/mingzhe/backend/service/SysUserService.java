package com.mingzhe.backend.service;

import com.mingzhe.backend.entity.SysUser;
import com.mingzhe.backend.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public Integer save(SysUser sysUser) {
        if (sysUser.getId() == null) {
            // no user => add a new user
            return userMapper.insert(sysUser);
        } else {
            // exits user => update user
            return userMapper.update(sysUser);
        }
    }
}
