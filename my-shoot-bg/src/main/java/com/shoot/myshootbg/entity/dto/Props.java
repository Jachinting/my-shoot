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
@TableName("props_tb")
@AllArgsConstructor
public class Props {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8",shape = JsonFormat.Shape.STRING)
    Date inputdate;
    String context;
    Integer count;
    String imgurl;
    String type;

}
