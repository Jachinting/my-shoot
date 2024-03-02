package com.shoot.myshootbg.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shoot.myshootbg.entity.dto.Notice;
import com.shoot.myshootbg.mapper.NoticeMapper;
import com.shoot.myshootbg.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    public Notice getLatestNotice() {
        return this.query().last("WHERE id = ( SELECT MAX(id) FROM notice_tb )").one();
    }

    @Override
    public PageInfo<Notice> noticePageInfo(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> notice = new ArrayList<>();
        System.out.println(name);
        if (name != null) {
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("title", name);
            notice = this.baseMapper.selectList(queryWrapper);
        } else {
            notice = this.list();
        }
        return new PageInfo<Notice>(notice);
    }
}
