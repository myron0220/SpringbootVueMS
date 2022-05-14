package com.mingzhe.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    // don't show password
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    @TableField(value = "avatar_url") // alias for DB column
    private String avatarUrl;
}
