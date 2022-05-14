package com.mingzhe.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SysUser {
    private Integer id;
    private String username;
    // don't show password
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
}
