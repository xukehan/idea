package com.itheima.service.impl;

import com.itheima.domain.Account;
import com.itheima.mapper.AccountMapper;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    //mapper代理对象
    @Autowired
    private AccountMapper accountmapper;


    public void save(Account account) {
        accountmapper.save(account);
    }

    public void update(Account account) {
        accountmapper.update(account);
    }

    public void delete(Integer id) {
        accountmapper.delete(id);
    }

    public Account findById(Integer id) {
        return accountmapper.findById(id);
    }

    public List<Account> findAll() {
        return accountmapper.findAll();
    }

    public void transfer(Integer sourceId, Integer targetId, Float money) {
        Account source = accountmapper.findById(sourceId);
        Account target = accountmapper.findById(targetId);
        source.setMoney(source.getMoney() - money);

        target.setMoney(target.getMoney() + money);
        accountmapper.update(source);
        // int i = 1 / 0;
        accountmapper.update(target);
    }


}
