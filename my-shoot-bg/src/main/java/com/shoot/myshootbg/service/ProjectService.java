package com.shoot.myshootbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shoot.myshootbg.entity.dto.Project;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectService extends IService<Project> {
    List<Project> getAllData();

    Project findEquipmentByNameOrId(String text);
}
