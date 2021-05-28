package com.controller.command;

import com.model.entity.Tariff;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetTariffByServiceCommand implements Command {
    List<Tariff> tariffList = new ArrayList<>();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TariffService tariff = new TariffService();
        tariffList = tariff.getAllTariffByService(
                Long.getLong(request.getParameter("idService")));
        request.getSession().setAttribute("tariffList", tariffList);
        return "/WEB-INF/user/showListTariff.jsp";
    }
}
