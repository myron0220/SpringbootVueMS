package com.mingzhe.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mingzhe.backend.entity.SysUser;
import com.mingzhe.backend.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {
    public boolean saveUser(SysUser user) {
        return saveOrUpdate(user);
    }
}
