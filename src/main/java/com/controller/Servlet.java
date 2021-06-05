package com.controller;


import com.controller.command.*;
import com.model.dao.CreateDataBase;

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
    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) {
        new CreateDataBase().createDB();
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new LogOutCommand());
        commands.put("login", new LoginCommand());
        commands.put("adminBasis", new AdminBasisCommand());
        commands.put("getFileServices", new DownloadFileServicesCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("getAllService", new GetAllServiceCommand());
        commands.put("addTariffWithService", new AddTariffWithServiceCommand());
        commands.put("getAllTariffList", new GetAllTariffByServiceCommand());
        commands.put("userBasis", new UserBasisCommand());
        commands.put("addCash", new AddCashCommand());
        commands.put("deleteTariffFromUser", new DeleteTariffForUserCommand());
        commands.put("activateTariff", new ActivateTariffCommand());
        commands.put("loginPage", new LoginPageCommand());
        commands.put("showUser", new ShowUserCommand());
        commands.put("changeStatusUser", new ChangeStatusUserCommand());
        commands.put("addTariff", new CreateTariffCommand());
        commands.put("showAllTariff", new ShowAllTariffCommand());
        commands.put("getAllUser", new GetAllUserCommand());
        commands.put("deleteTariff", new DeleteTariffCommand());
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        path = path.replaceAll(".*/", "");
        Command command = commands.getOrDefault(path,
                (r, b) -> "/index.jsp");
        System.out.println(command.getClass().getName());
        String page = Path.WEB_INF_ERROR_JSP;
        try {
            page = command.execute(request, response);
        } catch (MyException e) {
            request.setAttribute("errorMessage",e.getMessage());
        } catch (Exception e) {
        }
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/provider"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
