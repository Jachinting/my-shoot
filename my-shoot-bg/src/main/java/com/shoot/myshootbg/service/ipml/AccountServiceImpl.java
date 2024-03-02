package com.shoot.myshootbg.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoot.myshootbg.entity.dto.Account;
import com.shoot.myshootbg.mapper.AccountMapper;
import com.shoot.myshootbg.service.AccountService;
import io.netty.util.internal.ObjectUtil;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findAccountByNameOrEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    public Account findAccountByNameOrEmail(String text) {
        return this.query()
                .eq("username", text).or()
                .eq("email", text)
                .one();
    }

    @Override
    public List<Account> getAllData() {
        return this.list();
    }

    @Override
    public boolean registerAccount(String username, String password, String email) {
        String passwordEncoded = new BCryptPasswordEncoder().encode(password);
        Account account = new Account(null, username, passwordEncoded, "user", email);
        if (this.baseMapper.exists(Wrappers.<Account>query().eq("username", username))) return false;
        return this.save(account);
    }


}
