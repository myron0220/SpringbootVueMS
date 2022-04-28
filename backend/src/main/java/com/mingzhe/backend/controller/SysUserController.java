package com.mingzhe.backend.controller;

import com.mingzhe.backend.entity.SysUser;
import com.mingzhe.backend.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysUserController {
    @Autowired
    private SysUserMapper userMapper;

    @GetMapping("/")
    public List<SysUser> index() {
/* debug test */
//        List<SysUser> all = userMapper.findAll();
//        return userMapper.findAll();
        return userMapper.findAll();
    }
}
