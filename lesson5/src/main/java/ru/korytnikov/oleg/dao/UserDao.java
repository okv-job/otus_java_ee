package ru.korytnikov.oleg.dao;

import ru.korytnikov.oleg.models.User;
import ru.korytnikov.oleg.models.Users;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    User findUser(String username, String password);

    List<User> getUsers();

    void init(Users users);

    void destroy();
}
