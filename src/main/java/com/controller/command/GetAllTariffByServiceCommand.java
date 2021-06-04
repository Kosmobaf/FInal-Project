package com.controller.command;

import com.model.constants.Constants;
import com.model.entity.Tariff;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetAllTariffByServiceCommand implements Command {
    TariffService tariff = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Tariff> tariffList = new ArrayList<>();
        //TODO
        /*if (request.getParameter("sortCommand") != null) {
            String sortCommand = request.getParameter("sortCommand");
            tariffList = tariff.sortList(sortCommand);
        }*/
        try {
            tariffList = tariff.getAllTariffByService(
                    Long.valueOf(request.getParameter("idService")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("tariffListForService", tariffList);
        return Constants.WEB_INF_USER_SHOW_LIST_TARIFF_JSP;
    }
}
