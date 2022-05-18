package com.mingzhe.backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String product;
    private String sku;
    private String type;
    @TableLogic
    private Integer deleted;
    private String deletion_comment;
}
