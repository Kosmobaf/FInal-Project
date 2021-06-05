package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

//TODO добавити валідацію на вхідні дані
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            return Path.WEB_INF_ADMIN_CREATE_USER_JSP;
        }

// create user
        service.createUser(login, password);
        return Path.REDIRECT_CREATE_USER;
    }
}

