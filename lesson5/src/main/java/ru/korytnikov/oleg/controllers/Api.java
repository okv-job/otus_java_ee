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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/users")
public class Api extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Update
        System.out.println("In add");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String firstName = null;
        String secondName = null;
        String address = null;
        int id = -1;

        List<FileItem> multiparts;
        try {
            multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : multiparts) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "firstName":
                            firstName = item.getString();
                            break;
                        case "secondName":
                            secondName = item.getString();
                            break;
                        case "id":
                            id = Integer.parseInt(item.getString());
                            break;
                        default:
                            address = item.getString();
                            break;
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        System.out.println(firstName + " " + secondName + " " + address + " " + id);
        if (id != -1){
            UserDao dao = new UserDaoImpl();
            User user = new User(firstName,secondName,address, id);
            dao.updateUser(user);
        }

        Map<String,Boolean> ans = new HashMap<>();
        ans.put("result",true);
        resp.getWriter().write(new Gson().toJson(ans));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Create new

        System.out.println("In add");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String firstName = null;
        String secondName = null;
        String address = null;

        List<FileItem> multiparts;
        try {
            multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : multiparts) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "firstName":
                            firstName = item.getString();
                            break;
                        case "secondName":
                            secondName = item.getString();
                            break;
                        default:
                            address = item.getString();
                            break;
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        System.out.println(firstName + " " + secondName + " " + address);
        UserDao dao = new UserDaoImpl();
        User user = new User(firstName,secondName,address);
        dao.addUser(user);

        Map<String,Boolean> ans = new HashMap<>();
        ans.put("result",true);
        resp.getWriter().write(new Gson().toJson(ans));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("In delete");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int id = -1;

        List<FileItem> multiparts;
        try {
            multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : multiparts) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "id":
                            id = Integer.parseInt(item.getString());
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        if (id != -1){
            UserDao dao = new UserDaoImpl();
            dao.deleteUser(id);
        }

        Map<String,Boolean> ans = new HashMap<>();
        ans.put("result",true);
        resp.getWriter().write(new Gson().toJson(ans));
    }
}
