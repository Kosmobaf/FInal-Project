package com.controller.command;

import com.model.constants.Constants;
import com.model.constants.TableName;
import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateUserCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList;
        if (request.getParameter("login") != null) {

            service.createUser(
                    request.getParameter(TableName.USER__LOGIN),
                    request.getParameter(TableName.USER__PASSWORD));

            userList = service.getAllUsers();
            request.getSession().setAttribute("userList", userList);
        }
        return Constants.WEB_INF_ADMIN_CREATE_USER_JSP;
    }
}
