package com.controller;


import com.controller.command.Command;
import com.controller.command.GetAllUsersCommand;
import com.controller.command.LogOutCommand;
import com.controller.command.LoginCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig){

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout",
                new LogOutCommand());
        commands.put("login",
                new LoginCommand());
        commands.put("getAllUsers",
                new GetAllUsersCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        path = path.replaceAll(".*/provider/" , "");
        Command command = commands.getOrDefault(path ,
                (r)->"/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/provider"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
