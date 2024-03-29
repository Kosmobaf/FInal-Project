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

/**
 * Main servlet controller.
 *
 * @author V.Lytvynuk
 */
public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) {
        new CreateDataBase().createDB();
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("getAllUser", new GetAllUserCommand());
        commands.put("changeStatusUser", new ChangeStatusUserCommand());
        commands.put("adminBasis", new AdminBasisCommand());
        commands.put("showUser", new ShowUserCommand());
        commands.put("showAllTariff", new ShowAllTariffCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("addTariff", new CreateTariffCommand());
        commands.put("userBasis", new UserBasisCommand());
        commands.put("deleteTariff", new DeleteTariffCommand());
        commands.put("addTariffWithService", new AddTariffWithServiceCommand());
        commands.put("getAllTariffList", new GetAllTariffByServiceCommand());
        commands.put("addCash", new AddCashCommand());
        commands.put("deleteTariffFromUser", new DeleteTariffForUserCommand());
        commands.put("activateTariff", new ActivateTariffCommand());
        commands.put("getFileServices", new DownloadFileServicesCommand());
        commands.put("getAllService", new GetAllServiceCommand());
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

    /**
     * Main method of this controller.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        path = path.replaceAll(".*/", "");
        Command command = commands.getOrDefault(path,
                (r, b) -> "/index.jsp");
        System.out.println(command.getClass().getName());
        String page;
        try {
            page = command.execute(request, response);
        } catch (MyException e) {
            request.setAttribute("errorMessage", e.getMessage());
            page = Path.WEB_INF_ERROR_JSP;
        } catch (Exception e) {
            page = Path.WEB_INF_ERROR_JSP;
        }
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/provider"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
