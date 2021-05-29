package com.controller.command;


import com.model.Role;
import com.model.constants.Constants;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || login.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }
        System.out.println(login + " " + password);

       /* if (CommandUtility.checkUserIsLogged(request, login)) {
            return "/WEB-INF/error.jsp";
        }*/

        if (login.contains("Admin")) {
            CommandUtility.setUserRole(request, Role.ADMIN, login);
            return "/WEB-INF/admin/adminbasis.jsp";
        }

        UserService service = new UserService();
        if (service.userIsExist(login, password)) {
            request.getSession().setAttribute("login", login);
            CommandUtility.setUserRole(request, Role.USER, login);
            return Constants.REDIRECT_GET_LIST_ORDERS_FOR_USER;
        }
        /*else {
            CommandUtility.setUserRole(request, Role.UNKNOWN, login);
            return "/login.jsp";
        }*/
        return "/login.jsp";
    }

}
