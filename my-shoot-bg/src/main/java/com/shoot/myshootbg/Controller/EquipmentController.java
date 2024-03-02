package com.shoot.myshootbg.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.entity.dto.Equipment;
import com.shoot.myshootbg.entity.vo.EqStateVo;
import com.shoot.myshootbg.service.EquipmentService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/eq")
public class EquipmentController {
    @Resource
    EquipmentService equipmentService;

    @PostMapping("/queryWithIdOrName")
    public String queryWithIdOrName(String text) {
        Equipment equipment = equipmentService.findEquipmentByNameOrId(text);
        return JSONObject.toJSONString(equipment, JSONWriter.Feature.WriteNulls);
    }

    @PostMapping("/StateUpdateById")
    public String stateUpdateById(@RequestBody EqStateVo stateVo) {
        Equipment equipment1 = new Equipment();
        equipment1.setId(stateVo.getId());
        switch (stateVo.getState()) {
            case 0,2,3 ->equipment1.setInputdate(new Date());
            case 1->equipment1.setOutputdate(new Date());
        }
        equipment1.setUser(stateVo.getUser());
        equipment1.setState(switch (stateVo.getState()) {
            case 0 -> "在库";
            case 1 -> "借出";
            case 2 -> "维修";
            case 3 -> "销毁";
            default -> throw new IllegalStateException("Unexpected value: " + stateVo.getState());
        });
        return JSONObject.toJSONString(equipmentService.updateById(equipment1), JSONWriter.Feature.WriteNulls);
    }


    @RequestMapping("/queryAll")
    public String queryAll() {
        List<Equipment> allData = equipmentService.getAllData();
        return JSONObject.toJSONString(allData, JSONWriter.Feature.WriteNulls);
    }

    @PostMapping("/save")
//    public String saveOne(String context, String name, @RequestParam("inputDate")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
//    Date inputDate,
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
//    Date outputDate,
//                          String state) {
//        if (context == null || name == null)
//            return Restbean.failure(500,"error:参数不足").asJsonString();
//        if (context.isEmpty() || name.isEmpty())
//            return Restbean.failure(500,"error:参数不足").asJsonString();
//        Equipment equipment = new Equipment();
//        equipment.setName(name);
//        equipment.setContext(context);
//        equipment.setInputdate(inputDate);
//        equipment.setOutputdate(outputDate);
//        equipment.setState(state);
//        if (equipmentService.save(equipment)) {
//            return Restbean.success("success:ok").asJsonString();
//        } else {
//            return Restbean.failure(500,"error:fail").asJsonString();
//        }
//    }
    public String saveOne(@RequestBody Equipment equipment) {
        equipment.setInputdate(new Date());
        System.out.println(equipment.toString());
        if (equipmentService.save(equipment)) {
            return Restbean.success("success:ok").asJsonString();
        } else {
            return Restbean.failure(500, "error:fail").asJsonString();
        }
    }
}
