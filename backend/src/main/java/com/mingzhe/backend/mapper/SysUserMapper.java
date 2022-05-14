package com.mingzhe.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mingzhe.backend.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("SELECT * FROM sys_user")
    List<SysUser> findAll();

//    @Insert("INSERT INTO SpringVueMSDB.sys_user " +
//            "(username, password, nickname, email, phone, address) " +
//            "VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
//    Integer insert(SysUser user);

    // mapping is defined in src/mapper/SysUser.xml to achieve dynamic update
    Integer update(SysUser sysUser);

    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    Integer deleteById(Integer id);

    @Select("SELECT * FROM sys_user WHERE username LIKE #{username} LIMIT #{pageNum}, #{pageSize}")
    List<SysUser> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("SELECT COUNT(*) FROM sys_user WHERE username LIKE #{username}")
    Integer selectTotal(String username);
}