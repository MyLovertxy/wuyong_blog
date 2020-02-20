package com.wuyong.service.impl;

import com.wuyong.dao.UserDao;
import com.wuyong.domain.User;
import com.wuyong.service.LoginService;

import javax.transaction.Transactional;

@Transactional
public class LoginServiceImpl implements LoginService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) {
        System.out.println("用户名="+user.getUsername());
        User resUser = userDao.getUser(user.getUsername(), user.getPassword());
        return resUser;
    }
}
