package com.mingzhe.backend.mapper;

import com.mingzhe.backend.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface SysUserMapper {
    @Select("SELECT * from sys_user")
    List<SysUser> findAll();
}
