package com.shoot.myshootbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.shoot.myshootbg.entity.dto.Notice;

public interface NoticeService extends IService<Notice> {
    Notice getLatestNotice();


    PageInfo<Notice> noticePageInfo(int pageNum, int pageSize, String name);
}
