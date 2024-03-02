package com.shoot.myshootbg.Controller;

import com.github.pagehelper.PageInfo;
import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.entity.dto.Event;
import com.shoot.myshootbg.entity.dto.Notice;
import com.shoot.myshootbg.entity.vo.PageVo;
import com.shoot.myshootbg.service.EventService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Resource
    EventService eventService;

    @PostMapping("/del")
    public String delete(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Event event = new Event((Integer) map.get("id"));
        System.out.println(event);
        if (eventService.removeById(event)) {
            return Restbean.success("删除成功").asJsonString();
        } else {
            return Restbean.failure(500, "删除失败").asJsonString();
        }
    }

    @PostMapping("/getlistbypage")
    public String queryListByPage(@RequestBody PageVo pageVo) {
        PageInfo<Event> eventPageInfo = eventService.eventPageInfo(pageVo.getPageNum(), pageVo.getPageSize(),pageVo.getName());
        return Restbean.success(eventPageInfo).asJsonString();
    }

    @PostMapping("/save")
    public String save(@RequestBody Event event) {
        System.out.println(event);
        if (event.getId()!=null){
            if (eventService.updateById(event)) {
                return Restbean.success("更新成功").asJsonString();
            } else {
                return Restbean.failure(500, "更失败").asJsonString();
            }
        }
        if (eventService.save(event)) {
            return Restbean.success("添加成功").asJsonString();
        } else {
            return Restbean.failure(500, "添加失败").asJsonString();
        }
    }
}
