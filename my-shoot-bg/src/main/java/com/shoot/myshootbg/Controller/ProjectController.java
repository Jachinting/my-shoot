package com.shoot.myshootbg.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.entity.dto.Equipment;
import com.shoot.myshootbg.entity.dto.Project;
import com.shoot.myshootbg.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Resource
    ProjectService projectService;

    @PostMapping("/save")
    public String saveOne(@RequestBody Project project){
        System.out.println(project);
        if (project.getId()!=null){
            projectService.save(project);
            return Restbean.success("保存成功").asJsonString();
        }else {
            projectService.updateById(project);
            return Restbean.success("更新成功").asJsonString();
        }
    }

    @PostMapping("/del")
    public String del(@RequestBody Project project){
        if (projectService.removeById(project)){
            return Restbean.success().asJsonString();
        }else {
            return Restbean.failure(500,"删除失败").asJsonString();
        }

    }

    @PostMapping("/queryAll")
    public String queryAll() {
        List<Project> allData = projectService.getAllData();
        return JSONObject.toJSONString(allData, JSONWriter.Feature.WriteNulls);
    }

    @PostMapping("/queryWithIdOrName")
    public String queryWithIdOrName(String text) {
        Project project = projectService.findEquipmentByNameOrId(text);
        System.out.println(project);
        return JSONObject.toJSONString(project, JSONWriter.Feature.WriteNulls);
    }
}
