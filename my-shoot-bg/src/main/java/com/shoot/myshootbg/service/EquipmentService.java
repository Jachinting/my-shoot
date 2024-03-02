package com.shoot.myshootbg.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shoot.myshootbg.entity.dto.Equipment;

import java.util.List;

public interface EquipmentService extends IService<Equipment> {
    Equipment findEquipmentByNameOrId(String text);

    <T> List<T> findAll();

    List<Equipment> getAllData();

}
