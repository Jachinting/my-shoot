package com.shoot.myshootbg.entity.vo;

import lombok.Data;

@Data
public class RecordPropsVo {
    Integer id;
    Integer propid;
    String username;
    Integer count;
    //0为归还，1为出借，2为维修，3为销毁
    Integer option;
}
