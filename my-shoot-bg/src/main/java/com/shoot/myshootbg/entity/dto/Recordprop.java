package com.shoot.myshootbg.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("recordprop_tb")
@AllArgsConstructor
public class Recordprop {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer propsid;
    String propsname;
    String username;
    Integer count;
    String state;
    Date outdate;

    public Recordprop() {

    }
}
