package com.shoot.myshootbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.shoot.myshootbg.entity.dto.Event;

public interface EventService extends IService<Event> {

    PageInfo<Event> eventPageInfo(int pageNum, int pageSize, String name);
}
