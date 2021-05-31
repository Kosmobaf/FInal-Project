package com.controller.command;


import com.model.Role;
import com.model.constants.Constants;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || login.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }
        System.out.println(login + " " + password);

        if (CommandUtility.checkUserIsLogged(request, login)) {
            return "/WEB-INF/error.jsp";
        }

        if (login.contains("Admin")) {
            CommandUtility.setUserRole(request, Role.ADMIN, login);
            return "/WEB-INF/admin/adminbasis.jsp";
        }

        UserService service = new UserService();
        if (service.userExist(login, password)) {
            BigDecimal cash = service.getUserCash(login);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("cash", cash);
            CommandUtility.setUserRole(request, Role.USER, login);
            return Constants.REDIRECT_USER_BASIS;
        }

        return "/login.jsp";
    }

}
