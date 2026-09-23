package com;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(String username) {
        return userDao.select(userDao.create(username));
    }

    public User getUserById(Long id) {
        return userDao.select(id);
    }

    public List<User> getAllUsers() {
        return userDao.selectAll();
    }

    public void deleteUser(Long id) {
        if (userDao.delete(id) == 0) {
            System.out.println(String.format("Пользователь с id = %s не найден", id));
        }
    }
}