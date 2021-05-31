package com.controller.command;

import com.model.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            Role role, String login) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("login", login);
        session.setAttribute("role", role);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String login) {
        @SuppressWarnings("unchecked") HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if (loggedUsers.stream().anyMatch(login::equals)) {
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    static void deleteUserFromLogged(HttpServletRequest request, String login) {
        @SuppressWarnings("unchecked") HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        loggedUsers.remove(login);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }
}
