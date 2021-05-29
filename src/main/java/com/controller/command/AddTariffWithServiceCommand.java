package com.controller.command;

import com.model.constants.Constants;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTariffWithServiceCommand implements Command {
    TariffService service = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        try {
            service.addTariff(
                    Long.valueOf(request.getParameter("idTariff")),
                    (String) request.getSession().getAttribute("login"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO добавити повідомлення якщо не достатньо коштів і послуга заблокована

        return Constants.REDIRECT_GET_LIST_ORDERS_FOR_USER;
    }
}
