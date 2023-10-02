package by.sep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userdata", urlPatterns = "/userdata.html")
public class UserDataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phonenumber");
        String email = req.getParameter("email");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        if (name.isEmpty() || (phoneNumber.isEmpty() && email.isEmpty())) {
            out.println("<html><h1>Error! Incomplete Data</h1></html>");
        } else {
            out.println("<html><body><h1>User Data</h1>");
            out.println("<a>Name: " + name + "</a><br>");
            out.println("<a>Phone number: " + phoneNumber + "</a><br>");
            out.println("<a>E-mail: " + email + "</a></body></html>");
        }
    }
}