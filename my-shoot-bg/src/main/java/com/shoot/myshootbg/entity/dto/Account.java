package com.shoot.myshootbg.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("user_tb")
public class Account {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String phone;
    private String name;

    public Account(Object id, String username, String passwordEncoded, String user, String email) {
        this.id = (Integer) id;
        this.username=username;
        this.password = passwordEncoded;
        this.email = email ;
    }
}
