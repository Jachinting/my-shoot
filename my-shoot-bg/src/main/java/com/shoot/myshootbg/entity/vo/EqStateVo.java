package com.shoot.myshootbg.entity.vo;

import lombok.Data;

@Data
public class EqStateVo {
    Integer id;
    //0为在库，1为出借，2为维修，3为销毁
    Integer state;
    String user;
}
