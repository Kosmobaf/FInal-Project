package com.controller.command;

import com.controller.Path;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeStatusUserCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long idUser = Long.parseLong(request.getParameter("idUser"));

//change status user
        userService.changeStatusUser(idUser);

        request.getSession().setAttribute("idUser", idUser);
        return Path.REDIRECT_SHOW_USER;
    }
}
