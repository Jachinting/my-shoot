package com.shoot.myshootbg.service.ipml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoot.myshootbg.entity.dto.Equipment;
import com.shoot.myshootbg.mapper.EquipmentMapper;
import com.shoot.myshootbg.service.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {


    @Override
    public Equipment findEquipmentByNameOrId(String text) {
        return this.query()
                .eq("name", text).or()
                .eq("id", text)
                .one();
    }

    @Override
    public <T> List<T> findAll() {
        return null;
    }

    @Override
    public List<Equipment> getAllData() {
        return this.list();
    }


}
