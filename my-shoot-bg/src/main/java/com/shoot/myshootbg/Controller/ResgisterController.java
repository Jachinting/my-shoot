package com.shoot.myshootbg.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/register")
public class ResgisterController {
    @Resource
    AccountService accountService;

    @PostMapping("/byusername")
    public String creatByUsername(String username, String password, String email) {
        if (accountService.registerAccount(username, password, email)) {
            return Restbean.success("'message': '注册成功'").asJsonString();
        } else {
            return JSONObject.toJSONString("'message': '注册失败'", JSONWriter.Feature.WriteNulls);
        }
    }


}
