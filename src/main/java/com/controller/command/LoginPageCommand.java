package com.controller.command;

import com.model.Role;
import com.model.constants.Constants;
import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login;
        String password;
        User user;
        Role userRole;

        login = (String) request.getSession().getAttribute("login");
        password = (String) request.getSession().getAttribute("password");

        if (login != null || password != null) {

            user = service.getUser(login);
            userRole = user.getRole();

            return moveToMenu(userRole);
        }
        return "/WEB-INF/login.jsp";
    }

    private String moveToMenu(Role userRole) {

        if (userRole == Role.ADMIN) {
            return Constants.REDIRECT_ADMIN_BASIS;
        }
        if (userRole == Role.USER) {
            return Constants.REDIRECT_USER_BASIS;
        }
        return Constants.WEB_INF_ERROR_JSP;
    }
}
