package com.shoot.myshootbg.entity.vo;

import lombok.Data;

import java.util.Date;
@Data
public class AuthUserVo {
    String username;
    String role;
    String token;
    Date expire;
    String name;
}
