package com.mingzhe.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mingzhe.backend.entity.SysUser;
import com.mingzhe.backend.mapper.SysUserMapper;
import com.mingzhe.backend.service.SysUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    // C U (create or update)
    @PostMapping
    public boolean save(@RequestBody SysUser sysUser) {
        // add new or update old
        return userService.saveUser(sysUser);
    }

    // R (read all)
    @GetMapping
    public List<SysUser> findAll() {
        return userService.list();
    }

    // D (delete)
    @DeleteMapping({"/{id}"})
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    // R (read by condition)
    // pagination
    @GetMapping("/page")
    public IPage<SysUser> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue =  "") String username,
                                   @RequestParam(defaultValue =  "") String nickname,
                                   @RequestParam(defaultValue =  "") String address) {
        IPage<SysUser> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(username),"username", username);
        // default add AND by framework
        queryWrapper.like(Strings.isNotEmpty(nickname),"nickname", nickname);
        // or test
        queryWrapper.or().like(Strings.isNotEmpty(address),"address", address);
        return userService.page(page, queryWrapper);
    }
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username) {
//        pageNum = (pageNum - 1) * pageSize;
//        username = "%" + username + "%";
//        Map<String, Object> res = new HashMap<>();
//        Integer total = userMapper.selectTotal(username);
//        List<SysUser> data = userMapper.selectPage(pageNum, pageSize, username);
//        res.put("total", total);
//        res.put("data", data);
//        return res;
//    }
}
