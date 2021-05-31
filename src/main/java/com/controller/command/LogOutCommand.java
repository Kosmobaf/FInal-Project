package com.controller.command;

import com.model.Role;
import com.model.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        @SuppressWarnings("unchecked") HashSet<String> loggedUsers =
                (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        String login = (String) request.getSession().getAttribute("login");
        loggedUsers.remove(login);
        // ToDo delete current user (context & session)
        CommandUtility.setUserRole(request, Role.UNKNOWN, "Guest");
        return Constants.REDIRECT_INDEX_JSP;
    }
}
