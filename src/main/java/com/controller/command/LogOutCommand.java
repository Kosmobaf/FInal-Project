package com.controller.command;

import com.model.Role;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // ToDo delete current user (context & session)
        CommandUtility.setUserRole(request, Role.UNKNOWN, "Guest");
        return "redirect:/index.jsp";
    }
}
