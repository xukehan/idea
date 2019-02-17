package com.itheima.service;

import com.itheima.domain.User;

import java.util.List;

public interface UserService {
    User loginUser(User user);

    User findById(int id);

    List<User> findAll();

    void delete(int id);

    void update(User user);

    void save(User user);

}
