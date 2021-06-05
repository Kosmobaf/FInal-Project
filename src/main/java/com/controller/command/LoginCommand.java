package com.controller.command;


import com.controller.Path;
import com.model.Role;
import com.model.Status;
import com.model.entity.User;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String errorMessage;
        String forward = Path.WEB_INF_ERROR_JSP;
        String login;
        String password;
        User user;
        Role userRole;

// check whether the user has logged out

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

// data validation check

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.getSession().setAttribute("errorMessage", errorMessage);

            return forward;
        }

        System.out.println(login + " " + password);

    // check if the user has logged in

        if (CommandUtility.checkUserIsLogged(request, login)) {

            errorMessage = "User is already logged in";
            request.getSession().setAttribute("errorMessage", errorMessage);

            return forward;
        }

        //check login and password

        if (service.userIsExist(login, password)) {
            request.getSession().setAttribute("login", login);

            user = service.getUser(login);
            userRole = user.getRole();

            // check user lock

            if (user.getStatus() == Status.BLOCKED) {
                errorMessage = "User is blocked";
                request.getSession().setAttribute("errorMessage", errorMessage);
                return forward;
            }

            //transition according to the role

            return moveToMenu(request, login, userRole);
        }

        errorMessage = "Cannot find user with such login/password";
        request.getSession().setAttribute("errorMessage", errorMessage);
        CommandUtility.deleteUserFromLogged(request, login);

        return forward;
    }

    // transition according to the role
    private String moveToMenu(HttpServletRequest request, String login, Role userRole) {

        if (userRole == Role.ADMIN) {

            CommandUtility.setUserRole(request, Role.ADMIN, login);

            return Path.REDIRECT_ADMIN_BASIS;
        }
        if (userRole == Role.USER) {

            CommandUtility.setUserRole(request, Role.USER, login);

            return Path.REDIRECT_USER_BASIS;
        }

        return Path.WEB_INF_ERROR_JSP;
    }
}
