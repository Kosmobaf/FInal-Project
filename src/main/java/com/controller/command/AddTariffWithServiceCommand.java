package com.controller.command;

import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTariffWithServiceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TariffService service = new TariffService();

        service.addTariff(
                Long.valueOf(request.getParameter("idTariff")),
                (Long) request.getSession().getAttribute("idUser"));
        //TODO добавити повідомлення якщо не достатньо коштів і послуга заблокована
        return "/WEB-INF/user/userbasis.jsp";
    }
}
