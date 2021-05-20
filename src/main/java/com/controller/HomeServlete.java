package com.controller;



import com.dao.UserDAO;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        UserDAO.insertUser(new User("vadim","12346","user"));
        UserDAO.deleteUserByName(UserDAO.getUserByUserName("vadim").getLogin());
        if (url.equals("/")) {
                request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);

        } else if (url.equals("/welcome")) {
            request.getRequestDispatcher("WEB-INF/view/welcome.jsp").forward(request, response);
        }
    }
}