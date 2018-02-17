package ru.korytnikov.oleg.fliters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class BrowserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("browserValidated")) {
                    if (cooky.getValue().equals("true")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
        }
        String userAgent = request.getHeader("User-Agent");
        String browserName = null;
        String browserVer = null;
        if (userAgent.contains("Chrome")) { //checking if Chrome
            String substring = userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0];
            browserName = substring.split("/")[0];
            browserVer = substring.split("/")[1];
        } else if (userAgent.contains("Firefox")) {  //Checking if Firefox
            String substring = userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0];
            browserName = substring.split("/")[0];
            browserVer = substring.split("/")[1];
        } else if (userAgent.contains("IE")) {
            String substring = userAgent.substring(userAgent.indexOf("IE")).split(" ")[0];
            browserName = substring.split("/")[0];
            browserVer = substring.split("/")[1];

        } else if (userAgent.contains("Opera")) {
            String substring = userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0];
            browserName = substring.split("/")[0];
            browserVer = substring.split("/")[1];
        } else servletResponse.getWriter().write("Unknown browser, good bye!");
        int ver = Integer.parseInt(browserVer.split("\\.")[0]);
        System.out.println(ver);
        switch (browserName) {
            case "Chrome": {
                if (ver < 50) {
                    request.setAttribute("BrowserName", browserName);
                    request.setAttribute("Version", ver);
                    request.setAttribute("RequiredVersion", 50);
                    request.getRequestDispatcher("/WEB-INF/BadBrowser.jsp").forward(servletRequest, servletResponse);
                    return;
                }
                break;
            }
            case "Firefox": {
                if (ver < 45) {
                    request.setAttribute("BrowserName", browserName);
                    request.setAttribute("Version", ver);
                    request.setAttribute("RequiredVersion", 45);
                    request.getRequestDispatcher("/WEB-INF/BadBrowser.jsp").forward(servletRequest, servletResponse);
                    return;
                }
                break;
            }
            case "IE": {
                if (ver < 10) {
                    request.setAttribute("BrowserName", browserName);
                    request.setAttribute("Version", ver);
                    request.setAttribute("RequiredVersion", 10);
                    request.getRequestDispatcher("/WEB-INF/BadBrowser.jsp").forward(servletRequest, servletResponse);
                    return;
                }
                break;
            }
            case "Opera": {
                if (ver < 38) {
                    request.setAttribute("BrowserName", browserName);
                    request.setAttribute("Version", ver);
                    request.setAttribute("RequiredVersion", 38);
                    request.getRequestDispatcher("/WEB-INF/BadBrowser.jsp").forward(servletRequest, servletResponse);
                    return;
                }
                break;
            }
        }
        Cookie cookie = new Cookie("browserValidated", "true");
        cookie.setMaxAge(60 * 60 * 24 * 365);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addCookie(cookie);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
