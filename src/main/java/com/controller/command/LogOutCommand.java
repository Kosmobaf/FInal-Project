package com.controller.command;

import com.controller.Path;
import com.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session= request.getSession();
        @SuppressWarnings("unchecked") HashSet<String> loggedUsers =
                (HashSet<String>) session.getServletContext()
                .getAttribute("loggedUsers");

        String login = (String) session.getAttribute("login");
        loggedUsers.remove(login);

        session = request.getSession(false);
        if (session != null)
            session.invalidate();

        return Path.REDIRECT_INDEX_JSP;
    }
}
