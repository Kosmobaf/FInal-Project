package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.UserOrderBeanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTariffForUserCommand implements Command {
    UserOrderBeanService beanService = new UserOrderBeanService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        long idOrder = Long.parseLong(request.getParameter("idOrder"));
        beanService.deleteTariffForUser(idOrder);

        return Path.REDIRECT_USER_BASIS;
    }
}
