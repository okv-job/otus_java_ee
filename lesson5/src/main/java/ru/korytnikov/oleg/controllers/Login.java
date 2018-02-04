package ru.korytnikov.oleg.controllers;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.korytnikov.oleg.dao.UserDao;
import ru.korytnikov.oleg.dao.UserDaoImpl;
import ru.korytnikov.oleg.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        Gson json = new Gson();
        Map<String, Object> ans = new HashMap<>();
        if (isLoggedIn == null) {
            ans.put("result", false);
        } else {
            if (isLoggedIn) {
                ans.put("result", true);
                ans.put("users", new UserDaoImpl().getUsers());
                ans.put("firstName", session.getAttribute("firstName"));
            } else {
                ans.put("result", isLoggedIn);
            }
        }
        resp.getWriter().write(json.toJson(ans));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = null;
        String password = null;

        List<FileItem> multiparts;
        try {
            multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : multiparts) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("login")) {
                        username = item.getString();
                    } else {
                        password = item.getString();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


        UserDao dao = new UserDaoImpl();

        User user = dao.findUser(username, password);
        System.out.println(user);
        Map<String, Object> answer = new HashMap<>();
        Gson json = new Gson();

        if (user == null) {
            answer.put("result", false);
            resp.getWriter().write(json.toJson(answer));
        } else {

            List<User> users = dao.getUsers();

            HttpSession session = req.getSession();
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("users", users);
            session.setAttribute("firstName", user.getFirstName());

            answer.put("result", true);
            answer.put("firstName", user.getFirstName());
            answer.put("users", users);
            resp.getWriter().write(json.toJson(answer));
        }
    }
}
