package ru.korytnikov.oleg.controllers;

import com.google.gson.Gson;
import ru.korytnikov.oleg.dao.UserDao;
import ru.korytnikov.oleg.dao.UserDaoImpl;
import ru.korytnikov.oleg.models.Marker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/mark")
public class MarkerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson json = new Gson();
        HttpSession session = req.getSession();
        Marker marker = json.fromJson(req.getReader(), Marker.class);
        String browserName = null;
        String userAgent = req.getHeader("User-Agent");
        if (userAgent.contains("Chrome")) { //checking if Chrome
            browserName = userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0];
        } else if (userAgent.contains("Firefox")) {  //Checking if Firefox
            browserName = userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0];
        } else if (userAgent.contains("IE")) {
            browserName = userAgent.substring(userAgent.indexOf("IE")).split(" ")[0];
        } else if (userAgent.contains("Opera")) {
            browserName = userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0];
        }


        marker.setBrowserName(browserName);
        marker.setIpAddress(req.getRemoteAddr());
        marker.setTime(new Date().toString());
        marker.setMarkerName(session.getId());
        System.out.println(marker);
        UserDao dao = new UserDaoImpl();
        dao.markPath(marker);
        Map<String, Boolean> answer = new HashMap<>();
        answer.put("result", true);
        resp.getWriter().write(json.toJson(answer));
    }
}
