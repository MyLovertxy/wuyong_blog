package com.wuyong.service.impl;

import com.wuyong.dao.AccountDao;
import com.wuyong.domain.User;
import com.wuyong.service.AccountService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class AccountServiceImpl implements AccountService {
    @Setter
    private AccountDao accountDao;
    @Override
    public List<User> getAllAccount() {
        System.out.println("userService--------");
        List<User> list=accountDao.getAllAccount();
        return list;
    }

    @Override
    public void sava(User user) {
        accountDao.save(user);
    }

    @Override
    public void delete(User user) {
        accountDao.delete(user);
    }

    @Override
    public void update(User user) {
        accountDao.update(user);
    }
}
