package com.mingzhe.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mingzhe.backend.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
// below are manually written SQL query w/o framework.
//    @Select("SELECT * FROM sys_user")
//    List<SysUser> findAll();
//
////    @Insert("INSERT INTO SpringVueMSDB.sys_user " +
////            "(username, password, nickname, email, phone, address) " +
////            "VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
////    Integer insert(SysUser user);
//
    // mapping is defined in src/mapper/SysUser.xml to achieve dynamic update
    boolean update(SysUser sysUser);


    @Select("SELECT * FROM sys_user WHERE deleted = 1 AND product LIKE #{product} AND sku LIKE #{sku} AND type LIKE #{type} ORDER BY id DESC LIMIT #{pageNum}, #{pageSize}")
    List<SysUser> findDeletedPage(Integer pageNum, Integer pageSize, String product, String sku, String type);

    @Select("SELECT COUNT(*) FROM sys_user WHERE deleted = 1 AND product LIKE #{product} AND sku LIKE #{sku} AND type LIKE #{type}")
    Integer findDeletedTotal(String product, String sku, String type);

    @Update("UPDATE sys_user t SET t.deleted = 0 WHERE t.id = #{id};")
    Integer restore(Integer id);
//
//    @Delete("DELETE FROM sys_user WHERE id = #{id}")
//    Integer deleteById(Integer id);
//
//    @Select("SELECT * FROM sys_user WHERE username LIKE #{username} LIMIT #{pageNum}, #{pageSize}")
//    List<SysUser> selectPage(Integer pageNum, Integer pageSize, String username);
//
//    @Select("SELECT COUNT(*) FROM sys_user WHERE username LIKE #{username}")
//    Integer selectTotal(String username);
}