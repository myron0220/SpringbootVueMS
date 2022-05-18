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

    @Autowired
    private SysUserMapper userMapper;

    // C U (create or update)
    @PostMapping
    public boolean save(@RequestBody SysUser sysUser) {
        // add new or update old
        return userService.saveOrUpdate(sysUser);
    }

    // manually ignore global logic deletion setting
    @PostMapping("/deletionComment")
    public boolean saveDeletionComment(@RequestBody SysUser sysUser) {
        return userMapper.update(sysUser);
    }

    // R (read all)
    @GetMapping
    public List<SysUser> findAll() {
        return userService.list();
    }

    // D (delete one)
    @DeleteMapping({"/{id}"})
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    // D (delete batch)
    @PostMapping({"/del/batch"})
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeBatchByIds(ids);
    }

    // R (read by condition)
    // pagination
    @GetMapping("/page")
    public IPage<SysUser> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue =  "") String product,
                                   @RequestParam(defaultValue =  "") String sku,
                                   @RequestParam(defaultValue =  "") String type) {
        IPage<SysUser> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(product),"product", product);
        // default add AND by framework
        // if OR, add .or() method after queryWrapper
        queryWrapper.like(Strings.isNotEmpty(sku),"sku", sku);
        queryWrapper.like(Strings.isNotEmpty(type),"type", type);
        queryWrapper.orderByDesc("id");
        return userService.page(page, queryWrapper);
    }


    // Manually written Pagination for showing deleted items
    // request address：/user/page?pageNum=1&pageSize=10
    // the first parameter of limit  = (pageNum - 1) * pageSize, because of 0-index in Mysql
    @GetMapping("/page/deleted")
    public Map<String, Object> findPageDeleted(@RequestParam Integer pageNum,
                                               @RequestParam Integer pageSize,
                                               @RequestParam(defaultValue =  "") String product,
                                               @RequestParam(defaultValue =  "") String sku,
                                               @RequestParam(defaultValue =  "") String type) {
        pageNum = (pageNum - 1) * pageSize;
        product = "%" + product + "%";
        sku = "%" + sku + "%";
        type = "%" + type + "%";
        List<SysUser> records = userMapper.findDeletedPage(pageNum, pageSize, product, sku, type);
        Integer total = userMapper.findDeletedTotal(product, sku, type);
        Map<String, Object> res = new HashMap<>();
        res.put("records", records);
        res.put("total", total);
        return res;
    }

    // handle restore
    @GetMapping({"/restore"})
    public Integer restore(@RequestParam Integer id) {
        return userMapper.restore(id);
    }


}
