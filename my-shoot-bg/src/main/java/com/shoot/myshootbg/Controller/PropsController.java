package com.shoot.myshootbg.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.entity.dto.Equipment;
import com.shoot.myshootbg.entity.dto.Props;
import com.shoot.myshootbg.entity.dto.Recordprop;
import com.shoot.myshootbg.entity.vo.EqStateVo;
import com.shoot.myshootbg.entity.vo.PageVo;
import com.shoot.myshootbg.entity.vo.RecordPropsVo;
import com.shoot.myshootbg.service.PropsService;
import com.shoot.myshootbg.service.RecordpropService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/props")
public class PropsController {
    @Resource
    PropsService propsService;
    @Resource
    RecordpropService recordpropService;

    @PostMapping("/queryWithIdOrName")
    public String queryWithIdOrName(String text) {
        Props props = propsService.findByNameOrId(text);
        return JSONObject.toJSONString(props, JSONWriter.Feature.WriteNulls);
    }

    @PostMapping("/queryRecord")
    public String queryRecord(@RequestBody RecordPropsVo propsVo) {
        List<Recordprop> recordpropList = recordpropService.findbyId(propsVo.getPropid());
        return JSONObject.toJSONString(recordpropList, JSONWriter.Feature.WriteNulls);
    }

    @PostMapping("/queryWithIdAndName")
    public String queryWithIdAndName(@RequestBody RecordPropsVo propsVo) {
        List<Recordprop> recordprop = recordpropService.findByNameAndId(propsVo.getUsername(), propsVo.getPropid());
        return JSONObject.toJSONString(recordprop, JSONWriter.Feature.WriteNulls);
    }

    @RequestMapping("/queryAll")
    public String queryAll() {
        List<Props> allData = propsService.getAllData();
        return JSONObject.toJSONString(allData, JSONWriter.Feature.WriteNulls);
    }

    @PostMapping("/save")
    public String saveOne(@RequestBody Props props) {
        props.setInputdate(new Date());
        System.out.println(props.toString());
        if (props.getId() != null) {
            if (propsService.updateById(props)) {
                return Restbean.success("更新成功:ok").asJsonString();
            } else {
                return Restbean.failure(500, "error:fail").asJsonString();
            }
        }
        if (propsService.save(props)) {
            return Restbean.success("添加成功:ok").asJsonString();
        } else {
            return Restbean.failure(500, "error:fail").asJsonString();
        }
    }
    @PostMapping("/del")
    public String delone(@RequestBody Props props){
        if (propsService.removeById(props)){
            //同时删除相关道具出借记录
            recordpropService.delByprops(props);
            return Restbean.success("删除成功").asJsonString();
        }else {
            return Restbean.failure(500,"删除出错").asJsonString();
        }
    }

    @PostMapping("/StateUpdateById")
    public String stateUpdateById(@RequestBody RecordPropsVo propsVo) {
//        System.out.println(propsVo);
        Recordprop recordprop = new Recordprop();
        recordprop.setState(switch (propsVo.getOption()) {
            case 0 -> "已归还";
            case 1 -> "借出";
            case 2 -> "维修";
            case 3 -> "销毁";
            default -> throw new IllegalStateException("Unexpected value: " + propsVo.getOption());
        });

        recordprop.setOutdate(new Date());
        recordprop.setCount(propsVo.getCount());
        recordprop.setUsername(propsVo.getUsername());
        recordprop.setPropsid(propsVo.getPropid());

        if (propsVo.getId() != null) {
            //归还
            recordprop.setId(propsVo.getId());
            Props props = propsService.findByNameOrId(String.valueOf(propsVo.getPropid()));
            props.setCount(props.getCount() + propsVo.getCount());
            propsService.updateById(props);
            return JSONObject.toJSONString(recordpropService.updateById(recordprop), JSONWriter.Feature.WriteNulls);
        } else {
            //借出
            Props props = propsService.findByNameOrId(String.valueOf(propsVo.getPropid()));
            props.setCount(props.getCount() - propsVo.getCount());
            propsService.updateById(props);
            recordprop.setPropsname(props.getName());
            System.out.println(recordprop);
            return JSONObject.toJSONString(recordpropService.save(recordprop), JSONWriter.Feature.WriteNulls);
        }

    }
}
