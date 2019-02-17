package com.itheima.mapper;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    User findById(int id);

    User loginUser(User user);

    List<User> findAll();

    void delete(int id);

    void update(User user);

    void save(User user);

}
