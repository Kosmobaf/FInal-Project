package com.controller.command;

import com.model.constants.Constants;
import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminBasisCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList;
        UserService service = new UserService();
        userList = service.getAllUsers();
        request.getSession().setAttribute("userList", userList);
        return Constants.WEB_INF_ADMIN_ALL_USERS_JSP;
    }
}
