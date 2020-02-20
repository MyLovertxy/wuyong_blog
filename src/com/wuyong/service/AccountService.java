package com.wuyong.service;

import com.wuyong.domain.User;

import java.util.List;

public interface AccountService {

    List<User> getAllAccount();

    void sava(User user);

    void delete(User user);

    void update(User user);
}
