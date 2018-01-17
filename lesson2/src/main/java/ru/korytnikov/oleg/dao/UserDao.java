package ru.korytnikov.oleg.dao;

import ru.korytnikov.oleg.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(int id);
    List<User> getUsers();
}
