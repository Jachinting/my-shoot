package com.shoot.myshootbg.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("equipment_tb")
@AllArgsConstructor
public class Equipment {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8",shape = JsonFormat.Shape.STRING)
    Date inputdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8",shape = JsonFormat.Shape.STRING)
    Date outputdate;
    String state;
    String context;
    Integer shutternumber;
    String imgurl;
    String type;
    String sensor;
    double price;
    String user;

    public Equipment() {

    }
}
