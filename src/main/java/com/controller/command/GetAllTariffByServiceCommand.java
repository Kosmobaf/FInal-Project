package com.controller.command;

import com.controller.Path;
import com.model.entity.Tariff;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllTariffByServiceCommand implements Command {
    TariffService tariff = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Tariff> tariffList;
        long idService;
        if (request.getParameter("idService") != null) {
            idService = Long.parseLong(request.getParameter("idService"));
        } else {
            idService = (long) request.getSession().getAttribute("idService");
        }
        request.getSession().setAttribute("idService", idService);

        String sortCommand = request.getParameter("sortCommand");
        tariffList = tariff.getAllTariffByServiceAndSort(idService, sortCommand);

        request.getSession().setAttribute("tariffListForService", tariffList);
        return Path.WEB_INF_USER_SHOW_LIST_TARIFF_JSP;
    }
}
