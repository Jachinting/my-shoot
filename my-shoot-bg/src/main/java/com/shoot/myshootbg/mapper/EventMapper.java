package com.shoot.myshootbg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shoot.myshootbg.entity.dto.Event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper extends BaseMapper<Event> {
}
