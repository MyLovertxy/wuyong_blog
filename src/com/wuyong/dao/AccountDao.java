package com.wuyong.dao;

import com.wuyong.domain.User;

import java.util.List;

public interface AccountDao {
    List<User> getAllAccount();

    void save(User user);

    void delete(User user);

    void update(User user);
}
