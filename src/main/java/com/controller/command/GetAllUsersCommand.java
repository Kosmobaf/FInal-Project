package com.controller.command;

import com.model.dao.impl.ConnectionPoolHolder;
import com.model.db.CreateDataBase;
import com.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GetAllUsersCommand implements Command {
    List<User> userList = new ArrayList<>();

    @Override
    public String execute(HttpServletRequest request) {
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());

        request.setAttribute("userList", userList);
        return "/WEB-INF/admin/allUsers.jsp";
    }
}
