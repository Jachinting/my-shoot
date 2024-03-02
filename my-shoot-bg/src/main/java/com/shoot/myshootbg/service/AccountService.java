package com.shoot.myshootbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shoot.myshootbg.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String username);

    List<Account> getAllData();

    boolean registerAccount(String username, String password, String email);


}
