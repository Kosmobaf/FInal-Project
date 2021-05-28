package com.controller.command;

import com.model.constants.TableName;
import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CreateUserCommand implements Command {
    List<User> userList = new ArrayList<>();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter(TableName.USER__LOGIN) != null) {
            UserService service = new UserService();
            service.createUser(
                    request.getParameter(TableName.USER__LOGIN),
                    request.getParameter(TableName.USER__PASSWORD)
            );
            userList = service.getAllUsers();
            request.getSession().setAttribute("userList", userList);
        }
        return "/WEB-INF/admin/createUser.jsp";
    }
}
