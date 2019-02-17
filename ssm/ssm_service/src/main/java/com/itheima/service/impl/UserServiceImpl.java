package com.itheima.service.impl;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User loginUser(User user) {
        return userMapper.loginUser(user);

    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public List<User> findAll() {
        return userMapper.findAll();

    }

    public void delete(int id) {
        userMapper.delete(id);

    }

    public void update(User user) {
        userMapper.update(user);

    }

    public void save(User user) {
        userMapper.save(user);

    }
}
