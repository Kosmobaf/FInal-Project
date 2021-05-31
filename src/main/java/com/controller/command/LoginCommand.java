package com.controller.command;


import com.model.Role;
import com.model.constants.Constants;
import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = null;
        String forward = Constants.WEB_INF_ERROR_JSP;

// obtain login and password from the request
        String login = request.getParameter("login");
        String password = request.getParameter("password");

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

        UserService service = new UserService();

        if (service.userExist(login, password)) {
            User user = service.getUser(login);
            Role userRole = user.getRole();
            if (userRole == Role.ADMIN) {
                CommandUtility.setUserRole(request, Role.ADMIN, login);
                return "/adminBasis";
            }

            BigDecimal cash = service.getUserCash(login);

            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("cash", cash);

            CommandUtility.setUserRole(request, Role.USER, login);

            return Constants.REDIRECT_USER_BASIS;
        }
        errorMessage = "Cannot find user with such login/password";
        request.getSession().setAttribute("errorMessage", errorMessage);
        // log.error("errorMessage --> " + errorMessage);
        CommandUtility.deleteUserFromLogged(request,login);
        return forward;
    }
}
