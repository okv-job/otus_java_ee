package ru.korytnikov.oleg.controllers;

import ru.korytnikov.oleg.dao.UserDao;
import ru.korytnikov.oleg.dao.UserDaoImpl;
import ru.korytnikov.oleg.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class ShowUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = new UserDaoImpl();
        Users users = new Users();
        users.setUsers(dao.getUsers());

        Helper.marshalXmlToFile("test.xml", users, Users.class);

        Helper.findBigSallary("test.xml");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String json = Helper.xmlToJson(Helper.getDataFromFile("test.xml"));

        Helper.saveJsonToFile("test.json",json);

        PrintWriter out = resp.getWriter();

        out.println(json);

        out.flush();
    }
}
