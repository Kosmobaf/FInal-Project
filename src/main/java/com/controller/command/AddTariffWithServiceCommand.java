package com.controller.command;

import com.controller.MyException;
import com.controller.Path;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTariffWithServiceCommand implements Command {
    TariffService service = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws MyException {
        String login = (String) request.getSession().getAttribute("login");
        long idTariff = Long.parseLong(request.getParameter("idTariff"));

//add tariff to user-order
        service.addTariffToUserOrder(idTariff, login);

        return Path.REDIRECT_USER_BASIS;
    }
}
