package com.ymatin.samlib.service;

import com.ymatin.samlib.dao.UserDao;
import com.ymatin.samlib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Author> getAll() {
        return userDao.getAll();
    }
}
