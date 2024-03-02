package com.shoot.myshootbg.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shoot.myshootbg.entity.dto.Event;
import com.shoot.myshootbg.mapper.EventMapper;
import com.shoot.myshootbg.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
    @Override
    public PageInfo<Event> eventPageInfo(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Event> eventList = new ArrayList<>();
        if (name != null) {
            QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("eventname", name).or().like("username",name);
            eventList = this.baseMapper.selectList(queryWrapper);
        } else {
            eventList = this.list();
        }
        return new PageInfo<>(eventList);
    }
}
