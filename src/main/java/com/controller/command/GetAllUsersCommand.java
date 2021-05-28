package com.controller.command;

import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetAllUsersCommand implements Command {
    List<User> userList = new ArrayList<>();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService service = new UserService();
        userList = service.getAllUsers();
        request.getSession().setAttribute("userList", userList);
        return "/WEB-INF/admin/allUsers.jsp";
    }
}
