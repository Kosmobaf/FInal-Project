package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.UserOrderBeanService;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActivateTariffCommand implements Command {
    UserOrderBeanService beanService = new UserOrderBeanService();
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        String login = (String) request.getSession().getAttribute("login");
        long idTariff = Long.parseLong(request.getParameter("idTariff"));

        userService.withdrawCashFromUser(login, idTariff);

        return Path.REDIRECT_USER_BASIS;
    }
}
