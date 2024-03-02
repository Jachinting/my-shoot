package com.shoot.myshootbg.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;
import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.entity.dto.Notice;
import com.shoot.myshootbg.entity.vo.PageVo;
import com.shoot.myshootbg.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Resource
    NoticeService noticeService;

    @GetMapping("/latest")
    public String querynotice() {
        return JSONObject.toJSONString(noticeService.getLatestNotice(), JSONWriter.Feature.WriteNulls);
    }

    @GetMapping("/getlist")
    public String querylist() {
        List<Notice> noticeList = noticeService.list();
        return Restbean.success(noticeList).asJsonString();
    }

    @PostMapping("/del")
    public String delete(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Notice notice = new Notice((Integer) map.get("id"));
        System.out.println(notice);
        if (noticeService.removeById(notice)) {
            return Restbean.success("删除成功").asJsonString();
        } else {
            return Restbean.failure(500, "删除失败").asJsonString();
        }

    }

    @PostMapping("/save")
    public String save(@RequestBody Notice notice) {

        notice.setEditdate(new Date());
        System.out.println(notice);
        if (notice.getId()!=null){
            if (noticeService.updateById(notice)) {
                return Restbean.success("更新成功").asJsonString();
            } else {
                return Restbean.failure(500, "更失败").asJsonString();
            }
        }
        if (noticeService.save(notice)) {
            return Restbean.success("添加成功").asJsonString();
        } else {
            return Restbean.failure(500, "添加失败").asJsonString();
        }
    }

    @PostMapping("/getlistbypage")
    public String queryListByPage(@RequestBody PageVo pageVo) {
        PageInfo<Notice> noticeList = noticeService.noticePageInfo(pageVo.getPageNum(), pageVo.getPageSize(),pageVo.getName());
        return Restbean.success(noticeList).asJsonString();
    }

    //此接口不在前端使用
    @PostMapping("/upload")
    public String uploadNotice(@RequestParam("editDate")
                               @DateTimeFormat(pattern = "yyyy-MM-dd")
                               @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
                               Date editDate, String context, @RequestParam(value = "title", defaultValue = "默认标题") String title) {
        boolean save = noticeService.save(new Notice(null, editDate, context, title));
        if (save) {
            return JSONObject.toJSONString("success", JSONWriter.Feature.WriteNulls);
        } else {
            return JSONObject.toJSONString("error", JSONWriter.Feature.WriteNulls);
        }
    }
}
