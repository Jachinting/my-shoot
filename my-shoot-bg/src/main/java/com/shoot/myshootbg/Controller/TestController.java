package com.shoot.myshootbg.Controller;

import com.shoot.myshootbg.entity.dto.Equipment;
import com.shoot.myshootbg.service.EquipmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Resource
    EquipmentService equipmentService;

    @GetMapping("/hello")
    public String test(){
        return "Hello!";
    }

    @GetMapping("/eq")
    public String testp(String text){
        Equipment equipment = equipmentService.findEquipmentByNameOrId(text);
        return JSONObject.toJSONString(equipment, JSONWriter.Feature.WriteNulls);
    }

}
