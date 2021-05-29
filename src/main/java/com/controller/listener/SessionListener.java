package com.controller.listener;

import com.model.db.CreateDataBase;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        new CreateDataBase().createDB();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String login = (String) httpSessionEvent.getSession()
                .getAttribute("login");
        loggedUsers.remove(login);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
