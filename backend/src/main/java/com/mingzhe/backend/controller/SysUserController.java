package com.mingzhe.backend.controller;

import com.mingzhe.backend.entity.SysUser;
import com.mingzhe.backend.mapper.SysUserMapper;
import com.mingzhe.backend.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserService userService;

    // add and update
    @PostMapping
    public Integer save(@RequestBody SysUser sysUser) {
        // add new or update old
        return userService.save(sysUser);
    }

    // query
    @GetMapping
    public List<SysUser> index() {
/* debug test */
//        List<SysUser> all = userMapper.findAll();
//        return userMapper.findAll();
        return userMapper.findAll();
    }

    @DeleteMapping({"/{id}"})
    public Integer delete(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username) {
        pageNum = (pageNum - 1) * pageSize;
        username = "%" + username + "%";
        Map<String, Object> res = new HashMap<>();
        Integer total = userMapper.selectTotal(username);
        List<SysUser> data = userMapper.selectPage(pageNum, pageSize, username);
        res.put("total", total);
        res.put("data", data);
        return res;
    }
}
