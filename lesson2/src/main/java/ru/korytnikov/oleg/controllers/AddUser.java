package ru.korytnikov.oleg.controllers;

import ru.korytnikov.oleg.dao.UserDao;
import ru.korytnikov.oleg.dao.UserDaoImpl;
import ru.korytnikov.oleg.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adduser")
public class AddUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/adduser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        UserDao dao = new UserDaoImpl();
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String address = req.getParameter("address");
        User user = new User(firstName,secondName,address);
        dao.addUser(user);
        resp.sendRedirect("/");
    }
}
