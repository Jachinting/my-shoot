package com.shoot.myshootbg.Controller;

import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.entity.dto.Account;
import com.shoot.myshootbg.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Resource
    AccountService accountService;

    @GetMapping("/queryAll")
    public String queryAll(){
//        System.out.println("account all");
        List<Account> allData = accountService.getAllData();
        for (Account i:allData){
            i.setPassword("blocked");
        }
//        System.out.println(allData);
        return Restbean.success(allData).asJsonString();
    }

    @PostMapping("/update")
    public String saveOne(@RequestBody Account table){
        if(table.getPassword().equals("blocked")){
            table.setPassword(null);
        }else {
            table.setPassword(new BCryptPasswordEncoder().encode(table.getPassword()));
        }

//        String passwordEncoded = new BCryptPasswordEncoder().encode(table.getPassword());
//        Account update = new Account(table.getId(), table.getUsername(), passwordEncoded, table.getName(), table.getEmail());
        accountService.updateById(table);
        return Restbean.success("更新成功").asJsonString();
    }
}
