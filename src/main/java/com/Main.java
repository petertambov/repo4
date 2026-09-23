package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);


        UserDao userDao = context.getBean(UserDao.class);
        userDao.initTable();

        UserService userService = context.getBean(UserService.class);

        User firstUser = userService.createUser("Я");
        System.out.printf("Создан пользователь: %s%n", firstUser);

        User secondUser = userService.createUser("Ты");
        System.out.printf("Создан пользователь: %s%n", secondUser);

        User retrieved = userService.getUserById(firstUser.getId());
        System.out.printf("Получен пользователь по id: %s%n", retrieved);

        List<User> allUsers = userService.getAllUsers();
        System.out.println("Список пользователей:");
        allUsers.forEach(System.out::println);

        userService.deleteUser(secondUser.getId());
        System.out.printf("Пользователь %s удалён.%n", secondUser.getUsername());

        System.out.println("После удаления:");
        userService.getAllUsers().forEach(System.out::println);

        context.close();
    }
}