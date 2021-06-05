package com.controller.command;

import com.controller.Path;
import com.model.service.TariffService;
import com.model.service.UserOrderBeanService;
import com.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTariffWithServiceCommand implements Command {
    TariffService service = new TariffService();
    UserService userService = new UserService();
    UserOrderBeanService beanService = new UserOrderBeanService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        long idTariff = Long.parseLong(request.getParameter("idTariff"));
        try {
            service.addTariffToUserOrder(idTariff, login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO добавити повідомлення якщо не достатньо коштів і послуга заблокована

        return Path.REDIRECT_USER_BASIS;
    }
}
