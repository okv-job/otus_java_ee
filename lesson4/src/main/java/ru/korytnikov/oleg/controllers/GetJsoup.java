package ru.korytnikov.oleg.controllers;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.korytnikov.oleg.models.JsoupAnswer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getjsoup")
public class GetJsoup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        System.out.println("===========in===============");
        Document doc = null;
        Gson json = new Gson();
        List<JsoupAnswer> answers = new ArrayList<>();
        try {
            doc = Jsoup.connect("http://rbk.ru/").get();
            Elements info = doc.getElementsByClass("item");
            for (Element element : info) {
                JsoupAnswer ans = new JsoupAnswer();
                ans.setUrl(element.getElementsByTag("a").get(0).attr("href"));
                ans.setTitle(element.getElementsByClass("item__title").text());
                answers.add(ans);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(json.toJson(answers));

    }
}
