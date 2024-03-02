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
@TableName("events_tb")
@AllArgsConstructor
public class Event {
    @TableId(type = IdType.AUTO)
    Integer id;
    String eventname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8", shape = JsonFormat.Shape.STRING)
    Date startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8", shape = JsonFormat.Shape.STRING)
    Date enddate;
    String content;
    String location;
    String username;

    public Event(Integer id) {
        this.setId(id);
    }
}
