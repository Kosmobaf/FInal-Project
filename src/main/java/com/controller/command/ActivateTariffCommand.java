package com.controller.command;

import com.model.bean.UserOrderBean;
import com.model.constants.Constants;
import com.model.service.UserOrderBeanService;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

public class ActivateTariffCommand implements Command {
    UserOrderBeanService beanService = new UserOrderBeanService();
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        long idTariff = Long.parseLong(request.getParameter("idTariff"));

//TODO добавити транзакцію ?
        if (userService.withdrawCashFromUser(login, idTariff)) {
            UserOrderBean orderBean = beanService.getOrderForUserByLogin(login, idTariff);
            beanService.changeStatus(orderBean);
        }
        return Constants.REDIRECT_USER_BASIS;
    }
}
