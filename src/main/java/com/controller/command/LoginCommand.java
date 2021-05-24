package com.controller.command;


import com.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || login.equals("") || password == null || password.equals("")) {
            //System.out.println("Not");
            return "/login.jsp";
        }
        System.out.println(login + " " + password);
        //System.out.println("Yes!");
        //todo: check login with DB

        if (CommandUtility.checkUserIsLogged(request, login)) {
            return "/WEB-INF/error.jsp";
        }

        if (login.equals("Admin")) {
            CommandUtility.setUserRole(request, Role.ADMIN, login);
            return "/WEB-INF/admin/adminbasis.jsp";
        } else if (login.equals("User")) {
            CommandUtility.setUserRole(request, Role.USER, login);
            return "/WEB-INF/user/userbasis.jsp";
        } else {
            CommandUtility.setUserRole(request, Role.UNKNOWN, login);
            return "/login.jsp";
        }
    }

}
