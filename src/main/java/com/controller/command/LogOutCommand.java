package com.controller.command;

import com.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // ToDo delete current user (context & session)
        CommandUtility.setUserRole(request, Role.UNKNOWN, "Guest");
        return "redirect:/index.jsp";
    }
}
