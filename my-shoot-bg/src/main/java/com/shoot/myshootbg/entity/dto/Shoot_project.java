package com.shoot.myshootbg.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("project_tb")
@AllArgsConstructor
public class Shoot_project {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Date setdate;
    String context;
    String staff_list;
    String state;

}
