package by.sep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "browserdetector", urlPatterns = "/browserdetector.html")
public class BrowserDetectorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getHeader("User-Agent");
        String browser;
        if (info.contains("Firefox")) {
            browser = "Firefox";
        } else if (info.contains("YaBrowser")) {
            browser = "Yandex";
        } else if (info.contains("OPR")) {
            browser = "Opera";
        } else if (info.contains("Edg")) {
            browser = "Microsoft Edge";
        } else if (info.contains("Chrome")) {
            browser = "Chrome";
        } else if (info.contains("Safari")) {
            browser = "Safari";
        } else {
            browser = "Unknown browser";
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><h2>Приветствую пользователя " + browser + "</h2></html>");
    }
}
