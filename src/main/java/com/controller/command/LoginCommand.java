package com.controller.command;


import com.model.Role;
import com.model.constants.Constants;
import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class LoginCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = null;
        String forward = Constants.WEB_INF_ERROR_JSP;
        String login;
        String password;
        User user;
        Role userRole;

        login = (String) request.getSession().getAttribute("login");
        password = (String) request.getSession().getAttribute("");
        if (login != null || password != null) {
            user = service.getUser(login);
            userRole = user.getRole();

            return moveToMenu(request, login, userRole);
        }

        // obtain login and password from the request
        login = request.getParameter("login");
        password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.getSession().setAttribute("errorMessage", errorMessage);
            // log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        System.out.println(login + " " + password);

        if (CommandUtility.checkUserIsLogged(request, login)) {

            errorMessage = "User is already logged in";
            request.getSession().setAttribute("errorMessage", errorMessage);

            return forward;
        }

        if (service.userIsExist(login, password)) {
            request.getSession().setAttribute("login", login);

            user = service.getUser(login);
            userRole = user.getRole();

            return moveToMenu(request, login, userRole);

        }
        errorMessage = "Cannot find user with such login/password";
        request.getSession().setAttribute("errorMessage", errorMessage);
        // log.error("errorMessage --> " + errorMessage);
        CommandUtility.deleteUserFromLogged(request, login);

        return forward;
    }

    private String moveToMenu(HttpServletRequest request, String login, Role userRole) {

        if (userRole == Role.ADMIN) {

            CommandUtility.setUserRole(request, Role.ADMIN, login);

            return Constants.REDIRECT_ADMIN_BASIS;
        }
        if (userRole == Role.USER) {

            CommandUtility.setUserRole(request, Role.USER, login);

            return Constants.REDIRECT_USER_BASIS;
        }
        return Constants.WEB_INF_ERROR_JSP;
    }
}
