package com.wuyong.dao;

import com.wuyong.domain.User;

public interface UserDao {
    public User getUser(String username, String password);
}
