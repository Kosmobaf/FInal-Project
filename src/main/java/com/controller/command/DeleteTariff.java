package com.controller.command;

import com.model.constants.Constants;
import com.model.service.UserOrderBeanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTariff implements Command {
    UserOrderBeanService beanService = new UserOrderBeanService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long idOrder = Long.parseLong(request.getParameter("idOrder"));
        beanService.deleteTariffForUser(idOrder);

        return Constants.REDIRECT_USER_BASIS;
    }
}
