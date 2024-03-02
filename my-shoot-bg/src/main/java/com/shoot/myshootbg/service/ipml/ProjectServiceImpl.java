package com.shoot.myshootbg.service.ipml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoot.myshootbg.entity.dto.Project;
import com.shoot.myshootbg.mapper.ProjectMapper;
import com.shoot.myshootbg.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
    @Override
    public List<Project> getAllData() {
        return this.list();
    }

    @Override
    public Project findEquipmentByNameOrId(String text) {
        return this.query()
                .eq("name", text).or()
                .eq("id", text)
                .one();
    }
}
