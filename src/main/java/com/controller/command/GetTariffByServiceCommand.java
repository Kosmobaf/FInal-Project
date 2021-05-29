package com.controller.command;

import com.model.constants.Constants;
import com.model.entity.Tariff;
import com.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetTariffByServiceCommand implements Command {
    TariffService tariff = new TariffService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Tariff> tariffList = new ArrayList<>();
        try {
            tariffList = tariff.getAllTariffByService(
                    Long.valueOf(request.getParameter("idService")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("tariffList", tariffList);
        return Constants.WEB_INF_USER_SHOW_LIST_TARIFF_JSP;
    }
}
