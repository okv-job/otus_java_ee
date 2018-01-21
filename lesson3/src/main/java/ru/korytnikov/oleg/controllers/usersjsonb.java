package ru.korytnikov.oleg.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.korytnikov.oleg.models.User;
import ru.korytnikov.oleg.models.UserDeserializer;
import ru.korytnikov.oleg.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userjsonb")
public class usersjsonb extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Users.class, new UserDeserializer());
        Gson gson = gsonBuilder.create();

        Users users = gson.fromJson(Helper.getDataFromFile("test.json"), Users.class);
        PrintWriter out = resp.getWriter();

        Users ans = new Users();
        List<User> ansUsers = new ArrayList<>();


        users.getUsers().forEach(user -> {
            if (user.getId() % 3 == 0) ansUsers.add(user);
        });
        ans.setUsers(ansUsers);
        System.out.println(ans.getUsers());
        out.write(gson.toJson(ans));
        out.flush();
        out.close();
    }
}
