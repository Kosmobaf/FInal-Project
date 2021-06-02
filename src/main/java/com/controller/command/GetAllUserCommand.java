package com.controller.command;

import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllUserCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList;
        userList = service.getAllUsers();
        request.getSession().setAttribute("userList", userList);
        return "WEB-INF/admin/allUsers.jsp";
    }
}
