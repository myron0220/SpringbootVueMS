package com.mingzhe.backend.entity;

import lombok.Data;

@Data
public class SysUser {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
}
